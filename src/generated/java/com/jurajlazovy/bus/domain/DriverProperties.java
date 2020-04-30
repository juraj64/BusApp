package com.jurajlazovy.bus.domain;

import com.jurajlazovy.bus.domain.BusConnectionProperties.BusConnectionProperty;
import com.jurajlazovy.bus.domain.Driver;
import org.sculptor.framework.domain.LeafProperty;
import org.sculptor.framework.domain.PropertiesCollection;
import org.sculptor.framework.domain.Property;

/**
 * This generated interface defines property names for all attributes and associatations in
 * {@link com.jurajlazovy.bus.domain.Driver}.
 * <p>
 * These properties are useful when building criteria with {@link org.sculptor.framework.accessapi.ConditionalCriteriaBuilder},
 * which can be used with findByCondition Repository operation.
 */
public class DriverProperties {

	private DriverProperties() {
	}

	private static final DriverPropertiesImpl<Driver> sharedInstance = new DriverPropertiesImpl<Driver>(Driver.class);

	public static Property<Driver> id() {
		return sharedInstance.id();
	}

	public static Property<Driver> name() {
		return sharedInstance.name();
	}

	public static Property<Driver> age() {
		return sharedInstance.age();
	}

	public static Property<Driver> uuid() {
		return sharedInstance.uuid();
	}

	public static Property<Driver> createdDate() {
		return sharedInstance.createdDate();
	}

	public static Property<Driver> createdBy() {
		return sharedInstance.createdBy();
	}

	public static Property<Driver> lastUpdated() {
		return sharedInstance.lastUpdated();
	}

	public static Property<Driver> lastUpdatedBy() {
		return sharedInstance.lastUpdatedBy();
	}

	public static Property<Driver> version() {
		return sharedInstance.version();
	}

	public static BusConnectionProperty<Driver> connections() {
		return sharedInstance.connections();
	}

	/**
	 * This class is used for references to {@link com.jurajlazovy.bus.domain.Driver}, i.e. nested property.
	 */
	public static class DriverProperty<T> extends DriverPropertiesImpl<T> implements Property<T> {
		private static final long serialVersionUID = 1L;

		public DriverProperty(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath, owningClass);
		}
	}

	protected static class DriverPropertiesImpl<T> extends PropertiesCollection {
		private static final long serialVersionUID = 1L;
		Class<T> owningClass;

		DriverPropertiesImpl(Class<T> owningClass) {
			super(null);
			this.owningClass = owningClass;
		}

		DriverPropertiesImpl(String parentPath, String additionalPath, Class<T> owningClass) {
			super(parentPath, additionalPath);
			this.owningClass = owningClass;
		}

		public Property<T> id() {
			return new LeafProperty<T>(getParentPath(), "id", false, owningClass);
		}

		public Property<T> name() {
			return new LeafProperty<T>(getParentPath(), "name", false, owningClass);
		}

		public Property<T> age() {
			return new LeafProperty<T>(getParentPath(), "age", false, owningClass);
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

		public BusConnectionProperty<T> connections() {
			return new BusConnectionProperty<T>(getParentPath(), "connections", owningClass);
		}
	}
}
