package commons.log;

/**
 * Configura el sistema de log a utilizar. Puede depender de la versión lanzada
 * (android, escritorio...), usando según ocasión el logger por defecto, de
 * log4j...
 * <p>
 * 21/11/2015 21:34:12
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class ConfigureLog {
	/**
	 * Establece el sistema de log y lo configura.
	 * 
	 * @param logSystem
	 *            Sistema de log utilizado.
	 *            
	 * @param concreteConfiguration
	 *            Configuración dependiente del sistema de logs utilizado.
	 */
	public static void configure(final LogSystem logSystem, final Object concreteConfiguration) {
		Log.configureLogSystem (logSystem, concreteConfiguration);
	}

	/**
	 * Establece el sistema de log sin configuración.
	 * 
	 * @param logSystem
	 *            Sistema de log utilizado.
	 */
	public static void configure(LogSystem logSystem) {
		configure(logSystem, null);
	}
}
