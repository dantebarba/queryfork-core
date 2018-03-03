package com.github.dantebarba.queryfork.core.phases;

public interface HasPhase {

	QueryPhase nextPhase();
	
	QueryPhase previousPhase();
	
}
