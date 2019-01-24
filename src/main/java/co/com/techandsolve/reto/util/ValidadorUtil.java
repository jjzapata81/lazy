package co.com.techandsolve.reto.util;

import java.util.Objects;

import co.com.techandsolve.reto.exception.ValidationException;

/**
 * @author Evillalba
 *
 */
public class ValidadorUtil {

	private ValidadorUtil() {
	}

	/**
	 * Valida si un objeto es Nulo o Vacío deacuerdo al tipo
	 * 
	 * @param objects
	 * @return boolean
	 * @throws ValidationException
	 */
	public static boolean isNullOrEmpty(Object... objects) throws ValidationException {

		for (Object object : objects) {
			if (object instanceof String) {
				isNullOrEmpty((String) object);
			} else {
				isNull(object);
			}
		}
		return false;
	}

	/**
	 * Valida un objeto null
	 * 
	 * @param object
	 * @return boolean
	 * @throws ValidationException
	 */
	public static boolean isNull(Object object) throws ValidationException {
		if (Objects.isNull(object)) {
			throw new ValidationException(Constantes.VALOR_NULO);
		}
		return false;
	}

	/**
	 * Valida un String null o vacío
	 * 
	 * @param string
	 * @return boolean
	 * @throws ValidationException
	 */
	public static boolean isNullOrEmpty(String string) throws ValidationException {
		if (string == null || string.trim().isEmpty()) {
			throw new ValidationException(Constantes.VALOR_NULO);
		}
		return false;
	}
}
