package depavlo.walker.util;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class Point {

	private int row;
	private int col;

	public Point(int row, int col) {
		setRow(row);
		setCol(col);
	}

	public Point(Point point) {
		this(point.getRow(), point.getCol());
	}

	public void setCol(int col) {
		if (col < 0) {
			throw new IllegalArgumentException("X-coordinate of Point must be greater than zero");
		}
		this.col = col;
	}

	public void setRow(int row) {
		if (row < 0) {
			throw new IllegalArgumentException("Y-coordinate of Point must be greater than zero");
		}
		this.row = row;
	}

	public void setRowCol(int row, int col) {
		setRow(row);
		setCol(col);
	}

	public void setRowCol(Point point) {
		setRowCol(point.getRow(), point.getCol());
	}

}
