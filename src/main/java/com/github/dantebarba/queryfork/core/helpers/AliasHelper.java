package com.github.dantebarba.queryfork.core.helpers;

import java.util.HashSet;
import java.util.Set;

public class AliasHelper {

	private Set<Alias> aliases = new HashSet<Alias>();
	
	public void add(Alias anAlias) {
		this.aliases.add(anAlias);
	}
	
}
