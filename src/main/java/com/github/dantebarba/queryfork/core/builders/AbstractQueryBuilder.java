package com.github.dantebarba.queryfork.core.builders;

import com.github.dantebarba.queryfork.core.exceptions.EndOfQueryException;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.IsQuery;
import com.github.dantebarba.queryfork.core.phases.QueryPhase;
import com.github.dantebarba.queryfork.core.phases.SelectPhase;
import com.github.dantebarba.queryfork.core.phases.concrete.BuiltInParameter;
import com.github.dantebarba.queryfork.core.phases.concrete.From;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.phases.concrete.Select;
import com.github.dantebarba.queryfork.core.phases.concrete.Where;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public abstract class AbstractQueryBuilder<T extends IsQuery> implements SelectPhase<T> {

	QueryPhase<T> nextPhase;

	Parameter<String, Object> parameters = new BuiltInParameter();

	@Override
	public HQLString getQuery() {
		return getBuildedQuery().getQuery();
	}

	@Override
	public From<T> count(String count) {
		return new Select<T>(this).count(count);
	}

	public From<T> select(String select) {
		return new Select<T>(this).select(select);
	}

	@Override
	public From<T> select() {
		return this.select("*");
	}

	public Where<T> from(String... from) {
		return new Select<T>(this).select().from(from);
	}

	@Override
	public abstract T build();

	@Override
	public Parameter getParameters() {
		return this.parameters;
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return this.parameters.merge(subQuery.getParameters());
	}

	@Override
	public QueryPhase<T> nextPhase() {
		return this.nextPhase;
	}

	@Override
	public QueryPhase<T> previousPhase() {
		throw new EndOfQueryException();
	}
	
	public abstract IsQuery getBuildedQuery();
}
