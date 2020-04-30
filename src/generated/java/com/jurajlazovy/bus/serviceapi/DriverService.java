package com.jurajlazovy.bus.serviceapi;

import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.exception.DriverNotFoundException;
import java.util.List;
import org.sculptor.framework.context.ServiceContext;

/**
 * Generated interface for the Service DriverService.
 */
public interface DriverService {

	public final static String BEAN_ID = "driverService";

	public Driver findById(ServiceContext ctx, Long id) throws DriverNotFoundException;

	public List<Driver> findAll(ServiceContext ctx);

	public Driver save(ServiceContext ctx, Driver entity);

	public void delete(ServiceContext ctx, Driver entity);

}
