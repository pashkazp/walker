package depavlo.walker.repo.serviceimpl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.service.Node;
import depavlo.walker.service.ResultStorer;
import depavlo.walker.service.model.WalkWayFinderTask;
import depavlo.walker.util.Point;
import lombok.extern.slf4j.Slf4j;

/**
 * Implementation of result save service Interface. Simple service that store
 * task and result of searching
 * 
 * @author Pavlo Degtyaryev
 * 
 */
@Slf4j
@Service
public class ResultStorerImpl implements ResultStorer {

	/**
	 * Store task and result.
	 *
	 * @param area    the work area
	 * @param start   the start Point
	 * @param finish  the finish Point
	 * @param shape   the Walker shape of Walker
	 * @param stepSet the Walker step set
	 * @param path    the founded path
	 */
	@Override
	public void storeResult(WalkWayFinderTask task, List<Node> path) {
		if (task == null) {
			throw new IllegalArgumentException("Task must be not null.");
		}
		log.info("\n========================================== RESULT =============================================");
		log.info("Start Point: {}", task.getStart());
		log.info("Finish Point: {}", task.getFinish());
		log.info("Walker Shape height:width is: {}:{}", task.getShape().getHeight(), task.getShape().getWidth());
		log.info("Walker Step Set: {}", task.getStepSet().name());
		if (path == null) {
			log.info("Found way length: nothing");
		} else {
			log.info("Found way length: {}", path.size());
			log.info("Way: {}", Arrays.deepToString(path.toArray()));
		}
		log.info("Area with path: \n{}", convertAreaToString(task, path));
		log.info("\n======================================== END RESULT ===========================================");

	}

	/**
	 * Make Picture of result Area
	 *
	 * @param area    the working area
	 * @param start   the start Point
	 * @param finish  the finish Point
	 * @param shape   the walker shape
	 * @param stepSet the walker step set
	 * @param path    the founded path
	 * @return the string
	 */
	private String convertAreaToString(WalkWayFinderTask task, List<Node> path) {
		StringBuilder builder = new StringBuilder();
		builder.append("   ");
		IArea area = task.getArea();
		Point start = task.getStart();
		Point finish = task.getFinish();
		IShape shape = task.getShape();
		for (int i = 0; i < area.getColsCount(); i++) {
			builder.append(StringUtils.rightPad(Integer.toString(i), 2, ' '));
		}
		builder.append("\n   ");
		builder.append(StringUtils.repeat('+', area.getColsCount() * 2)).append("\n");
		for (int row = 0; row < area.getRowsCount(); row++) {
			builder.append(StringUtils.leftPad(Integer.toString(row), 2, ' ')).append("[ ");
			for (int col = 0; col < area.getColsCount(); col++) {
				if (start.getCol() == col && start.getRow() == row) {
					builder.append("S ");
					continue;
				}
				if (finish.getCol() == col && finish.getRow() == row) {
					builder.append("F ");
					continue;
				}
				int rowv = row;
				int colv = col;

				if (path.stream().filter((n) -> {
					return shape.coversPoint(n.getPoint(), rowv, colv);
				}).count() > 0) {
					builder.append("* ");
				} else {
					int cell = area.getFieldCost(row, col);
					builder.append(cell == Integer.MAX_VALUE ? "W " : ". ");
				}
			}
			builder.deleteCharAt(builder.length() - 1).append("]\n");
		}
		builder.append("   ").append(StringUtils.repeat('+', area.getColsCount() * 2));
		return builder.toString();
	}

}
