package depavlo.walker.ui.payload;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * The Class ShapeMessage that represent payload string that used for set shape
 * of walker.
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ShapeMessage {

	/** The shape string. */
	private String shape;
}
