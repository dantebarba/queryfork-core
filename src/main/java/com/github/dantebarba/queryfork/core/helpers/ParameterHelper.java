package com.github.dantebarba.queryfork.core.helpers;

import com.github.dantebarba.queryfork.core.phases.HasParameter;
import com.github.dantebarba.queryfork.core.phases.ParameterPhase;
import com.github.dantebarba.queryfork.core.phases.concrete.BuiltInParameter;
import com.github.dantebarba.queryfork.core.phases.concrete.Parameter;
import com.github.dantebarba.queryfork.core.queries.IsQueriable;

public abstract class ParameterHelper<V extends ParameterPhase<B>, B> extends Helper<V> implements IsQueriable {

	private Parameter parameters = new BuiltInParameter();

	public <T extends ParameterPhase<B>> V parameter(String paramKey) {
		getHelpedPhase().getQuery().parameter(paramKey);
		this.getParameters().setParameter(paramKey);
		return getHelpedPhase();
	}

	public Parameter<Object, Object> getParameters() {
		return this.parameters;
	}

	public Parameter<Object, Object> mergeParameters(HasParameter subQuery) {
		return this.getParameters().merge(subQuery.getParameters());
	}

	public <T extends ParameterPhase<B>> V parameter(String paramKey, Object paramValue) {
		this.parameter(paramKey);
		this.getParameters().setParameter(paramKey, paramValue);
		return getHelpedPhase();
	}
	
	

}
