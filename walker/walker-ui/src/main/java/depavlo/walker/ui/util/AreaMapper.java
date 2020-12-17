package depavlo.walker.ui.util;

import org.springframework.stereotype.Component;

import depavlo.walker.service.IArea;
import depavlo.walker.service.impl.Area;

@Component
public class AreaMapper {

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
