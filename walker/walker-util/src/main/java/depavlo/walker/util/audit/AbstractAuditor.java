package depavlo.walker.util.audit;

/**
 * The interface is a reference to a class that implements audit of the abstract
 * data. This interface is the parent of interfaces that implement specific data
 * auditing.
 *
 * @param <T> the generic type
 * 
 * @author Pavlo Degtyaryev
 */
public interface AbstractAuditor<T> {

	/**
	 * Validate abstract data. Return the AuditResponse
	 *
	 * @param audibleData the audible data
	 * @param result      the result
	 * @return the audit response
	 */
	public abstract AuditResponse validate(T audibleData, AuditResponse result);

}
