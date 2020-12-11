package depavlo.walker.ui.controller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;

import depavlo.walker.ui.payload.CellData;
import depavlo.walker.ui.payload.ShapeMessage;
import depavlo.walker.ui.util.CalcFiller;
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
}
