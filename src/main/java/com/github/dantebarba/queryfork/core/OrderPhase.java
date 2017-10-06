package com.github.dantebarba.queryfork.core;

public interface OrderPhase extends QueryPhase {

	AbstractQuery<?> orderBy(String field, Order order);
	
	AbstractQuery<?> orderBy(String field);
	
}
