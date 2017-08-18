package com.github.dantebarba.queryfork.core;

import java.util.List;

public class EmptyAdapter implements DatabaseAdapter {

	@Override
	public List getResultList() {
		return null;
	}

	@Override
	public List getSingleResult() {
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
