package com.github.dantebarba.queryfork.core.queries;

import com.github.dantebarba.queryfork.core.adapters.DatabaseAdapter;
import com.github.dantebarba.queryfork.core.adapters.HasAdapter;
import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class Query extends AbstractQuery {

	@Override
	public AbstractQuery createQuery(HQLString query2) {
		adapter.createQuery(query2);
		return this;
	}

	@Override
	public HasAdapter queryParameters(HasParameter query) {
		this.getAdapter().queryParameters(query.getParameters());
		return this;
	}
	
	@Override
	public DatabaseAdapter getAdapter() {
		return adapter;
	}
	
	@Override
	public AbstractQuery paginate(Paginator pages) {
		adapter.paginate(pages);
		return this;
	}
	
}
