package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.queries.AbstractQuery;

/**
 * Where clause with all its subqueries and Operands. 
 * At this time, Operands are defined as part of the query.
 * TODO: Operands must have their own implementation, as Comparators
 * (eq, lte, gte, lt, gt)
 * @author dantebarba
 *
 */
public interface WherePhase<T> extends QueryPhase<T> {

	 OrderingPhase<T> where(AbstractQuery start);
	
}
