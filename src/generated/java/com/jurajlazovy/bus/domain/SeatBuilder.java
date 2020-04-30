package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Seat;
import com.jurajlazovy.bus.domain.SeatStatus;
import java.util.Date;

/**
 * Builder for Seat class.
 */
public class SeatBuilder {

	private int seatNo;
	private Date reservationDate;
	private String reservationKey;
	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	private SeatStatus seatStatus;
	private BusConnection direction;

	/**
	 * Static factory method for SeatBuilder
	 */
	public static SeatBuilder seat() {
		return new SeatBuilder();
	}

	public SeatBuilder() {
	}

	public SeatBuilder seatNo(int val) {
		this.seatNo = val;
		return this;
	}

	public SeatBuilder reservationDate(Date val) {
		this.reservationDate = val;
		return this;
	}

	public SeatBuilder reservationKey(String val) {
		this.reservationKey = val;
		return this;
	}

	public SeatBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public SeatBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public SeatBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public SeatBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	public SeatBuilder seatStatus(SeatStatus seatStatus) {
		this.seatStatus = seatStatus;
		return this;
	}

	public SeatBuilder direction(BusConnection direction) {
		this.direction = direction;
		return this;
	}

	public int getSeatNo() {
		return seatNo;
	}

	public Date getReservationDate() {
		return reservationDate;
	}

	public String getReservationKey() {
		return reservationKey;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public SeatStatus getSeatStatus() {
		return seatStatus;
	}

	public BusConnection getDirection() {
		return direction;
	}

	/**
	 * @return new Seat instance constructed based on the values that have been set into this builder
	 */
	public Seat build() {
		Seat obj = new Seat();
		obj.setSeatNo(seatNo);
		obj.setReservationDate(reservationDate);
		obj.setReservationKey(reservationKey);
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);
		obj.setSeatStatus(seatStatus);
		obj.setDirection(direction);

		return obj;
	}
}
