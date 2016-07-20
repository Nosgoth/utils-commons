package commons.log;

import commons.util.PrintUtils;

/**
 * Implementación de {@link AbstractLogger} con los métodos para log por
 * defecto, utilizando salida estandar por consola.
 * <p>
 * 21/11/2015 21:25:50
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
class DefaultLogger extends AbstractLogger {

	/* Formateo de tag y mensaje: [Hora][nivel][Hilo][Clase] :: Mensaje */
	private static final String logFormat = "[%s][%s][%-5s][%s] :: %s";
	
	/* Niveles de log. */
	private enum LEVEL {FATAL, ERROR, INFO, DEBUG}

	/* Niveles de log, numéricos. */
	private static final int LOG_DEBUG = 3;
	private static final int LOG_INFO = 2;
	private static final int LOG_ERROR = 1;
	private static final int LOG_FATAL = 0;
	
	private int levelIndex = DefaultLogger.getDefaultLogLevelIndex();
	
	@Override
	void configure(final Object configuration) {
		this.levelIndex = getLogLevelIndex(LEVEL.DEBUG);
	}

	private static int getDefaultLogLevelIndex() {
		return LOG_INFO;
	}
	
	private int getLogLevelIndex(final LEVEL level) {
		final int index;
		if (LEVEL.DEBUG.equals(level)) {
			index = LOG_DEBUG;
		} else if (LEVEL.INFO.equals(level)) {
			index = LOG_INFO;
		} else if (LEVEL.ERROR.equals(level)) {
			index = LOG_ERROR;
		} else if (LEVEL.FATAL.equals(level)) {
			index = LOG_FATAL;
		} else {
			index = getDefaultLogLevelIndex();
		}
		return index;
	}
	
	
	private static String getLog (final Class<?> clazz, final LEVEL level, final String message) {
		return PrintUtils.format(logFormat, PrintUtils.time(), Thread.currentThread().getName(), level, clazz.getSimpleName(), message);
	}
	
	@Override
	void debug(final Class<?> clazz, final String message) {
		if (levelIndex >= LOG_DEBUG) {
			System.out.println(getLog(clazz, LEVEL.DEBUG, message));
		}
	}

	@Override
	void debug(final Class<?> clazz, final String message, final Throwable throwable) {
		if (levelIndex >= LOG_DEBUG) {
			System.out.println(getLog(clazz, LEVEL.DEBUG, message));
			if (throwable != null) {
				throwable.printStackTrace();
			}
		}
	}

	@Override
	void info(final Class<?> clazz, final String message) {
		if (levelIndex >= LOG_INFO) {
			System.out.println(getLog(clazz, LEVEL.INFO, message));
		}
	}

	@Override
	void info(final Class<?> clazz, final String message, final Throwable throwable) {
		if (levelIndex >= LOG_INFO) {
			System.out.println(getLog(clazz, LEVEL.INFO, message));
			if (throwable != null) {
				throwable.printStackTrace();
			}
		}
	}

	@Override
	void error(final Class<?> clazz, final String message) {
		if (levelIndex >= LOG_ERROR) {
			System.out.println(getLog(clazz, LEVEL.ERROR, message));
		}
	}

	@Override
	void error(final Class<?> clazz, final String message, final Throwable throwable) {
		if (levelIndex >= LOG_ERROR) {
			System.out.println(getLog(clazz, LEVEL.ERROR, message));
			if (throwable != null) {
				throwable.printStackTrace();
			}
		}
	}

	@Override
	void fatal(final Class<?> clazz, final String message) {
		if (levelIndex >= LOG_FATAL) {
			System.out.println(getLog(clazz, LEVEL.FATAL, message));
		}
	}

	@Override
	void fatal(final Class<?> clazz, final String message, final Throwable throwable) {
		if (levelIndex >= LOG_ERROR) {
			System.out.println(getLog(clazz, LEVEL.ERROR, message));
			if (throwable != null) {
				throwable.printStackTrace();
			}
		}
	}
}
