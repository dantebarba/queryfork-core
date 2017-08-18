package com.github.dantebarba.queryfork.core;

public abstract class AbstractQuery<T> implements QueryPhase,
		AbstractBuilder<T> {

	private StringBuilder where = new StringBuilder().append(" where 1=1");
	Parameter<String, Object> parameters = new BuiltInParameter();
	HQLString query = new HQLString();

	@Override
	public HQLString getQuery() {
		return null;
	}

	@Override
	public AbstractQuery<T> where(AbstractQuery<QueryPhase> subQuery) {
		this.mergeParameters(subQuery);
		this.where.replace(0, this.where.length(),
				" where " + subQuery.getQuery());
		return this;
	}

	@Override
	public AbstractQuery<T> and(String subQuery) {
		this.where.append(" and " + subQuery);
		return this;
	}

	@Override
	public AbstractQuery<T> or(String subQuery) {
		this.where.append(" or " + subQuery);
		return this;
	}

	@Override
	public AbstractQuery<T> in(String parameter, Object inList) {
		this.where.append(" in ");
		this.parameter(parameter, inList);
		return this;
	}

	@Override
	public AbstractQuery<T> and(AbstractQuery<QueryPhase> subQuery) {
		subQuery.build();
		this.mergeParameters(subQuery);
		return this.and(subQuery.getQuery().toString());
	}

	@Override
	public AbstractQuery<T> or(AbstractQuery<QueryPhase> subQuery) {
		this.mergeParameters(subQuery);
		return this.or(subQuery.getQuery().toString());
	}

	@Override
	public AbstractQuery<T> parameter(String paramKey) {
		this.where.append(":" + paramKey);
		this.parameters.setParameter(paramKey);
		return this;
	}

	@Override
	public Parameter getParameters() {
		return this.parameters;
	}

	@Override
	public Parameter mergeParameters(AbstractQuery subQuery) {
		for (Object key : subQuery.getParameters().iterate()) {
			this.getParameters().setParameter(key,
					subQuery.getParameters().getParameter(key));
		}
		return this.getParameters();
	}

	@Override
	public AbstractQuery parameter(String paramKey, Object paramValue) {
		this.parameters.setParameter(paramKey, paramValue);
		return this;
	}

	@Override
	public abstract T build();

}
