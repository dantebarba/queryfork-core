package com.github.dantebarba.queryfork.core.phases.concrete;

import com.github.dantebarba.queryfork.core.builders.Builder;
import com.github.dantebarba.queryfork.core.helpers.Alias;
import com.github.dantebarba.queryfork.core.helpers.PhaseHelper;
import com.github.dantebarba.queryfork.core.phases.FromPhase;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.JoinPhase;
import com.github.dantebarba.queryfork.core.phases.OrderingPhase;
import com.github.dantebarba.queryfork.core.phases.QueryPhase;
import com.github.dantebarba.queryfork.core.phases.WherePhase;
import com.github.dantebarba.queryfork.core.queries.AbstractQuery;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class Join<T extends IsQuery> extends PhaseHelper<T> implements Builder<T>, JoinPhase<T> {

	private JoinPhase<T> nextPhase;
	private FromPhase<T> previousPhase;
	private HQLString privateQuery;

	public Join(From<T> from) {
		this.previousPhase = from;
	}

	public Join(Join<T> join) {
		this.previousPhase = join;
	}

	@Override
	public T build() {
		this.getQuery().prepend(privateQuery);
		return this.previousPhase().build();
	}

	@Override
	public JoinPhase<T> nextPhase() {
		return this.nextPhase;
	}

	@Override
	public FromPhase<T> previousPhase() {
		return this.previousPhase;
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
	public HQLString getQuery() {
		return this.previousPhase().getQuery();
	}

	@Override
	public QueryPhase<T> getNextPhase() {
		return this.nextPhase;
	}

	@Override
	public QueryPhase<T> createNextPhase() {
		this.nextPhase = new Join<T>(this);
		return this.getNextPhase();
	}

	@Override
	public QueryPhase<T> count(String count) {
		return this.previousPhase().count(count);
	}

	@Override
	public JoinPhase<T> from(String... from) {
		return this.previousPhase().from(from);
	}

	@Override
	public FromPhase<T> select(String select) {
		return this.previousPhase().select(select);
	}

	@Override
	public FromPhase<T> select(Class clazz) {
		return this.previousPhase().select(clazz);
	}

	@Override
	public JoinPhase<T> join(String join) {
		return null;
	}

	@Override
	public JoinPhase<T> join(Alias alias, String path) {
		return null;
	}

	@Override
	public JoinPhase<T> leftJoin(String join) {
		return null;
	}

	@Override
	public FromPhase distinct() {
		return null;
	}

	@Override
	public JoinPhase<T> leftJoin(Alias anAlias, String path) {
		return null;
	}

	@Override
	public OrderingPhase<T> where(AbstractQuery start) {
		return this.nextPhase().where(start);
	}

}
