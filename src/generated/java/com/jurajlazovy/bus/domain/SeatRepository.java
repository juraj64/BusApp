package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.exception.SeatNotFoundException;
import java.util.List;

/**
 * Generated interface for Repository for Seat
 */
public interface SeatRepository {

	public final static String BEAN_ID = "seatRepository";

	public Seat findById(Long id) throws SeatNotFoundException;

	public List<Seat> findAll();

	public Seat save(Seat entity);

	public void delete(Seat entity);

}
