package depavlo.walker.ui.util;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;

public enum CellFiller {

	SP(" ", 0, 0b000),
	F("f", 1, 0b001),
	S("s", 10, 0b010),
	SF("sf", 11, 0b011),
	W("w", 100, 0b100),
	WF("wf", 101, 0b101),
	WS("ws", 110, 0b110),
	WSF("wsf", 111, 0b111);

	public final static String[] textByMask = { " ", "f", "s", "sf", "w", "wf", "ws", "wsf" };
	public final static int[] veightByMask = { 0, 1, 10, 11, 100, 101, 110, 111 };
	public final static CellFiller[] cellFilterByMask = { SP, F, S, SF, W, WF, WS,
			WSF };
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

	public static final Map<String, CellFiller> lookup = new HashMap<>();
	static {
		for (CellFiller cf : CellFiller.values()) {
			lookup.put(cf.getText(), cf);
		}
	}
	@Getter
	public final String text;
	@Getter
	public final int veight;
	@Getter
	public final int bitmask;

	private CellFiller(String text, int veight, int mask) {
		this.text = text;
		this.veight = veight;
		this.bitmask = mask;
	}

	public static CellFiller getCellFiller(String text) {
		return lookup.get(text);
	}

	public static int getMask(String text) {
		return lookup.getOrDefault(text, SP).bitmask;
	}

	public static int getVeigh(String text) {
		return lookup.getOrDefault(text, SP).veight;
	}

}
