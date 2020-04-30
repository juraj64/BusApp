package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.exception.BusNotFoundException;
import java.util.List;

/**
 * Generated interface for Repository for Bus
 */
public interface BusRepository {

	public final static String BEAN_ID = "busRepository";

	public Bus findById(Long id) throws BusNotFoundException;

	public List<Bus> findAll();

	public Bus save(Bus entity);

	public void delete(Bus entity);

}
