package commons.util;

import org.apache.commons.lang3.StringUtils;

/**
 * Utilidades para trabajar con cadenas de texto.
 * <p>
 * 21/11/2015 14:25:50
 * </p>
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class StrUtils {
	
	/** Constante para cadena vacía. */
	public static final String EMPTY_STRING = StringUtils.EMPTY;
	
	/** Constante para cadena null */
	public static final String NULL_STRING = "null";
	
	/**
	 * Indica si una cadena tiene algún caracter o por el contrario es cadena
	 * nula o vacía. Se puede establecer si se quiere comprobar con trim o sin
	 * trim.
	 * 
	 * @param str
	 *            Cadena de texto a verificar.
	 * @param trimmed
	 *            Indica si la cadena debe obviar espacios en blanco o no, a la
	 *            hora de determinar si tiene algún caracter o no.
	 * @return <code>true</code> si la cadena de texto facilitada es distinta de
	 *         <code>null</code> y tiene caracteres, <code>false</code> en caso
	 *         contrario. Puede tener en cuenta espacios en blanco dependiendo
	 *         de parámetro <code>trimmed</code> (<code>false</code> para
	 *         tenerlos en cuenta).
	 */
	public static boolean hasChars (final String str, final boolean trimmed) {
		final String checkStr = str != null ? (trimmed ? str.trim(): str) : null;
		return checkStr != null && checkStr.length() > 0;
	}

}
