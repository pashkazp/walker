package depavlo.walker.service;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import depavlo.walker.util.Point;

class NodeTest {

	@Test
	@DisplayName("Test IllegalArgumentException on setDistance and setEdistance")
	void test1() {
		Node node = new Node();
		assertThrows(IllegalArgumentException.class, () -> node.setDistance(-1));
		assertThrows(IllegalArgumentException.class, () -> node.setDistance(-10));
		assertThrows(IllegalArgumentException.class, () -> node.setEdistance(-1));
		assertThrows(IllegalArgumentException.class, () -> node.setEdistance(-10));
		assertDoesNotThrow(() -> node.setDistance(0));
		assertDoesNotThrow(() -> node.setDistance(1));
		assertDoesNotThrow(() -> node.setDistance(10));
		assertDoesNotThrow(() -> node.setEdistance(0));
		assertDoesNotThrow(() -> node.setEdistance(1));
		assertDoesNotThrow(() -> node.setEdistance(10));
	}

	@Test
	@DisplayName("Test correct change X Y a parameters by Point")
	void test2() {
		Node node = new Node();
		Point point = new Point(1, 2);
		node.setPoint(point);
		point.setRowCol(3, 4);

		assertEquals(1, node.getPoint().getRow());
		assertEquals(2, node.getPoint().getCol());
	}

	@Test
	@DisplayName("Test correct change distance edistance and weight")
	void test3() {
		Node node = new Node();
		assertEquals(0, node.getDistance());
		assertEquals(0, node.getEdistance());
		assertEquals(0, node.getWeight());
		node.setDistance(10);
		assertEquals(10, node.getDistance());
		assertEquals(0, node.getEdistance());
		assertEquals(10, node.getWeight());
		node.setEdistance(10);
		assertEquals(10, node.getDistance());
		assertEquals(10, node.getEdistance());
		assertEquals(20, node.getWeight());
	}
}
