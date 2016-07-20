package commons.util;



/**
 * Utilidades comunes que no entran en otras categorías del paquete.
 * <p>
 * 13/01/2016 01:06:33
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class CommonUtils {
	
	/**
	 * Devuelve el mismo valor que se pasa en <code>value</code> salvo si es
	 * <code>null</code>, en cuyo caso se devuelve <code>whenNullValue</code>.
	 * 
	 * @param value
	 *            Valor que se devuelve si es distinto de <code>null</code>.
	 * @param whenNullValue
	 *            Valor que se devuelve si <code>value</code> es
	 *            <code>null</code>.
	 * @return <code>value</code> si es distinto de <code>null</code>,
	 *         <code>whenNullValue</code> si <code>value</code> es
	 *         <code>null</code>.
	 */
	public static <T> T coalesce (T value, T whenNullValue) {
		return value != null ? value : whenNullValue;
	}
	
	
	/**
	 * Suma dos enteros y devuelve la suma, siempre que se cumpla la condición.
	 * En caso de no cumplirse, devuelve el propio número. Si el número es
	 * <code>null</code> lo tratará como si fuese un cero. Si el número que suma
	 * es <code>null</code> no suma nada, y devolverá siempre el mismo número
	 * que se ha pasado. Si la condición es <code>null</code> la tratará como
	 * falso y no sumará.
	 * 
	 * @param number
	 *            Número original. Se tratará como cero si es nulo.
	 * @param sum
	 *            Número que se suma. Sólo sumará si es distinto de
	 *            <code>null</code>.
	 * @param condition
	 *            Condición a cumplir. Si es <code>null</code> se tratará como
	 *            <code>false</code>.
	 * @return Suma de <code>number</code> y <code>sum</code> si se cumple
	 *         <code>condition</code>.
	 */
	public static int sumIf (final Integer number, final Integer sum, final Boolean condition) {
		int result = number != null ? number : 0;
		if (Boolean.TRUE.equals(condition) && sum != null) {
			result += sum;
		}
		return result;
	}
	
	/**
	 * Comprueba si dos objetos del mismo tipo son iguales mediante su equals,
	 * considerándolos iguales también si ambos son nulos.
	 * 
	 * @param obj1
	 *            Primer objeto a comparar, puede ser nulo.
	 * @param obj2
	 *            Primer objeto a comparar, puede ser nulo.
	 * @return Indica si son iguales o ambos nulos.
	 */      
    public static <T> boolean equalsOrNull (final T obj1, final T obj2) {
    	return (obj1 == null && obj2 == null) || (obj1 != null && obj1.equals(obj2)); 
    }	
}
