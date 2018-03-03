package com.github.dantebarba.queryfork.core.paginators;

import com.github.dantebarba.queryfork.core.phases.IsQuery;

public interface HasPaginator {

	IsQuery paginate(Paginator paginator);
	
}
