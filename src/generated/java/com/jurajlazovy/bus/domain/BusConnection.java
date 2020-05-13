package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Bus;
import com.jurajlazovy.bus.domain.Driver;
import com.jurajlazovy.bus.domain.Seat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.ForeignKey;
import org.sculptor.framework.domain.AbstractDomainObject;
import org.sculptor.framework.domain.AuditListener;
import org.sculptor.framework.domain.Auditable;
import org.sculptor.framework.domain.Identifiable;

/**
 * Entity representing BusConnection.
 */

@Entity
@Table(name = "BUSCONNECTION")
@EntityListeners({ AuditListener.class })
public class BusConnection extends AbstractDomainObject implements Auditable, Identifiable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long id;
	@Column(name = "DESTINATION", nullable = false, length = 100)
	@NotNull
	private String destination;
	@Column(name = "MINSEATS", nullable = false)
	private int minSeats;
	@Column(name = "STARTHOURS", nullable = false)
	private int startHours;
	@Column(name = "STARTMINUTES", nullable = false)
	private int startMinutes;
	@Column(name = "DURATIONMINUTES", nullable = false)
	private int durationMinutes;
	@Column(name = "UUID", nullable = false, length = 36, unique = true)
	private String uuid;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATEDDATE")
	private Date createdDate;
	@Column(name = "CREATEDBY", length = 50)
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "LASTUPDATED")
	private Date lastUpdated;
	@Column(name = "LASTUPDATEDBY", length = 50)
	private String lastUpdatedBy;
	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	@ManyToOne(optional = false, cascade = { CascadeType.PERSIST, CascadeType.MERGE })
	@JoinColumn(name = "DRIVER", nullable = false)
	@ForeignKey(name = "FK_BUSCONNECTION_DRIVER")
	@NotNull
	private Driver driver;
	@ManyToOne(optional = false, cascade = CascadeType.ALL)
	@JoinColumn(name = "BUS", nullable = false)
	@ForeignKey(name = "FK_BUSCONNECTION_BUS")
	@NotNull
	private Bus bus;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "direction")
	@NotNull
	private List<Seat> seats = new ArrayList<Seat>();

	public BusConnection() {
	}

	public Long getId() {
		return id;
	}

	/**
	 * The id is not intended to be changed or assigned manually, but for test purpose it is allowed to assign the id.
	 */
	protected void setId(Long id) {
		if ((this.id != null) && !this.id.equals(id)) {
			throw new IllegalArgumentException("Not allowed to change the id property.");
		}
		this.id = id;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;

	}

	public int getMinSeats() {
		return minSeats;
	}

	public void setMinSeats(int minSeats) {
		this.minSeats = minSeats;

	}

	public int getStartHours() {
		return startHours;
	}

	public void setStartHours(int startHours) {
		this.startHours = startHours;

	}

	public int getStartMinutes() {
		return startMinutes;
	}

	public void setStartMinutes(int startMinutes) {
		this.startMinutes = startMinutes;

	}

	public int getDurationMinutes() {
		return durationMinutes;
	}

	public void setDurationMinutes(int durationMinutes) {
		this.durationMinutes = durationMinutes;

	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;

	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;

	}

	public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;

	}

	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;

	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;

	}

	/**
	 * This domain object doesn't have a natural key and this random generated identifier is the unique identifier for this domain
	 * object.
	 */
	public String getUuid() {
		// lazy init of UUID
		if (uuid == null) {
			uuid = UUID.randomUUID().toString();
		}
		return uuid;
	}

	public Driver getDriver() {
		return driver;
	}

	public void setDriver(Driver driver) {
		this.driver = driver;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public List<Seat> getSeats() {
		return seats;
	}

	/**
	 * Adds an object to the bidirectional many-to-one association in both ends. It is added the collection {@link #getSeats} at
	 * this side and the association {@link com.jurajlazovy.bus.domain.Seat#setDirection} at the opposite side is set.
	 */
	public void addSeat(Seat seatElement) {
		getSeats().add(seatElement);
		seatElement.setDirection((com.jurajlazovy.bus.domain.BusConnection) this);
	}

	/*
	 * EclipseLink/DataNucleus are trying to update the related entities. This fails on non nullable references
	 */
	/**
	 * Removes an object from the bidirectional many-to-one association in both ends. It is removed from the collection
	 * {@link #getSeats} at this side and the association {@link com.jurajlazovy.bus.domain.Seat#setDirection} at the opposite side
	 * is cleared (nulled).
	 */
	public void removeSeat(Seat seatElement) {
		getSeats().remove(seatElement);

		seatElement.setDirection(null);
	}

	/*
	 * EclipseLink/DataNucleus are trying to update the related entities. This fails on non nullable references
	 */
	/**
	 * Removes all object from the bidirectional many-to-one association in both ends. All elements are removed from the collection
	 * {@link #getSeats} at this side and the association {@link com.jurajlazovy.bus.domain.Seat#setDirection} at the opposite side
	 * is cleared (nulled).
	 */
	public void removeAllSeats() {
		for (Seat d : getSeats()) {
			d.setDirection(null);
		}
		getSeats().clear();
	}

	@PrePersist
	protected void prePersist() {
		getUuid();
	}

	/**
	 * This method is used by equals and hashCode.
	 * 
	 * @return {{@link #getUuid}
	 */
	public Object getKey() {
		return getUuid();
	}

}
