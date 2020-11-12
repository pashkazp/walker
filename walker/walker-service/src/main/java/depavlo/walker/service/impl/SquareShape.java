package depavlo.walker.service.impl;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.service.exception.ShapeOutOfBoundsException;
import depavlo.walker.util.Step;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class that represent the Walker Square Shape .
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
public class SquareShape implements IShape {

	/** The rows count. */
	private int rowsCount = 1;

	/** The cols count. */
	private int colsCount = 1;

	/**
	 * Instantiates a new square shape.
	 *
	 * @param height the height
	 * @param width  the width
	 */
	public SquareShape(int height, int width) {
		setHeight(height);
		setWidth(width);
	}

	/**
	 * Sets the height of shape.
	 *
	 * @param height the new height
	 */
	public void setHeight(int height) {
		if (height < 1) {
			throw new ShapeOutOfBoundsException("Height of Shape must be more than 0.", null);
		}
		this.rowsCount = height;
	}

	/**
	 * Sets the width of shape.
	 *
	 * @param width the new width
	 */
	public void setWidth(int width) {
		if (width < 1) {
			throw new ShapeOutOfBoundsException("Width of Shape must be more than 0.", null);
		}
		this.colsCount = width;
	}

	/**
	 * Check if Can put shape on the Area position
	 *
	 * @param area the area
	 * @param row  the row
	 * @param col  the col
	 * @return true, if successful
	 */
	@Override
	public boolean canPut(IArea area, int row, int col) {
		if (row < 0 || col < 0) {
			throw new ShapeOutOfBoundsException("Row or Col must be more than 0.", null);
		}
		if (row + rowsCount > area.getRowsCount() || col + colsCount > area.getColsCount()) {
			throw new ShapeOutOfBoundsException("The Shape must be within the area.", null);
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

	/**
	 * Check if Can move shape from the given point to the direction.
	 *
	 * @param area     the area
	 * @param startRow the start row
	 * @param startCol the start col
	 * @param step     the step
	 * @return true, if successful
	 */
	@Override
	public boolean canMove(IArea area, int startRow, int startCol, Step step) {
		if (startRow < 0 || startCol < 0 || startRow + rowsCount > area.getRowsCount()
				|| startCol + colsCount > area.getColsCount()) {
			throw new ShapeOutOfBoundsException("The Shape must be within the area.", null);
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

	/**
	 * check if shape Covers given point.
	 *
	 * @param shapeStartRow the shape start row
	 * @param shapeStartCol the shape start col
	 * @param targetRow     the target row
	 * @param targetCol     the target col
	 * @return true, if successful
	 */
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

	/**
	 * Gets the height of shape.
	 *
	 * @return the height
	 */
	@Override
	public int getHeight() {
		return rowsCount;
	}

	/**
	 * Gets the width of shape.
	 *
	 * @return the width
	 */
	@Override
	public int getWidth() {
		return colsCount;
	}

}
