package commons.exception;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import commons.util.PrintUtils;

/**
 * Excepción genérica de alto nivel.
 * <p>
 * 03/01/2016 20:08:09
 * </p>
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class CommonException extends Exception {
	private static final String PRINT_ARGUMENT_FORMAT = "[Arg %s: %s, %s]";
	
	private static final long serialVersionUID = -4786088067172952160L;

	private final LinkedHashMap<String, Object> arguments;
	
	/**
	 * Constructor.
	 */
	public CommonException() {
		arguments = new LinkedHashMap<String, Object>();
	}

	/**
	 * Constructor con un mensaje.
	 * 
	 * @param message
	 *            Mensaje que describe la excepción.
	 */
	public CommonException(final String message) {
		super(message);
		arguments = new LinkedHashMap<String, Object>();
	}

	/**
	 * Constructor con otra excepción de la que partir.
	 * 
	 * @param cause
	 *            Excepción que a su vez ha provocado esta otra excepción a
	 *            crear.
	 */
	public CommonException(final Throwable cause) {
		super(cause);
		arguments = new LinkedHashMap<String, Object>();
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
	public CommonException(final String message, final Throwable cause) {
		super(message, cause);
		arguments = new LinkedHashMap<String, Object>();
	}

	/**
	 * Constructor con mensaje y motivo.
	 * 
	 * @param errorType
	 *            Tipo de error
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
	public CommonException(final String message, final Throwable cause, final boolean enableSuppression,
			final boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		arguments = new LinkedHashMap<String, Object>();
	}
	
	/**
	 * Añade un argumento a la excepción.
	 * 
	 * @param argumentKey
	 *            Clave de texto que identifica el argumento.
	 * @param argumentValue
	 *            Valor del argumento.
	 */
	public void addArgument (final String argumentKey, final Object argumentValue) {
		arguments.put(argumentKey, argumentValue);
	}
	
	/**
	 * Obtiene un argumento a partir de su clave.
	 * 
	 * @param argumentKey
	 *            Clave del argumento.
	 * @return Valor del argumento, <code>null</code> si no existe.
	 */
	public Object getArgument (final String argumentKey) {
		return arguments.get(argumentKey);
	}
	
	/**
	 * Obtiene una copia de todos los argumentos.
	 * 
	 * @return Copia de los argumentos, vacío si no hay ninguno.
	 */
	public LinkedHashMap<String, Object> getArguments() {
		return new LinkedHashMap<String, Object>(arguments);
	}
	
	/**
	 * Imprime los argumentos según el formato {@value #PRINT_ARGUMENT_FORMAT}.
	 * 
	 * @return Cadena de texto con los argumentos de la excepción.
	 */
	public String printArguments () {
		final StringBuilder sb = new StringBuilder();
		
		int i = 0;
		for (final Entry<String, Object> entry : arguments.entrySet()) {
			sb.append(PrintUtils.format(PRINT_ARGUMENT_FORMAT, i, entry.getKey(), entry.getValue()));
		}
		return sb.toString();
	}
}
