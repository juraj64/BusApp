package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.exception.DriverNotFoundException;
import java.util.List;

/**
 * Generated interface for Repository for Driver
 */
public interface DriverRepository {

	public final static String BEAN_ID = "driverRepository";

	public Driver findById(Long id) throws DriverNotFoundException;

	public List<Driver> findAll();

	public Driver save(Driver entity);

	public void delete(Driver entity);

}
