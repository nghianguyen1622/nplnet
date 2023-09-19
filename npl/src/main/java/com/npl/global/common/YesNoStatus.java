package com.npl.global.common;

public enum YesNoStatus {
	YES("Y"), NO("N");

	private final String value;

	YesNoStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
