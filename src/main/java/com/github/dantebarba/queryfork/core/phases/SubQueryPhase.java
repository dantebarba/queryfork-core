package com.github.dantebarba.queryfork.core.phases;

import java.util.List;

import com.github.dantebarba.queryfork.core.queries.AbstractQuery;

public interface SubQueryPhase<T> extends ParameterPhase<T> {
	
	SubQueryPhase<T> or(AbstractQuery subQuery);

	SubQueryPhase<T> and(AbstractQuery subQuery);

	SubQueryPhase<T> in(AbstractQuery subQuery);

	SubQueryPhase<T> or(String subQuery);

	SubQueryPhase<T> and(String subQuery);

	SubQueryPhase<T> eq(String subQuery);

	SubQueryPhase<T> eq();

	SubQueryPhase<T> in(String string, List asList);

}
