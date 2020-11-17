package depavlo.walker.service.impl;

import org.springframework.stereotype.Component;

import depavlo.walker.service.IArea;
import depavlo.walker.service.IShape;
import depavlo.walker.service.audit.IWalkWayFinderFindParamsAuditor;
import depavlo.walker.service.model.WalkWayFinderTask;
import depavlo.walker.util.Point;
import depavlo.walker.util.audit.AuditResponse;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Component
@ToString
@EqualsAndHashCode
public class WalkWayFinderFindParamsAuditorImpl implements IWalkWayFinderFindParamsAuditor {

	@Override
	public AuditResponse validate(WalkWayFinderTask task, AuditResponse result) {
		if (result == null) {
			result = new AuditResponse();
			result.setValid(true);
		}
		if (task == null
				|| task.getArea() == null
				|| task.getStart() == null
				|| task.getFinish() == null
				|| task.getShape() == null
				|| task.getStepSet() == null) {
			result.setValid(false);
			result.addMessage("", "All required params must be set");
			return result;
		}

		IArea area = task.getArea();
		Point start = task.getStart();
		Point finish = task.getFinish();
		IShape shape = task.getShape();

		if (start.getRow() > area.getRowsCount() || start.getCol() > area.getColsCount()) {
			result.setValid(false);
			result.addMessage("start", "The starting point must be inside the playing area.");
		}

		if (finish.getRow() > area.getRowsCount() || finish.getCol() > area.getColsCount()) {
			result.setValid(false);
			result.addMessage("finish", "The finish point must be inside the playing area.");
		}

		if (shape.getHeight() > area.getRowsCount() || shape.getWidth() > area.getColsCount()) {
			result.setValid(false);
			result.addMessage("shape", "The shape must fit within the playing area.");
		}

		return result;
	}

}
