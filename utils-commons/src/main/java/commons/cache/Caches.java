package commons.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import commons.vo.Id;

/**
 * Clase que puede registrar y manipular varias cachés según el tipo que se
 * indique.
 * <p>
 * 22/05/2016 17:24:12
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class Caches {
	/* Tamaño por defecto, 1000 elementos. */
	private static final int DEFAULT_MAX_ELEMENTS = 1000;
	
	/* Cachés registradas. */
	private final Map<Class<?>, ICache<?, ?>> caches;
	
	public Caches() {
		this.caches = new HashMap<>();
	}
	
	/* Registra una caché con tamaño por defecto. */
	public <ID, T extends Id<ID>> ICache<ID, T> register(final Class<T> classCache) {
		return register(classCache, DEFAULT_MAX_ELEMENTS);
	}
	
	/* Registra una caché con el tamaño indicado. */
	public <ID, T extends Id<ID>> ICache<ID, T> register(final Class<T> classCache, final int maxCapacity) {
		/* Por el momento sólo hay cachés LRU, si es necesario meter más tipos añadir un constructor
		 * para indicarlo también. Tipo por defecto LRU en cualquier caso. */
		Objects.requireNonNull(classCache);
		caches.put(classCache, new LRUCache<>(maxCapacity));
		return get(classCache);
	}
	
	/* Devuelve una caché */
	@SuppressWarnings("unchecked")
	public <ID, T extends Id<ID>> ICache<ID, T> get (final Class<T> classCache) {
		return (ICache<ID, T>) caches.get(classCache);
	}
	
	/* Añade un elemento a una caché */
	public <ID, T extends Id<ID>> void add (final Class<T> classCache, final T element) {
		Objects.requireNonNull(classCache);
		final ICache<ID, T> currentCache = this.get(classCache);

		Objects.requireNonNull(currentCache);
		currentCache.add(element);
	}
	
	/* Elimina un elemento de una caché y lo devuelve, por id. */
	public <ID, T extends Id<ID>> T remove (final Class<T> classCache, final ID id) {
		Objects.requireNonNull(classCache);
		final ICache<ID, T> currentCache = this.get(classCache);
		Objects.requireNonNull(currentCache);
		return currentCache.remove(id);
	}
	
	/* Devuelve un elemento de una caché, por id. */
	public <ID, T extends Id<ID>> T get (final Class<T> classCache, final ID id) {
		Objects.requireNonNull(classCache);
		final ICache<ID, T> currentCache = this.get(classCache);
		Objects.requireNonNull(currentCache);
		return currentCache.get(id);
	}
}
