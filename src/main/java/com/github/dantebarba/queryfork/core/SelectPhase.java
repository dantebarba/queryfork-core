package com.github.dantebarba.queryfork.core;

public interface SelectPhase extends QueryPhase, HasAggregation {

	AbstractQuery select(String select);
	
	
}
