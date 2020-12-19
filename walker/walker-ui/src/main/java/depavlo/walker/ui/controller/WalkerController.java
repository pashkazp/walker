package depavlo.walker.ui.controller;

import java.util.Iterator;
import java.util.List;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import depavlo.walker.service.Node;
import depavlo.walker.service.WalkWayFinder;
import depavlo.walker.service.exception.WalkWayFinderTaskParamAuditException;
import depavlo.walker.service.impl.SquareShape;
import depavlo.walker.service.model.WalkWayFinderTask;
import depavlo.walker.ui.payload.CellData;
import depavlo.walker.ui.payload.PathCommand;
import depavlo.walker.ui.payload.ShapeMessage;
import depavlo.walker.ui.util.AreaMapper;
import depavlo.walker.ui.util.CalcFiller;
import depavlo.walker.ui.util.NodeMapper;
import depavlo.walker.ui.util.UsersWalkerTask;
import depavlo.walker.util.Point;
import depavlo.walker.util.StepSetType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Controller
@Slf4j
public class WalkerController {

	private final UsersWalkerTask userTask;
	private final SimpMessagingTemplate simpMessagingTemplate;

	private final CalcFiller calcFiller;
	private final NodeMapper nodeMapper;
	private final WalkWayFinder finder;
	private final AreaMapper areaMapper;

	@MessageMapping("/walker/task/setshape")
	public void setShape(@Payload ShapeMessage message) throws Exception {
		log.debug(message.toString());
		switch (message.getShape()) {
		case "square1":
			userTask.setShape(1);
			break;
		case "square2":
			userTask.setShape(2);
			break;
		case "square3":
			userTask.setShape(3);
			break;
		default:
			break;
		}
	}

	@MessageMapping("/walker/task/setsteptype")
	public void setStepType(@Payload String message) throws Exception {
		log.debug(message);
		switch (message) {
		case "ortho":
			userTask.setStepSet(StepSetType.ORTHOGONAL);
			break;
		case "octa":
			userTask.setStepSet(StepSetType.OCTAGON);
			break;
		case "chess":
			userTask.setStepSet(StepSetType.CHESS);
			break;
		default:
			break;
		}
	}

	@MessageMapping("/walker/task/clearall")
	public void clearTask(@Payload String message, @Header("simpSessionId") String sessionId) throws Exception {
		log.debug(message);
		switch (message) {
		case "clear":
			userTask.clear();
			userTask.setShape(1);
			simpMessagingTemplate.convertAndSend("/walker/commandclearall" + "-user" + sessionId, "clear");
		default:
			break;
		}
	}

	@MessageMapping("/walker/task/setcell")
	public void clearTask(@Payload CellData cellData, @Header("simpSessionId") String sessionId) throws Exception {
		log.debug(cellData.toString());
		String path = "/walker/commandsetcell" + "-user" + sessionId;
		int row = cellData.getCellNum() / 100;
		int col = cellData.getCellNum() % 100;
		log.debug("Set sell row:{} col:{}", row, col);
		int[][] area = userTask.getArea();
		Point start = userTask.getStart();
		Point finish = userTask.getFinish();
		Iterator<CellData> it = userTask.getWalkerPath().iterator();
		while (it.hasNext()) {
			CellData cd = it.next();
			if (cd.getCellNum() == row * 100 + col) {
				it.remove();
			}
		}
//		userTask.getWalkerPath().removeIf(cd->cd.)
		switch (cellData.getCellValue()) {
		case "s":
			if (start != null) {
				CellData oldCellData = new CellData(start.getRow() * 100 + start.getCol(),
						calcFiller.getFill(area, start.getRow(), start.getCol(), start, finish, "s"));
				simpMessagingTemplate.convertAndSend(path, oldCellData);
				if (start.getRow() == row && start.getCol() == col) {
					start = null;
					userTask.setStart(null);
				} else {
					start.setRowCol(row, col);
				}
				cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish, ""));
			} else {
				userTask.setStart(row, col);
				start = userTask.getStart();
				cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish, ""));
			}
			break;
		case "f":
			if (finish != null) {
				CellData oldCellData = new CellData(finish.getRow() * 100 + finish.getCol(),
						calcFiller.getFill(area, finish.getRow(), finish.getCol(), start, finish, "f"));
				simpMessagingTemplate.convertAndSend(path, oldCellData);
				if (finish.getRow() == row && finish.getCol() == col) {
					finish = null;
					userTask.setFinish(null);
				} else {
					finish.setRowCol(row, col);
				}
				cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish, ""));
			} else {
				userTask.setFinish(row, col);
				finish = userTask.getFinish();
				cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish, ""));
			}
			break;
		case "w":
			cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish, "w"));
			area[row][col] = Integer.MAX_VALUE;
			break;
		case " ":
			cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish, " "));
			area[row][col] = 0;
			break;
		default:
			break;
		}
		// cellData.setCellValue(calcFiller.getFill(area, row, col, start, finish,
		// cellData.getCellValue()));
		simpMessagingTemplate.convertAndSend(path, cellData);
	}

	@MessageMapping("/walker/task")
	@SendToUser(destinations = { "/walker/solutions" }, broadcast = false)
	public String send(Message message) throws Exception {
		log.debug(message.toString());
		log.debug(userTask.toString());
		// userTask.setCounter(userTask.getCounter() + 1);
		return message.toString();
	}

	@MessageMapping("/walker/task/execute")
	@SendToUser(destinations = { "/walker/commandfillpath" }, broadcast = false)
	public PathCommand execute(Message<String> message, @Header("simpSessionId") String sessionId) throws Exception {
		// log.debug(message.toString());
		// log.debug(userTask.toString());
		PathCommand payload = null;

		// if start point or finish point is null and walkerpath is exist then clear
		// path
		if ((userTask.getStart() == null || userTask.getFinish() == null) && userTask.getWalkerPath().size() > 0) {
			payload = new PathCommand("clear", userTask.getWalkerPath());
			simpMessagingTemplate.convertAndSend("/walker/commandfillpath" + "-user" + sessionId, payload);
			userTask.getWalkerPath().clear();
			return null;
		}

		WalkWayFinderTask task = new WalkWayFinderTask(areaMapper.taskToArea(userTask.getArea()), userTask.getStart(),
				userTask.getFinish(), new SquareShape(userTask.getShape(), userTask.getShape()), userTask.getStepSet());
		List<Node> nodePath = null;
		try {
			nodePath = finder.findWay(task);
		} catch (WalkWayFinderTaskParamAuditException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
		}

		if (userTask.getWalkerPath().size() > 0) {
			payload = new PathCommand("clear", userTask.getWalkerPath());
			simpMessagingTemplate.convertAndSend("/walker/commandfillpath" + "-user" + sessionId, payload);
			userTask.getWalkerPath().clear();
		}

		nodeMapper.nodeListToWalkerPath(nodePath, userTask.getShape(), userTask.getStart(), userTask.getFinish(),
				userTask.getWalkerPath());

		if (userTask.getWalkerPath().size() > 0) {
			payload = new PathCommand("set", userTask.getWalkerPath());
		}

		return payload;
	}
}
