package commons.util;

import java.util.Iterator;
import java.util.Objects;
import java.util.function.Consumer;

/**
 * Iterador para recorrer un array unidimensional. No es
 * <code>thread-safe</code>.
 * <p>
 * 3/1/2016 02:10:11
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class ArrayIterator<T> implements Iterator<T> {

	/* Array sobre el que se itera.*/
	private final T[] array;
	
	/* Número de elementos del array. */
	private final int size;
	
	/* Índice actual del iterador.*/
	private int index;
	
	/**
	 * Constructor. Recibe el array sobre el que itera.
	 * 
	 * @param array
	 *            Array unidimensional.
	 */
	public ArrayIterator(final T[] array) {
		this.array = array;
		this.size = array != null ? array.length : Constants.ZERO;
		this.index = Constants.ZERO;
	}
	
	/**
	 * Resetea el iterador, devolviendo los índices a la posición inicial.
	 */
	public void reset() {
		index = Constants.ZERO;
	}

	@Override
	public boolean hasNext() {
		return index < size;
	}

	@Override
	public T next() {
		final T element = array[index++];
		return element;
	}

	@Override
	public void remove() {
		array[index] = null;
	}

	@Override
	public void forEachRemaining(Consumer<? super T> action) {
		Objects.requireNonNull(action);
		T nxt;
		while (hasNext()) {
			nxt = next();
			if (nxt != null) {
				action.accept(nxt);
			}
		}
	}
	
	/**
	 * Obtiene el tamaño del array.
	 * 
	 * @return Número de filas.
	 */
	public int getSize() {
		return size;
	}
}
