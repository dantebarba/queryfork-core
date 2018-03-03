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
	private Join<T> nextPhase;

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
	public Join<T> from(String... from) {
		this.privateQuery.from(from);
		return (Join<T>) nextPhase();
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
	public FromPhase<T> select(Class clazz) {
		return this.previousPhase.select(clazz);
	}

	@Override
	public Join<T> getNextPhase() {
		return this.nextPhase;
	}

	@Override
	public Join<T> createNextPhase() {
		this.nextPhase = new Join<T>(this);
		return this.getNextPhase();
	}

	@Override
	public Parameter getParameters() {
		return this.previousPhase().getParameters();
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return this.nextPhase().mergeParameters(subQuery);
	}

	@Override
	public FromPhase distinct() {
		return this.previousPhase.distinct();
	}

}
