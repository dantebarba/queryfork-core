package com.github.dantebarba.queryfork.core.paginators;

/**
 * A simple paginator useful for general resources. 
 * Your personal paginator must implement and at least, provide
 * the functionality described on this interface.
 * @author dantebarba
 *
 */
public interface Paginator {

	int getFirst();
	
	int getPageSize();
	
	int getCount();
	
	
}
