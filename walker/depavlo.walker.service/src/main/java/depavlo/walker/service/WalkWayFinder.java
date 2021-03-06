package depavlo.walker.service;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import depavlo.walker.service.audit.IWalkWayFinderFindParamsAuditor;
import depavlo.walker.service.exception.WalkWayFinderTaskParamAuditException;
import depavlo.walker.service.model.WalkWayFinderTask;
import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import depavlo.walker.util.audit.AuditResponse;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

/**
 * The Class WalkWayFinder.
 * 
 * @author Pavlo Degtyaryev
 */
@Component
@ToString
@EqualsAndHashCode
@Slf4j
public class WalkWayFinder {

	/** The heuristic distance. */
	private final HeuristicDistance heuristicDistance;

	/** The Result storer. */
	// private final ResultStorer storer;

	/** The walker. */
	private Walker walker;

	/** The table white. */
	private final Table<Integer, Integer, Node> tableWhite;

	private final IWalkWayFinderFindParamsAuditor paramAuditor;

	/**
	 * Instantiates a new walk way finder.
	 *
	 * @param heuristicDistance the heuristic distance
	 * @param storer            the storer
	 */
	public WalkWayFinder(HeuristicDistance heuristicDistance, ResultStorer storer,
			IWalkWayFinderFindParamsAuditor paramAuditor) {
		this.heuristicDistance = heuristicDistance;
		// this.storer = storer;
		this.paramAuditor = paramAuditor;
		tableWhite = HashBasedTable.create();
	}

	/**
	 * Find way.
	 *
	 * @param area    the area
	 * @param start   the start
	 * @param finish  the finish
	 * @param shape   the shape
	 * @param stepSet the step set
	 * @return the list
	 */
	public List<Node> findWay(WalkWayFinderTask task) {
		AuditResponse response = paramAuditor.validate(task, null);
		if (response.isInvalid()) {
			// log.warn("The path search task settings are incorrect: {}", task);
			WalkWayFinderTaskParamAuditException ex = new WalkWayFinderTaskParamAuditException(response);
			ex.setErrMsg("Param is wrong.");
			ex.setErrMsgExt("The path search task settings are incorrect.");
			throw ex;
		}
		tableWhite.clear();
		walker = new Walker(task.getShape(), task.getStepSet().getSteps());
		if (!walker.putIfCan(task.getArea(), task.getStart())) {
			return null;
		}
		log.info("\n========================================= NEW TASK ============================================");
		log.debug("Try find way on area: \n{}",
				cycleToString(task.getArea(), task.getStart(), task.getFinish(), task.getShape()));
		log.info("Start point: {}", task.getStart());
		log.info("Finish point: {}", task.getFinish());
		log.info("Shape is: {}", task.getShape());
		log.info("Steps set: {}", task.getStepSet().name());

		if (walker.arrived(task.getFinish())) {
			return Collections.emptyList();
		}

		Node currentNode = new Node.NodeBuilder()
				.comeDirect(Step.S)
				.point(task.getStart())
				.distance(0)
				.edistance(heuristicDistance.getDistance(task.getArea(), task.getStart(), task.getFinish()))
				.build();

		tableWhite.put(task.getStart().getRow(), task.getStart().getCol(), currentNode);
		int maxIterations = task.getArea().getRowsCount() * task.getArea().getColsCount();
		while (maxIterations > 0 && !walker.arrived(task.getFinish()) && !tableWhite.isEmpty()) {

			calculateWalkerNeighbors(task.getArea(), currentNode, walker, task.getFinish());
			log.debug("Calculated Neighbors: \n{}",
					cycleToString(task.getArea(), task.getStart(), task.getFinish(), task.getShape()));

			Optional<Node> node = getSmaller();
			log.debug("Smaller Neighbor: {}", node);
			if (node.isEmpty()) {
				return null;
			}
			currentNode = node.get();
			if (!walker.putIfCan(task.getArea(), currentNode.getPoint())) {
				return null;
			}
			maxIterations--;
		}
		log.debug("Walker is stopped at Point: {}", walker.getPosition());
		if (walker.arrived(task.getFinish())) {
			List<Node> path = new LinkedList<>();
			currentNode = tableWhite.get(walker.getPositionRow(), walker.getPositionCol());
			currentNode.setComeDirect(Step.F);
			path.add(currentNode);
			do {
				currentNode = currentNode.getComeFrom();
				path.add(currentNode);
			} while (currentNode.getComeDirect() != Step.S);
			Collections.reverse(path);
			log.debug("Path: {}", path.stream().map((n) -> new Point(n.getPoint())).collect(Collectors.toList()));
			// storer.storeResult(task, path);
			return path;
		}

		return null;
	}

	/**
	 * Gets the smaller unprocessed Node.
	 *
	 * @return the smaller
	 */
	private Optional<Node> getSmaller() {
		Collection<Node> nodes = tableWhite.values();

		Optional<Node> minNode = nodes.stream().filter((n) -> !n.isProcessed())
				.min(Comparator.comparing(Node::getWeight).thenComparing(Node::getEdistance));

		return minNode;
	}

	/**
	 * Calculate walker neighbors nodes.
	 *
	 * @param area        the area
	 * @param currentNode the current node
	 * @param walker      the walker
	 * @param finish      the finish
	 */
	private void calculateWalkerNeighbors(IArea area, Node currentNode, Walker walker, Point finish) {
		for (Step step : walker.getSteps()) {
			if (walker.canBeMove(area, step)) {
				Point point = walker.getNeighborPoint(step);
				Node node = tableWhite.get(point.getRow(), point.getCol());
				if (node != null && node.isProcessed()) {
					continue;
				}
				int newDistance = currentNode.getDistance() + Step.getStepPrice(step);
				if (node == null) {
					node = new Node.NodeBuilder()
							.comeDirect(Step.getOppositeStep(step))
							.comeFrom(tableWhite.get(walker.getPositionRow(), walker.getPositionCol()))
							.distance(newDistance)
							.edistance(heuristicDistance.getDistance(area, point, finish))
							.point(point)
							.build();
					tableWhite.put(point.getRow(), point.getCol(), node);
				} else {
					if (node.getDistance() > newDistance) {
						node.setDistance(newDistance);
						node.setComeDirect(Step.getOppositeStep(step));
						node.setComeFrom(tableWhite.get(walker.getPositionRow(), walker.getPositionCol()));
					}
				}
			}
		}
		currentNode.setProcessed(true);
	}

	/**
	 * Convert current state to string
	 *
	 * @param area   the area
	 * @param start  the start
	 * @param finish the finish
	 * @param shape  the shape
	 * @return the string
	 */
	private String cycleToString(IArea area, Point start, Point finish, IShape shape) {
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
				if (walker.getPositionCol() >= col
						&& walker.getPositionCol() + walker.getShape().getWidth() < col
						&& walker.getPositionRow() >= row
						&& walker.getPositionRow() + walker.getShape().getHeight() < row) {
					builder.append("* ");
				} else if (tableWhite.contains(row, col)) {
					builder.append("+ ");
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
