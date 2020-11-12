package depavlo.walker.service.exception;

public class ShapeOutOfBoundsException extends RuntimeException {

	private static final long serialVersionUID = 1235486289794668220L;

	public ShapeOutOfBoundsException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}
}
