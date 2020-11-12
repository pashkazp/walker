package depavlo.walker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import depavlo.walker.util.Point;
import depavlo.walker.util.StepSetType;

@Service
public interface ResultStorer {
	void storeResult(IArea area, Point start, Point finish, IShape shape, StepSetType stepSet,
			List<Node> path);
}
