package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.exception.BusConnectionNotFoundException;
import java.util.List;
import org.sculptor.framework.accessapi.ConditionalCriteria;

/**
 * Generated interface for Repository for BusConnection
 */
public interface BusConnectionRepository {

	public final static String BEAN_ID = "busConnectionRepository";

	public List<BusConnection> findByCondition(List<ConditionalCriteria> condition);

	public BusConnection findById(Long id) throws BusConnectionNotFoundException;

	public List<BusConnection> findAll();

	public BusConnection save(BusConnection entity);

	public void delete(BusConnection entity);

}
