package com.github.dantebarba.queryfork.core.phases.concrete;

import java.util.HashMap;
import java.util.Map;

public class BuiltInParameter implements Parameter<String, Object> {

	Map<String, Object> parameterMap = new HashMap<String, Object>();

	@Override
	public boolean setParameter(String key, Object value) {
		parameterMap.put(key, value);
		return true;
	}

	@Override
	public boolean setParameter(String key) {
		parameterMap.put(key, null);
		return true;
	}

	@Override
	public Object getParameter(String key) {
		return parameterMap.get(key);
	}

	@Override
	public boolean hasParameter(String key) {
		return parameterMap.containsKey(key) && parameterMap.get(key) != null;
	}

	@Override
	public Iterable<String> iterate() {
		return this.parameterMap.keySet();
	}
	
	@Override
	public Parameter merge(Parameter parameter) {
		for (Object key : parameter.iterate()) {
			this.setParameter((String) key, parameter.getParameter(key));
		}
		return this;
	}

}
