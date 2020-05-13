package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.Seat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Builder for BusConnection class.
 */
public class BusConnectionBuilder {

	private String destination;
	private int minSeats;
	private int startHours;
	private int startMinutes;
	private int durationMinutes;
	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	private Driver driver;
	private Bus bus;

	private List<Seat> seats = new ArrayList<Seat>();

	/**
	 * Static factory method for BusConnectionBuilder
	 */
	public static BusConnectionBuilder busConnection() {
		return new BusConnectionBuilder();
	}

	public BusConnectionBuilder() {
	}

	public BusConnectionBuilder destination(String val) {
		this.destination = val;
		return this;
	}

	public BusConnectionBuilder minSeats(int val) {
		this.minSeats = val;
		return this;
	}

	public BusConnectionBuilder startHours(int val) {
		this.startHours = val;
		return this;
	}

	public BusConnectionBuilder startMinutes(int val) {
		this.startMinutes = val;
		return this;
	}

	public BusConnectionBuilder durationMinutes(int val) {
		this.durationMinutes = val;
		return this;
	}

	public BusConnectionBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public BusConnectionBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public BusConnectionBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public BusConnectionBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	public BusConnectionBuilder driver(Driver driver) {
		this.driver = driver;
		return this;
	}

	public BusConnectionBuilder bus(Bus bus) {
		this.bus = bus;
		return this;
	}

	/**
	 * Adds an object to the to-many association. It is added the collection {@link #getSeats}.
	 */
	public BusConnectionBuilder addSeat(Seat seatElement) {
		getSeats().add(seatElement);
		return this;
	}

	public String getDestination() {
		return destination;
	}

	public int getMinSeats() {
		return minSeats;
	}

	public int getStartHours() {
		return startHours;
	}

	public int getStartMinutes() {
		return startMinutes;
	}

	public int getDurationMinutes() {
		return durationMinutes;
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

	public Driver getDriver() {
		return driver;
	}

	public Bus getBus() {
		return bus;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	/**
	 * @return new BusConnection instance constructed based on the values that have been set into this builder
	 */
	public BusConnection build() {
		BusConnection obj = new BusConnection();
		obj.setDestination(destination);
		obj.setMinSeats(minSeats);
		obj.setStartHours(startHours);
		obj.setStartMinutes(startMinutes);
		obj.setDurationMinutes(durationMinutes);
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);
		obj.setDriver(driver);
		obj.setBus(bus);
		obj.getSeats().addAll(seats);

		return obj;
	}
}
