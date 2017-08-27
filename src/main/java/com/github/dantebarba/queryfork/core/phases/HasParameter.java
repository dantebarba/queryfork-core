package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;

public interface HasParameter {
	Parameter getParameters();

	/**
	 * Combines the parent and subquerie parameter.
	 * @param subQuery
	 * @return
	 */
	Parameter mergeParameters(HasParameter subQuery);


		
}
