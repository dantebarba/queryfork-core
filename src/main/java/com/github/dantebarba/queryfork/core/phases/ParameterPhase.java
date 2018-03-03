package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;

public interface ParameterPhase<T> extends QueryPhase<T>, HasParameter {
	/**
	 * Sets a parameter to the query, including its value.
	 * 
	 * @param paramKey
	 * @param paramValue
	 * @return
	 */
	SubQueryPhase<T> parameter(String paramKey, Object paramValue);

	/**
	 * Sets a parameter to the query, with null value. You can set afterwards
	 * the null parameter using the method
	 * {@link Parameter#setParameter(Object, Object)}
	 * 
	 * @param paramKey
	 * @return
	 */
	SubQueryPhase<T> parameter(String paramKey);
}
