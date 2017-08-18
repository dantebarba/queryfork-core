package com.github.dantebarba.queryfork.core;

import java.util.List;

/**
 * Describes the operations that must be provided
 * by the database handler of your choice. This
 * abstract operations are the minimum requirements for
 * the core to work. If no handler is declared, you can use
 * {@link Builder#getQuery()} to get the HQL representative
 * String. 
 * @author dantebarba
 *
 */
public interface DatabaseAdapter {
	
	List getResultList();
	
	List getSingleResult();
	
	int count();
	
	void paginate(Paginator paginator);
	
	void queryParameters(Parameter parameters);

	void createQuery(HQLString query);
	
	
	
}
