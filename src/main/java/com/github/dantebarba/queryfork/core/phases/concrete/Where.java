package com.github.dantebarba.queryfork.core.phases.concrete;

import com.github.dantebarba.queryfork.core.helpers.PhaseHelper;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.QueryPhase;
import com.github.dantebarba.queryfork.core.phases.SelectPhase;
import com.github.dantebarba.queryfork.core.phases.WherePhase;
import com.github.dantebarba.queryfork.core.queries.Query;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class Where<T extends IsQuery> extends PhaseHelper<T> implements SelectPhase<T>, WherePhase<T> {

	private HQLString privateQuery = new HQLString();
	private From<T> previousPhase;
	private Order<T> nextPhase;

	public Where(From<T> from) {
		this.previousPhase = from;
	}
	
	@Override
	public HQLString getQuery() {
		return this.previousPhase().getQuery();
	}

	@Override
	public Order<T> where(Query start) {
		this.privateQuery.where(start.getQuery().getHql());
		return (Order<T>) nextPhase();
	}

	@Override
	public T build() {
		this.getQuery().prepend(privateQuery);
		return this.previousPhase().build();
	}

	@Override
	public From<T> previousPhase() {
		return this.previousPhase;
	}

	@Override
	public QueryPhase<T> count(String count) {
		return this.previousPhase().count(count);
	}

	@Override
	public Order<T> getNextPhase() {
		return this.nextPhase;
	}

	@Override
	public Order<T> createNextPhase() {
		this.nextPhase = new Order<T>(this);
		return this.getNextPhase();
	}

	@Override
	public Parameter getParameters() {
		return this.previousPhase().getParameters();
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return this.getParameters().merge(subQuery.getParameters());
	}

	@Override
	public From<T> select(String select) {
		return this.previousPhase().select(select);
	}

	@Override
	public From<T> select() {
		return this.previousPhase().select();
	}

}
