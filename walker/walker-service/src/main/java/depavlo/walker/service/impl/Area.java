package depavlo.walker.service.impl;

import depavlo.walker.service.IArea;
import depavlo.walker.util.Point;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * The Class that represent working Area where walker search path from Start to
 * Finish.
 * 
 * @author Pavlo Degtyaryev
 */

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Area implements IArea {

	/**
	 * Sets the working area.
	 *
	 * @param area the new area
	 */
	@Setter(value = AccessLevel.PRIVATE)
	private int[][] area;

	/**
	 * Instantiates a new area.
	 */
	public Area() {
		this.area = new int[1][1];
	}

	/**
	 * Instantiates a new area.
	 *
	 * @param rowsCount the rows count
	 * @param colsCount the cols count
	 */
	public Area(int rowsCount, int colsCount) {
		if (colsCount < 1 || rowsCount < 1) {
			throw new IllegalArgumentException("Width and Height of Area must be more than 0.");
		}
		this.area = new int[rowsCount][colsCount];
	}

	/**
	 * Sets the rows count.
	 *
	 * @param rows the new rows count
	 */
	protected void setRowsCount(int rows) {
		if (rows < 1) {
			throw new IllegalArgumentException("Rows qty of Area must be more than 0.");
		}
		if (getRowsCount() != rows) {
			this.area = new int[rows][getColsCount()];
		}
	}

	/**
	 * Sets the cols count.
	 *
	 * @param cols the new cols count
	 */
	protected void setColsCount(int cols) {
		if (cols < 1) {
			throw new IllegalArgumentException("Width of Area must be more than 0.");
		}
		if (getColsCount() != cols) {
			this.area = new int[getRowsCount()][cols];
		}
	}

	/**
	 * Sets the Area field cost.
	 *
	 * @param point the point
	 * @param cost  the cost
	 */
	public void setFieldCost(Point point, int cost) {
		setFieldCost(point.getRow(), point.getCol(), cost);
	}

	/**
	 * Sets the Area field cost.
	 *
	 * @param row  the row
	 * @param col  the col
	 * @param cost the cost
	 */
	public void setFieldCost(int row, int col, int cost) {
		area[row][col] = cost;
	}

	/**
	 * Gets the Area field cost.
	 *
	 * @param row the row
	 * @param col the col
	 * @return the field cost
	 */
	@Override
	public int getFieldCost(int row, int col) {
		return area[row][col];
	}

	/**
	 * Gets the Area rows count.
	 *
	 * @return the rows count
	 */
	@Override
	public int getRowsCount() {
		return area.length;
	}

	/**
	 * Gets the Area cols count.
	 *
	 * @return the cols count
	 */
	@Override
	public int getColsCount() {
		return area[0].length;
	}

	/**
	 * Convert Area to string with line breaks
	 *
	 * @return the string
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		for (int[] is : area) {
			builder.append("[");
			for (int i : is) {
				builder.append("\t").append(i == Integer.MAX_VALUE ? "W" : i);
			}
			builder.append("]\n");
		}
		return builder.toString();
	}

}
