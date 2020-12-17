package depavlo.walker.ui.util;

import java.util.List;

import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import depavlo.walker.service.Node;
import depavlo.walker.ui.payload.CellData;
import depavlo.walker.util.Point;

@Component
public class NodeMapper {

	public void nodeListToWalkerPath(List<Node> source, int shape, Point start, Point finish,
			List<CellData> walkerPath) {
		if (source == null) {
			walkerPath.clear();
			return;
		}
		Table<Integer, Integer, String> map = HashBasedTable.create();
		for (Node node : source) {
			int row = node.getPoint().getRow();
			int col = node.getPoint().getCol();
			if (map.contains(row, col) || start.equals(node.getPoint()) || finish.equals(node.getPoint())) {
				continue;
			}
			map.put(row, col, "p");
		}
		for (Node node : source) {
			int row = node.getPoint().getRow();
			int col = node.getPoint().getCol();
			for (int i = 1; i < shape; i++) {
				if (map.contains(row, col + i) || (start.getRow() == row && start.getCol() == (col + i))
						|| (finish.getRow() == row && finish.getCol() == (col + i))) {
					continue;
				}
				map.put(row, col + i, "pe");
			}
			for (int j = 1; j < shape; j++) {
				for (int i = 0; i < shape; i++) {
					if (map.contains(row + j, col + i) ||
							(start.getRow() == (row + j) && start.getCol() == (col + i))
							|| (finish.getRow() == (row + j) && finish.getCol() == (col + i))) {
						continue;
					}
					map.put(row + j, col + i, "pe");
				}
			}
		}
		map.cellSet().forEach((cell) -> walkerPath
				.add(new CellData(cell.getRowKey() * 100 + cell.getColumnKey(), cell.getValue())));

	}

}
