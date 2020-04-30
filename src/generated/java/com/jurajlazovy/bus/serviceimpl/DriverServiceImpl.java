package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.DriverRepository;
import com.jurajlazovy.bus.exception.DriverNotFoundException;
import com.jurajlazovy.bus.serviceapi.DriverService;
import java.util.List;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of DriverService.
 */
@Service("driverService")
public class DriverServiceImpl implements DriverService {

	public DriverServiceImpl() {
	}

	@Autowired
	private DriverRepository driverRepository;

	protected DriverRepository getDriverRepository() {
		return driverRepository;
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.DriverRepository#findById}
	 */
	public Driver findById(ServiceContext ctx, Long id) throws DriverNotFoundException {
		return driverRepository.findById(id);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.DriverRepository#findAll}
	 */
	public List<Driver> findAll(ServiceContext ctx) {
		return driverRepository.findAll();
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.DriverRepository#save}
	 */
	public Driver save(ServiceContext ctx, Driver entity) {
		return driverRepository.save(entity);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.DriverRepository#delete}
	 */
	public void delete(ServiceContext ctx, Driver entity) {
		driverRepository.delete(entity);
	}

}
