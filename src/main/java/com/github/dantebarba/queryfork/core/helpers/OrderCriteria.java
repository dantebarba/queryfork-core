package com.github.dantebarba.queryfork.core.helpers;

public enum OrderCriteria {

	ASC, DESC;
	
	public static OrderCriteria getDefault() {
		return OrderCriteria.DESC;
	}
	
}
