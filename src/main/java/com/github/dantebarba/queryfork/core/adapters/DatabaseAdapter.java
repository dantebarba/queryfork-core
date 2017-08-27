package com.github.dantebarba.queryfork.core.adapters;

import java.util.List;

import com.github.dantebarba.queryfork.core.builders.QueryBuilder;
import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

/**
 * Describes the operations that must be provided
 * by the database handler of your choice. This
 * abstract operations are the minimum requirements for
 * the core to work. If no handler is declared, you can use
 * {@link QueryBuilder#getQuery()} to get the HQL representative
 * String. 
 * @author dantebarba
 *
 */
public interface DatabaseAdapter {
	
	List getResultList();
	
	Object getSingleResult();
	
	int count();
	
	void paginate(Paginator paginator);
	
	void queryParameters(Parameter parameters);

	void createQuery(HQLString query);
	
	
	
}
