package commons.vo;

import java.io.Serializable;

import commons.util.CommonUtils;
import commons.util.PrintUtils;

/**
 * Representa un objeto con un identificador invariable de un determinado tipo.
 * <p>
 * 15/05/2016 18:36:05
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class Id<T> implements Serializable {
	private static final Integer PRIME = 873913;
	private static final String PRINT_FORMAT = "Id: '%s'";
	private static final long serialVersionUID = 5855581620312953879L;

	/* Identificador. */
	private final T id;
	
	/**
	 * Constructor, establece el identificador.
	 * 
	 * @param id
	 *            Identificador.
	 */
	public Id (final T id) {
		this.id = id;
	}
	
	/**
	 * Obtiene el identificador.
	 * 
	 * @return Identificador.
	 */
	public T getId() {
		return id;
	}

	
	
	@Override
	public String toString() {
		return PrintUtils.format(PRINT_FORMAT, id);
	}

	@Override
	public int hashCode() {
		return PRIME * (id != null ? toString().hashCode() : id.hashCode()); 
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) return true;
		if (obj == null) return false;
		if (getClass() != obj.getClass()) return false;
		
		Id<?> other;
		try {
			other = (Id<?>) obj;
		} catch (Exception e) {
			return false;
		}
		
		return CommonUtils.equalsOrNull(id, other.id);
	}
}
