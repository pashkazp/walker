package depavlo.walker.service;

import depavlo.walker.util.Point;

public interface IArea {
	int getColsCount();

	int getRowsCount();

	int getFieldCost(int row, int col);

	default int getFieldCost(Point point) {
		return getFieldCost(point.getRow(), point.getCol());
	}
}
