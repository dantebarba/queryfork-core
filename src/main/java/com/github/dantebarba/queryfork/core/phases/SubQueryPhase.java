package com.github.dantebarba.queryfork.core.phases;

import java.util.List;

import com.github.dantebarba.queryfork.core.queries.Query;

public interface SubQueryPhase<T> extends ParameterPhase<T> {
	
	SubQueryPhase<T> or(Query subQuery);

	SubQueryPhase<T> and(Query subQuery);

	SubQueryPhase<T> in(Query subQuery);

	SubQueryPhase<T> or(String subQuery);

	SubQueryPhase<T> and(String subQuery);

	SubQueryPhase<T> eq(String subQuery);

	SubQueryPhase<T> eq();

	SubQueryPhase<T> in(String string, List asList);

}
