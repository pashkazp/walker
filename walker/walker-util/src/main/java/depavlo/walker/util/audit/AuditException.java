package depavlo.walker.util.audit;

import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import lombok.Getter;
import lombok.Setter;

/**
 * Exception that occurs when the auditor checks the data and contains a list of
 * existing data errors.
 * 
 * @author Pavlo Degtyaryev
 *
 */
@Getter
@Setter
public class AuditException extends CustomGenericException {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4728309790778116393L;

	/** The audit messages. */
	private Multimap<String, String> auditMessages = TreeMultimap.create();

	/**
	 * Instantiates a new audit exception.
	 *
	 * @param audit the audit
	 */
	public AuditException(AuditResponse audit) {
		audit.getMessages().forEach((key, value) -> auditMessages.put(key, value));
	}

	/**
	 * Adds the audit message to the list of messages.
	 *
	 * @param key     the key
	 * @param message the message
	 */
	public void addAuditMessage(String key, String message) {
		auditMessages.put(key, message);
	}
}
