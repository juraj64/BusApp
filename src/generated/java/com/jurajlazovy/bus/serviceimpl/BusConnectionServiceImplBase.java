package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.BusConnectionRepository;
import com.jurajlazovy.bus.exception.BusConnectionNotFoundException;
import com.jurajlazovy.bus.serviceapi.BusConnectionService;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Generated base class for implementation of BusConnectionService.
 * <p>
 * Make sure that subclass defines the following annotations:
 * 
 * <pre>
 * @org.springframework.stereotype.Service("busConnectionService")
 * </pre>
 *
 */
public abstract class BusConnectionServiceImplBase implements BusConnectionService {

	public BusConnectionServiceImplBase() {
	}

	@Autowired
	private BusConnectionRepository busConnectionRepository;

	protected BusConnectionRepository getBusConnectionRepository() {
		return busConnectionRepository;
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusConnectionRepository#findByCondition}
	 */
	public List<BusConnection> findByCondition(ServiceContext ctx, List<ConditionalCriteria> condition) {
		return busConnectionRepository.findByCondition(condition);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusConnectionRepository#findById}
	 */
	public BusConnection findById(ServiceContext ctx, Long id) throws BusConnectionNotFoundException {
		return busConnectionRepository.findById(id);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusConnectionRepository#findAll}
	 */
	public List<BusConnection> findAll(ServiceContext ctx) {
		return busConnectionRepository.findAll();
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusConnectionRepository#save}
	 */
	public BusConnection save(ServiceContext ctx, BusConnection entity) {
		return busConnectionRepository.save(entity);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.BusConnectionRepository#delete}
	 */
	public void delete(ServiceContext ctx, BusConnection entity) {
		busConnectionRepository.delete(entity);
	}

}
