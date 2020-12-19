package depavlo.walker.service;

import java.util.Set;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class that represent Walker with given shape and set of steps.
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Walker {

	/** The shape. */
	private final IShape shape;

	/** The current position. */
	private Point position = new Point(0, 0);

	/** The enabled steps. */
	private final Set<Step> steps;

	/**
	 * Instantiates a new walker.
	 *
	 * @param shape the shape
	 * @param steps the steps
	 */
	public Walker(IShape shape, Set<Step> steps) {
		this.shape = shape;
		this.steps = steps;
	}

	/**
	 * Instantiates a new walker.
	 *
	 * @param shape the shape
	 * @param steps the steps
	 * @param row   the row
	 * @param col   the col
	 */
	public Walker(IShape shape, Set<Step> steps, int row, int col) {
		this(shape, steps);
		this.position.setRowCol(row, col);
	}

	/**
	 * Instantiates a new walker.
	 *
	 * @param shape the shape
	 * @param steps the steps
	 * @param point the point
	 */
	public Walker(IShape shape, Set<Step> steps, Point point) {
		this(shape, steps, point.getRow(), point.getCol());
	}

	/**
	 * check if Can be put walker to the given point of the area.
	 *
	 * @param area the area
	 * @param row  the row
	 * @param col  the col
	 * @return true, if successful
	 */
	public boolean canBePut(IArea area, int row, int col) {
		return shape.canPut(area, row, col);
	}

	/**
	 * check if Can be put walker to the given point of the area.
	 *
	 * @param area  the area
	 * @param point the point
	 * @return true, if successful
	 */
	public boolean canBePut(IArea area, Point point) {
		return canBePut(area, point.getRow(), point.getCol());
	}

	/**
	 * Put walker to the point.
	 *
	 * @param row the row
	 * @param col the col
	 */
	private void put(int row, int col) {
		position.setRowCol(row, col);
	}

	/**
	 * Put the walker at the point
	 *
	 * @param point the point
	 */
	private void put(Point point) {
		put(point.getRow(), point.getCol());
	}

	/**
	 * Put the walker at the point if possible
	 *
	 * @param area the area
	 * @param row  the row
	 * @param col  the col
	 * @return true, if successful
	 */
	public boolean putIfCan(IArea area, int row, int col) {
		if (canBePut(area, row, col)) {
			put(row, col);
			return true;
		}
		return false;
	}

	/**
	 * Put the walker at the point if possible
	 *
	 * @param area  the area
	 * @param point the point
	 * @return true, if successful
	 */
	public boolean putIfCan(IArea area, Point point) {
		return putIfCan(area, point.getRow(), point.getCol());
	}

	/**
	 * check if Move the walker from the point to direction
	 *
	 * @param area the area
	 * @param step the step
	 * @return true, if successful
	 */
	public boolean canBeMove(IArea area, Step step) {
		return steps.contains(step) && shape.canMove(area, position, step);
	}

	/**
	 * Move the walker from the point to direction
	 *
	 * @param step the step
	 */
	private void move(Step step) {
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

	/**
	 * Move the walker from the point to direction if possible
	 *
	 * @param area the area
	 * @param step the step
	 * @return true, if successful
	 */
	public boolean moveIfCan(IArea area, Step step) {
		if (canBeMove(area, step)) {
			move(step);
			return true;
		}
		return false;
	}

	/**
	 * check if shape covers the given point
	 *
	 * @param row the row
	 * @param col the col
	 * @return true, if successful
	 */
	public boolean arrived(int row, int col) {
		return shape.coversPoint(position, row, col);
	}

	/**
	 * check if shape covers the given point
	 *
	 * @param finish the finish
	 * @return true, if successful
	 */
	public boolean arrived(Point finish) {
		return arrived(finish.getRow(), finish.getCol());
	}

	/**
	 * Gets the neighbor point.
	 *
	 * @param step the step
	 * @return the neighbor point
	 */
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

	/**
	 * Gets the position row.
	 *
	 * @return the position row
	 */
	public int getPositionRow() {
		return position.getRow();
	}

	/**
	 * Gets the position col.
	 *
	 * @return the position col
	 */
	public int getPositionCol() {
		return position.getCol();
	}
}
