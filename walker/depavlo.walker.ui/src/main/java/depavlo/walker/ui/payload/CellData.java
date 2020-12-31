package depavlo.walker.ui.payload;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class CellData that represent single item of Browser fields.
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
public class CellData {
	private int cellNum;
	private String cellValue = " ";
}
