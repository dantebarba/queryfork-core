package com.github.dantebarba.queryfork.core;

import java.util.List;

public interface DatabaseAdapter {
	
	List getResultList();
	
	List getSingleResult();
	
	int count();
	
	void paginate(Paginator paginator);
	
	void queryParameters(Parameter parameters);

	AbstractQuery createQuery();
	
	
	
}
