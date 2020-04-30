package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusRepository;
import com.jurajlazovy.bus.exception.BusNotFoundException;
import com.jurajlazovy.bus.serviceapi.BusService;
import java.util.List;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of BusService.
 */
@Service("busService")
public class BusServiceImpl implements BusService {

	public BusServiceImpl() {
	}

	@Autowired
	private BusRepository busRepository;

	protected BusRepository getBusRepository() {
		return busRepository;
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusRepository#findById}
	 */
	public Bus findById(ServiceContext ctx, Long id) throws BusNotFoundException {
		return busRepository.findById(id);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusRepository#findAll}
	 */
	public List<Bus> findAll(ServiceContext ctx) {
		return busRepository.findAll();
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusRepository#save}
	 */
	public Bus save(ServiceContext ctx, Bus entity) {
		return busRepository.save(entity);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusRepository#delete}
	 */
	public void delete(ServiceContext ctx, Bus entity) {
		busRepository.delete(entity);
	}

}
