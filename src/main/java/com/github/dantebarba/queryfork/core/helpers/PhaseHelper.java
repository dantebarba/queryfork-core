package com.github.dantebarba.queryfork.core.helpers;

import com.github.dantebarba.queryfork.core.phases.QueryPhase;

public abstract class PhaseHelper<T> {

	public QueryPhase<T> nextPhase() {
		return hasNextPhase() ? getNextPhase() : createNextPhase();
	}
	
	public boolean hasNextPhase() {
		return this.getNextPhase() != null;
	}

	public abstract QueryPhase<T> getNextPhase();
	
	public abstract QueryPhase<T> createNextPhase();
	
}
