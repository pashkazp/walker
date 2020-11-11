package depavlo.walker.util;

import java.util.Set;

import com.google.common.collect.ImmutableSet;

import lombok.Getter;

public enum Step {
	S("Start"),
	F("Finish"),
	U("Up"),
	UL("Up-Left"),
	L("Left"),
	DL("Down-Left"),
	D("Down"),
	DR("Down-Right"),
	R("Right"),
	UR("Up-Right");

	public static final Step[] oppositeSteps = {
			F, S, D, DR, R, UR, U, UL, L, DL
	};
	public static final int[] stepsPrice = {
			0, 0, 10, 14, 10, 14, 10, 14, 10, 14
	};

	@Getter
	private String directionName;

	@Getter
	private Step oppositeStep;

	private Step(String directionName) {
		this.directionName = directionName;
	}

	public static Step getOppositeStep(Step step) {
		return oppositeSteps[step.ordinal()];
	}

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
