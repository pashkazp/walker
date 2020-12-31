package depavlo.walker.service;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class Node that represent the tree of the solutions.
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Node {

	/** The Node coordinates. */
	private Point point;

	/** The distance from start point. */
	private int distance;

	/** The distance to finish point. */
	private int edistance;

	/** Direction from where you came to the current node */
	private Step comeDirect;

	/** the node is processed. */
	private boolean processed;

	/** Node where you came to the current node . */
	private Node comeFrom;

	/**
	 * Instantiates a new node.
	 */
	public Node() {
		this.point = new Point();
		this.comeDirect = Step.S;
	}

	/**
	 * Sets the distance.
	 *
	 * @param value the new distance from the start
	 */
	public void setDistance(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("The distance of the node must not be negative");
		}
		distance = value;
	}

	/**
	 * Sets the edistance.
	 *
	 * @param value the new edistance
	 */
	public void setEdistance(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("The Edistance of the node must not be negative");
		}
		edistance = value;
	}

	/**
	 * Sets the Node point.
	 *
	 * @param point the new point
	 */
	public void setPoint(Point point) {
		this.point.setRowCol(point);
	}

	/**
	 * Gets the Node weight.
	 *
	 * @return the weight
	 */
	public int getWeight() {
		return edistance + distance;
	}
}
