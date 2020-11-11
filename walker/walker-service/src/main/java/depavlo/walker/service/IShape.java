package depavlo.walker.service;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;

public interface IShape {
	boolean canPut(IArea area, int row, int col);

	default boolean canPut(IArea area, Point target) {
		return canPut(area, target.getRow(), target.getCol());
	};

	boolean canMove(IArea area, int startRow, int startCol, Step step);

	default boolean canMove(IArea area, Point start, Step step) {
		return canMove(area, start.getRow(), start.getCol(), step);
	};

	boolean coversPoint(int shapeStartRow, int shapeStartCol, int targetRow, int targetCol);

	default boolean coversPoint(Point shapeStart, Point target) {
		return coversPoint(shapeStart.getRow(), shapeStart.getCol(), target.getRow(), target.getCol());
	};

	default boolean coversPoint(int shapeStartRow, int shapeStartCol, Point target) {
		return coversPoint(shapeStartRow, shapeStartCol, target.getRow(), target.getCol());
	};

	default boolean coversPoint(Point shapeStart, int targetRow, int targetCol) {
		return coversPoint(shapeStart.getRow(), shapeStart.getCol(), targetRow, targetCol);
	};

	int getHeight();

	int getWidth();

	default Point getCenter() {
		return new Point(0, 0);
	}
}
