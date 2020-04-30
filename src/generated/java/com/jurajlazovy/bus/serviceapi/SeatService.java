package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.exception.SeatNotFoundException;
import java.util.List;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service SeatService.
 */
public interface SeatService {

	public final static String BEAN_ID = "seatService";

	public Seat findById(ServiceContext ctx, Long id) throws SeatNotFoundException;

	public List<Seat> findAll(ServiceContext ctx);

	public Seat save(ServiceContext ctx, Seat entity);

	public void delete(ServiceContext ctx, Seat entity);

}
