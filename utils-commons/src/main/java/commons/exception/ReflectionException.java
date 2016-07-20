package commons.exception;



/**
 * Excepciones producidas por operaciones de reflexión Java en tiempo de
 * ejecución.
 * <p>
 * 03/01/2016 20:36:33
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class ReflectionException extends CommonException {
	private static final long serialVersionUID = 8296064594973759482L;

	/**
	 * Constructor.
	 */
	public ReflectionException() {
		super();
	}

	/**
	 * Constructor con un mensaje.
	 * @param message
	 *            Mensaje que describe la excepción.
	 */
	public ReflectionException(final String message) {
		super(message);
	}

	/**
	 * Constructor con otra excepción de la que partir.
	 * @param cause
	 *            Excepción que a su vez ha provocado esta otra excepción a
	 *            crear.
	 */
	public ReflectionException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y motivo.
	 * @param message
	 *            Mensaje que describe la excepción.
	 * @param cause
	 *            Excepción que a su vez ha provocado esta otra excepción a
	 *            crear.
	 */
	public ReflectionException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor con mensaje y motivo.
	 * 
	 * @param message
	 *            Mensaje que describe la excepción.
	 * @param cause
	 *            Excepción que a su vez ha provocado esta otra excepción a
	 *            crear.
	 * @param enableSuppression
	 *            Indica si se permite suprimir excepciones de la pila de la
	 *            excepción.
	 * @param writableStackTrace
	 *            Indica si se permite imprimir la pila de la excepción.
	 */
	public ReflectionException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
