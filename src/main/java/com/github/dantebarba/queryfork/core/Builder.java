package com.github.dantebarba.queryfork.core;

public class Builder extends AbstractQuery<QueryBuilder> implements HasPaginator {

	QueryBuilder instance = new QueryBuilder();
	/**
	 * TODO: Esto debería ser modificado a una QueryPhase.
	 */

	@Override
	public QueryBuilder build() {
		this.instance.adapter.createQuery(this.getQuery());
		this.instance.adapter.queryParameters(this.getParameters());
		return this.instance;
	}

	public Builder paginate(Paginator pages) {
		this.instance.adapter.paginate(pages);
		return this;
	}

}
