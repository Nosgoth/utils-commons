package commons.cache;

import commons.vo.Id;

/**
 * Métodos que implementarán las cachés. Los elementos contenidos se indexarán
 * por id, por lo que deben implementar la interfaz {@link Id}.
 * <p>
 * 22/05/2016 16:29:30
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public interface ICache<ID, E extends Id<ID>> {

	/* Añade un elemento a la caché */
	void add (E element);
	
	/* Elimina un elemento de la caché y lo devuelve, por id. */
	E remove (ID id);
	
	/* Devuelve un elemento de la caché, por id. */
	E get (ID id);
}
