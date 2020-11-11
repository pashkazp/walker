package depavlo.walker.util;

import java.util.Set;

import lombok.Getter;

public enum StepSetType {
	ORTHOGONAL(Step.ORTHOGONAL),
	OCTAGON(Step.OCTAGON),
	CHESS(Step.CHESS);

	@Getter
	private Set<Step> steps;

	private StepSetType(Set<Step> steps) {
		this.steps = steps;
	}

}
