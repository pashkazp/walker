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

import depavlo.walker.util.Point;
import depavlo.walker.util.Step;
import depavlo.walker.util.StepSetType;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;

@Component
@ToString
@EqualsAndHashCode
@Slf4j
public class WalkWayFinder {

	private final HeuristicDistance heuristicDistance;
	private final ResultStorer storer;

	private Walker walker;
	private final Table<Integer, Integer, Node> tableWhite;
	// private final Table<Integer, Integer, Node> tableBlack;

	public WalkWayFinder(HeuristicDistance heuristicDistance, ResultStorer storer) {
		this.heuristicDistance = heuristicDistance;
		this.storer = storer;
		tableWhite = HashBasedTable.create();
		// tableBlack = HashBasedTable.create();
	}

	public List<Node> findWay(IArea area, Point start, Point finish, IShape shape, StepSetType stepSet) {
		tableWhite.clear();
		walker = new Walker(shape, stepSet.getSteps());
		if (!walker.putIfCan(area, start)) {
			return null;
		}
		log.info("\n========================================= NEW TASK ============================================");
		log.info("Try find way on area: \n{}", cycleToString(area, start, finish, shape));
		log.info("Start point: {}", start);
		log.info("Finish point: {}", finish);
		log.info("Shape is: {}", shape);
		log.info("Steps set: {}", stepSet.name());

		if (walker.arrived(finish)) {
			return Collections.emptyList();
		}

		Node currentNode = new Node.NodeBuilder()
				.comeDirect(Step.S)
				.point(start)
				.distance(0)
				.edistance(heuristicDistance.getDistance(area, start, finish))
				.build();

		tableWhite.put(start.getRow(), start.getCol(), currentNode);
		int maxIterations = area.getRowsCount() * area.getColsCount();
		while (maxIterations > 0 && !walker.arrived(finish) && !tableWhite.isEmpty()) {

			calculateWalkerNeighbors(area, currentNode, walker, finish);
			log.debug("Calculated Neighbors: \n{}", cycleToString(area, start, finish, shape));

			Optional<Node> node = getSmaller();
			log.debug("Smaller Neighbor: {}", node);
			if (node.isEmpty()) {
				return null;
			}
			currentNode = node.get();
			if (!walker.putIfCan(area, currentNode.getPoint())) {
				return null;
			}
			maxIterations--;
		}
		log.debug("Walker is stopped at Point: {}", walker.getPosition());
		if (walker.arrived(finish)) {
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
			storer.storeResult(area, start, finish, shape, stepSet, path);
			return path;
		}

		return null;
	}

	private Optional<Node> getSmaller() {
		Collection<Node> nodes = tableWhite.values();

		Optional<Node> minNode = nodes.stream().filter((n) -> !n.isProcessed())
				.min(Comparator.comparingInt(Node::getWeight));

		return minNode;
	}

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
