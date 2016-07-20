package commons.util;

import commons.exception.TypeConvertException;

/**
 * Clase de utilidad para transformar unos tipos en otros.
 * 
 * <p>
 * 09/01/2016 21:08:21
 * </p>
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class TypeConverter {

	/**
	 * Convierte <b>desde</b> {@link String} a otros tipos de objeto.
	 * 
	 * @param str
	 *            Cadena a transformar.
	 * @param type
	 *            Clase a la que se transforma.
	 * @return Dato equivalente a la cadena pero en el tipo indicado.
	 * @throws TypeConvertException
	 *             Si no se puede realizar la transformación de tipo (por
	 *             ejemplo, intentar convertir "123a" a Integer.
	 */
	@SuppressWarnings("unchecked")
	public static final <T> T from(final String str, final Class<T> type) throws TypeConvertException {
		try {
			if (str == null) {
				return null;
			}
			
			if (type.isAssignableFrom(str.getClass())) {
				return (T) str;
			} 
	
			if (type == String.class) {
				return (T) str.toString();
			}
	
			if (isInteger(type)) {
				return (T) Integer.valueOf(str);
			}
	
			if (isBoolean(type)) {
				return (T) Boolean.valueOf(str);
			}
	
			if (isLong(type)) {
				return (T) Long.valueOf(str);
			}
			
			if (isShort(type)) {
				return (T) Short.valueOf(str);
			}
	
			if (isFloat(type)) {
				return (T) Float.valueOf(str);
			}
	
			if (isDouble(type)) {
				return (T) Double.valueOf(str);
			}
	
			if (isCharacter(type)) {
				return (T) Character.valueOf(str.charAt(Constants.ZERO));
			}
		} catch (Exception e) {
			throw getException(str, type, e);
		}
		
		throw getException (str, type, null);
	}
	
	private static TypeConvertException getException(final String str, final Class<?> type, final Throwable t) {
		TypeConvertException tce = 
				new TypeConvertException(
					PrintUtils.format("Error al convertir a tipo de dato '%s' desde String '%s'", type, str), t
			);
		tce.addArgument("Tipo", type);
		tce.addArgument("string", str);
		return tce;
	}
	
	private static <T> boolean isInteger(final Class<T> type) {
		return (type == Integer.class) || (type == Integer.TYPE);
	}

	private static <T> boolean isBoolean(final Class<T> type) {
		return (type == Boolean.class) || (type == Boolean.TYPE);
	}

	private static <T> boolean isLong(final Class<T> type) {
		return (type == Long.class) || (type == Long.TYPE);
	}
	
	private static <T> boolean isShort(final Class<T> type) {
		return (type == Short.class) || (type == Short.TYPE);
	}

	private static <T> boolean isFloat(final Class<T> type) {
		return (type == Float.class) || (type == Float.TYPE);
	}

	private static <T> boolean isDouble(final Class<T> type) {
		return (type == Double.class) || (type == Double.TYPE);
	}

	private static <T> boolean isCharacter(final Class<T> type) {
		return (type == Character.class) || (type == Character.TYPE);
	}
}
