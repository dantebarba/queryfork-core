package com.github.dantebarba.queryfork.core;

public interface FromPhase extends QueryPhase {

	AbstractQuery from(String... from);
	
}
