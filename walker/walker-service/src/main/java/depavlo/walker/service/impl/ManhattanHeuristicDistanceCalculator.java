package depavlo.walker.service.impl;

import static java.lang.Math.abs;

import org.springframework.stereotype.Component;

import depavlo.walker.service.HeuristicDistance;
import depavlo.walker.service.IArea;
import depavlo.walker.util.Step;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@ToString
@EqualsAndHashCode
public class ManhattanHeuristicDistanceCalculator implements HeuristicDistance {

	@Override
	public int getDistance(IArea area, int startY, int startX, int targetY, int targetX) {
		if (startY < 0 || startY >= area.getRowsCount() || startX < 0 || startX >= area.getColsCount() || targetY < 0
				|| targetY >= area.getRowsCount() || targetX < 0 || targetX >= area.getColsCount()) {
			throw new IndexOutOfBoundsException(
					"The coordinates of the start and target points must be within the boundaries of the Area.");
		}
		return abs(startY - targetY) * Step.getStepPrice(Step.D) + abs(startX - targetX) * Step.getStepPrice(Step.R);
	}

}
