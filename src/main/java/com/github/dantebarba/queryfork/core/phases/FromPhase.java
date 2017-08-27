package com.github.dantebarba.queryfork.core.phases;

public interface FromPhase<T> extends QueryPhase<T> {

	WherePhase from(String... from);
	
}
