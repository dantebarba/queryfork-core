package com.github.dantebarba.queryfork.core.phases.concrete;

import java.util.List;

import com.github.dantebarba.queryfork.core.helpers.ParameterHelper;
import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.QueryPhase;
import com.github.dantebarba.queryfork.core.phases.SubQueryPhase;
import com.github.dantebarba.queryfork.core.queries.AbstractQuery;
import com.github.dantebarba.queryfork.core.queries.Query;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public class SubQuery implements SubQueryPhase<AbstractQuery> {

	SubQuery thisIsMe = null;

	private ParameterHelper<SubQuery, AbstractQuery> helper = new ParameterHelper<SubQuery, AbstractQuery>() {

		@Override
		public HQLString getQuery() {
			return thisIsMe.getQuery();
		}

		@Override
		public SubQuery getHelpedPhase() {
			return thisIsMe;
		}

	};

	private HQLString privateQuery = new HQLString();

	public SubQuery() {
		this("");
	}

	public SubQuery(String start) {
		this.thisIsMe = this;
		this.getQuery().subQuery(start);
	}

	@Override
	public AbstractQuery build() {
		this.getQuery().end();
		AbstractQuery subQuery = new Query();
		subQuery.mergeParameters(this);
		subQuery.getQuery().prepend(this.getQuery());
		return subQuery;
	}

	@Override
	public HQLString getQuery() {
		return this.privateQuery;
	}

	@Override
	public QueryPhase<AbstractQuery> nextPhase() {
		return null;
	}

	@Override
	public QueryPhase<AbstractQuery> previousPhase() {
		return null;
	}

	@Override
	public Parameter getParameters() {
		return helper.getParameters();
	}

	@Override
	public Parameter mergeParameters(HasParameter subQuery) {
		return helper.mergeParameters(subQuery);
	}

	@Override
	public SubQuery or(AbstractQuery subQuery) {
		this.privateQuery.or(subQuery.getQuery().getHql());
		return this;
	}

	@Override
	public SubQuery and(AbstractQuery subQuery) {
		this.privateQuery.and(subQuery.getQuery().getHql());
		return this;
	}

	@Override
	public SubQuery in(AbstractQuery subQuery) {
		this.privateQuery.in(subQuery.getQuery().getHql());
		this.mergeParameters(subQuery);
		return this;
	}

	@Override
	public SubQuery or(String subQuery) {
		this.privateQuery.or(subQuery);
		return this;
	}

	@Override
	public SubQuery and(String subQuery) {
		this.privateQuery.and(subQuery);
		return this;
	}

	@Override
	public SubQuery eq(String subQuery) {
		this.privateQuery.eq(subQuery);
		return this;
	}

	@Override
	public SubQuery eq() {
		this.privateQuery.eq("");
		return this;
	}

	@Override
	public SubQuery parameter(String paramKey, Object paramValue) {
		return helper.parameter(paramKey, paramValue);
	}

	@Override
	public SubQuery parameter(String paramKey) {
		return helper.parameter(paramKey);
	}

	@Override
	public SubQuery in(String string, List asList) {
		this.in(new SubQuery().parameter(string, asList).build());
		return this;
	}

}
