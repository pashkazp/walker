package depavlo.walker.ui.util;

import org.springframework.stereotype.Component;

import depavlo.walker.util.Point;

/**
 * The CalcFiller class, which calculates the field mask for a given point at a
 * given start and end point in a given area.
 * 
 * @author Pavlo Degtyaryev
 */
@Component
public class CalcFiller {

	/** The mask. */
	private int mask;

	/**
	 * Gets the fill.
	 *
	 * @param area    the area
	 * @param row     the row
	 * @param col     the col
	 * @param start   the start
	 * @param finish  the finish
	 * @param command the command
	 * @return the fill
	 */
	public String getFill(int[][] area, int row, int col, Point start, Point finish, String command) {
		switch (command) {
		case "w":// mask to set wall
			mask = 0b100;
			mask |= (start != null && start.getRow() == row && start.getCol() == col) ? 0b010 : 0b000;
			mask |= (finish != null && finish.getRow() == row && finish.getCol() == col) ? 0b001 : 0b000;
			break;
		case " ":// mask to clear wall
			mask = 0b000;
			mask |= (start != null && start.getRow() == row && start.getCol() == col) ? 0b010 : 0b000;
			mask |= (finish != null && finish.getRow() == row && finish.getCol() == col) ? 0b001 : 0b000;
			break;
		case "s":// mask to set start
			mask = area[row][col] == Integer.MAX_VALUE ? 0b100 : 0b000;
			mask |= (start != null && start.getRow() == row && start.getCol() == col) ? 0b000 : 0b010;
			mask |= (finish != null && finish.getRow() == row && finish.getCol() == col) ? 0b001 : 0b000;
			break;
		case "f":// mask to set finish
			mask = area[row][col] == Integer.MAX_VALUE ? 0b100 : 0b000;
			mask |= (start != null && start.getRow() == row && start.getCol() == col) ? 0b010 : 0b000;
			mask |= (finish != null && finish.getRow() == row && finish.getCol() == col) ? 0b000 : 0b001;
			break;
		default:// read mask
			mask = area[row][col] == Integer.MAX_VALUE ? 0b100 : 0b000;
			mask |= (start != null && start.getRow() == row && start.getCol() == col) ? 0b010 : 0b000;
			mask |= (finish != null && finish.getRow() == row && finish.getCol() == col) ? 0b001 : 0b000;
			break;
		}
		return CellFiller.TEXT_BY_MASK[mask];
	}

}
