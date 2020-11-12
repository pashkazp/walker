package depavlo.walker.util;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lombok.Getter;

// TODO: Auto-generated Javadoc
/**
 * The Enum Step. All possible moving directions
 * 
 * @author Pavlo Degtyaryev
 */
public enum Step {

	/** The start. */
	S("Start"),

	/** The finish. */
	F("Finish"),

	/** The up. */
	U("Up"),

	/** The up+left. */
	UL("Up-Left"),

	/** The left. */
	L("Left"),

	/** The down+left. */
	DL("Down-Left"),

	/** The down. */
	D("Down"),

	/** The down+right. */
	DR("Down-Right"),

	/** The right. */
	R("Right"),

	/** The up+right. */
	UR("Up-Right");

	/** The Constant opposite direction Steps . */
	public static final Step[] oppositeSteps = {
			F, S, D, DR, R, UR, U, UL, L, DL
	};

	/** The Constant steps Price. */
	public static final int[] stepsPrice = {
			0, 0, 10, 14, 10, 14, 10, 14, 10, 14
	};

	/**
	 * Gets the direction name.
	 *
	 * @return the direction name
	 */
	@Getter
	private String directionName;

	/**
	 * Gets the opposite step.
	 *
	 * @return the opposite step
	 */
	@Getter
	private Step oppositeStep;

	/**
	 * Instantiates a new step.
	 *
	 * @param directionName the direction name
	 */
	private Step(String directionName) {
		this.directionName = directionName;
	}

	/**
	 * Gets the opposite step.
	 *
	 * @param step the step
	 * @return the opposite step
	 */
	public static Step getOppositeStep(Step step) {
		return oppositeSteps[step.ordinal()];
	}

	/**
	 * Gets the step price.
	 *
	 * @param step the step
	 * @return the step price
	 */
	public static int getStepPrice(Step step) {
		return stepsPrice[step.ordinal()];
	}

	/** The Constant ORTHOGONAL steps set. */
	public static final Set<Step> ORTHOGONAL = ImmutableSet.of(U, L, D, R);

	/** The Constant CHESS steps set. */
	public static final Set<Step> CHESS = ImmutableSet.of(UL, DL, DR, UR);

	/** The Constant OCTAGON steps set. */
	public static final Set<Step> OCTAGON = ImmutableSet.of(U, UL, L, DL, D, DR, R, UR);

}
