package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.adapters.HasAdapter;
import com.github.dantebarba.queryfork.core.paginators.HasPaginator;
import com.github.dantebarba.queryfork.core.queries.IsQueriable;

public interface IsQuery extends IsQueriable, HasPaginator, HasParameter, HasAdapter {

	String getRootAlias();
		
}
