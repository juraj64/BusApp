package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.exception.BusNotFoundException;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class BusServiceTest extends AbstractDbUnitJpaTests implements BusServiceTestBase {

	@Autowired
	protected BusService busService;

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
		Bus bus = new Bus();
		bus.setBusSpz("BL-147");
		bus.setNumberOfSeats(20);
		Bus newBus = busService.save(getServiceContext(),bus);
		assertEquals("BL-147", newBus.getBusSpz());

	}

	@Test // ide deletnut len bus, ktory nie je v žiadnej busConnection. Inak vyhadzuje chybu.
	// Predtým keď bol busNum key, tak to problém nebol
	public void testDelete() throws Exception {
		Bus bus = busService.findById(getServiceContext(), 4L);
		busService.delete(getServiceContext(),bus);

		boolean exception = false;
		try {
			Bus delBus = busService.findById(getServiceContext(), 4L);
			fail();
		} catch (BusNotFoundException bdnfe) {
			exception = true;
		}

		assertTrue(exception);
	}
}
