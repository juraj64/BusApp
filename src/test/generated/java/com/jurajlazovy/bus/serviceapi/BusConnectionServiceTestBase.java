package com.jurajlazovy.bus.serviceapi;

/**
 * Definition of test methods to implement.
 */
public interface BusConnectionServiceTestBase {

	public void testMakeConnection() throws Exception;

	public void testReserveSeat() throws Exception;

	public void testConfirmSeat() throws Exception;

	public void testFreeReservedSeats() throws Exception;

	public void testFindById() throws Exception;

	public void testFindAll() throws Exception;

	public void testSave() throws Exception;

	public void testDelete() throws Exception;
}
