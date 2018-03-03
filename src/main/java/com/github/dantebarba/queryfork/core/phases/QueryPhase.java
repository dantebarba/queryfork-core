package com.github.dantebarba.queryfork.core.phases;

import com.github.dantebarba.queryfork.core.builders.Builder;
import com.github.dantebarba.queryfork.core.queries.IsQueriable;

/**
 * All queries on HQL can be reduced to a String representative
 * form. This method ensures all QueryPhases are compatible with
 * the HQL standard. At the moment, Criteria Query are not supported, and
 * are out of the propose of this small library.
 * @author dantebarba
 *
 */
public interface QueryPhase<T> extends Builder<T>, HasPhase, HasParameter, IsQueriable {

}
