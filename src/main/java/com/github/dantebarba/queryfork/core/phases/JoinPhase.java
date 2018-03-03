package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.helpers.Alias;

public interface JoinPhase<T> extends SelectPhase<T>, FromPhase<T>, WherePhase<T>, QueryPhase<T> {

	JoinPhase<T> join(String join);
	
	JoinPhase<T> join(Alias anAlias, String path);
	
	JoinPhase<T> leftJoin(Alias anAlias, String path);
	
	JoinPhase<T> leftJoin(String join);

	
}
