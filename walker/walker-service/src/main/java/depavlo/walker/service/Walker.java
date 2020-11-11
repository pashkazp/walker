package depavlo.walker.service;

import java.util.Set;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Walker {

	private final IShape shape;

	private Point position = new Point(0, 0);

	private final Set<Step> steps;

	public Walker(IShape shape, Set<Step> steps) {
		this.shape = shape;
		this.steps = steps;
	}

	public Walker(IShape shape, Set<Step> steps, int row, int col) {
		this(shape, steps);
		this.position.setRowCol(row, col);
	}

	public Walker(IShape shape, Set<Step> steps, Point point) {
		this(shape, steps, point.getRow(), point.getCol());
	}

	public boolean canBePut(IArea area, int row, int col) {
		return shape.canPut(area, row, col);
	}

	public boolean canBePut(IArea area, Point point) {
		return canBePut(area, point.getRow(), point.getCol());
	}

	private void put(int row, int col) {
		position.setRowCol(row, col);
	}

	private void put(Point point) {
		put(point.getRow(), point.getCol());
	}

	public boolean putIfCan(IArea area, int row, int col) {
		if (canBePut(area, row, col)) {
			put(row, col);
			return true;
		}
		return false;
	}

	public boolean putIfCan(IArea area, Point point) {
		return putIfCan(area, point.getRow(), point.getCol());
	}

	public boolean canBeMove(IArea area, Step step) {
		return steps.contains(step) && shape.canMove(area, position, step);
	}

	private void Move(Step step) {
		switch (step) {
		case U:
			position.setRow(position.getRow() - 1);
			break;
		case UL:
			position.setRowCol(position.getRow() - 1, position.getCol() - 1);
			break;
		case L:
			position.setCol(position.getCol() - 1);
			break;
		case DL:
			position.setRowCol(position.getRow() + 1, position.getCol() - 1);
			break;
		case D:
			position.setRow(position.getRow() + 1);
			break;
		case DR:
			position.setRowCol(position.getRow() + 1, position.getCol() + 1);
			break;
		case R:
			position.setCol(position.getCol() + 1);
			break;
		case UR:
			position.setRowCol(position.getRow() - 1, position.getCol() + 1);
			break;
		default:
			break;
		}
	}

	public boolean moveIfCan(IArea area, Step step) {
		if (canBeMove(area, step)) {
			Move(step);
			return true;
		}
		return false;
	}

	public boolean arrived(int row, int col) {
		return shape.coversPoint(position, row, col);
	}

	public boolean arrived(Point finish) {
		return arrived(finish.getRow(), finish.getCol());
	}

	public Point getNeighborPoint(Step step) {
		switch (step) {
		case U:
			return new Point(position.getRow() - 1, position.getCol());
		case UL:
			return new Point(position.getRow() - 1, position.getCol() - 1);
		case L:
			return new Point(position.getRow(), position.getCol() - 1);
		case DL:
			return new Point(position.getRow() + 1, position.getCol() - 1);
		case D:
			return new Point(position.getRow() + 1, position.getCol());
		case DR:
			return new Point(position.getRow() + 1, position.getCol() + 1);
		case R:
			return new Point(position.getRow(), position.getCol() + 1);
		case UR:
			return new Point(position.getRow() - 1, position.getCol() + 1);
		default:
			return new Point(position.getRow(), position.getCol());
		}
	}

	public int getPositionRow() {
		return position.getRow();
	}

	public int getPositionCol() {
		return position.getCol();
	}
}
