package depavlo.walker.service;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;

/**
 * The Interface IShape.
 * 
 * @author Pavlo Degtyaryev
 */
public interface IShape {

	/**
	 * Check if Can put Shape to the given area.
	 *
	 * @param area the area
	 * @param row  the row
	 * @param col  the col
	 * @return true, if successful
	 */
	boolean canPut(IArea area, int row, int col);

	/**
	 * Check if Can put Shape to the given area.
	 *
	 * @param area   the area
	 * @param target the target
	 * @return true, if successful
	 */
	default boolean canPut(IArea area, Point target) {
		return canPut(area, target.getRow(), target.getCol());
	};

	/**
	 * check if the shape can move from a given point over a given area in a given
	 * direction
	 *
	 * @param area     the area
	 * @param startRow the start row
	 * @param startCol the start col
	 * @param step     the step
	 * @return true, if successful
	 */
	boolean canMove(IArea area, int startRow, int startCol, Step step);

	/**
	 * check if the shape can move from a given point over a given area in a given
	 * direction
	 *
	 * @param area  the area
	 * @param start the start
	 * @param step  the step
	 * @return true, if successful
	 */
	default boolean canMove(IArea area, Point start, Step step) {
		return canMove(area, start.getRow(), start.getCol(), step);
	};

	/**
	 * check if Covers given point.
	 *
	 * @param shapeStartRow the shape start row
	 * @param shapeStartCol the shape start col
	 * @param targetRow     the target row
	 * @param targetCol     the target col
	 * @return true, if successful
	 */
	boolean coversPoint(int shapeStartRow, int shapeStartCol, int targetRow, int targetCol);

	/**
	 * check if Covers given point.
	 *
	 * @param shapeStart the shape start
	 * @param target     the target
	 * @return true, if successful
	 */
	default boolean coversPoint(Point shapeStart, Point target) {
		return coversPoint(shapeStart.getRow(), shapeStart.getCol(), target.getRow(), target.getCol());
	};

	/**
	 * check if Covers given point.
	 *
	 * @param shapeStartRow the shape start row
	 * @param shapeStartCol the shape start col
	 * @param target        the target
	 * @return true, if successful
	 */
	default boolean coversPoint(int shapeStartRow, int shapeStartCol, Point target) {
		return coversPoint(shapeStartRow, shapeStartCol, target.getRow(), target.getCol());
	};

	/**
	 * check if Covers given point.
	 *
	 * @param shapeStart the shape start
	 * @param targetRow  the target row
	 * @param targetCol  the target col
	 * @return true, if successful
	 */
	default boolean coversPoint(Point shapeStart, int targetRow, int targetCol) {
		return coversPoint(shapeStart.getRow(), shapeStart.getCol(), targetRow, targetCol);
	};

	/**
	 * Gets the Shape height.
	 *
	 * @return the height
	 */
	int getHeight();

	/**
	 * Gets the Shape width.
	 *
	 * @return the width
	 */
	int getWidth();

	/**
	 * Gets the center of shape.
	 *
	 * @return the center
	 */
	default Point getCenter() {
		return new Point(0, 0);
	}
}
