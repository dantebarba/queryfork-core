package com.github.dantebarba.queryfork.core.phases.concrete;

import java.util.ArrayList;

public class EmptyParameter implements Parameter {

	@Override
	public boolean setParameter(Object key, Object value) {
		throw new IllegalStateException("No parameter encapsulation defined");
	}

	@Override
	public boolean setParameter(Object key) {
		throw new IllegalStateException("No parameter encapsulation defined");
	}

	@Override
	public Object getParameter(Object key) {
		throw new IllegalStateException("No parameter encapsulation defined");
	}

	@Override
	public boolean hasParameter(Object key) {
		throw new IllegalStateException("No parameter encapsulation defined");
	}

	@Override
	public Iterable iterate() {
		return new ArrayList();
	}

	@Override
	public Parameter merge(Parameter parameter) {
		throw new IllegalStateException("No parameter encapsulation defined");
	}

}
