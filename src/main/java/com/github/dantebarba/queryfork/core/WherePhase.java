package com.github.dantebarba.queryfork.core;

/**
 * Where clause with all its subqueries and Operands. 
 * At this time, Operands are defined as part of the query.
 * TODO: Operands must have their own implementation, as Comparators
 * (eq, lte, gte, lt, gt)
 * @author dantebarba
 *
 */
public interface WherePhase extends QueryPhase, HasParameter {

	AbstractQuery or(WherePhase subQuery);

	AbstractQuery and(WherePhase subQuery);

	AbstractQuery in(String parameter, Object inList);

	AbstractQuery or(String subQuery);

	AbstractQuery and(String subQuery);

	AbstractQuery where(WherePhase subQuery);
	
	AbstractQuery subQuery(WherePhase subQuery);
	
}
