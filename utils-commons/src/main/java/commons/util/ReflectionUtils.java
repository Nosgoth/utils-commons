package commons.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import commons.exception.ReflectionException;

/**
 * Utilidades para usar reflexión (<i>reflection</i>) en Java.
 * 
 * <p>
 * 03/01/2016 20:50:17
 * </p>
 * 
 * @author Jorge Fdez. &lt;jfmillan@gmail.com&gt;
 * @version 1.0
 */
public class ReflectionUtils {


    /**
	 * Crea una instancia de una clase llamando al constructor sin argumentos.
	 *
	 * @param clazz
	 *            Clase a instanciar.
	 * @return Instancia de la clase.
	 * @throws ReflectionException
	 *             en caso de producirse algún error al crear la instancia.
	 */
    public static <C> C instance (final Class<C> clazz) throws ReflectionException {
    	return instance(clazz, null, null);
    }
	
    /**
	 * Crea una instancia de una clase llamando al constructor con los
	 * argumentos facilitados.
	 *
	 * @param clazz
	 *            Clase a instanciar.
	 * @param params
	 *            Objetos que necesita el constructor para instanciar la clase.
	 * @return Instancia de la clase.
	 * @throws ReflectionException
	 *             en caso de producirse algún error al crear la instancia.
	 */
    public static <C> C instance (final Class<C> clazz, final Object[] params) throws ReflectionException {
    	return instance(clazz, params, ColUtils.getClasses(params));
    }
	
    /**
	 * Crea una instancia de una clase llamando al constructor con los
	 * argumentos facilitados.
	 *
	 * @param clazz
	 *            Clase a instanciar.
	 * @param params
	 *            Objetos que necesita el constructor para instanciar la clase.
	 * @param paramsClass
	 *            Clase de los argumentos de <code>params</code>.
	 * @return Instancia de la clase.
	 * @throws ReflectionException
	 *             en caso de producirse algún error al crear la instancia.
	 */
    public static <C> C instance (final Class<C> clazz, final Object[] params, final Class<?>[] paramsClass) 
    		throws ReflectionException {
    	ReflectionException error = null;
    	C result = null;
        try {
            final Constructor<C> constructor = clazz.getConstructor(paramsClass);
            if (constructor == null) {
            	error = createReflectionException("No existe constructor", null, clazz, params, paramsClass);
            } else {
            	result = constructor.newInstance(params);
            }
        } catch (NoSuchMethodException | SecurityException | InstantiationException 
        		| IllegalAccessException | InvocationTargetException e) {
            error = createReflectionException("Error al crear instancia", e, clazz, params, paramsClass);
        }
        
        if (error != null) {
        	throw error;
        }

        return result;
    }
    
    /**
	 * Crea una excepción ReflectionException.
	 * 
	 * @param message
	 *            Mensaje que describe la excepción.
	 * @param cause
	 *            Causa inicial de la excepción.
	 * @param clazz
	 *            Clase que provoca la excepción de reflexión.
	 * @param params
	 *            Parámetros.
	 * @param paramsClass
	 *            Clase de los parámetros.
	 * @return Excepción creada.
	 */
    public static ReflectionException createReflectionException(final String message, final Throwable cause, final Class<?> clazz, 
    		final Object[] params, final Class<?>[] paramsClass) {
    	final ReflectionException exception = new ReflectionException(message, cause);
    	exception.addArgument("Clase", clazz);
    	if (params != null) {
    		exception.addArgument("Params", params);
    	}
    	
    	if (paramsClass != null) {
    		exception.addArgument("ParamsClass", paramsClass);
    	}
    	
    	return exception;
    }

    /**
	 * Determina si las clases especificadas en el primer argumento son iguales
	 * a las clases especificadas en el segundo argumento según método equals.
	 * La primera condición a cumplir es que ambos arrays de clases tengan el
	 * mismo número de elementos.
	 * 
	 * @param classes
	 *            Array de clases que se quiere comparar con
	 *            <code>otherClasses</code>.
	 * @param otherClasses
	 *            Array de clases que se quiere comparar con
	 *            <code>classes</code>.
	 * @return <code>true</code> si todas las <code>classes</code> son iguales a
	 *         las <code>ohterClasses</code>, <code>false</code> en caso
	 *         contrario.
	 */
    public static boolean classesAreEquals (final Class<?>[] classes, final Class<?>[] otherClasses) {
    	final int classesSize = classes != null ? classes.length : Constants.ZERO;
    	final int othersSize = otherClasses != null ? otherClasses.length : Constants.ZERO;
    	boolean result = classesSize == othersSize;
    	
    	if (result) {
        	int i = 0;
        	while (result && i < classesSize) { /* si classes/otherClasses es null ya no entrará */
        		result = classes[i] != null && classes[i].equals(otherClasses[i]);
        		i++;
        	}
    	}
    	return result;
    }
    
    /**
	 * Determina si las clases especificadas en el primer argumento son iguales
	 * o superclase de las clases especificadas en el segundo argumento,
	 * utilizando el método disponible en {@link Class#isAssignableFrom(Class)}.
	 * La primera condición a cumplir es que ambos arrays de clases tengan el
	 * mismo número de elementos.
	 * 
	 * @param supClasses
	 *            Array de clases para las que se quiere comprobar que son
	 *            iguales o superclase de subClasses.
	 * @param subClasses
	 *            Array de clases para las que se quiere comprabar que son
	 *            iguales o subclases de supClasses.
	 * @return <code>true</code> si todas las supClases son iguales o superclase
	 *         a las subClases, <code>false</code> en caso contrario.
	 */
    public static boolean classesAreAssignableFrom (final Class<?>[] supClasses, final Class<?>[] subClasses) {
    	final int supSize = supClasses != null ? supClasses.length : Constants.ZERO;
    	final int subSize = subClasses != null ? subClasses.length : Constants.ZERO;
    	boolean result = supSize == subSize;
    	
    	if (result) {
        	int i = 0;
        	while (result && i < supSize) { /* si sup/b/Classes es null ya no entrará */
        		result = supClasses[i] != null
        				&& subClasses[i] != null
        				&& supClasses[i].isAssignableFrom(subClasses[i]);
        		i++;
        	}
    	}
    	return result;
    }
}
