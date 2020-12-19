/*
 * 
 */
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
import lombok.NoArgsConstructor;
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
@NoArgsConstructor
public class ResultStorerImpl implements ResultStorer {

	/**
	 * Store result.
	 *
	 * @param task the users WalkWayFinder task
	 * @param path the list of Node
	 */
	@Override
	public void storeResult(final WalkWayFinderTask task, final List<Node> path) {
		if (task == null) {
			throw new IllegalArgumentException("Task must be not null.");
		}
		log.info("\n========================================== RESULT =============================================");
		log.info("Start Point: {}", task.getStart());
		log.info("Finish Point: {}", task.getFinish());
		log.info("Walker Shape height:width is: {}:{}", task.getShape().getHeight(), task.getShape().getWidth()); // NOPMD
																													// by
																													// Pavlo
																													// Degtyaryev
																													// on
																													// 17.12.20,
																													// 14:58
		log.info("Walker Step Set: {}", task.getStepSet().name()); // NOPMD by Pavlo Degtyaryev on 17.12.20, 15:00
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
	 * Convert walker area to single string.
	 *
	 * @param task the users WalkWayFinder task
	 * @param path the Node list
	 * @return the string
	 */
	private String convertAreaToString(final WalkWayFinderTask task, final List<Node> path) {
		final StringBuilder builder = new StringBuilder();
		builder.append("   ");
		final IArea area = task.getArea();
		final Point start = task.getStart();
		final Point finish = task.getFinish();
		final IShape shape = task.getShape();
		for (int i = 0; i < area.getColsCount(); i++) {
			builder.append(StringUtils.rightPad(Integer.toString(i), 2, ' '));
		}
		builder.append("\n   ")
				.append(StringUtils.repeat('+', area.getColsCount() * 2)).append("\n"); // NOPMD by Pavlo Degtyaryev on 17.12.20, 15:03
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
				final int rowv = row;
				final int colv = col;

				if (path.stream().filter((n) -> { // NOPMD by Pavlo Degtyaryev on 17.12.20, 14:57
					return shape.coversPoint(n.getPoint(), rowv, colv);
				}).count() > 0) {
					builder.append("* ");
				} else {
					final int cell = area.getFieldCost(row, col);
					builder.append(cell == Integer.MAX_VALUE ? "W " : ". ");
				}
			}
			builder.deleteCharAt(builder.length() - 1).append("]\n");
		}
		builder.append("   ").append(StringUtils.repeat('+', area.getColsCount() * 2)); // NOPMD by Pavlo Degtyaryev on 17.12.20, 15:03
		return builder.toString();
	}

}
