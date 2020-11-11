package depavlo.walker.util;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PointTest {

	@Test
	@DisplayName("Test IllegalArgumentException")
	void test1() {
		assertThrows(IllegalArgumentException.class, () -> new Point(-1, 0));
		assertThrows(IllegalArgumentException.class, () -> new Point(-1, 10));
		assertThrows(IllegalArgumentException.class, () -> new Point(0, -1));
		assertThrows(IllegalArgumentException.class, () -> new Point(10, -1));
		assertDoesNotThrow(() -> new Point(0, 0));
		assertDoesNotThrow(() -> new Point(1, 0));
		assertDoesNotThrow(() -> new Point(10, 0));
		assertDoesNotThrow(() -> new Point(0, 1));
		assertDoesNotThrow(() -> new Point(0, 10));
		assertDoesNotThrow(() -> new Point(10, 10));
	}

	@Test
	@DisplayName("Test correct initialization using Point as a parameter")
	void test2() {
		Point point1 = new Point(0, 0);
		Point point2 = new Point(point1);
		point1.setRowCol(1, 1);

		assertNotEquals(point2.getCol(), point1.getCol());
		assertNotEquals(point2.getRow(), point1.getRow());
	}

	@Test
	@DisplayName("Test correct initialization by Y X parameters")
	void test3() {
		Point point1 = new Point(1, 2);

		assertEquals(1, point1.getRow());
		assertEquals(2, point1.getCol());
	}

	@Test
	@DisplayName("Test correct initialization X Y using Point as a parameter")
	void test4() {
		Point point1 = new Point(1, 2);
		Point point2 = new Point(point1);

		assertEquals(1, point2.getRow());
		assertEquals(2, point2.getCol());
	}

	@Test
	@DisplayName("Test correct creation Point without parameters")
	void test5() {
		Point point1 = new Point();

		assertEquals(0, point1.getRow());
		assertEquals(0, point1.getCol());
	}

	@Test
	@DisplayName("Test correct change X Y a parameters")
	void test6() {
		Point point = new Point();

		assertThrows(IllegalArgumentException.class, () -> point.setRow(-1));
		assertThrows(IllegalArgumentException.class, () -> point.setRow(-10));
		assertThrows(IllegalArgumentException.class, () -> point.setCol(-1));
		assertThrows(IllegalArgumentException.class, () -> point.setCol(-10));
		assertThrows(IllegalArgumentException.class, () -> point.setRowCol(0, -1));
		assertThrows(IllegalArgumentException.class, () -> point.setRowCol(0, -10));
		assertThrows(IllegalArgumentException.class, () -> point.setRowCol(-1, -1));
		assertThrows(IllegalArgumentException.class, () -> point.setRowCol(-10, -10));
		assertThrows(IllegalArgumentException.class, () -> point.setRowCol(-1, 0));
		assertThrows(IllegalArgumentException.class, () -> point.setRowCol(-10, 0));
	}

	@Test
	@DisplayName("Test correct change X Y a parameters by Point")
	void test7() {
		Point point1 = new Point();
		Point point2 = new Point(1, 2);
		point1.setRowCol(point2);

		assertEquals(2, point1.getCol());
		assertEquals(1, point1.getRow());
	}

}
