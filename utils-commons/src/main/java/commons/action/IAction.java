package commons.action;

import commons.exception.CommonException;
import commons.facade.ActionFacade;

/**
 * Interfaz parametrizada con el método que deben implementar todas las acciones a ejecutar
 * por {@link ActionFacade}. Se indica el tipo de dato a resolver por el método.
 * <p>
 * 03/01/2016 19:09:16
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public interface IAction<R> {
	R execute() throws CommonException;
}