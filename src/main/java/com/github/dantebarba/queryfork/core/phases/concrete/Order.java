package com.github.dantebarba.queryfork.core.phases.concrete;

import com.github.dantebarba.queryfork.core.exceptions.EndOfQueryException;
import com.github.dantebarba.queryfork.core.helpers.OrderCriteria;
import com.github.dantebarba.queryfork.core.helpers.PhaseHelper;
import com.github.dantebarba.queryfork.core.phases.FromPhase;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.OrderingPhase;
import com.github.dantebarba.queryfork.core.phases.QueryPhase;
import com.github.dantebarba.queryfork.core.phases.SelectPhase;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class Order<T extends IsQuery> extends PhaseHelper<T> implements SelectPhase<T>, OrderingPhase<T> {

	private HQLString privateQuery = new HQLString();

	private Where<T> previousPhase;

	public Order(Where<T> where) {
		previousPhase = where;
	}

	@Override
	public T build() {
		this.getQuery().prepend(privateQuery);
		return this.previousPhase().build();
	}

	@Override
	public QueryPhase<T> count(String count) {
		return this.previousPhase().count(count);
	}

	@Override
	public HQLString getQuery() {
		return this.previousPhase().getQuery();
	}

	@Override
	public Order<T> orderBy(String attribute) {
		this.privateQuery.orderBy(attribute, OrderCriteria.getDefault());
		return this;
	}

	@Override
	public Order<T> orderBy(String attribute, OrderCriteria criteria) {
		this.privateQuery.orderBy(attribute, criteria);
		return this;
	}

	@Override
	public Parameter getParameters() {
		return null;
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return null;
	}

	@Override
	public QueryPhase<T> getNextPhase() {
		throw new EndOfQueryException();
	}

	@Override
	public QueryPhase<T> createNextPhase() {
		throw new EndOfQueryException();
	}

	@Override
	public Where<T> previousPhase() {
		return this.previousPhase;
	}

	@Override
	public FromPhase<T> select(String select) {
		return this.previousPhase().select();
	}

	@Override
	public FromPhase<T> select(Class clazz) {
		return this.previousPhase().select(clazz);
	}

	@Override
	public FromPhase distinct() {
		return this.previousPhase().distinct();
	}

}
