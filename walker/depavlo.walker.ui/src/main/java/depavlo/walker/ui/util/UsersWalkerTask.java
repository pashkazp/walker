package depavlo.walker.ui.util;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import depavlo.walker.ui.payload.CellData;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * The UsersWalkerTask class stores the current task state in the user session.
 * 
 * @author Pavlo Degtyaryev
 */
@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UsersWalkerTask {

	/** The start Point. */
	private Point start;

	/** The finish Point */
	private Point finish;

	/** The step type. */
	private StepType stepType;

	/** The game area. */
	private int[][] area;

	/** The walker shape size. */
	private int shape;

	/** The counted walker path. */
	private final List<CellData> walkerPath = new LinkedList<>();

	/**
	 * Set initial state.
	 */
	@PostConstruct
	public void init() {
		stepType = StepType.ORTHOGONAL;
		shape = 1;
		area = new int[100][100];
	}

	// ...

	/**
	 * Destroy.
	 */
	@PreDestroy
	public void destroy() {
		start = null;
		finish = null;
		area = null;
	}

	/**
	 * Clear state.
	 */
	public void clear() {
		start = null;
		finish = null;
		walkerPath.clear();
		init();
	}

	/**
	 * Sets the start position.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void setStart(int row, int col) {
		if (start == null) {
			start = new Point();
		}
		start.setRowCol(row, col);
	}

	/**
	 * Sets the finish position.
	 *
	 * @param row the row
	 * @param col the col
	 */
	public void setFinish(int row, int col) {
		if (finish == null) {
			finish = new Point();
		}
		finish.setRowCol(row, col);
	}

}
