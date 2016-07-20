package commons.log;

/**
 * Clase con métodos estáticos para log, según el sistema utilizado (por
 * defecto, log4j...). Debe configurarse previamente desde {@link ConfigureLog}.
 * 
 * <p>
 * 21/11/2015 21:29:02
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class Log {

	/* Sistema de logs utilizado. */
	private static AbstractLogger logger;
	
	/* Recibe el sistema de logs utilizado y lo configura. Accesible desde {@link ConfigureLog}. */ 
	static synchronized void configureLogSystem(final LogSystem logSystem, final Object configuration) {
		if (LogSystem.LOG4J.equals(logSystem)) {
			logger = new Log4jLogger();
			logger.configure(configuration);
		} else { /* incluye DEFAULT */
			logger = new DefaultLogger();
			logger.configure(configuration);
		}
	}
	
	/* Obtiene la clase de un objeto. En caso de ser nulo se devuelve la clase del logger configurado. */
	private static Class<?> getClass(final Object object) {
		return object != null ? object.getClass() : logger.getClass();
	}

	
	/**
	 * Muestra un log a nivel de debug.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void debug (final Object object, final String message) {
		if (logger != null) {
			logger.debug(getClass(object), message);
		}
	}
	
	/**
	 * Muestra un log a nivel de debug.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void debug (final Object object, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.debug(getClass(object), message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de debug.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void debug (final Class<?> clazz, final String message) {
		if (logger != null) {
			logger.debug(clazz, message);
		}
	}

	/**
	 * Muestra un log a nivel de debug.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void debug (final Class<?> clazz, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.debug(clazz, message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de info.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void info (final Object object, final String message) {
		if (logger != null) {
			logger.info(getClass(object), message);
		}
	}
	
	/**
	 * Muestra un log a nivel de info.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void info (final Object object, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.info(getClass(object), message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de info.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void info (final Class<?> clazz, final String message) {
		if (logger != null) {
			logger.info(clazz, message);
		}
	}

	/**
	 * Muestra un log a nivel de info.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void info (final Class<?> clazz, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.info(clazz, message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de error.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void error (final Object object, final String message) {
		if (logger != null) {
			logger.error(getClass(object), message);
		}
	}
	
	/**
	 * Muestra un log a nivel de error.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void error (final Object object, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.error(getClass(object), message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de error.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void error (final Class<?> clazz, final String message) {
		if (logger != null) {
			logger.error(clazz, message);
		}
	}

	/**
	 * Muestra un log a nivel de error.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void error (final Class<?> clazz, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.error(clazz, message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de error fatal.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void fatal (final Object object, final String message) {
		if (logger != null) {
			logger.fatal(getClass(object), message);
		}
	}
	
	/**
	 * Muestra un log a nivel de error fatal.
	 * 
	 * @param object
	 *            Objeto del que se extraerá la clase que muestra el log. Si se
	 *            pasa <code>null</code> se utilizará la clase del logger
	 *            configurado.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void fatal (final Object object, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.fatal(getClass(object), message, throwable);
		}
	}
	
	/**
	 * Muestra un log a nivel de error fatal.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 */
	public static void fatal (final Class<?> clazz, final String message) {
		if (logger != null) {
			logger.fatal(clazz, message);
		}
	}

	/**
	 * Muestra un log a nivel de error fatal.
	 * 
	 * @param clazz
	 *            Clase que muestra el log.
	 * @param message
	 *            Mensaje de log a mostrar.
	 * @param throwable
	 *            Pila de excepción a loggear.
	 */
	public static void fatal (final Class<?> clazz, final String message, final Throwable throwable) {
		if (logger != null) {
			logger.fatal(clazz, message, throwable);
		}
	}
}
