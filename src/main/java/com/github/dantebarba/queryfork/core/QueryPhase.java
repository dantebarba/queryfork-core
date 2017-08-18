package com.github.dantebarba.queryfork.core;

public interface QueryPhase {

	HQLString getQuery();

	Parameter getParameters();

	Parameter mergeParameters(AbstractQuery subQuery);

	AbstractQuery parameter(String paramKey, Object paramValue);

	AbstractQuery parameter(String paramKey);

	AbstractQuery or(AbstractQuery<QueryPhase> subQuery);

	AbstractQuery and(AbstractQuery<QueryPhase> subQuery);

	AbstractQuery in(String parameter, Object inList);

	AbstractQuery or(String subQuery);

	AbstractQuery and(String subQuery);

	AbstractQuery where(AbstractQuery<QueryPhase> subQuery);

}
