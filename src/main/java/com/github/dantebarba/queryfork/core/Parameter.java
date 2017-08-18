package com.github.dantebarba.queryfork.core;

public interface Parameter<K, V> {

	boolean setParameter(K key, V value);
	
	boolean setParameter(K key);
	
	V getParameter(K key);
	
	boolean hasParameter(K key);
	
	Iterable<K> iterate();
	
}
