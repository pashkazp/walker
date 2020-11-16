
package depavlo.walker.util.audit;

import java.io.Serializable;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * A class that is the represent of a data type auditor.
 * 
 * @author Pavlo Degtyaryev
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AuditResponse implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4622666897725203725L;

	/** the truth means that all checks have been completed successfully. */
	private boolean valid;

	/** The map of messages where field name is key. */
	private Multimap<String, String> messages = TreeMultimap.create();

	/**
	 * Adds the error message.
	 *
	 * @param key     the field name
	 * @param message the error message
	 */
	public void addMessage(String key, String message) {
		messages.put(key, message);
	}

	/**
	 * Checks if audit response list is empty.
	 *
	 * @return true, if is empty
	 */
	public boolean isEmpty() {
		return messages.isEmpty();
	}

	/**
	 * Return current validation status
	 *
	 * @return true, if is invalid
	 */
	public boolean isInvalid() {
		return !isValid();
	}
}
