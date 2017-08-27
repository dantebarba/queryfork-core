package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.adapters.DatabaseAdapter;
import com.github.dantebarba.queryfork.core.paginators.HasPaginator;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.IsQueriable;
import com.github.dantebarba.queryfork.core.queries.representation.HQLString;

public interface IsQuery extends IsQueriable, HasPaginator {

	IsQuery createQuery(HQLString query2);

	DatabaseAdapter getAdapter();

	IsQuery parameters(Parameter params);

}
