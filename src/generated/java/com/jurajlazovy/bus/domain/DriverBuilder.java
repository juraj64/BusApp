package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.Driver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Builder for Driver class.
 */
public class DriverBuilder {

	private String name;
	private int age;
	private Date createdDate;
	private String createdBy;
	private Date lastUpdated;
	private String lastUpdatedBy;

	private List<BusConnection> connections = new ArrayList<BusConnection>();

	/**
	 * Static factory method for DriverBuilder
	 */
	public static DriverBuilder driver() {
		return new DriverBuilder();
	}

	public DriverBuilder() {
	}

	public DriverBuilder name(String val) {
		this.name = val;
		return this;
	}

	public DriverBuilder age(int val) {
		this.age = val;
		return this;
	}

	public DriverBuilder createdDate(Date val) {
		this.createdDate = val;
		return this;
	}

	public DriverBuilder createdBy(String val) {
		this.createdBy = val;
		return this;
	}

	public DriverBuilder lastUpdated(Date val) {
		this.lastUpdated = val;
		return this;
	}

	public DriverBuilder lastUpdatedBy(String val) {
		this.lastUpdatedBy = val;
		return this;
	}

	/**
	 * Adds an object to the to-many association. It is added the collection {@link #getConnections}.
	 */
	public DriverBuilder addConnection(BusConnection connectionElement) {
		getConnections().add(connectionElement);
		return this;
	}

	public String getName() {
		return name;
	}

	public int getAge() {
		return age;
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

	public List<BusConnection> getConnections() {
		return connections;
	}

	/**
	 * @return new Driver instance constructed based on the values that have been set into this builder
	 */
	public Driver build() {
		Driver obj = new Driver();
		obj.setName(name);
		obj.setAge(age);
		obj.setCreatedDate(createdDate);
		obj.setCreatedBy(createdBy);
		obj.setLastUpdated(lastUpdated);
		obj.setLastUpdatedBy(lastUpdatedBy);
		obj.getConnections().addAll(connections);

		return obj;
	}
}
