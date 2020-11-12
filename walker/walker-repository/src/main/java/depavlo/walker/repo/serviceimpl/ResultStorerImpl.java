package depavlo.walker.repo.serviceimpl;

import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.service.Node;
import depavlo.walker.service.ResultStorer;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepSetType;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResultStorerImpl implements ResultStorer {

	@Override
	public void storeResult(IArea area, Point start, Point finish, IShape shape, StepSetType stepSet, List<Node> path) {
		log.info("\n========================================== RESULT =============================================");
		log.info("Start Point: {}", start);
		log.info("Finish Point: {}", finish);
		log.info("Walker Shape height:width is: {}:{}", shape.getHeight(), shape.getWidth());
		log.info("Walker Step Set: {}", stepSet.name());
		if (path == null) {
			log.info("Found way length: nothing");
		} else {
			log.info("Found way length: {}", path.size());
			log.info("Way: {}", Arrays.deepToString(path.toArray()));
		}
		log.info("Area with path: \n{}", convertAreaToString(area, start, finish, shape, stepSet, path));
		log.info("\n======================================== END RESULT ===========================================");

	}

	private String convertAreaToString(IArea area, Point start, Point finish, IShape shape, StepSetType stepSet,
			List<Node> path) {
		StringBuilder builder = new StringBuilder();
		builder.append("   ");
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
