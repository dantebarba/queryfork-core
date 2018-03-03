package com.github.dantebarba.queryfork.core.helpers;

public class Alias {

	private String path = "";
	private String alias = "";
	int depth = 0;

	public Alias join(Alias otherAlias, String entityPath) {
		return this.join(otherAlias, entityPath, depth);
	}

	/**
	 * Generates a join alias with a specified path and depth. The alias is
	 * automatically generated with the following format: entityPath_depth For
	 * example, if I wish to join an entity with the following alias: "company"
	 * with it's collection of employees "employees" the entityPath would be
	 * "employees" and the alias represented in parameter otherAlias would be
	 * company.
	 * 
	 * @param otherAlias
	 * @param entityPath
	 * @param depth
	 * @return
	 */
	public Alias join(Alias otherAlias, String entityPath, int depth) {
		this.depth = depth;
		this.path = otherAlias.getAlias() + "." + entityPath + " ";
		this.alias = entityPath + "_" + depth;
		return this;
	}

	public String getAlias() {
		return alias;
	}

	@Override
	public boolean equals(Object anObject) {
		return this.getAlias().equals(((Alias) anObject).getAlias());
	}

	public String getFullPath() {
		return this.path + this.alias;
	}

}
