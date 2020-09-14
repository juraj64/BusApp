package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.exception.BusNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service BusService.
 */
public interface BusService {

	public final static String BEAN_ID = "busService";

	public List<Bus> findBusesByCondition(ServiceContext ctx, int numberOfSeats);

	public List<Bus> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition);

	public Bus findById(ServiceContext ctx, Long id) throws BusNotFoundException;

	public List<Bus> findAll(ServiceContext ctx);

	public Bus save(ServiceContext ctx, Bus entity);

	public void delete(ServiceContext ctx, Bus entity);

}
