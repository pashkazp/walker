package depavlo.walker.service.model;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

/**
 * The Class WalkWayFinderTask for store state of user task.
 * 
 * @author Pavlo Degtyaryev
 */

@Getter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class WalkWayFinderTask {

	/** The area. current IArea */
	private final IArea area;

	/** The start Point. */
	private final Point start;

	/** The finish Point. */
	private final Point finish;

	/** The current walker shape. */
	private final IShape shape;

	/** The current step type. */
	private final StepType stepSet;
}
