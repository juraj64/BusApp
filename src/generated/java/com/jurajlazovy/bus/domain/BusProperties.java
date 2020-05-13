package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.Bus;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in {@link com.jurajlazovy.bus.domain.Bus}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class BusProperties {

	private BusProperties() {
	}

	private static final BusPropertiesImpl<Bus> sharedInstance = new BusPropertiesImpl<Bus>(Bus.class);

	public static Property<Bus> id() {
		return sharedInstance.id();
	}

	public static Property<Bus> busNum() {
		return sharedInstance.busNum();
	}

	public static Property<Bus> busSpz() {
		return sharedInstance.busSpz();
	}

	public static Property<Bus> numberOfSeats() {
		return sharedInstance.numberOfSeats();
	}

	public static Property<Bus> uuid() {
		return sharedInstance.uuid();
	}

	public static Property<Bus> createdDate() {
		return sharedInstance.createdDate();
	}

	public static Property<Bus> createdBy() {
		return sharedInstance.createdBy();
	}

	public static Property<Bus> lastUpdated() {
		return sharedInstance.lastUpdated();
	}

	public static Property<Bus> lastUpdatedBy() {
		return sharedInstance.lastUpdatedBy();
	}

	public static Property<Bus> version() {
		return sharedInstance.version();
	}

	/**
	 * This class is used for references to {@link com.jurajlazovy.bus.domain.Bus}, i.e. nested property.
	 */
	public static class BusProperty<T> extends BusPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public BusProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class BusPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		BusPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		BusPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> busNum() {
			return new LeafProperty<T>(getParentPath(), "busNum", false, owningClass);
		}

		public Property<T> busSpz() {
			return new LeafProperty<T>(getParentPath(), "busSpz", false, owningClass);
		}

		public Property<T> numberOfSeats() {
			return new LeafProperty<T>(getParentPath(), "numberOfSeats", false, owningClass);
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
	}
}
