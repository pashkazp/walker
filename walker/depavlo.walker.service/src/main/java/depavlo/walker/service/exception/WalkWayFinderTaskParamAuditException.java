package depavlo.walker.service.exception;

import depavlo.walker.util.audit.AuditException;
import depavlo.walker.util.audit.AuditResponse;

public class WalkWayFinderTaskParamAuditException extends AuditException {

	private static final long serialVersionUID = 3396220328233703425L;

	public WalkWayFinderTaskParamAuditException(AuditResponse audit) {
		super(audit);
	}

}
