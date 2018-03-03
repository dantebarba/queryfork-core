package com.github.dantebarba.queryfork.core.phases;

public interface HasAggregation<T> {
	
	QueryPhase<T> count(String count);
	
}
