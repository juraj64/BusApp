package com.jurajlazovy.bus.serviceimpl;

import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.domain.SeatRepository;
import com.jurajlazovy.bus.exception.SeatNotFoundException;
import com.jurajlazovy.bus.serviceapi.SeatService;
import java.util.List;
import org.sculptor.framework.context.ServiceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Implementation of SeatService.
 */
@Service("seatService")
public class SeatServiceImpl implements SeatService {

	public SeatServiceImpl() {
	}

	@Autowired
	private SeatRepository seatRepository;

	protected SeatRepository getSeatRepository() {
		return seatRepository;
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.SeatRepository#findById}
	 */
	public Seat findById(ServiceContext ctx, Long id) throws SeatNotFoundException {
		return seatRepository.findById(id);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.SeatRepository#findAll}
	 */
	public List<Seat> findAll(ServiceContext ctx) {
		return seatRepository.findAll();
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.SeatRepository#save}
	 */
	public Seat save(ServiceContext ctx, Seat entity) {
		return seatRepository.save(entity);
	}

	/**
	 * Delegates to {@link com.jurajlazovy.bus.domain.SeatRepository#delete}
	 */
	public void delete(ServiceContext ctx, Seat entity) {
		seatRepository.delete(entity);
	}

}
