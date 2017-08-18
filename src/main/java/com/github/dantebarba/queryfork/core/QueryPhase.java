package com.github.dantebarba.queryfork.core;

/**
 * All queries on HQL can be reduced to a String representative
 * form. This method ensures all QueryPhases are compatible with
 * the HQL standard. At the moment, Criteria Query are not supported, and
 * are out of the propose of this small library.
 * @author dantebarba
 *
 */
public interface QueryPhase {

	HQLString getQuery();

}
