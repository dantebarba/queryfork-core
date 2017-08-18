package com.github.dantebarba.queryfork.core;


public class SubQuery extends AbstractQuery<SubQuery> implements QueryPhase {

	private StringBuilder where = new StringBuilder();
	Parameter<String, Object> parameters = new BuiltInParameter();

	public SubQuery(String start) {
		this.where.append("(");
		this.where.append(start);
	}

	@Override
	public SubQuery build() {
		where.append(")");
		return this;
	}

	@Override
	public HQLString getQuery() {
		return new HQLString(this.where.toString());
	}

}
