package depavlo.walker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import depavlo.walker.service.impl.Area;
import depavlo.walker.service.impl.SquareShape;
import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import depavlo.walker.util.StepSetType;

class WalkerTest {

	@Test
	@DisplayName("Test sets of steps")
	void test1() {
		Area area = new Area(10, 10);
		SquareShape shape = new SquareShape(2, 2);
		Walker walkerOr = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		Walker walkerOc = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		Walker walkerCh = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);

		assertTrue(walkerOr.canBeMove(area, Step.U));
		assertFalse(walkerOr.canBeMove(area, Step.UL));
		assertTrue(walkerOr.canBeMove(area, Step.L));
		assertFalse(walkerOr.canBeMove(area, Step.DL));
		assertTrue(walkerOr.canBeMove(area, Step.D));
		assertFalse(walkerOr.canBeMove(area, Step.DR));
		assertTrue(walkerOr.canBeMove(area, Step.R));
		assertFalse(walkerOr.canBeMove(area, Step.UR));

		assertFalse(walkerCh.canBeMove(area, Step.U));
		assertTrue(walkerCh.canBeMove(area, Step.UL));
		assertFalse(walkerCh.canBeMove(area, Step.L));
		assertTrue(walkerCh.canBeMove(area, Step.DL));
		assertFalse(walkerCh.canBeMove(area, Step.D));
		assertTrue(walkerCh.canBeMove(area, Step.DR));
		assertFalse(walkerCh.canBeMove(area, Step.R));
		assertTrue(walkerCh.canBeMove(area, Step.UR));

		assertTrue(walkerOc.canBeMove(area, Step.U));
		assertTrue(walkerOc.canBeMove(area, Step.UL));
		assertTrue(walkerOc.canBeMove(area, Step.L));
		assertTrue(walkerOc.canBeMove(area, Step.DL));
		assertTrue(walkerOc.canBeMove(area, Step.D));
		assertTrue(walkerOc.canBeMove(area, Step.DR));
		assertTrue(walkerOc.canBeMove(area, Step.R));
		assertTrue(walkerOc.canBeMove(area, Step.UR));

	}

	@Test
	@DisplayName("Test simple moving")
	void test2() {
		SquareShape shape = new SquareShape(2, 2);
		Walker walkerOr = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		Walker walkerOc = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		Walker walkerCh = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);

		Area area = new Area(10, 10);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walkerOr.canBeMove(area, Step.U));
		assertFalse(walkerOr.canBeMove(area, Step.L));
		assertFalse(walkerOr.canBeMove(area, Step.D));
		assertFalse(walkerOr.canBeMove(area, Step.R));

		area = new Area(10, 10);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walkerCh.canBeMove(area, Step.UL));
		assertFalse(walkerCh.canBeMove(area, Step.DL));
		assertFalse(walkerCh.canBeMove(area, Step.DR));
		assertFalse(walkerCh.canBeMove(area, Step.UR));

		area = new Area(10, 10);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walkerOc.canBeMove(area, Step.U));
		assertFalse(walkerOc.canBeMove(area, Step.UL));
		assertFalse(walkerOc.canBeMove(area, Step.L));
		assertFalse(walkerOc.canBeMove(area, Step.DL));
		assertFalse(walkerOc.canBeMove(area, Step.D));
		assertFalse(walkerOc.canBeMove(area, Step.DR));
		assertFalse(walkerOc.canBeMove(area, Step.R));
		assertFalse(walkerOc.canBeMove(area, Step.UR));
	}

	@Test
	@DisplayName("Test moving if can for ORTHOGONAL")
	void test3() {
		Area area;
		Walker walker;
		SquareShape shape = new SquareShape(2, 2);
		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.U));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.U));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.U));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.U));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.U));

		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.L));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.L));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.L));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.L));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.L));

		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.D));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.D));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.D));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.D));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.D));

		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.R));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.R));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.R));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.R));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 5, 5);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.R));

		// ****************
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 0, 0);

		assertFalse(walker.moveIfCan(area, Step.U));

		assertFalse(walker.moveIfCan(area, Step.L));

		assertTrue(walker.moveIfCan(area, Step.D));

		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 0, 0);

		assertTrue(walker.moveIfCan(area, Step.R));

		// ****************
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 8, 0);

		assertFalse(walker.moveIfCan(area, Step.D));

		assertFalse(walker.moveIfCan(area, Step.L));

		assertTrue(walker.moveIfCan(area, Step.R));

		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 8, 0);

		assertTrue(walker.moveIfCan(area, Step.U));

		// ****************
		area = new Area(10, 10);

		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 0, 8);

		assertFalse(walker.moveIfCan(area, Step.R));

		assertFalse(walker.moveIfCan(area, Step.U));

		assertTrue(walker.moveIfCan(area, Step.D));

		walker = new Walker(shape, StepSetType.ORTHOGONAL.getSteps(), 0, 8);

		assertTrue(walker.moveIfCan(area, Step.L));

	}

	@Test
	@DisplayName("Test moving if can for CHESS")
	void test4() {
		Area area;
		Walker walker;
		SquareShape shape = new SquareShape(2, 2);

		// ===============
		walker = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);

		area = new Area(10, 10);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DL));
		area = new Area(10, 10);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DL));
		area = new Area(10, 10);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DL));

		area = new Area(10, 10);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DR));
		area = new Area(10, 10);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DR));
		area = new Area(10, 10);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DR));

		area = new Area(10, 10);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UR));
		area = new Area(10, 10);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UR));
		area = new Area(10, 10);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UR));

		area = new Area(10, 10);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UL));
		area = new Area(10, 10);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UL));
		area = new Area(10, 10);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UL));

		walker = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.DL));

		walker = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.DR));

		walker = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.UR));

		walker = new Walker(shape, StepSetType.CHESS.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.UL));

	}

	@Test
	@DisplayName("Test moving if can for OCTAGON")
	void test5() {
		Area area;
		Walker walker;
		SquareShape shape = new SquareShape(2, 2);
		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.U));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.U));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.U));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.U));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.U));

		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.L));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.L));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.L));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.L));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.L));

		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.D));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.D));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.D));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.D));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.D));

		// ===============
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		assertTrue(walker.moveIfCan(area, Step.R));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.R));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.R));

		// -----------
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.R));

		// +++++++++++
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.R));

		// ****************
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 0, 0);

		assertFalse(walker.moveIfCan(area, Step.U));

		assertFalse(walker.moveIfCan(area, Step.L));

		assertFalse(walker.moveIfCan(area, Step.UL));

		assertFalse(walker.moveIfCan(area, Step.UR));

		assertFalse(walker.moveIfCan(area, Step.DL));

		assertTrue(walker.moveIfCan(area, Step.D));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 0, 0);

		assertTrue(walker.moveIfCan(area, Step.R));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 0, 0);

		assertTrue(walker.moveIfCan(area, Step.DR));

		// ****************
		area = new Area(10, 10);
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 8, 0);

		assertFalse(walker.moveIfCan(area, Step.D));

		assertFalse(walker.moveIfCan(area, Step.L));

		assertFalse(walker.moveIfCan(area, Step.UL));

		assertFalse(walker.moveIfCan(area, Step.DL));

		assertFalse(walker.moveIfCan(area, Step.DR));

		assertTrue(walker.moveIfCan(area, Step.R));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 8, 0);

		assertTrue(walker.moveIfCan(area, Step.U));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 8, 0);

		assertTrue(walker.moveIfCan(area, Step.UR));

		// ****************
		area = new Area(10, 10);

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 0, 8);

		assertFalse(walker.moveIfCan(area, Step.R));

		assertFalse(walker.moveIfCan(area, Step.U));

		assertFalse(walker.moveIfCan(area, Step.UL));

		assertFalse(walker.moveIfCan(area, Step.UR));

		assertFalse(walker.moveIfCan(area, Step.DR));

		assertTrue(walker.moveIfCan(area, Step.D));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 0, 8);

		assertTrue(walker.moveIfCan(area, Step.L));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 0, 8);

		assertTrue(walker.moveIfCan(area, Step.DL));

	}

	@Test
	@DisplayName("Test moving if can for OCTAGON")
	void test6() {
		Area area;
		Walker walker;
		SquareShape shape = new SquareShape(2, 2);

		// ===============
		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);

		area = new Area(10, 10);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DL));
		area = new Area(10, 10);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DL));
		area = new Area(10, 10);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DL));

		area = new Area(10, 10);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DR));
		area = new Area(10, 10);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DR));
		area = new Area(10, 10);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.DR));

		area = new Area(10, 10);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UR));
		area = new Area(10, 10);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UR));
		area = new Area(10, 10);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UR));

		area = new Area(10, 10);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UL));
		area = new Area(10, 10);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UL));
		area = new Area(10, 10);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		assertFalse(walker.moveIfCan(area, Step.UL));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.DL));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.DR));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(5, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 5, Integer.MAX_VALUE);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.UR));

		walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);
		area = new Area(10, 10);
		area.setFieldCost(6, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 7, Integer.MAX_VALUE);
		area.setFieldCost(7, 6, Integer.MAX_VALUE);
		area.setFieldCost(7, 5, Integer.MAX_VALUE);
		area.setFieldCost(7, 4, Integer.MAX_VALUE);
		area.setFieldCost(6, 4, Integer.MAX_VALUE);
		area.setFieldCost(4, 6, Integer.MAX_VALUE);
		area.setFieldCost(4, 7, Integer.MAX_VALUE);
		area.setFieldCost(5, 7, Integer.MAX_VALUE);
		assertTrue(walker.moveIfCan(area, Step.UL));
	}

	@Test
	@DisplayName("Test getNeighborPoint")
	void test7() {
		SquareShape shape = new SquareShape(2, 2);
		Walker walker = new Walker(shape, StepSetType.OCTAGON.getSteps(), 5, 5);

		assertEquals(new Point(6, 5), walker.getNeighborPoint(Step.D));
		assertEquals(new Point(6, 6), walker.getNeighborPoint(Step.DR));
		assertEquals(new Point(5, 6), walker.getNeighborPoint(Step.R));
		assertEquals(new Point(4, 6), walker.getNeighborPoint(Step.UR));
		assertEquals(new Point(4, 5), walker.getNeighborPoint(Step.U));
		assertEquals(new Point(4, 4), walker.getNeighborPoint(Step.UL));
		assertEquals(new Point(5, 4), walker.getNeighborPoint(Step.L));
		assertEquals(new Point(6, 4), walker.getNeighborPoint(Step.DL));

	}
}
