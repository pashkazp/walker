package depavlo.walker.ui.util;

import org.springframework.stereotype.Component;

import depavlo.walker.service.IArea;
import depavlo.walker.service.impl.Area;

/**
 * The Class AreaMapper that convert current state of area to area for walker
 * finder.
 * 
 * @author Pavlo Degtyaryev
 */
@Component
public class AreaMapper {

	/**
	 * Task to area.
	 *
	 * @param area the area
	 * @return the iArea
	 */
	public IArea taskToArea(int[][] area) {
		if (area == null) {
			return null;
		}
		Area iArea = new Area(area.length, area[0].length);

		for (int i = 0; i < area.length; i++) {
			for (int j = 0; j < area[i].length; j++) {
				iArea.setFieldCost(i, j, area[i][j]);
			}
		}
		return iArea;
	}

}
