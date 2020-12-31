package depavlo.walker.service.impl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import depavlo.walker.service.exception.ShapeOutOfBoundsException;
import depavlo.walker.util.Point;
import depavlo.walker.util.Step;

class SquareShapeTest {

	@Test
	@DisplayName("Test ShapeOutOfBoundsException on constructor, setHeight and setWidth")
	void test1() {
		SquareShape shape = new SquareShape();
		assertThrows(ShapeOutOfBoundsException.class, () -> new SquareShape(0, 0));
		assertThrows(ShapeOutOfBoundsException.class, () -> new SquareShape(1, 0));
		assertThrows(ShapeOutOfBoundsException.class, () -> new SquareShape(0, 1));
		assertThrows(ShapeOutOfBoundsException.class, () -> new SquareShape(1, -1));
		assertThrows(ShapeOutOfBoundsException.class, () -> new SquareShape(-1, 1));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape.setHeight(0));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape.setHeight(-1));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape.setWidth(0));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape.setWidth(-1));
		assertDoesNotThrow(() -> new SquareShape(1, 1));
		assertDoesNotThrow(() -> new SquareShape(10, 10));
		assertDoesNotThrow(() -> new SquareShape(10, 10));
		assertDoesNotThrow(() -> shape.setHeight(1));
		assertDoesNotThrow(() -> shape.setHeight(10));
		assertDoesNotThrow(() -> shape.setWidth(1));
		assertDoesNotThrow(() -> shape.setWidth(10));
	}

	@Test
	@DisplayName("Test setHeight and setWidth")
	void test2() {
		SquareShape shape = new SquareShape();
		assertEquals(1, shape.getHeight());
		assertEquals(1, shape.getWidth());
		shape.setWidth(2);
		assertEquals(1, shape.getHeight());
		assertEquals(2, shape.getWidth());
		shape.setHeight(3);
		assertEquals(3, shape.getHeight());
		assertEquals(2, shape.getWidth());
	}

	@Test
	@DisplayName("Test getHeight and getWidth")
	void test3() {
		SquareShape shape = new SquareShape(5, 10);
		assertEquals(5, shape.getHeight());
		assertEquals(10, shape.getWidth());
	}

	@Test
	@DisplayName("Test covers target Point by Shape")
	void test4() {

		Point pointShape00 = new Point(0, 0);
		Point pointShape01 = new Point(0, 1);
		Point pointShape10 = new Point(1, 0);
		Point pointShape11 = new Point(1, 1);

		Point pointTarget00 = new Point(0, 0);
		Point pointTarget21 = new Point(2, 1);
		Point pointTarget55 = new Point(5, 5);

		SquareShape shape11 = new SquareShape(1, 1);
		SquareShape shape22 = new SquareShape(2, 2);
		SquareShape shape33 = new SquareShape(3, 3);
		SquareShape shape51 = new SquareShape(5, 1);

		assertTrue(shape11.coversPoint(pointShape00, pointTarget00));
		assertTrue(shape22.coversPoint(pointShape00, pointTarget00));
		assertTrue(shape33.coversPoint(pointShape00, pointTarget00));
		assertTrue(shape51.coversPoint(pointShape00, pointTarget00));

		assertFalse(shape11.coversPoint(pointShape01, pointTarget00));
		assertFalse(shape11.coversPoint(pointShape10, pointTarget00));
		assertFalse(shape11.coversPoint(pointShape11, pointTarget00));

		assertFalse(shape22.coversPoint(pointShape01, pointTarget00));
		assertFalse(shape22.coversPoint(pointShape10, pointTarget00));
		assertFalse(shape22.coversPoint(pointShape11, pointTarget00));

		assertFalse(shape33.coversPoint(pointShape01, pointTarget00));
		assertFalse(shape33.coversPoint(pointShape10, pointTarget00));
		assertFalse(shape33.coversPoint(pointShape11, pointTarget00));

		assertFalse(shape51.coversPoint(pointShape01, pointTarget00));
		assertFalse(shape51.coversPoint(pointShape10, pointTarget00));
		assertFalse(shape51.coversPoint(pointShape11, pointTarget00));

		assertFalse(shape11.coversPoint(pointShape00, pointTarget21));
		assertFalse(shape11.coversPoint(pointShape01, pointTarget21));
		assertFalse(shape11.coversPoint(pointShape10, pointTarget21));
		assertFalse(shape11.coversPoint(pointShape11, pointTarget21));

		assertFalse(shape22.coversPoint(pointShape00, pointTarget21));
		assertFalse(shape22.coversPoint(pointShape01, pointTarget21));
		assertTrue(shape22.coversPoint(pointShape10, pointTarget21));
		assertTrue(shape22.coversPoint(pointShape11, pointTarget21));

		assertTrue(shape33.coversPoint(pointShape00, pointTarget21));
		assertTrue(shape33.coversPoint(pointShape01, pointTarget21));
		assertTrue(shape33.coversPoint(pointShape10, pointTarget21));
		assertTrue(shape33.coversPoint(pointShape11, pointTarget21));

		assertFalse(shape33.coversPoint(pointShape00, pointTarget55));
		assertFalse(shape33.coversPoint(pointShape01, pointTarget55));
		assertFalse(shape33.coversPoint(pointShape10, pointTarget55));
		assertFalse(shape33.coversPoint(pointShape11, pointTarget55));

		assertFalse(shape51.coversPoint(pointShape00, pointTarget21));
		assertTrue(shape51.coversPoint(pointShape01, pointTarget21));
		assertFalse(shape51.coversPoint(pointShape10, pointTarget21));
		assertTrue(shape51.coversPoint(pointShape11, pointTarget21));

		assertFalse(shape51.coversPoint(pointShape00, pointTarget55));
		assertFalse(shape51.coversPoint(pointShape01, pointTarget55));
		assertFalse(shape51.coversPoint(pointShape10, pointTarget55));
		assertFalse(shape51.coversPoint(pointShape11, pointTarget55));
	}

	@Test
	@DisplayName("Test Can put Shape on Area")
	void test5() {
		Area area = new Area(10, 10);
		area.setFieldCost(8, 8, Integer.MAX_VALUE);
		area.setFieldCost(5, 0, Integer.MAX_VALUE);
		SquareShape shape11 = new SquareShape(1, 1);
		SquareShape shape22 = new SquareShape(2, 2);
		SquareShape shape51 = new SquareShape(5, 1);

		assertTrue(shape11.canPut(area, 0, 0));
		assertTrue(shape11.canPut(area, 0, 9));
		assertTrue(shape11.canPut(area, 9, 0));
		assertTrue(shape11.canPut(area, 9, 9));
		assertTrue(shape11.canPut(area, 9, 0));
		assertTrue(shape11.canPut(area, 9, 9));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canPut(area, -1, 0));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canPut(area, 0, -1));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canPut(area, -1, -1));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canPut(area, 0, 10));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canPut(area, 10, 0));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canPut(area, 10, 10));

		assertTrue(shape22.canPut(area, 0, 0));
		assertTrue(shape22.canPut(area, 0, 8));
		assertTrue(shape22.canPut(area, 8, 0));
		assertFalse(shape22.canPut(area, 8, 8));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape22.canPut(area, 0, 9));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape22.canPut(area, 9, 0));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape22.canPut(area, 9, 9));

		assertTrue(shape51.canPut(area, 0, 0));
		assertFalse(shape51.canPut(area, 1, 0));
		assertFalse(shape51.canPut(area, 5, 0));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape51.canPut(area, 6, 0));
	}

	@Test
	@DisplayName("Test Can move Shape on Area")
	void test6() {
		Area area = new Area(10, 10);
		area.setFieldCost(8, 8, Integer.MAX_VALUE);
		area.setFieldCost(5, 2, Integer.MAX_VALUE);
		SquareShape shape11 = new SquareShape(1, 1);
		SquareShape shape22 = new SquareShape(2, 2);
		SquareShape shape51 = new SquareShape(5, 1);

		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canMove(area, -1, 0, Step.D));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canMove(area, -1, -1, Step.D));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canMove(area, -5, 2, Step.D));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canMove(area, 0, 10, Step.D));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape11.canMove(area, 10, 3, Step.D));
		assertThrows(ShapeOutOfBoundsException.class, () -> shape51.canMove(area, 6, 2, Step.D));

		assertFalse(shape11.canMove(area, 0, 0, Step.U));
		assertFalse(shape11.canMove(area, 0, 0, Step.UL));
		assertFalse(shape11.canMove(area, 0, 0, Step.L));
		assertFalse(shape11.canMove(area, 0, 0, Step.DL));
		assertTrue(shape11.canMove(area, 0, 0, Step.D));
		assertTrue(shape11.canMove(area, 0, 0, Step.DR));
		assertTrue(shape11.canMove(area, 0, 0, Step.R));
		assertFalse(shape11.canMove(area, 0, 0, Step.UR));

		assertTrue(shape11.canMove(area, 9, 0, Step.U));
		assertFalse(shape11.canMove(area, 9, 0, Step.UL));
		assertFalse(shape11.canMove(area, 9, 0, Step.L));
		assertFalse(shape11.canMove(area, 9, 0, Step.DL));
		assertFalse(shape11.canMove(area, 9, 0, Step.D));
		assertFalse(shape11.canMove(area, 9, 0, Step.DR));
		assertTrue(shape11.canMove(area, 9, 0, Step.R));
		assertTrue(shape11.canMove(area, 9, 0, Step.UR));

		assertTrue(shape11.canMove(area, 9, 9, Step.U));
		assertFalse(shape11.canMove(area, 9, 9, Step.UL));
		assertTrue(shape11.canMove(area, 9, 9, Step.L));
		assertFalse(shape11.canMove(area, 9, 9, Step.DL));
		assertFalse(shape11.canMove(area, 9, 9, Step.D));
		assertFalse(shape11.canMove(area, 9, 9, Step.DR));
		assertFalse(shape11.canMove(area, 9, 9, Step.R));
		assertFalse(shape11.canMove(area, 9, 9, Step.UR));

		assertFalse(shape11.canMove(area, 0, 9, Step.U));
		assertFalse(shape11.canMove(area, 0, 9, Step.UL));
		assertTrue(shape11.canMove(area, 0, 9, Step.L));
		assertTrue(shape11.canMove(area, 0, 9, Step.DL));
		assertTrue(shape11.canMove(area, 0, 9, Step.D));
		assertFalse(shape11.canMove(area, 0, 9, Step.DR));
		assertFalse(shape11.canMove(area, 0, 9, Step.R));
		assertFalse(shape11.canMove(area, 0, 9, Step.UR));

		assertTrue(shape22.canMove(area, 8, 8, Step.U));
		assertTrue(shape22.canMove(area, 8, 8, Step.UL));
		assertTrue(shape22.canMove(area, 8, 8, Step.L));
		assertFalse(shape22.canMove(area, 8, 8, Step.DL));
		assertFalse(shape22.canMove(area, 8, 8, Step.D));
		assertFalse(shape22.canMove(area, 8, 8, Step.DR));
		assertFalse(shape22.canMove(area, 8, 8, Step.R));
		assertFalse(shape22.canMove(area, 8, 8, Step.UR));

		assertTrue(shape51.canMove(area, 5, 2, Step.U));
		assertFalse(shape51.canMove(area, 4, 3, Step.UL));
		assertFalse(shape51.canMove(area, 4, 3, Step.L));
		assertFalse(shape51.canMove(area, 4, 3, Step.DL));
		assertFalse(shape51.canMove(area, 0, 2, Step.D));
		assertFalse(shape51.canMove(area, 0, 1, Step.DR));
		assertFalse(shape51.canMove(area, 3, 1, Step.R));
		assertFalse(shape51.canMove(area, 5, 1, Step.UR));
	}

}
