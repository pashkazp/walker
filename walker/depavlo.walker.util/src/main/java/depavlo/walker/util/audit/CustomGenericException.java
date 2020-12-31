
package depavlo.walker.util.audit;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The Class CustomGenericException. Extends RuntimeExxception and have more
 * information about Exception.
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@NoArgsConstructor
public class CustomGenericException extends RuntimeException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -4902468204270195408L;

	/** The err code. */
	private String errCode = "";

	/** The err msg. */
	private String errMsg = "";

	/** The err msg ext. */
	private String errMsgExt = "";

	/**
	 * Instantiates a new custom generic exception.
	 *
	 * @param errCode the err code
	 * @param errMsg  the err msg
	 */
	public CustomGenericException(String errCode, String errMsg) {
		super();
		this.errCode = StringUtils.defaultString(errCode);
		this.errMsg = StringUtils.defaultString(errMsg);
	}

	/**
	 * Instantiates a new custom generic exception.
	 *
	 * @param errCode   the err code
	 * @param errMsg    the err msg
	 * @param errMsgExt the err msg ext
	 */
	public CustomGenericException(String errCode, String errMsg, String errMsgExt) {
		super();
		this.errCode = StringUtils.defaultString(errCode);
		this.errMsg = StringUtils.defaultString(errMsg);
		this.errMsgExt = StringUtils.defaultString(errMsgExt);
	}
}
