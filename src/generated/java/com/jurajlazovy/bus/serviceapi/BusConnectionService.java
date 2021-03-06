package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.exception.BusConnectionNotFoundException;
import com.jurajlazovy.bus.exception.NoneFreeBusOrDriver;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service BusConnectionService.
 */
public interface BusConnectionService {

	public final static String BEAN_ID = "busConnectionService";

	public BusConnection makeConnection(ServiceContext ctx, String destination, int minSeats, int startHours, int startMinutes,
			int durationMinutes) throws NoneFreeBusOrDriver;

	public void freeReservedSeats(ServiceContext ctx);

	public List<BusConnection> findBusConnectionsByCondition(ServiceContext ctx);

	public List<BusConnection> findBusConnectionsJoinByCondition(ServiceContext ctx);

	public List<BusConnection> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition);

	public BusConnection findById(ServiceContext ctx, Long id) throws BusConnectionNotFoundException;

	public List<BusConnection> findAll(ServiceContext ctx);

	public BusConnection save(ServiceContext ctx, BusConnection entity);

	public void delete(ServiceContext ctx, BusConnection entity);

}
