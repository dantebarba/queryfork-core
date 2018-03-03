package com.github.dantebarba.queryfork.core.phases;

public interface FromPhase<T> extends SelectPhase<T>, QueryPhase<T> {

	JoinPhase<T> from(String... from);
	
}
