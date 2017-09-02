package com.github.dantebarba.queryfork.core.phases.concrete;

import com.github.dantebarba.queryfork.core.builders.AbstractQueryBuilder;
import com.github.dantebarba.queryfork.core.helpers.PhaseHelper;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.SelectPhase;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class Select<T extends IsQuery> extends PhaseHelper<T> implements SelectPhase<T> {

	HQLString privateQuery = new HQLString();
	private From<T> nextPhase = null;
	private AbstractQueryBuilder<T> previousPhase = null;

	public Select(AbstractQueryBuilder<T> abstractQuery) {
		this.previousPhase = abstractQuery;
	}

	@Override
	public HQLString getQuery() {
		return this.previousPhase().getQuery();
	}

	@Override
	public From<T> count(String count) {
		privateQuery.count(count);
		return (From<T>) this.nextPhase();
	}

	@Override
	public From<T> select(String select) {
		privateQuery.select(select);
		return (From<T>) this.nextPhase();
	}

	@Override
	public From<T> select() {
		return this.select("*");

	}
	
	@Override
	public AbstractQueryBuilder<T> previousPhase() {
		return this.previousPhase;
	}

	@Override
	public T build() {
		this.previousPhase.getQuery().prepend(privateQuery);
		return this.previousPhase.build();
	}

	@Override
	public Parameter getParameters() {
		return this.previousPhase().getParameters();
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return this.previousPhase().mergeParameters(subQuery);
	}

	@Override
	public From<T> getNextPhase() {
		return this.nextPhase;
	}

	@Override
	public From<T> createNextPhase() {
		return new From<T>(this);
	}

}
