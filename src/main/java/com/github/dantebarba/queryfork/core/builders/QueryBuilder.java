package com.github.dantebarba.queryfork.core.builders;

import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.queries.Query;

public class QueryBuilder extends AbstractQuery<IsQuery> {


	private IsQuery buildedQuery = new Query();

	/**
	 * TODO: Esto debería ser modificado a una QueryPhase.
	 */

	@Override
	public IsQuery build() {
		this.buildedQuery.createQuery(this.getQuery());
		this.buildedQuery.parameters(this.getParameters());
		return this.buildedQuery;
	}

	@Override
	public IsQuery getBuildedQuery() {
		return this.buildedQuery ;
	}


}
