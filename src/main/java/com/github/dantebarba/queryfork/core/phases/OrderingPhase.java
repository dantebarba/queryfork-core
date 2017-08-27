package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.helpers.OrderCriteria;

public interface OrderingPhase<T> extends QueryPhase<T> {

	OrderingPhase<T> orderBy(String attribute);
	
	OrderingPhase<T> orderBy(String attribute, OrderCriteria criteria);
	
}
