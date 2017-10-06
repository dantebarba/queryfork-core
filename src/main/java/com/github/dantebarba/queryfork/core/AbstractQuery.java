package com.github.dantebarba.queryfork.core;

public abstract class AbstractQuery<T>
		implements WherePhase, OrderPhase, FromPhase, SelectPhase, HasParameter, AbstractBuilder<T> {

	Parameter parameters = new BuiltInParameter();

	HQLString query = new HQLString();

	@Override
	public HQLString getQuery() {
		return query;
	}

	public AbstractQuery<T> count() {
		this.count("*");
		return this;
	}

	public AbstractQuery<T> select(String select) {
		this.getQuery().select(select);
		return this;
	}

	public AbstractQuery<T> from(String... from) {
		this.getQuery().from(from);
		return this;
	}

	public AbstractQuery<T> count(String count) {
		this.getQuery().count(count);
		return this;
	}

	@Override
	public AbstractQuery<T> where(WherePhase subQuery) {
		this.mergeParameters(subQuery);
		this.getQuery().where(subQuery.getQuery().getHql());
		return this;
	}

	/**
	 * Non-Optional where helper, to pass Strings.
	 */
	public AbstractQuery<T> where(String subQuery) {
		this.where(new SubQuery(subQuery).build());
		return this;
	}

	@Override
	public AbstractQuery<T> and(String subQuery) {
		this.getQuery().and(subQuery);
		return this;
	}

	@Override
	public AbstractQuery<T> or(String subQuery) {
		this.getQuery().or(subQuery);
		return this;
	}

	@Override
	public AbstractQuery<T> in(String parameter, Object inList) {
		this.in(new SubQuery("").parameter(parameter, inList).build());
		return this;
	}

	@Override
	public AbstractQuery<T> in(WherePhase subQuery) {
		this.mergeParameters(subQuery);
		this.getQuery().in(subQuery.getQuery().getHql());
		return this;
	}

	@Override
	public AbstractQuery<T> and(WherePhase subQuery) {
		this.mergeParameters(subQuery);
		return this.and(subQuery.getQuery().getHql());
	}

	@Override
	public AbstractQuery<T> or(WherePhase subQuery) {
		this.mergeParameters(subQuery);
		return this.or(subQuery.getQuery().getHql());
	}

	@Override
	public AbstractQuery<T> parameter(String paramKey) {
		this.getQuery().parameter(paramKey);
		this.parameters.setParameter(paramKey);
		return this;
	}

	@Override
	public Parameter<Object, Object> getParameters() {
		return this.parameters;
	}

	@Override
	public Parameter<Object, Object> mergeParameters(HasParameter subQuery) {
		for (Object key : subQuery.getParameters().iterate()) {
			this.getParameters().setParameter(key, subQuery.getParameters().getParameter(key));
		}
		return this.getParameters();
	}

	@Override
	public AbstractQuery<T> parameter(String paramKey, Object paramValue) {
		this.parameter(paramKey);
		this.parameters.setParameter(paramKey, paramValue);
		return this;
	}

	public AbstractQuery<T> subQuery(WherePhase subQuery) {
		this.mergeParameters(subQuery);
		this.getQuery().subQuery(subQuery.getQuery().getHql());
		return this;
	}

	@Override
	public AbstractQuery<T> orderBy(String field, Order order) {
		this.getQuery().orderBy(field, order);
		return this;
	}

	/**
	 * Orden descendiente por defecto {@link Order}
	 */
	@Override
	public AbstractQuery<T> orderBy(String field) {
		return this.orderBy(field, Order.DESC);
	}

	@Override
	public abstract T build();

}
