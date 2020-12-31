package depavlo.walker.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;

class ManhattanHeuristicDistanceCalculatorTest {

	@Test
	@DisplayName("Test IndexOutOfBoundsException on setDistance and setEdistance")
	void test1() {
		Area area = new Area(10, 10);
		ManhattanHeuristicDistanceCalculator func = new ManhattanHeuristicDistanceCalculator();
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, -1, 0, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, -1, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, -1, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, 0, -1));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, -10, 0, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, -10, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, -10, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, 0, -10));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 10, 0, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 10, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, 10, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, 0, 10));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 20, 0, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 20, 0, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, 20, 0));
		assertThrows(IndexOutOfBoundsException.class, () -> func.getDistance(area, 0, 0, 0, 20));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 0, 0, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 9, 0, 0, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 9, 0, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 0, 9, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 0, 0, 9));
		assertDoesNotThrow(() -> func.getDistance(area, 5, 0, 0, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 5, 0, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 0, 5, 0));
		assertDoesNotThrow(() -> func.getDistance(area, 0, 0, 0, 5));
	}

	@Test
	@DisplayName("Test calculate of Distance")
	void test2() {
		Area area = new Area(10, 10);
		ManhattanHeuristicDistanceCalculator func = new ManhattanHeuristicDistanceCalculator();
		Point point00 = new Point(0, 0);
		Point point99 = new Point(9, 9);
		Point point09 = new Point(0, 9);
		Point point90 = new Point(9, 0);
		Point point01 = new Point(0, 1);
		Point point10 = new Point(1, 0);
		Point point11 = new Point(1, 1);
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 0, 0));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point00));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point00));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 0, 0));
		assertEquals(2 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 1, 1));
		assertEquals(2 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point11));
		assertEquals(2 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point11));
		assertEquals(2 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 1, 1));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 1, 0));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point10));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point10));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 1, 0));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 0, 1));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point01));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point01));
		assertEquals(1 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 0, 1));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 9, 9));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point99));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point99));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 9, 9));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 9, 0));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point90));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point90));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 9, 0));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, 0, 9));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, point00, point09));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 0, point09));
		assertEquals(9 * Step.getStepPrice(Step.D), func.getDistance(area, point00, 0, 9));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 9, 9, 0));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, point09, point90));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, 0, 9, point90));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, point09, 9, 0));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, 9, 9, 0, 0));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, point99, point00));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, 9, 9, point00));
		assertEquals(18 * Step.getStepPrice(Step.D), func.getDistance(area, point99, 0, 0));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, 9, 9, 9, 9));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, point99, point99));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, 9, 9, point99));
		assertEquals(0 * Step.getStepPrice(Step.D), func.getDistance(area, point99, 9, 9));
	}

}
