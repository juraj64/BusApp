package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Bus;
import java.util.Date;

/**
 * Builder for Bus class.
 */
public class BusBuilder {

	private int busNum;
	private String busSpz;
	private int numberOfSeats;
	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	/**
	 * Static factory method for BusBuilder
	 */
	public static BusBuilder bus() {
		return new BusBuilder();
	}

	public BusBuilder() {
	}

	public BusBuilder(int busNum) {

		this.busNum = busNum;

	}

	public BusBuilder busNum(int val) {
		this.busNum = val;
		return this;
	}

	public BusBuilder busSpz(String val) {
		this.busSpz = val;
		return this;
	}

	public BusBuilder numberOfSeats(int val) {
		this.numberOfSeats = val;
		return this;
	}

	public BusBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public BusBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public BusBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public BusBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	public int getBusNum() {
		return busNum;
	}

	public String getBusSpz() {
		return busSpz;
	}

	public int getNumberOfSeats() {
		return numberOfSeats;
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

	/**
	 * @return new Bus instance constructed based on the values that have been set into this builder
	 */
	public Bus build() {
		Bus obj = new Bus(getBusNum());
		obj.setBusSpz(busSpz);
		obj.setNumberOfSeats(numberOfSeats);
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);

		return obj;
	}
}
