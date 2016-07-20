package commons.facade;

import java.lang.reflect.Method;
import java.util.Objects;

import commons.action.IAction;
import commons.exception.CommonException;
import commons.exception.ReflectionException;
import commons.log.Log;
import commons.util.ArrayIterator;
import commons.util.ColUtils;
import commons.util.Constants;
import commons.util.PrintUtils;
import commons.util.ReflectionUtils;

/**
 * Clase abstracta para definir fachadas de acceso a acciones implementadas por
 * {@link IAction}.
 * <p>
 * 03/01/2016 18:17:29
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public abstract class ActionFacade {
	/* Nombre del método que ejecuta la acción. */
	private static final String EXECUTE_METHOD_NAME = "execute";
	
	/* Ejecuta método de la acción actionClass. */
	protected <R, C extends IAction<R>> R execute (final Class<C> actionClass, final Object... params) 
			throws CommonException {
		Objects.requireNonNull(actionClass);
		Log.debug(actionClass, 
			PrintUtils.format("Ejecutar acción [ActionFacade, %s], parametros %s", 
				actionClass.getSimpleName(), (PrintUtils.printArray(params)))
		);
		
		Method method = null;
		try {
			method = findMethod(params);
		} catch (ReflectiveOperationException e) {
			final ReflectionException reflectionException = ReflectionUtils.createReflectionException(
				PrintUtils.format("No se encuentra método para clase '%s'", actionClass), e, actionClass, params, null);
			throw reflectionException;
		}
		
		final C instance = (C) ReflectionUtils.instance(actionClass, params, method.getParameterTypes());
		return instance.execute();
	}

	/* Busca el método según los parámetros que recibe la acción. */
	private <C> Method findMethod (final Object[] params) throws ReflectiveOperationException{
		Method methodFacade = null;
		Class<?> classFacade = null;
		
		/* Se busca en la pila la llamada de la fachada. */
		final StackTraceElement stack = findStackTraceElementFacade();
		
		if (stack != null) {
			classFacade = Class.forName(stack.getClassName());
			methodFacade = findMethod (classFacade, stack.getMethodName(), params);
		}
		
        if (methodFacade == null) {
            throw new NoSuchMethodException();
        }
		return methodFacade;
	}

	/* Busca la llamada de la pila de la fachada. */
	private StackTraceElement findStackTraceElementFacade () {
		final ArrayIterator<StackTraceElement> traceIterator = 
			new ArrayIterator<StackTraceElement>(Thread.currentThread().getStackTrace());
		
		boolean found = false;
		StackTraceElement current = null;
		while (!found && traceIterator.hasNext()) {
			current = traceIterator.next();
			found = EXECUTE_METHOD_NAME.equals(current.getMethodName());
		}

		/* La llamada que interesa es la siguiente en la pila al execute */
		current = found && traceIterator.hasNext() ? traceIterator.next() : null;

		return current;
	}
	
	/* Busca un método en la clase indicada, con el nombre indicado, y que pueda recibir los argumentos indicados. */
	private Method findMethod(final Class<?> clazz, final String methodName, final Object[] params) {
		boolean found = false;
		final Class<?>[] paramClasses = ColUtils.getClasses(params);
		final Method[] methods = clazz.getDeclaredMethods();
		final ArrayIterator<Method> methodIterator = new ArrayIterator<Method>(methods);
		Method current = null;
		
		// 1er intento, buscar por exactamente iguales
		while (!found && methodIterator.hasNext()) {
			current = methodIterator.next();
			found = current.getName().equals(methodName)
					&& ReflectionUtils.classesAreEquals(current.getParameterTypes(), paramClasses);
		}
		
		// 2º intento, buscar por asignables
		methodIterator.reset();
		while (!found && methodIterator.hasNext()) {
			current = methodIterator.next();
			found = current.getName().equals(methodName)
					&& ReflectionUtils.classesAreAssignableFrom(current.getParameterTypes(), paramClasses);
		}
		
		// 3er intento, buscar por numero de elementos, lo mas probable si el resto ha fallado es que falle igualmente
		methodIterator.reset();
		while (!found && methodIterator.hasNext()) {
			current = methodIterator.next();
			final int sizeCurrent = current.getParameterTypes() != null ? current.getParameterTypes().length : Constants.ZERO;
			final int sizeParams = paramClasses != null ? paramClasses.length : Constants.ZERO;
			found = current.getName().equals(methodName) && sizeCurrent == sizeParams;
		}
		
		return found ? current : null;
	}
}
