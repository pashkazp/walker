package depavlo.walker.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import depavlo.walker.service.exception.ShapeOutOfBoundsException;
import depavlo.walker.service.impl.Area;
import depavlo.walker.service.impl.SquareShape;
import depavlo.walker.service.model.WalkWayFinderTask;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepSetType;
import depavlo.walker.web.WalkerApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = { WalkerApplication.class })
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Slf4j
@DisplayName("Testing WalkWayFinder")
class MoreComplexWalkWayFinderTest {
	WalkWayFinder finder;

	@BeforeEach
	void makeWalkWayFinder(@Autowired WalkWayFinder finder) {
		this.finder = finder;
	}

	@Test
	@DisplayName("Test Shape out of the Area")
	void test01() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(2, 2);
		Point start = new Point(5, 0);
		Point finish = new Point(2, 4);
		assertThrows(ShapeOutOfBoundsException.class,
				() -> {
					List<Node> steps = finder
							.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
				});
	}

	@Test
	@DisplayName("Test Shape out of the Area")
	void test02() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(2, 2);
		Point start = new Point(5, 0);
		Point finish = new Point(4, 2);
		assertThrows(ShapeOutOfBoundsException.class,
				() -> {
					List<Node> steps = finder
							.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
				});
	}

	@Test
	@DisplayName("Test Shape out of the Area")
	void test03() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(2, 2);
		Point start = new Point(5, 0);
		Point finish = new Point(4, 4);
		assertThrows(ShapeOutOfBoundsException.class,
				() -> {
					List<Node> steps = finder
							.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
				});
	}

	@Test
	@DisplayName("Simple horisontal path finder test with ORTHOGONAL step set")
	void test1() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(2, 2);
		Point start = new Point(2, 0);
		Point finish = new Point(2, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple vertical path finder test with ORTHOGONAL step set")
	void test2() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 2);
		Point finish = new Point(4, 2);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple first diagonal path finder test with ORTHOGONAL step set")
	void test3() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 0);
		Point finish = new Point(4, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple second diagonal path finder test with ORTHOGONAL step set")
	void test4() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 4);
		Point finish = new Point(4, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse horisontal path finder test with ORTHOGONAL step set")
	void test5() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(2, 4);
		Point finish = new Point(2, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse vertical path finder test with ORTHOGONAL step set")
	void test6() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 2);
		Point finish = new Point(0, 2);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse first diagonal path finder test with ORTHOGONAL step set")
	void test7() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 4);
		Point finish = new Point(0, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse second diagonal path finder test with ORTHOGONAL step set")
	void test8() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 0);
		Point finish = new Point(0, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple horisontal path finder test with CHESS step set")
	void test9() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(2, 0);
		Point finish = new Point(2, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple vertical path finder test with CHESS step set")
	void test10() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 2);
		Point finish = new Point(4, 2);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple first diagonal path finder test with CHESS step set")
	void test11() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 0);
		Point finish = new Point(4, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple second diagonal path finder test with CHESS step set")
	void test12() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 4);
		Point finish = new Point(4, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse horisontal path finder test with CHESS step set")
	void test13() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(2, 4);
		Point finish = new Point(2, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse vertical path finder test with CHESS step set")
	void test14() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 2);
		Point finish = new Point(0, 2);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse first diagonal path finder test with CHESS step set")
	void test15() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 4);
		Point finish = new Point(0, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse second diagonal path finder test with CHESS step set")
	void test16() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 0);
		Point finish = new Point(0, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple horisontal path finder test with OCTAGON step set")
	void test17() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(2, 0);
		Point finish = new Point(2, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple vertical path finder test with OCTAGON step set")
	void test18() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 2);
		Point finish = new Point(4, 2);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple first diagonal path finder test with OCTAGON step set")
	void test19() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 0);
		Point finish = new Point(4, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple second diagonal path finder test with OCTAGON step set")
	void test20() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 4);
		Point finish = new Point(4, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse horisontal path finder test with OCTAGON step set")
	void test21() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(2, 4);
		Point finish = new Point(2, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse vertical path finder test with OCTAGON step set")
	void test22() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 2);
		Point finish = new Point(0, 2);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse first diagonal path finder test with OCTAGON step set")
	void test23() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 4);
		Point finish = new Point(0, 0);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Simple reverse second diagonal path finder test with OCTAGON step set")
	void test24() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(4, 0);
		Point finish = new Point(0, 4);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Complex path finder test with ORTHOGONAL step set")
	void test25() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		IShape shape = new SquareShape(3, 3);
		Point start = new Point(17, 0);
		Point finish = new Point(0, 18);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Complex path finder test with ORTHOGONAL step set")
	void test26() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(15, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(16, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(17, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(18, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(19, 11, Integer.MAX_VALUE);

		((Area) area).setFieldCost(0, 19, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 18, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(1, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(2, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(4, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(5, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(6, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(7, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(8, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 17, Integer.MAX_VALUE);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(19, 0);
		Point finish = new Point(1, 15);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Complex path finder test with ORTHOGONAL step set")
	void test27() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(15, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(16, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(17, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(18, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(19, 11, Integer.MAX_VALUE);

		((Area) area).setFieldCost(0, 19, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 18, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(1, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(2, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(4, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(5, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(6, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(7, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(8, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 17, Integer.MAX_VALUE);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(19, 0);
		Point finish = new Point(2, 15);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.OCTAGON));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Complex path finder test with ORTHOGONAL step set")
	void test28() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(15, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(16, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(17, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(18, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(19, 11, Integer.MAX_VALUE);

		((Area) area).setFieldCost(0, 19, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 18, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(1, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(2, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(4, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(5, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(6, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(7, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(8, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 17, Integer.MAX_VALUE);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(19, 0);
		Point finish = new Point(1, 15);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Complex path finder test with ORTHOGONAL step set")
	void test29() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(15, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(16, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(17, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(18, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(19, 11, Integer.MAX_VALUE);

		((Area) area).setFieldCost(0, 19, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 18, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(1, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(2, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(4, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(5, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(6, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(7, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(8, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 17, Integer.MAX_VALUE);
		IShape shape = new SquareShape(2, 2);
		Point start = new Point(18, 0);
		Point finish = new Point(2, 15);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.ORTHOGONAL));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

	@Test
	@DisplayName("Complex path finder test with CHESS step set. (Not found)")
	void test30() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(15, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(16, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(17, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(18, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(19, 11, Integer.MAX_VALUE);

		((Area) area).setFieldCost(0, 19, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 18, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(1, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(2, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(4, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(5, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(6, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(7, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(8, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 17, Integer.MAX_VALUE);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(19, 0);
		Point finish = new Point(1, 15);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		assertNull(steps);
	}

	@Test
	@DisplayName("Complex path finder test with CHESS step set. (Found)")
	void test31() {
		IArea area = new Area(20, 20);
		((Area) area).setFieldCost(8, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 8, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 9, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 10, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(15, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(16, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(17, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(18, 11, Integer.MAX_VALUE);
		((Area) area).setFieldCost(19, 11, Integer.MAX_VALUE);

		((Area) area).setFieldCost(0, 19, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 18, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(0, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(1, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(2, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 14, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 15, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 16, Integer.MAX_VALUE);
		((Area) area).setFieldCost(3, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(4, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(5, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(6, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(7, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(8, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(9, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(10, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(11, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(12, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(13, 17, Integer.MAX_VALUE);
		((Area) area).setFieldCost(14, 17, Integer.MAX_VALUE);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(19, 0);
		Point finish = new Point(2, 15);
		List<Node> steps = finder.findWay(new WalkWayFinderTask(area, start, finish, shape, StepSetType.CHESS));
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertTrue(shape.coversPoint(stepsP.get(stepsP.size() - 1), finish)));
	}

}
