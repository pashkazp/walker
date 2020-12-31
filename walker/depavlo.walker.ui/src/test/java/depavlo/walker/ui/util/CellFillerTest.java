package depavlo.walker.ui.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;

class CellFillerTest {

	@ParameterizedTest
	@EnumSource(CellFiller.class)
	@DisplayName("init GetCellFiller")
	void test1(CellFiller size) {
		assertNotNull(size);
	}

	@DisplayName("test GetCellFiller")
	@ParameterizedTest
	@MethodSource("stringAndCellFiller")
	void test2(String text, CellFiller filler) {
		assertEquals(filler, CellFiller.getCellFiller(text));
	}

	static Stream<Arguments> stringAndCellFiller() {
		return Stream.of(
				arguments("f", CellFiller.F),
				arguments("s", CellFiller.S),
				arguments("sf", CellFiller.SF),
				arguments(" ", CellFiller.SP),
				arguments("w", CellFiller.W),
				arguments("wf", CellFiller.WF),
				arguments("ws", CellFiller.WS),
				arguments("wsf", CellFiller.WSF),
				arguments("", null),
				arguments("a", null));
	}

	@DisplayName("test GetMask")
	@ParameterizedTest
	@MethodSource("stringAndMask")
	void testGetMask(String text, int mask) {
		assertEquals(mask, CellFiller.getMask(text));
	}

	static Stream<Arguments> stringAndMask() {
		return Stream.of(
				arguments("f", 0b001),
				arguments("s", 0b010),
				arguments("sf", 0b011),
				arguments(" ", 0b000),
				arguments("w", 0b100),
				arguments("wf", 0b101),
				arguments("ws", 0b110),
				arguments("wsf", 0b111),
				arguments("", 0b000),
				arguments("a", 0b000));
	}

	@ParameterizedTest
	@DisplayName("test GetVeigh")
	@MethodSource("stringAndVeight")
	void testGetVeigh(String text, int veight) {
		assertEquals(veight, CellFiller.getVeigh(text));
	}

	static Stream<Arguments> stringAndVeight() {
		return Stream.of(
				arguments("f", 1),
				arguments("s", 10),
				arguments("sf", 11),
				arguments(" ", 0),
				arguments("w", 100),
				arguments("wf", 101),
				arguments("ws", 110),
				arguments("wsf", 111),
				arguments("", 0),
				arguments("a", 0));
	}

}
