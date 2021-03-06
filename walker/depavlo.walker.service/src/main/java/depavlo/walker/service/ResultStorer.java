package depavlo.walker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import depavlo.walker.service.model.WalkWayFinderTask;

/**
 * The Interface ResultStorer.
 * 
 * @author Pavlo Degtyaryev
 */
@Service
public interface ResultStorer {

	/**
	 * Store task and result.
	 *
	 * @param area    the area
	 * @param start   the start Point
	 * @param finish  the finish Point
	 * @param shape   the walker shape
	 * @param stepSet the walker step set
	 * @param path    the path that was found
	 */
	void storeResult(WalkWayFinderTask task, List<Node> path);
}
