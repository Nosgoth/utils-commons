package commons.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import commons.util.ColUtils;
import commons.util.PrintUtils;
import commons.vo.Id;

/**
 * Caché genérica simple LRU. Mantiene los elementos en memoria hasta un máximo,
 * indicado al crear la caché. Cuando se alcanza ese máximo el elemento menos
 * recientemente utilizado es eliminado. 
 * <p>
 * Esta caché es <i>thread-safe</i>.
 * <p>
 * 22/05/2016 16:33:51
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class LRUCache<ID, E extends Id<ID>> implements ICache<ID, E> {

	/* Formato para pasar a String. */
	private static final String PRINT_FORMAT = "LRUCache [max:%s] [cap ini:%s] [elementos:%s]";

	/* Bloqueos para lectura y escritura. */
	private final Lock writeLock;
	private final Lock readLock;
	
	/* Factor de carga. */
	private static final float LOAD_FACTOR = 0.75f;
	
	/* Capacidad inicial, porcentaje del máximo indicado */
	private static final float INITIAL_CAPACITY_MAX_PERCENT = 0.10f;
	
	/* Elementos almacenados en la caché. */
	private final LinkedHashMap<ID, E> elements;
	
	/* Máximo número de elementos en caché. */
	private final int maxElements;
	
	/* Capacidad inicial, porcentaje del maximo indicado. */
	private final int initialCapacity;
	
	/* Constructor, recibe el máximo número de elementos. */
	LRUCache (final int maxCapacity) {
		if (maxCapacity <= 0) {
			throw new IllegalArgumentException(PrintUtils.format(
				"No se puede crear cache LRU con capacidad máxima 0 o negativa: '%s'", maxCapacity)
			);
		}
		
		final ReadWriteLock accessLock = new ReentrantReadWriteLock(); 
		this.writeLock = accessLock.writeLock();
		this.readLock = accessLock.readLock();
		
		this.maxElements = maxCapacity;
		this.initialCapacity = getInitialCapacity();
		this.elements = createCacheMap();
	}

	/*
	 * Obtiene la capacidad inicial, porcentaje INITIAL_CAPACITY_MAX_PERCENT del
	 * máximo indicado. Se redondea hacia arriba.
	 */
	private int getInitialCapacity() {
		return (int) Math.ceil(Integer.valueOf(maxElements).doubleValue() * INITIAL_CAPACITY_MAX_PERCENT);
	}

	/*
	 * Inicia el mapa que contendrá los elementos de la caché. Se facilita una
	 * capacidad del porcentaje indicado en INITIAL_CAPACITY_MAX_PERCENT
	 * respecto a la capacidad máxima. Por ejemplo, para un tamaño máximo de 500
	 * elementos y un porcentaje del 10%, tendremos una capacidad inicial de 50
	 * elementos.
	 * 
	 * Por último, se inicia el mapa con el flag 'accessOrder' que tiene en
	 * cuenta el orden de los elementos según los accesos a los mismos, y no al
	 * orden de inserción.
	 * 
	 * @return
	 */
	private LinkedHashMap<ID, E> createCacheMap() {
		return new LinkedHashMap<ID, E>(initialCapacity, LOAD_FACTOR, Boolean.TRUE) {
			private static final long serialVersionUID = 5537739269230171839L;
			
			@Override
			protected boolean removeEldestEntry(final Map.Entry<ID, E> eldest) {
				return elements.size() > maxElements;
			}
		};
	}

	@Override
	public void add(final E element) {
		ColUtils.requireNonNull(element, element.getId());
		try {
			writeLock.lock();
			elements.put(element.getId(), element);
		} finally {
			writeLock.unlock();
		}
		
	}

	@Override
	public E remove(final ID id) {
		try {
			writeLock.lock();
			return elements.remove(id);
		} finally {
			writeLock.unlock();
		}
	}

	@Override
	public E get(final ID id) {
		try {
			readLock.lock();
			return elements.get(id);
		} finally {
			readLock.unlock();
		}
	}
	
	@Override
	public String toString() {
		return PrintUtils.format(PRINT_FORMAT, maxElements, initialCapacity, elements);
	}
}
