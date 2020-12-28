package depavlo.walker.ui.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

/**
 * The Enum CellFiller that helps convert digital representation of task
 * properties to text representation.
 * 
 * @author Pavlo Degtyaryev
 */
public enum CellFiller {

	SP(" ", 0, 0b000),
	F("f", 1, 0b001),
	S("s", 10, 0b010),
	SF("sf", 11, 0b011),
	W("w", 100, 0b100),
	WF("wf", 101, 0b101),
	WS("ws", 110, 0b110),
	WSF("wsf", 111, 0b111);

	/** The Constant TEXT_BY_MASK. */
	public final static String[] TEXT_BY_MASK = { " ", "f", "s", "sf", "w", "wf", "ws", "wsf" };

	/** The Constant VEIGHT_BY_MASK. */
	public final static int[] VEIGHT_BY_MASK = { 0, 1, 10, 11, 100, 101, 110, 111 };

	/** The Constant CELL_FILTER_BY_MASK. */
	public final static CellFiller[] CELL_FILTER_BY_MASK = { SP, F, S, SF, W, WF, WS,
			WSF };

	/** The Constant maskByVeight. */
	public final static int[] maskByVeight = {
			0b000, 0b001, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b010, 0b011, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b100, 0b101, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000, 0b000,
			0b110, 0b111
	};

	/** The Constant textByVeight. */
	public final static String[] textByVeight = {
			" ", "f", "", "", "", "", "", "", "", "",
			"s", "sf", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"", "", "", "", "", "", "", "", "", "",
			"w", "wf", "", "", "", "", "", "", "", "",
			"ws", "wsf"
	};

	/** The Constant lookup. */
	public static final Map<String, CellFiller> lookup = new HashMap<>();
	// initialize th map
	static {
		for (CellFiller cf : CellFiller.values()) {
			lookup.put(cf.getText(), cf);
		}
	}

	/**
	 * Gets the text representation.
	 *
	 * @return the text
	 */
	@Getter
	public final String text;

	/**
	 * Gets the veight of mask.
	 *
	 * @return the veight
	 */
	@Getter
	public final int veight;

	/**
	 * Gets the bitmask of property.
	 *
	 * @return the bitmask
	 */
	@Getter
	public final int bitmask;

	/**
	 * Instantiates a new cell filler.
	 *
	 * @param text   the text
	 * @param veight the veight
	 * @param mask   the mask
	 */
	private CellFiller(String text, int veight, int mask) {
		this.text = text;
		this.veight = veight;
		this.bitmask = mask;
	}

	/**
	 * Gets the cell filler.
	 *
	 * @param text the text
	 * @return the cell filler
	 */
	public static CellFiller getCellFiller(String text) {
		return lookup.get(text);
	}

	/**
	 * Gets the mask.
	 *
	 * @param text the text
	 * @return the mask
	 */
	public static int getMask(String text) {
		return lookup.getOrDefault(text, SP).bitmask;
	}

	/**
	 * Gets the veigh.
	 *
	 * @param text the text
	 * @return the veigh
	 */
	public static int getVeigh(String text) {
		return lookup.getOrDefault(text, SP).veight;
	}

}
