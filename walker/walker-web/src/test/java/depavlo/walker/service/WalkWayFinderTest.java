package depavlo.walker.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import depavlo.walker.service.impl.Area;
import depavlo.walker.service.impl.ManhattanHeuristicDistanceCalculator;
import depavlo.walker.service.impl.SquareShape;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepSetType;
import depavlo.walker.web.WalkerApplication;
import lombok.extern.slf4j.Slf4j;

@SpringBootTest(classes = { WalkerApplication.class })
@ExtendWith(SpringExtension.class)
@ContextConfiguration
@Slf4j
@DisplayName("Testing WalkWayFinder")
class WalkWayFinderTest {
	@Autowired
	private WalkWayFinder finder;

	@Test
	void test1() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(2, 0);
		Point finish = new Point(2, 4);
		WalkWayFinder finder = new WalkWayFinder(new ManhattanHeuristicDistanceCalculator());
		List<Node> steps = finder.findWay(area, start, finish, shape, StepSetType.ORTHOGONAL);
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertEquals(finish, stepsP.get(stepsP.size() - 1)));
	}

	@Test
	void test2() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 2);
		Point finish = new Point(4, 2);
		WalkWayFinder finder = new WalkWayFinder(new ManhattanHeuristicDistanceCalculator());
		List<Node> steps = finder.findWay(area, start, finish, shape, StepSetType.ORTHOGONAL);
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertEquals(finish, stepsP.get(stepsP.size() - 1)));
	}

	@Test
	void test3() {
		IArea area = new Area(5, 5);
		IShape shape = new SquareShape(1, 1);
		Point start = new Point(0, 0);
		Point finish = new Point(4, 4);
		WalkWayFinder finder = new WalkWayFinder(new ManhattanHeuristicDistanceCalculator());
		List<Node> steps = finder.findWay(area, start, finish, shape, StepSetType.ORTHOGONAL);
		List<Point> stepsP = steps.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList());
		assertAll(
				"The beginning of the list must coincide with the Start point and the end of the list must coincide with the Finish point.",
				() -> assertEquals(start, stepsP.get(0)),
				() -> assertEquals(finish, stepsP.get(stepsP.size() - 1)));
	}

}
