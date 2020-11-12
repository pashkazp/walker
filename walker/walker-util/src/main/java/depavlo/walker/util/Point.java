package depavlo.walker.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class that represent the coordinates of any object
 * 
 * @author Pavlo Degtyaryev
 */

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Point {

	/** The row. */
	private int row;

	/** The col. */
	private int col;

	/**
	 * Instantiates a new point.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public Point(int row, int col) {
		setRow(row);
		setCol(col);
	}

	/**
	 * Instantiates a new point.
	 *
	 * @param point the point
	 */
	public Point(Point point) {
		this(point.getRow(), point.getCol());
	}

	/**
	 * Sets the column.
	 *
	 * @param col the new col
	 */
	public void setCol(int col) {
		if (col < 0) {
			throw new IllegalArgumentException("X-coordinate of Point must be greater than zero");
		}
		this.col = col;
	}

	/**
	 * Sets the row.
	 *
	 * @param row the new row
	 */
	public void setRow(int row) {
		if (row < 0) {
			throw new IllegalArgumentException("Y-coordinate of Point must be greater than zero");
		}
		this.row = row;
	}

	/**
	 * Sets the row column.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void setRowCol(int row, int col) {
		setRow(row);
		setCol(col);
	}

	/**
	 * Sets the row column.
	 *
	 * @param point the new row col
	 */
	public void setRowCol(Point point) {
		setRowCol(point.getRow(), point.getCol());
	}

}
