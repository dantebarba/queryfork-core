package com.github.dantebarba.queryfork.core.queries.representation;

import com.github.dantebarba.queryfork.core.helpers.OrderCriteria;

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
		return this.getHql();
	}

	public void where(String query) {
		this.reset();
		this.query.append(" where " + query);
	}

	private void reset() {
		this.query.setLength(0);
	}

	public void subQuery(String start) {
		this.reset();
		this.query.append(" (" + start);
	}

	public void select(String query) {
		this.reset();
		this.query.append("select " + query);
	}

	public void from(String query) {
		this.query.setLength(0);
		this.query.append(" from " + query);
	}

	public void and(String subQuery) {
		this.query.append(" and " + subQuery);
	}

	public void or(String subQuery) {
		this.query.append(" or " + subQuery);
	}

	public void in() {
		this.query.append(" in ");
	}

	public void parameter(String paramKey) {
		this.query.append(":" + paramKey);
	}

	public void end() {
		this.query.append(") ");
	}

	public void count(String string) {
		this.query.setLength(0);
		this.query.append("select count("+string+") ");
	}

	public void from(String[] from) {
		this.query.setLength(0);
		String COMMA_SEPARATOR = "";
		StringBuilder entities = new StringBuilder();
		if (from.length > 0) {
			for (String anEntity : from) {
				entities.append(anEntity + COMMA_SEPARATOR);
				COMMA_SEPARATOR = ",";
			}
		}
		this.query.append(" from " + entities.toString());
	}

	public HQLString prepend(HQLString privateQuery) {
		this.query.insert(0, privateQuery);
		return this;
	}

	public void orderBy(String attribute, OrderCriteria criteria) {
		reset();
		this.query.insert(0, " order by "+attribute+" "+criteria.toString());
	}

	public void eq(String subQuery) {
		this.query.append(" = "+subQuery);
	}

	public void in(String hql) {
		this.in();
		this.query.append(hql);
	}

}