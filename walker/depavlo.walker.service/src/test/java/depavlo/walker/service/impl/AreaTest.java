package depavlo.walker.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import depavlo.walker.util.Point;

class AreaTest {

	@Test
	@DisplayName("Test IllegalArgumentException")
	void test1() {
		Area area = new Area(2, 2);

		assertThrows(IllegalArgumentException.class, () -> new Area(0, 0));
		assertThrows(IllegalArgumentException.class, () -> new Area(2, 0));
		assertThrows(IllegalArgumentException.class, () -> new Area(2, -1));
		assertThrows(IllegalArgumentException.class, () -> new Area(0, 2));
		assertThrows(IllegalArgumentException.class, () -> new Area(-1, 2));
		assertThrows(IllegalArgumentException.class, () -> new Area(-1, -1));
		assertThrows(IllegalArgumentException.class, () -> area.setRowsCount(0));
		assertThrows(IllegalArgumentException.class, () -> area.setRowsCount(-1));
		assertThrows(IllegalArgumentException.class, () -> area.setColsCount(0));
		assertThrows(IllegalArgumentException.class, () -> area.setColsCount(-1));
		assertDoesNotThrow(() -> new Area(1, 1));
		assertDoesNotThrow(() -> new Area(1, 100));
		assertDoesNotThrow(() -> area.setRowsCount(1));
		assertDoesNotThrow(() -> area.setRowsCount(10));
		assertDoesNotThrow(() -> area.setColsCount(1));
		assertDoesNotThrow(() -> area.setColsCount(10));
	}

	@Test
	@DisplayName("Test Area.getFieldCost Area.getFieldCost")
	void test3() {
		Area area = new Area(2, 2);
		area.setFieldCost(1, 1, 1);
		Point point00 = new Point(0, 0);
		Point point01 = new Point(0, 1);
		Point point10 = new Point(1, 0);
		Point point11 = new Point(1, 1);

		assertEquals(0, area.getFieldCost(0, 0));
		assertEquals(0, area.getFieldCost(point00));
		assertEquals(0, area.getFieldCost(0, 1));
		assertEquals(0, area.getFieldCost(point01));
		assertEquals(0, area.getFieldCost(1, 0));
		assertEquals(0, area.getFieldCost(point10));
		assertEquals(1, area.getFieldCost(1, 1));
		assertEquals(1, area.getFieldCost(point11));
	}

	@Test
	@DisplayName("Test setRowsCount setColsCount getColsCount getRowsCount")
	void test4() {
		Area area = new Area(2, 3);
		assertEquals(2, area.getArea().length);
		assertEquals(3, area.getArea()[0].length);
		assertEquals(2, area.getRowsCount());
		assertEquals(3, area.getColsCount());
		area.setRowsCount(4);
		assertEquals(4, area.getRowsCount());
		assertEquals(3, area.getColsCount());
		area.setColsCount(5);
		assertEquals(4, area.getRowsCount());
		assertEquals(5, area.getColsCount());
	}

}
