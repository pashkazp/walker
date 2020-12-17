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
import depavlo.walker.util.StepSetType;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Component
@Scope(scopeName = "websocket", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UsersWalkerTask {

	private Point start;
	private Point finish;
	private StepSetType stepSet;
	private int[][] area;
	private int shape;
	private final List<CellData> walkerPath = new LinkedList<>();

	@PostConstruct
	public void init() {
		stepSet = StepSetType.ORTHOGONAL;
		shape = 1;
		area = new int[100][100];
	}

	// ...

	@PreDestroy
	public void destroy() {
		start = null;
		finish = null;
		area = null;
	}

	public void clear() {
		start = null;
		finish = null;
		walkerPath.clear();
		init();
	}

	public void setStart(int row, int col) {
		if (start == null) {
			start = new Point();
		}
		start.setRowCol(row, col);
	}

	public void setFinish(int row, int col) {
		if (finish == null) {
			finish = new Point();
		}
		finish.setRowCol(row, col);
	}

}
