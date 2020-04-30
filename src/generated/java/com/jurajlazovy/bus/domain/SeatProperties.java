package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.BusConnectionProperties.BusConnectionProperty;
import com.jurajlazovy.bus.domain.Seat;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in {@link com.jurajlazovy.bus.domain.Seat}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class SeatProperties {

	private SeatProperties() {
	}

	private static final SeatPropertiesImpl<Seat> sharedInstance = new SeatPropertiesImpl<Seat>(Seat.class);

	public static Property<Seat> id() {
		return sharedInstance.id();
	}

	public static Property<Seat> seatNo() {
		return sharedInstance.seatNo();
	}

	public static Property<Seat> reservationDate() {
		return sharedInstance.reservationDate();
	}

	public static Property<Seat> reservationKey() {
		return sharedInstance.reservationKey();
	}

	public static Property<Seat> uuid() {
		return sharedInstance.uuid();
	}

	public static Property<Seat> createdDate() {
		return sharedInstance.createdDate();
	}

	public static Property<Seat> createdBy() {
		return sharedInstance.createdBy();
	}

	public static Property<Seat> lastUpdated() {
		return sharedInstance.lastUpdated();
	}

	public static Property<Seat> lastUpdatedBy() {
		return sharedInstance.lastUpdatedBy();
	}

	public static Property<Seat> version() {
		return sharedInstance.version();
	}

	public static Property<Seat> seatStatus() {
		return sharedInstance.seatStatus();
	}

	public static BusConnectionProperty<Seat> direction() {
		return sharedInstance.direction();
	}

	/**
	 * This class is used for references to {@link com.jurajlazovy.bus.domain.Seat}, i.e. nested property.
	 */
	public static class SeatProperty<T> extends SeatPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public SeatProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class SeatPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		SeatPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		SeatPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> seatNo() {
			return new LeafProperty<T>(getParentPath(), "seatNo", false, owningClass);
		}

		public Property<T> reservationDate() {
			return new LeafProperty<T>(getParentPath(), "reservationDate", false, owningClass);
		}

		public Property<T> reservationKey() {
			return new LeafProperty<T>(getParentPath(), "reservationKey", false, owningClass);
		}

		public Property<T> uuid() {
			return new LeafProperty<T>(getParentPath(), "uuid", false, owningClass);
		}

		public Property<T> createdDate() {
			return new LeafProperty<T>(getParentPath(), "createdDate", false, owningClass);
		}

		public Property<T> createdBy() {
			return new LeafProperty<T>(getParentPath(), "createdBy", false, owningClass);
		}

		public Property<T> lastUpdated() {
			return new LeafProperty<T>(getParentPath(), "lastUpdated", false, owningClass);
		}

		public Property<T> lastUpdatedBy() {
			return new LeafProperty<T>(getParentPath(), "lastUpdatedBy", false, owningClass);
		}

		public Property<T> version() {
			return new LeafProperty<T>(getParentPath(), "version", false, owningClass);
		}

		public Property<T> seatStatus() {
			return new LeafProperty<T>(getParentPath(), "seatStatus", false, owningClass);
		}

		public BusConnectionProperty<T> direction() {
			return new BusConnectionProperty<T>(getParentPath(), "direction", owningClass);
		}
	}
}
