package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.BusConnection;
import com.jurajlazovy.bus.domain.BusProperties.BusProperty;
import com.jurajlazovy.bus.domain.DriverProperties.DriverProperty;
import com.jurajlazovy.bus.domain.SeatProperties.SeatProperty;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in
 * {@link com.jurajlazovy.bus.domain.BusConnection}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class BusConnectionProperties {

	private BusConnectionProperties() {
	}

	private static final BusConnectionPropertiesImpl<BusConnection> sharedInstance = new BusConnectionPropertiesImpl<BusConnection>(
			BusConnection.class);

	public static Property<BusConnection> id() {
		return sharedInstance.id();
	}

	public static Property<BusConnection> destination() {
		return sharedInstance.destination();
	}

	public static Property<BusConnection> minSeats() {
		return sharedInstance.minSeats();
	}

	public static Property<BusConnection> durationMinutes() {
		return sharedInstance.durationMinutes();
	}

	public static Property<BusConnection> uuid() {
		return sharedInstance.uuid();
	}

	public static Property<BusConnection> createdDate() {
		return sharedInstance.createdDate();
	}

	public static Property<BusConnection> createdBy() {
		return sharedInstance.createdBy();
	}

	public static Property<BusConnection> lastUpdated() {
		return sharedInstance.lastUpdated();
	}

	public static Property<BusConnection> lastUpdatedBy() {
		return sharedInstance.lastUpdatedBy();
	}

	public static Property<BusConnection> version() {
		return sharedInstance.version();
	}

	public static DriverProperty<BusConnection> driver() {
		return sharedInstance.driver();
	}

	public static BusProperty<BusConnection> bus() {
		return sharedInstance.bus();
	}

	public static SeatProperty<BusConnection> seats() {
		return sharedInstance.seats();
	}

	/**
	 * This class is used for references to {@link com.jurajlazovy.bus.domain.BusConnection}, i.e. nested property.
	 */
	public static class BusConnectionProperty<T> extends BusConnectionPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public BusConnectionProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class BusConnectionPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		BusConnectionPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		BusConnectionPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> destination() {
			return new LeafProperty<T>(getParentPath(), "destination", false, owningClass);
		}

		public Property<T> minSeats() {
			return new LeafProperty<T>(getParentPath(), "minSeats", false, owningClass);
		}

		public Property<T> durationMinutes() {
			return new LeafProperty<T>(getParentPath(), "durationMinutes", false, owningClass);
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

		public DriverProperty<T> driver() {
			return new DriverProperty<T>(getParentPath(), "driver", owningClass);
		}

		public BusProperty<T> bus() {
			return new BusProperty<T>(getParentPath(), "bus", owningClass);
		}

		public SeatProperty<T> seats() {
			return new SeatProperty<T>(getParentPath(), "seats", owningClass);
		}
	}
}
