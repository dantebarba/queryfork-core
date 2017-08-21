package com.github.dantebarba.queryfork.core;

public interface HasParameter {
	Parameter getParameters();

	/**
	 * Combines the parent and subquerie parameter.
	 * @param subQuery
	 * @return
	 */
	Parameter mergeParameters(HasParameter subQuery);

	/**
	 * Sets a parameter to the query, including its value.
	 * @param paramKey
	 * @param paramValue
	 * @return
	 */
	AbstractQuery<?> parameter(String paramKey, Object paramValue);

	/**
	 * Sets a parameter to the query, with null value. 
	 * You can set afterwards the null parameter using the method
	 * {@link Parameter#setParameter(Object, Object)}
	 * @param paramKey
	 * @return
	 */
	AbstractQuery<?> parameter(String paramKey);
}
