package com.jurajlazovy.bus.domain;

import java.io.Serializable;

/**
 * Enum for SeatStatus
 */
public enum SeatStatus implements Serializable {
	Free, Reserved, Paid;

	/**
	 */
	private SeatStatus() {
	}

	public String getName() {
		return name();
	}
}
