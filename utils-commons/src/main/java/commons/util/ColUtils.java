package commons.util;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.ArrayUtils;


/**
 * Clase de utilidad para colecciones, mapas, arreglos...
 * <p>
 * 21/11/2015 14:45:54
 * </p>
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class ColUtils {
	
	/**
	 * Comprueba si un arreglo es distinto de <code>null</code> y tiene
	 * elementos.
	 * 
	 * @param array
	 *            Arreglo con objetos.
	 *            
	 * @return <code>true</code> si el arreglo es distinto de <code>null</code>
	 *         y tiene elementos.
	 */
	public static <T extends Object> boolean hasElements (final T[] array) {
		return array != null && array.length > Constants.ZERO;
	}
	
	/**
	 * Comprueba si una colección tiene elementos, es decir, si es distinta de
	 * <code>null</code> y contiene al menos un elemento.
	 * 
	 * @param col
	 *            Colección.
	 * 
	 * @return <code>true</code> si la colección tiene elementos,
	 *         <code>false</code> en caso contrario.
	 */
	public static boolean hasElements (final Collection<?> col) {
		return col != null && !col.isEmpty();
	}
	
	/**
	 * Comprueba si un mapa tiene elementos, es decir, si es distinto de
	 * <code>null</code> y contiene al menos un elemento.
	 * 
	 * @param map
	 *            Mapa.
	 * 
	 * @return <code>true</code> si el mapa tiene elementos, <code>false</code>
	 *         en caso contrario.
	 */
	public static boolean hasElements (final Map<?, ?> map) {
		return map != null && !map.isEmpty();
	}

	/**
	 * Cuenta el número de elementos del array. Permite controlar si se cuentan
	 * nulos.
	 * 
	 * @param countNulls
	 *            Indica si se cuentan nulos (<code>true</code>) o no (
	 *            <code>false</code>).
	 * 
	 * @param array
	 *            Arreglo con objetos.
	 * @return Número de elementos del array. Pueden ser todos (
	 *         <code>countNulls = true</code>) o sólo los no nulos (
	 *         <code>countNulls = false</code>). <code>Cero</code> si el array
	 *         es nulo o vacío.
	 */
	public static int count (final boolean countNulls, final Object...array) {
    	int count = 0;
		if (hasElements(array)) {
			count = countNulls ? array.length
				: Long.valueOf(Arrays.stream(array).filter(o -> o != null).count()).intValue();
		 }
		 return count;
	}
	
   	/**
	 * Devuelve el tamaño de una colección, controlando colección nula. Si se
	 * pasa una colección nula devuelve 0 elementos.
	 * 
	 * @param collection
	 *            Colección.
	 * @return Número de elementos de la colección, cero si es nula.
	 */
	public static int size (final Collection<?> collection) {
		return collection != null ? collection.size() : 0;
	}
		
	/**
	 * Comprueba si de entre varios elementos hay alguno nulo.
	 * 
	 * @param array
	 *            Elementos a comprobar.
	 * @return <code>true</code> si no se pasan elementos o si alguno de los
	 *         elementos facilitados es nulo, <code>false</code> en caso
	 *         contrario.
	 */
	public static boolean hasNulls (final Object...array) {
		return Arrays.stream(array).anyMatch(o -> o == null);
	}

	/**
	 * Comprueba que los elementos de una colección no son nulos, lanzando
	 * excepción en caso contrario. Es equivalente a llamar a
	 * {@link Objects#requireNonNull(Object)} para cada uno de los objetos,
	 * incluido el propio array.
	 * 
	 * @param objects
	 *            Objetos a evaluar.
	 * @throws NullPointerException
	 *             si alguno de los elementos o el propio array es nulo.
	 */
	public static void requireNonNull (final Object...objects) {
		Objects.requireNonNull(objects);
		Arrays.stream(objects).iterator().forEachRemaining(o -> Objects.requireNonNull(o));
	}

	/**
	 * Obtiene las clases de un determinado número de objetos. En caso de que
	 * alguno sea <code>null</code>, le asignará {@link Object}.
	 * 
	 * @param objects
	 *            Objetos cuyas clases se quieren obtener.
	 * @return Clases de los objetos, {@link Object} para los nulos.
	 */
	public static Class<?>[] getClasses(final Object... objects) {
		final Class<?> [] result;
		if (objects == null) {
			result = null;
		} else {
			result = new Class<?>[objects.length];
			for (int i = 0; i < objects.length; i++) {
				result[i] = objects[i] != null ? objects[i].getClass() : Object.class;
			}
		}
		return result;
	}
	
	/**
	 * Concatena dos arrays de bytes en un nuevo array cuya longitud es la misma
	 * que la de ambos arrays juntos.
	 * 
	 * @param array1
	 *            Primer array.
	 * @param array2
	 *            Segundo array, sus bytes se concatenarán al segundo en un
	 *            nuevo array.
	 * @return Array copia de ambos arrays concatenados.
	 */
	public static byte[] concat (final byte[] array1, final byte[] array2) {
		final byte[] __array1 = array1 != null ? array1 : new byte[] {};
		final byte[] __array2 = array2 != null ? array2 : new byte[] {};
		return ArrayUtils.addAll(__array1, __array2);
	}
	
	/**
	 * Compara dos arrays de bytes. Devuelve <code>true</code> si y solo si
	 * ambos arrays son distintos de <code>null</code> y contienen los mismos
	 * elementos.
	 * 
	 * @param array1
	 *            Primer array.
	 * @param array2
	 *            Segundo array.
	 * @return <code>true</code> si y solo si ambos arrays son distintos de
	 *         <code>null</code> y contienen los mismos elementos,
	 *         <code>false</code> en caso contraio.
	 */
	public static boolean equals (final byte[] array1, final byte[] array2) {
		if (hasNulls(array1, array2) || array1.length != array2.length) {
			return false;
		}
		
		int i = 0;
		while (i < array1.length && array1[i] == array2[i]) {
			i++;
		}
		
		/* son iguales si se ha recorrido todo el bucle sin salirse */
		return i == array1.length; 
	}
	
	/**
	 * Copia un array de bytes devolviendo un array igual. En caso de pasar un
	 * array nulo, devuelve <code>null</code>.
	 * 
	 * @param bytes
	 *            Bytes a copiar
	 * @return Copia de los bytes en otro array, en caso de pasar array nulo,
	 *         devuelve <code>null</code>.
	 */
	public static byte[] copy (final byte[] bytes) {
		return bytes != null ? Arrays.copyOf(bytes, bytes.length) : null;
	}
	
	/**
	 * Obtiene el mínimo de una colección en base a un comparador determinado.
	 * Se diferencia de {@link Collections#min(Collection, Comparator)} en que
	 * este método devuelve <b>todos</b> los elementos que cumplen el mínimo.
	 * 
	 * @param collection
	 *            Colección cuyo mínimo se busca.
	 * @param comparator
	 *            Comparador utilizado para determinar el orden de los valores.
	 * @return Conjunto de valores que cumplen el mínimo.
	 */
	public static <T> Set<T> minAll(Collection<? extends T> collection, Comparator<? super T> comparator) {
		ColUtils.requireNonNull(collection, comparator);
		final Set<T> result = new HashSet<>();
		final T minSample = Collections.min(collection, comparator);
		for (final T current : collection) {
			if (comparator.compare(minSample, current) == Constants.ZERO) {
				result.add(current);
			}
		}
		return result;
	}
}	
