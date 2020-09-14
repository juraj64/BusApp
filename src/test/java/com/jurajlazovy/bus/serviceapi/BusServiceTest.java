package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.exception.BusNotFoundException;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 * Spring based transactional test with DbUnit support.
 */
public class BusServiceTest extends AbstractDbUnitJpaTests implements BusServiceTestBase {

	@Autowired
	protected BusService busService;

	@Autowired
	protected BusConnectionService busConnectionService;

	@Override
	public void testFindBusesByCondition() throws Exception {

	}

	@Override
	public void testFindByCondition() throws Exception {

	}

	@Test
	public void testFindById() throws Exception {
		Bus bus = busService.findById(getServiceContext(), 1L);
		assertEquals(10, bus.getBusNum());
	}

	@Test
	public void testFindAll() throws Exception {
		List<Bus> allBuses = busService.findAll(getServiceContext());
		assertEquals(10, allBuses.get(0).getBusNum());
	}

	@Test
	public void testSave() throws Exception {
		//Bus bus = new Bus(99);
		int before = countRowsInTable(Bus.class);
		Bus bus = new Bus();
		bus.setBusSpz("BL-147");
		bus.setNumberOfSeats(20);
		Bus newBus = busService.save(getServiceContext(),bus);
		assertEquals("BL-147", newBus.getBusSpz());
		assertEquals(before + 1, countRowsInTable(Bus.class));

	}

	@Test
	public void testDelete() throws Exception {
		Bus bus = busService.findById(getServiceContext(), 3L);
		List<BusConnection> allConnections = busConnectionService.findAll(getServiceContext());
		boolean result = false;

		// pripad, ak je bus v niektorej busConnection (vtedy sa neda deletnut)
		for(BusConnection connection : allConnections) {
			if(connection.getBus().getId().equals(bus.getId())){
				result = true;
				break;
			}
		}

		if(!result) { // ak bus nie je v ziadnej busConnection, tak deletni
			busService.delete(getServiceContext(),bus);
			try {
				Bus delBus = busService.findById(getServiceContext(), 3L);
				fail();
			} catch (BusNotFoundException bdnfe) {
				result = true;
			}
		}

		assertTrue(result);
	}

	@Test
	public void testSimpleDelete() throws Exception {
		int before = countRowsInTable(Bus.class);
		Bus bus = busService.findById(getServiceContext(), 3L);
		List<BusConnection> allConnections = busConnectionService.findAll(getServiceContext());
		boolean result = false;

		// pripad, ak je bus v niektorej busConnection (vtedy sa neda deletnut)
		for(BusConnection connection : allConnections) {
			if(connection.getBus().getId().equals(bus.getId())){
				result = true;
				assertTrue(result);
				break;
			}
		}

		if(!result) { // ak bus nie je v ziadnej busConnection, tak deletni
			busService.delete(getServiceContext(), bus);
			assertEquals(before - 1, countRowsInTable(Bus.class));
		}

	}
}
