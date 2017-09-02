package com.github.dantebarba.queryfork.core.adapters;

import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public interface HasAdapter {

	HasAdapter createQuery(HQLString query2);
 
	DatabaseAdapter getAdapter();
	
	HasAdapter queryParameters(HasParameter query);
	
}
