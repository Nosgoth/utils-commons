package commons.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;


/**
 * Utilidades para imprimir cadenas de texto y otros objetos.
 * 
 * <p>
 * 21/11/2015 14:25:01
 * </p>
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class PrintUtils {
	
	/** Constante para escribir <code>null</code> como cadena de texto. */
	public static final String NULL_STR = "null";

	/** Símbolo de porcentaje.*/
	public static final Character PERCENT_SYMBOL = '%';
	
	/** Constante para una coma.*/
	public static final Character COMMA = ',';
	
	/** Constante para un espacio en blanco */
	public static final Character BLANK_SPACE = ' ';

	/** Formateo para hora del sistema. */
	public static final String TIME_FORMAT = "HH:mm:ss,SSS";
	
	/**
	 * Imprime una cadena formateada. Si el número de parámetros no coincide,
	 * imprime la cadena sin formatear, seguida del <code>String</code> de los
	 * parámetros facilitados.
	 * 
	 * @param str
	 *            Cadena a imprimir formateada.
	 * @param params
	 *            Parámetros para formatear la cadena.
	 * @return Cadena formateada.
	 */
	public static String format (final String str, final Object... params) {
		final String formatStr = str != null ? str : NULL_STR;
		
		final int formatTags = StringUtils.countMatches(formatStr, PERCENT_SYMBOL);
		final int numParams = ColUtils.count(Boolean.TRUE, params);
		
		final String result;
		if (formatTags != numParams) {
			final StringBuilder sb = new StringBuilder(formatStr);
			sb.append(", parametros: ");
			sb.append(StringUtils.join(params, COMMA)); /* String utils ya controla que si params es null devuelva null */
			result = sb.toString();
		} else {
			result = String.format(formatStr, params);
		}
		return result;
	}
	
	/**
	 * Imprime la hora actual del sistema.
	 * 
	 * @return Hora actual del sistema en formato {@link #TIME_FORMAT}.
	 */
	public static String time() {
		return DateFormatUtils.format(System.currentTimeMillis(), TIME_FORMAT);
	}
	
	/**
	 * Imprime una secuencia de bytes en formato hexadecimal.
	 * 
	 * @param bytes
	 *            Bytes a imprimir.
	 * @return Byte impresos en hexadecimal.
	 */
	public static String print(final byte[] bytes) {
		final String format = "<%s>";
		
		if (bytes == null) {
			return PrintUtils.format(format, StrUtils.NULL_STRING);
		}
		
		final StringBuffer sb = new StringBuffer();
		for (final byte b : bytes) {
			sb.append(StringUtils.leftPad(PrintUtils.format("%1$H", b&0xFF), 2, '0'));
			sb.append(BLANK_SPACE);
		}
		return PrintUtils.format(format, StringUtils.chop(sb.toString()));
	}
	
	/**
	 * Imprime el contenido de un array separado por comas.
	 * 
	 * @param array
	 *            Array a imprimir.
	 * @return String con el array impreso.
	 */
	public static <T> String printArray(final T[] array) {
		final String formatAll = "[%s]";
		final String formatOne = "%s,";
		
		final StringBuilder sb = new StringBuilder();
		if (array != null) {
			for (final T element : array) {
				sb.append(PrintUtils.format(formatOne, element));
			}
		}
		
		final String str = sb.length() >= 0 ? StringUtils.chop(sb.toString()) : sb.toString();
		return PrintUtils.format(formatAll, str);
	}
}
