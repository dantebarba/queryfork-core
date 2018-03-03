package com.github.dantebarba.queryfork.core.queries;

import com.github.dantebarba.queryfork.core.adapters.DatabaseAdapter;
import com.github.dantebarba.queryfork.core.adapters.EmptyAdapter;
import com.github.dantebarba.queryfork.core.adapters.HasAdapter;
import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.concrete.EmptyParameter;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

/**
 * The standard implemented Query. This can be easily replaced with your own
 * implemented query with your own implemented adapter
 * 
 * @author dantebarba
 *
 */
public abstract class AbstractQuery implements IsQuery {

	DatabaseAdapter adapter = new EmptyAdapter();

	Parameter params = new EmptyParameter();

	HQLString query = new HQLString();

	@Override
	public abstract DatabaseAdapter getAdapter();

	@Override
	public HQLString getQuery() {
		return query;
	}

	@Override
	public abstract AbstractQuery paginate(Paginator pages);

	@Override
	public Parameter mergeParameters(HasParameter query) {
		this.params = query.getParameters();
		return this.params;
	}

	@Override
	public abstract AbstractQuery createQuery(HQLString query2);

	@Override
	public Parameter getParameters() {
		return this.params;
	}

	@Override
	public abstract HasAdapter queryParameters(HasParameter query);

}
