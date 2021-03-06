package com.jurajlazovy.bus.serviceapi;

/**
 * Definition of test methods to implement.
 */
public interface BusConnectionServiceTestBase {

	public void testMakeConnection() throws Exception;

	public void testFreeReservedSeats() throws Exception;

	public void testFindBusConnectionsByCondition() throws Exception;

	public void testFindBusConnectionsJoinByCondition() throws Exception;

	public void testFindByCondition() throws Exception;

	public void testFindById() throws Exception;

	public void testFindAll() throws Exception;

	public void testSave() throws Exception;

	public void testDelete() throws Exception;
}
