package depavlo.walker.service;

import depavlo.walker.util.Point;

public interface HeuristicDistance {
	int getDistance(IArea area, int startRow, int startCol, int targetRow, int targetCol);

	default int getDistance(IArea area, Point source, Point target) {
		return getDistance(area, source.getRow(), source.getCol(), target.getRow(), target.getCol());
	}

	default int getDistance(IArea area, int startRow, int startCol, Point target) {
		return getDistance(area, startRow, startCol, target.getRow(), target.getCol());
	}

	default int getDistance(IArea area, Point source, int targetRow, int targetCol) {
		return getDistance(area, source.getRow(), source.getCol(), targetRow, targetCol);
	}
}
