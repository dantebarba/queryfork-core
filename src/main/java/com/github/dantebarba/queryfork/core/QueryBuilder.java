package com.github.dantebarba.queryfork.core;


public class QueryBuilder {

	DatabaseAdapter adapter = new EmptyAdapter();

	HQLString query = new HQLString();

	public DatabaseAdapter getAdapter() {
		return adapter;
	}
	
	
	
}
