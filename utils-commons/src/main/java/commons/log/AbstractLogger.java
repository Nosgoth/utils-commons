package commons.log;

/**
 * Clase abstracta con los métodos comunes para log de la aplicación. Podrán ser
 * impletados por el logger por defecto, log4j, etc. 
 * <p>
 * 21/11/2015 14:10:42
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
abstract class AbstractLogger {

	/* Escribe un log de debug, con un separador entre tag y mensaje.*/
	abstract void debug(Class<?> clazz, String message);
	
	/* Escribe un log de debug, con un separador entre tag y mensaje.*/
	abstract void debug(Class<?> clazz, String message, Throwable throwable);
	
	/* Escribe un log de info, con un separador entre tag y mensaje.*/
	abstract void info(Class<?> clazz, String message);
	
	/* Escribe un log de info, con un separador entre tag y mensaje.*/
	abstract void info(Class<?> clazz, String message, Throwable throwable);
	
	/* Escribe un log de error, con un separador entre tag y mensaje.*/
	abstract void error(Class<?> clazz, String message);
	
	/* Escribe un log de error, con un separador entre tag y mensaje.*/
	abstract void error(Class<?> clazz, String message, Throwable throwable);

	/* Escribe un log de error fatal, con un separador entre tag y mensaje.*/
	abstract void fatal(Class<?> clazz, String message);
	
	/* Escribe un log de error fatal, con un separador entre tag y mensaje.*/
	abstract void fatal(Class<?> clazz, String message, Throwable throwable);
	
	/* Configura el logger concreto. */
	abstract void configure (Object configuration);
}
