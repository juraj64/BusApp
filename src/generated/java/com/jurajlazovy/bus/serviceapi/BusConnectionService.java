package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.exception.BusConnectionNotFoundException;
import com.jurajlazovy.bus.exception.SeatAlreadyReserved;
import com.jurajlazovy.bus.exception.WrongKey;
import java.util.List;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service BusConnectionService.
 */
public interface BusConnectionService {

	public final static String BEAN_ID = "busConnectionService";

	public void makeConnection(ServiceContext ctx, String destination, int minSeats, int durationMinutes);

	public String reserveSeat(ServiceContext ctx, int seatNum) throws SeatAlreadyReserved;

	public String confirmSeat(ServiceContext ctx, int seatNum, String reservationKey) throws WrongKey;

	public void freeReservedSeats(ServiceContext ctx);

	public BusConnection findById(ServiceContext ctx, Long id) throws BusConnectionNotFoundException;

	public List<BusConnection> findAll(ServiceContext ctx);

	public BusConnection save(ServiceContext ctx, BusConnection entity);

	public void delete(ServiceContext ctx, BusConnection entity);

}
