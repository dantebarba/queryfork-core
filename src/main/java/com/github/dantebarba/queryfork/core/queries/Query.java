package com.github.dantebarba.queryfork.core.queries;

import com.github.dantebarba.queryfork.core.adapters.DatabaseAdapter;
import com.github.dantebarba.queryfork.core.adapters.EmptyAdapter;
import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

/**
 * The standard implemented Query.
 * This can be easily replaced with your own 
 * implemented query with your own implemented adapter
 * 
 * @author dantebarba
 *
 */
public class Query implements IsQuery {

	DatabaseAdapter adapter = new EmptyAdapter();

	HQLString query = new HQLString();

	@Override
	public DatabaseAdapter getAdapter() {
		return adapter;
	}

	@Override
	public HQLString getQuery() {
		return query;
	}
	
	@Override
	public Query paginate(Paginator pages) {
		adapter.paginate(pages);
		return this;
	}
	
	@Override
	public Query parameters(Parameter params) {
		this.adapter.queryParameters(params);
		return this;
	}

	@Override
	public Query createQuery(HQLString query2) {
		adapter.createQuery(query2);
		return this;
	}
	
	
	
}
