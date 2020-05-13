package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.*;
import com.jurajlazovy.bus.exception.BusConnectionNotFoundException;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Spring based transactional test with DbUnit support.
 */
public class BusConnectionServiceTest extends AbstractDbUnitJpaTests implements BusConnectionServiceTestBase {

	@Autowired
	protected BusConnectionService busConnectionService;
	@Autowired
	protected DriverService driverService;
	@Autowired
	protected BusService busService;
	@Autowired
	protected SeatService seatService;

	@Test
	public void testFindById() throws Exception {
		BusConnection direction = busConnectionService.findById(getServiceContext(), 1L);
		assertEquals(10, direction.getMinSeats());
	}

	@Test
	public void testFindAll() throws Exception {
		List<BusConnection> myDirections = busConnectionService.findAll(getServiceContext());
		assertEquals(10, myDirections.get(0).getMinSeats());
		assertEquals(3, myDirections.size());

	}

	@Test
	public void testSave() throws Exception {
		BusConnection direction = new BusConnection();
		direction.setDestination("ZA");
		direction.setMinSeats(20);
		direction.setStartHours(9);
		direction.setStartMinutes(40);
		direction.setDurationMinutes(160);

		Driver driver = driverService.findById(getServiceContext(), 1L);
		direction.setDriver(driver);
		Bus bus = busService.findById(getServiceContext(), 1L);
		direction.setBus(bus);

		Seat seatOne = seatService.findById(getServiceContext(),1L);
		Seat seatTwo = seatService.findById(getServiceContext(),2L);
		Seat seatThree = seatService.findById(getServiceContext(),3L);
		direction.addSeat(seatOne);
		direction.addSeat(seatTwo);
		direction.addSeat(seatThree);

		BusConnection newDirection = busConnectionService.save(getServiceContext(), direction);
		assertEquals("ZA", newDirection.getDestination());
		assertEquals(25, newDirection.getDriver().getAge());
		assertEquals("BB-123", newDirection.getBus().getBusSpz());
		assertEquals(SeatStatus.Paid, newDirection.getSeats().get(2).getSeatStatus());
		assertEquals(9, newDirection.getStartHours());

	}

	@Test
	public void testDelete() throws Exception {
		BusConnection direction = busConnectionService.findById(getServiceContext(),2L);
		busConnectionService.delete(getServiceContext(),direction);
		boolean exception = false;

		try {
			BusConnection delDirection = busConnectionService.findById(getServiceContext(),2L);
			fail();
		} catch (BusConnectionNotFoundException bcnfe) {
			exception = true;
		}

		assertTrue(exception);
	}

	@Test
	public void testMakeConnection() throws Exception {
		List<BusConnection> directionsBefore = busConnectionService.findAll(getServiceContext());
		assertEquals(3, directionsBefore.size());

		busConnectionService.makeConnection(getServiceContext(),"Trnava",
				20,8, 0, 40);

		List<BusConnection> directionsAfter = busConnectionService.findAll(getServiceContext());
		assertEquals(4, directionsAfter.size()); // doplnena o vytvorenu connection
		for(BusConnection connection : directionsAfter) {
			System.out.println("Connection`s ID: " + connection.getId());
			// toto funguje, t.j. jeden driver na viacero connections vratane novo vytvorenej
			System.out.println("Driver`ID: " + connection.getDriver().getId());
		}

		// nova connection s ID = 4
		BusConnection connection = busConnectionService.findById(getServiceContext(), 4L);
		assertEquals("PN-999", connection.getBus().getBusSpz());
		assertEquals("Juro", connection.getDriver().getName());

		// ale takto spatne to nefunguje, t.j. nepriradi driverovi novu connection. Opytat sa Pala
		//Driver driver = directionsAfter.get(3).getDriver(); // driver novej connection
		Driver driver = connection.getDriver();
		assertEquals("Juro", driver.getName());
		assertEquals(1, driver.getConnections().size()); // ale medzi driver`s connection nie je
		assertEquals("BB", driver.getConnections().get(0).getDestination()); // len povodne destinacie

	}


	@Test
	public void testFreeReservedSeats() throws Exception {
		BusConnection direction = busConnectionService.findById(getServiceContext(),3L);
		busConnectionService.freeReservedSeats(getServiceContext());
		//BusConnection direction = busConnectionService.findById(getServiceContext(),3L); moze byt aj za metodou
		assertEquals(SeatStatus.Free, direction.getSeats().get(0).getSeatStatus());
		//assertEquals(SeatStatus.Reserved, direction.getSeats().get(1).getSeatStatus()); // pozor na cas rezervacie (10 min)
		assertEquals(SeatStatus.Free, direction.getSeats().get(2).getSeatStatus());

		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY,17);
		cal.set(Calendar.MINUTE,30);
		cal.set(Calendar.SECOND,0);
		//cal.set(Calendar.MILLISECOND,0);

		Date d = cal.getTime();
		System.out.println("Time: " + d);

	}

}
