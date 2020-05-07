package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.*;
import com.jurajlazovy.bus.exception.BusConnectionNotFoundException;
import org.junit.Test;
import org.sculptor.framework.test.AbstractDbUnitJpaTests;
import org.springframework.beans.factory.annotation.Autowired;

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
		busConnectionService.makeConnection(getServiceContext(),"Trnava", 20,40);
	}


	@Test
	public void testFreeReservedSeats() throws Exception {
		busConnectionService.freeReservedSeats(getServiceContext());

	}

}
