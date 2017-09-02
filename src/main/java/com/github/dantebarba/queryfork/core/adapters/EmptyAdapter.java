package com.github.dantebarba.queryfork.core.adapters;

import java.util.List;

import org.apache.log4j.Logger;

import com.github.dantebarba.queryfork.core.paginators.Paginator;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class EmptyAdapter implements DatabaseAdapter {

	Logger logger = Logger.getLogger(EmptyAdapter.class);
	
	@Override
	public List getResultList() {
		throw new IllegalStateException("No adapter representation defined");
	}

	@Override
	public Object getSingleResult() {
		throw new IllegalStateException("No adapter representation defined");
	}

	@Override
	public int count() {
		throw new IllegalStateException("No adapter representation defined");
	}

	@Override
	public void paginate(Paginator paginator) {
		logger.warn("WARNING: No adapter defined. Please define an adapter for your desired query engine");
	}

	@Override
	public void queryParameters(Parameter parameters) {
		logger.warn("WARNING: No adapter defined. Please define an adapter for your desired query engine");
	}

	@Override
	public void createQuery(HQLString query) {
		logger.warn("WARNING: No adapter defined. Please define an adapter for your desired query engine");
	}

}
