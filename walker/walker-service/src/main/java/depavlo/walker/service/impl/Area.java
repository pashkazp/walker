package depavlo.walker.service.impl;

import depavlo.walker.service.IArea;
import depavlo.walker.util.Point;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Area implements IArea {

	@Setter(value = AccessLevel.PRIVATE)
	private int[][] area;

	public Area() {
		this.area = new int[1][1];
	}

	public Area(int rowsCount, int colsCount) {
		if (colsCount < 1 || rowsCount < 1) {
			throw new IndexOutOfBoundsException("Width and Height of Area must be more than 0.");
		}
		this.area = new int[rowsCount][colsCount];
	}

	protected void setRowsCount(int rows) {
		if (rows < 1) {
			throw new IndexOutOfBoundsException("Rows qty of Area must be more than 0.");
		}
		if (getRowsCount() != rows) {
			this.area = new int[rows][getColsCount()];
		}
	}

	protected void setColsCount(int cols) {
		if (cols < 1) {
			throw new IndexOutOfBoundsException("Width of Area must be more than 0.");
		}
		if (getColsCount() != cols) {
			this.area = new int[getRowsCount()][cols];
		}
	}

	public void setFieldCost(Point point, int cost) {
		setFieldCost(point.getRow(), point.getCol(), cost);
	}

	public void setFieldCost(int row, int col, int cost) {
		area[row][col] = cost;
	}

	@Override
	public int getFieldCost(int row, int col) {
		return area[row][col];
	}

	@Override
	public int getRowsCount() {
		return area.length;
	}

	@Override
	public int getColsCount() {
		return area[0].length;
	}

	@Override
	public String toString() {
//		return "\n".concat(Arrays.deepToString(area).replace("],", "]\n").replace("]]", "]").replace("[[", "["));
		StringBuilder builder = new StringBuilder();
		for (int[] is : area) {
			builder.append("[");
			for (int i : is) {
				builder.append("\t").append(i == Integer.MAX_VALUE ? "W" : i);
			}
			builder.append("]\n");
			// builder.append("[").append(Arrays.toString(is)).append("]").append("\n");
		}
		// builder.append("Area [area=").append(Arrays.toString(area)).append("]");
		return builder.toString();
	}

}
