package depavlo.walker.service;

import depavlo.walker.util.Point;

/**
 * The Interface IArea.
 * 
 * @author Pavlo Degtyaryev
 */
public interface IArea {

	/**
	 * Gets the Area cols count.
	 *
	 * @return the cols count
	 */
	int getColsCount();

	/**
	 * Gets the Area rows count.
	 *
	 * @return the rows count
	 */
	int getRowsCount();

	/**
	 * Gets the Area field cost.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the field cost
	 */
	int getFieldCost(int row, int col);

	/**
	 * Gets the Area field cost.
	 *
	 * @param point the point
	 * @return the field cost
	 */
	default int getFieldCost(Point point) {
		return getFieldCost(point.getRow(), point.getCol());
	}
}
