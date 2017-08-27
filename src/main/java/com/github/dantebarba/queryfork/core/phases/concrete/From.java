package com.github.dantebarba.queryfork.core.phases.concrete;

import com.github.dantebarba.queryfork.core.builders.Builder;
import com.github.dantebarba.queryfork.core.helpers.PhaseHelper;
import com.github.dantebarba.queryfork.core.phases.FromPhase;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.SelectPhase;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class From<T extends IsQuery> extends PhaseHelper<T> implements SelectPhase<T>, FromPhase<T>, Builder<T> {

	private Select<T> previousPhase;
	private HQLString privateQuery = new HQLString();
	private Where<T> nextPhase;

	public From(Select<T> select) {
		this.previousPhase = select;
	}
	
	@Override
	public HQLString getQuery() {
		return this.previousPhase().getQuery();
	}

	@Override
	public Select<T> previousPhase() {
		return this.previousPhase;
	}

	@Override
	public Where<T> from(String... from) {
		this.privateQuery.from(from);
		return (Where<T>) nextPhase();
	}

	@Override
	public T build() {
		this.getQuery().prepend(privateQuery);
		return this.previousPhase().build();
	}

	@Override
	public From<T> count(String count) {
		return this.previousPhase().count(count);
	}

	@Override
	public From<T> select(String select) {
		return this.previousPhase().select(select);
	}

	@Override
	public From<T> select() {
		return this.previousPhase.select();
	}

	@Override
	public Where<T> getNextPhase() {
		return this.nextPhase;
	}

	@Override
	public Where<T> createNextPhase() {
		this.nextPhase = new Where<T>(this);
		return this.getNextPhase();
	}

	@Override
	public Parameter getParameters() {
		return this.nextPhase().getParameters();
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return this.nextPhase().mergeParameters(subQuery);
	}

}
