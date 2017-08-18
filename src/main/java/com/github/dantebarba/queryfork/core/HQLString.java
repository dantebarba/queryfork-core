package com.github.dantebarba.queryfork.core;

public class HQLString {

	StringBuilder query = new StringBuilder();

	public HQLString(String string) {
		this.query.insert(0, string);
	}

	public HQLString() {
	}

	public String getHql() {
		return this.query.toString();
	}

	public void setHql(String string) {
		this.query.insert(0, string);
	}

	@Override
	public String toString() {
		return this.query.toString();
	}

}
