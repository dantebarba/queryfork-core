package com.github.dantebarba.queryfork.core.adapters;

import java.util.List;

import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class EmptyAdapter implements DatabaseAdapter {

	@Override
	public List getResultList() {
		return null;
	}

	@Override
	public Object getSingleResult() {
		return null;
	}

	@Override
	public int count() {
		return 0;
	}

	@Override
	public void paginate(Paginator paginator) {

	}

	@Override
	public void queryParameters(Parameter parameters) {

	}

	@Override
	public void createQuery(HQLString query) {

	}

}
