package depavlo.walker.util;

import java.util.Set;

import lombok.Getter;

// TODO: Auto-generated Javadoc
/**
 * The Enum Step Set Type.
 * 
 * @author Pavlo Degtyaryev
 */
public enum StepSetType {

	/** The orthogonal step set. */
	ORTHOGONAL(Step.ORTHOGONAL),

	/** The octagon step set. */
	OCTAGON(Step.OCTAGON),

	/** The chess step set. */
	CHESS(Step.CHESS);

	/**
	 * Gets the steps set.
	 *
	 * @return the steps
	 */
	@Getter
	private Set<Step> steps;

	/**
	 * Instantiates a new step set type.
	 *
	 * @param steps the steps
	 */
	private StepSetType(Set<Step> steps) {
		this.steps = steps;
	}

}
