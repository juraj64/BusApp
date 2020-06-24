package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.exception.DriverNotFoundException;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * Spring based transactional test with DbUnit support.
 */
public class DriverServiceTest extends AbstractDbUnitJpaTests implements DriverServiceTestBase {

	@Autowired
	protected DriverService driverService;
	@Autowired
	protected BusService busService;
	@Autowired
	protected BusConnectionService busConnectionService;

	@Test
	public void testFindById() throws Exception {
		Driver driver = driverService.findById(getServiceContext(), 1L);
		assertEquals("Joe", driver.getName());
		assertEquals(25, driver.getAge());
	}

	@Test
	public void testFindAll() throws Exception {
		List<Driver> allDrivers = driverService.findAll(getServiceContext());
		assertEquals("Joe", allDrivers.get(0).getName());
		assertEquals(55, allDrivers.get(1).getAge());
	}

	@Test
	public void testSave() throws Exception {
		int before = countRowsInTable(Driver.class);
		Driver driver = new Driver();
		driver.setName("Palo");
		driver.setAge(48);

		BusConnection connection = new BusConnection();
		connection.setDestination("TT");
		connection.setMinSeats(10);
		connection.setDurationMinutes(120);
		Bus bus = busService.findById(getServiceContext(), 2L);
		connection.setBus(bus);
		driver.addConnection(connection);

		BusConnection otherConnection = busConnectionService.findById(getServiceContext(),2L);
		driver.addConnection(otherConnection);

		Driver newDriver = driverService.save(getServiceContext(),driver);
		assertEquals("Palo", newDriver.getName());
		assertEquals(48, newDriver.getAge());
		assertEquals(120, newDriver.getConnections().get(0).getDurationMinutes());
		assertEquals(before + 1, countRowsInTable(Driver.class));

	}

	@Test
	// Treba si dat pozor v DriverServiceTest.xml. Nemozu byt dvaja rovnaki driveri pre ten isty bus/connection.
	// To je aj v súlade s modelom. Naopak to problem nie je - jeden driver viacero busov
	public void testDelete() throws Exception {
		Driver driver = driverService.findById(getServiceContext(), 1L);
		driverService.delete(getServiceContext(),driver);

		boolean exception = false;
		try {
			Driver delDriver = driverService.findById(getServiceContext(), 1L);
			fail();
		} catch (DriverNotFoundException dnfe) {
			exception = true;
		}

		assertTrue(exception);
	}

	@Test
	public void testSimpleDelete() throws Exception {
		int before = countRowsInTable(Driver.class);
		Driver driver = driverService.findById(getServiceContext(), 4L);
		driverService.delete(getServiceContext(),driver);
		assertEquals(before - 1, countRowsInTable(Driver.class));
	}

}
