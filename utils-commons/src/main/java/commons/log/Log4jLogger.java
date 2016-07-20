package commons.log;


/**
 * Implementación de {@link AbstractLogger} con los métodos para log en
 * <code>log4j</code>. Visibilidad por defecto, de paquete.
 * <p>
 * 21/11/2015 21:07:47
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
class Log4jLogger extends AbstractLogger {

	@Override
	void debug(final Class<?> clazz, final String message) {
		/* XXX-JF TODO implementar. */
	}

	@Override
	void debug(final Class<?> clazz, final String message, final Throwable throwable) {
		/* XXX-JF TODO implementar. */
	}

	@Override
	void info(final Class<?> clazz, final String message) {
		/* XXX-JF TODO implementar. */		
	}

	@Override
	void info(final Class<?> clazz, final String message, final Throwable throwable) {
		/* XXX-JF TODO implementar. */		
	}

	@Override
	void error(final Class<?> clazz, final String message) {
		/* XXX-JF TODO implementar. */		
	}

	@Override
	void error(final Class<?> clazz, final String message, final Throwable throwable) {
		/* XXX-JF TODO implementar. */		
	}

	@Override
	void fatal(final Class<?> clazz, final String message) {
		/* XXX-JF TODO implementar. */		
	}

	@Override
	void fatal(final Class<?> clazz, final String message, final Throwable throwable) {
		/* XXX-JF TODO implementar. */		
	}

	@Override
	void configure(final Object configuration) {
		/* XXX-JF TODO implementar */
	}
}
