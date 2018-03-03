package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.builders.Builder;

public interface SelectPhase<T> extends QueryPhase<T>, HasAggregation, Builder<T>, HasParameter {

	FromPhase select(String select);
	
	FromPhase select(Class clazz);
	
	FromPhase distinct();
	
	
}
