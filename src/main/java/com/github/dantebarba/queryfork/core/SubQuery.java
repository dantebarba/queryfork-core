package com.github.dantebarba.queryfork.core;


public class SubQuery extends AbstractQuery<SubQuery> {

	public SubQuery(String start) {
		this.getQuery().subQuery(start);
	}

	@Override
	public SubQuery build() {
		this.getQuery().end();
		return this;
	}




}
