package commons.exception;

/**
 * Excepciones por conversión de un tipo de dato a otro tipo.
 * <p>
 * 09/01/2016 21:10:21
 * </p>
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class TypeConvertException extends CommonException {
	private static final long serialVersionUID = -3989787347839744361L;

	/**
	 * Constructor.
	 */
	public TypeConvertException() {
		super();
	}

	/**
	 * Constructor con un mensaje.
	 * 
	 * @param message
	 *            Mensaje que describe la excepción.
	 */
	public TypeConvertException(String message) {
		super(message);
	}

	/**
	 * Constructor con otra excepción de la que partir.
	 * 
	 * @param cause
	 *            Excepción que a su vez ha provocado esta otra excepción a
	 *            crear.
	 */
	public TypeConvertException(Throwable cause) {
		super(cause);
	}

	/**
	 * Constructor con mensaje y motivo.
	 * 
	 * @param message
	 *            Mensaje que describe la excepción.
	 * @param cause
	 *            Excepción que a su vez ha provocado esta otra excepción a
	 *            crear.
	 */
	public TypeConvertException(String message, Throwable cause) {
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
	public TypeConvertException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
