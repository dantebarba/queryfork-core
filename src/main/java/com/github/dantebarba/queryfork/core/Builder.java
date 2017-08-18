package com.github.dantebarba.queryfork.core;

public class Builder extends AbstractQuery<QueryBuilder> {

	QueryBuilder instance = new QueryBuilder();
	/**
	 * TODO: Esto debería ser modificado a una QueryPhase.
	 */
	private StringBuilder select = new StringBuilder();
	private StringBuilder from = new StringBuilder();
	private StringBuilder where = new StringBuilder().append(" where 1=1");

	Parameter<String, Object> parameters = new BuiltInParameter();
	HQLString query = new HQLString();

	@Override
	public QueryBuilder build() {
		this.getQuery();
		this.instance.adapter.createQuery();
		this.instance.adapter.queryParameters(this.parameters);
		return this.instance;
	}

	public Builder select(String fields) {
		if (fields.isEmpty()) {
			fields = " * ";
		}
		this.select.insert(0, "select " + fields);
		return this;
	}

	public Builder count() {
		this.select.insert(0, "select count(*)");
		return this;
	}

	public Builder from(String... from) {
		String COMMA_SEPARATOR = "";
		StringBuilder entities = new StringBuilder();
		if (from.length > 0) {
			for (String anEntity : from) {
				entities.append(anEntity + COMMA_SEPARATOR);
				COMMA_SEPARATOR = ",";
			}
		}
		this.from.insert(0, " from " + entities.toString());
		return this;

	}

	public Builder where(String where) {
		this.where.replace(0, this.where.length(), " where " + where);
		return this;
	}

	@Override
	public Builder parameter(String paramKey) {
		this.where.append(":" + paramKey);
		this.parameters.setParameter(paramKey);
		return this;
	}

	@Override
	public Builder parameter(String paramKey, Object paramValue) {
		if (!this.parameters.hasParameter(paramKey)) {
			this.parameter(paramKey);
		}
		this.parameters.setParameter(paramKey, paramValue);
		return this;
	}

	public Builder limit(Paginator pages) {
		this.instance.adapter.paginate(pages);
		return this;
	}

	public Builder subQuery(QueryPhase subQuery) {
		this.where.append(subQuery);
		return this;
	}

	@Override
	public HQLString getQuery() {
		this.query.setHql(select.toString() + from.toString()
				+ where.toString());
		return this.query;
	}
}
