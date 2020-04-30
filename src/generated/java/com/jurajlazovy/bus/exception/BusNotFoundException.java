package com.jurajlazovy.bus.exception;

import org.sculptor.framework.errorhandling.ApplicationException;

public class BusNotFoundException extends ApplicationException {
	private static final long serialVersionUID = 1L;
	private static final String CLASS_NAME = BusNotFoundException.class.getSimpleName();
	private static final String CLASS_FULL_NAME = BusNotFoundException.class.getName();

	public BusNotFoundException(String m, java.io.Serializable... messageParameter) {
		super(CLASS_FULL_NAME, m);
		setMessageParameters(messageParameter);
	}

	public BusNotFoundException(int errorCode, String m, java.io.Serializable... messageParameter) {
		super(String.format("%1$s-%3$04d", CLASS_FULL_NAME, CLASS_NAME, errorCode), m);
		setMessageParameters(messageParameter);
	}
}
