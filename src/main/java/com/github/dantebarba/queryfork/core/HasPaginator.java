package com.github.dantebarba.queryfork.core;

public interface HasPaginator {

	AbstractQuery paginate(Paginator paginator);
	
}
