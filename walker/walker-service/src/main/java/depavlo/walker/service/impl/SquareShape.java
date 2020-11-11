package depavlo.walker.service.impl;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.util.Step;
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
public class SquareShape implements IShape {

	private int rowsCount = 1;

	private int colsCount = 1;

	public SquareShape(int height, int width) {
		setHeight(height);
		setWidth(width);
	}

	public void setHeight(int height) {
		if (height < 1) {
			throw new IndexOutOfBoundsException("Height of Shape must be more than 0.");
		}
		this.rowsCount = height;
	}

	public void setWidth(int width) {
		if (width < 1) {
			throw new IndexOutOfBoundsException("Width of Shape must be more than 0.");
		}
		this.colsCount = width;
	}

	@Override
	public boolean canPut(IArea area, int row, int col) {
		if (row < 0 || col < 0) {
			throw new IndexOutOfBoundsException("Row or Col must be more than 0.");
		}
		if (row + rowsCount > area.getRowsCount() || col + colsCount > area.getColsCount()) {
			throw new IndexOutOfBoundsException("The Shape must be within the area.");
		}
		for (int i = 0; i < rowsCount; i++) {
			for (int j = 0; j < colsCount; j++) {
				if (area.getFieldCost(i + row, j + col) == Integer.MAX_VALUE) {
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public boolean canMove(IArea area, int startRow, int startCol, Step step) {
		if (startRow < 0 || startCol < 0 || startRow + rowsCount > area.getRowsCount()
				|| startCol + colsCount > area.getColsCount()) {
			throw new IndexOutOfBoundsException("The Shape must be within the area.");
		}
		switch (step) {
		case U:
			if (startRow == 0) {
				return false;
			}
			// horizontal edge
			for (int i = 0, y = startRow - 1, x = startCol; i < colsCount; i++) {
				if (area.getFieldCost(y, x + i) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case UL:
			if (startRow == 0 || startCol == 0) {
				return false;
			}
			// horizontal edge
			for (int i = 0, y = startRow - 1, x = startCol - 1; i < colsCount; i++) {
				if (area.getFieldCost(y, x + i) == Integer.MAX_VALUE) {
					return false;
				}
			}
			// reduced vertical edge
			for (int i = 1, y = startRow - 1, x = startCol - 1; i < rowsCount; i++) {
				if (area.getFieldCost(y + i, x) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case L:
			if (startCol == 0) {
				return false;
			}
			// vertical edge
			for (int i = 0, y = startRow, x = startCol - 1; i < rowsCount; i++) {
				if (area.getFieldCost(y + i, x) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case DL:
			if (startCol == 0 || startRow + rowsCount == area.getRowsCount()) {
				return false;
			}
			// vertical edge
			for (int i = 0, y = startRow + 1, x = startCol - 1; i < rowsCount; i++) {
				if (area.getFieldCost(y + i, x) == Integer.MAX_VALUE) {
					return false;
				}
			}
			// reduced horizontal edge
			for (int i = 1, y = startRow + rowsCount, x = startCol - 1; i < colsCount; i++) {
				if (area.getFieldCost(y, x + i) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case D:
			if (startRow + rowsCount == area.getRowsCount()) {
				return false;
			}
			// horizontal edge
			for (int i = 0, y = startRow + rowsCount, x = startCol; i < colsCount; i++) {
				if (area.getFieldCost(y, x + i) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case DR:
			if (startRow + rowsCount == area.getRowsCount() || startCol + colsCount == area.getColsCount()) {
				return false;
			}
			// vertical edge
			for (int i = 0, y = startRow + 1, x = startCol + colsCount; i < rowsCount; i++) {
				if (area.getFieldCost(y + i, x) == Integer.MAX_VALUE) {
					return false;
				}
			}
			// reduced horizontal edge
			for (int i = 1, y = startRow + rowsCount, x = startCol; i < colsCount; i++) {
				if (area.getFieldCost(y, x + i) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case R:
			if (startCol + colsCount == area.getColsCount()) {
				return false;
			}
			// vertical edge
			for (int i = 0, y = startRow, x = startCol + colsCount; i < rowsCount; i++) {
				if (area.getFieldCost(y + i, x) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		case UR:
			if (startRow == 0 || startCol + colsCount == area.getColsCount()) {
				return false;
			}
			// right edge
			for (int i = 0, y = startRow - 1, x = startCol + colsCount; i < rowsCount; i++) {
				if (area.getFieldCost(y + i, x) == Integer.MAX_VALUE) {
					return false;
				}
			}
			// reduced up edge
			for (int i = 1, y = startRow - 1, x = startCol; i < colsCount; i++) {
				if (area.getFieldCost(y, x + i) == Integer.MAX_VALUE) {
					return false;
				}
			}
			return true;
		default:
			return false;
		}
	}

	@Override
	public boolean coversPoint(int shapeStartRow, int shapeStartCol, int targetRow, int targetCol) {
		if (shapeStartRow > targetRow || (shapeStartRow + rowsCount) <= targetRow) {
			return false;
		}
		if (shapeStartCol > targetCol || (shapeStartCol + colsCount) <= targetCol) {
			return false;
		}
		return true;
	}

	@Override
	public int getHeight() {
		return rowsCount;
	}

	@Override
	public int getWidth() {
		return colsCount;
	}

}
