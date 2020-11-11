package depavlo.walker.service;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter

@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class Node {

	private Point point;
	private int distance;
	private int edistance;
	private Step comeDirect;
	private boolean processed;
	private Node comeFrom;

	public Node() {
		this.point = new Point();
		this.comeDirect = Step.S;
	}

	public void setDistance(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("The distance of the node must not be negative");
		}
		distance = value;
	}

	public void setEdistance(int value) {
		if (value < 0) {
			throw new IllegalArgumentException("The Edistance of the node must not be negative");
		}
		edistance = value;
	}

	public void setPoint(Point point) {
		this.point.setRowCol(point);
	}

	public int getWeight() {
		return edistance + distance;
	}
}
