package depavlo.walker.ui.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import depavlo.walker.util.StepSetType;

class UsersWalkerTaskTest {
	UsersWalkerTask task;

	@BeforeEach
	void init() {
		task = new UsersWalkerTask();
		task.init();
	}

	@Test
	@DisplayName("UsersWalkerTask")
	void test1() {
		// UsersWalkerTask task = new UsersWalkerTask();

		assertEquals(1, task.getShape(), "Expected shape size 1");
		assertEquals(StepSetType.ORTHOGONAL, task.getStepSet(), "Expected step type ORTHOGONAL");
		assertNotNull(task.getArea(), "Expected initialized area");
		assertEquals(100, task.getArea().length, "expected 100 rows of area");
		assertEquals(100, task.getArea()[0].length, "expected 100 cols of area");
		assertNull(task.getStart(), "Expected nulled Start point");
		assertNull(task.getFinish(), "Expected nulled Finish point");

		task.setStart(1, 2); // initialize Start point
		assertNotNull(task.getStart(), "Expected non nulled Start point");
		assertNull(task.getFinish(), "Expected nulled Finish point");
		assertEquals(1, task.getStart().getRow(), "Expected row position 1 of start point");
		assertEquals(2, task.getStart().getCol(), "Expected col position 2 of start point");

		task.clear();// clear task
		assertNull(task.getStart(), "Expected nulled Start point");
		assertNull(task.getFinish(), "Expected nulled Finish point");

		task.setFinish(1, 2); // initialize finish point
		assertNotNull(task.getFinish(), "Expected non nulled Finish point");
		assertNull(task.getStart(), "Expected nulled Start point");
		assertEquals(1, task.getFinish().getRow(), "Expected row position 1 of finish point");
		assertEquals(2, task.getFinish().getCol(), "Expected col position 2 of finish point");

		task.clear();// clear task
		assertNull(task.getStart(), "Expected nulled Start point");
		assertNull(task.getFinish(), "Expected nulled Finish point");

	}

}
