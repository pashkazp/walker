package depavlo.walker.service.model;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepSetType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WalkWayFinderTask {
	private final IArea area;
	private final Point start;
	private final Point finish;
	private final IShape shape;
	private final StepSetType stepSet;
}
