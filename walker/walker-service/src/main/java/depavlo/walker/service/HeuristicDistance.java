package depavlo.walker.service;

import depavlo.walker.util.Point;

/**
 * The Interface HeuristicDistance.
 * 
 * @author Pavlo Degtyaryev
 */
public interface HeuristicDistance {

	/**
	 * Gets the distance between two points on the Area.
	 *
	 * @param area      the area
	 * @param startRow  the start row
	 * @param startCol  the start col
	 * @param targetRow the target row
	 * @param targetCol the target col
	 * @return the distance
	 */
	int getDistance(IArea area, int startRow, int startCol, int targetRow, int targetCol);

	/**
	 * Gets the distance between two points on the Area.
	 *
	 * @param area   the area
	 * @param source the source
	 * @param target the target
	 * @return the distance
	 */
	default int getDistance(IArea area, Point source, Point target) {
		return getDistance(area, source.getRow(), source.getCol(), target.getRow(), target.getCol());
	}

	/**
	 * Gets the distance between two points on the Area.
	 *
	 * @param area     the area
	 * @param startRow the start row
	 * @param startCol the start col
	 * @param target   the target
	 * @return the distance
	 */
	default int getDistance(IArea area, int startRow, int startCol, Point target) {
		return getDistance(area, startRow, startCol, target.getRow(), target.getCol());
	}

	/**
	 * Gets the distance between two points on the Area.
	 *
	 * @param area      the area
	 * @param source    the source
	 * @param targetRow the target row
	 * @param targetCol the target col
	 * @return the distance
	 */
	default int getDistance(IArea area, Point source, int targetRow, int targetCol) {
		return getDistance(area, source.getRow(), source.getCol(), targetRow, targetCol);
	}
}
