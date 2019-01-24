package co.com.techandsolve.reto.exception;

/**
 * @author Evillalba
 *
 */
public class ValidationException extends RetoException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param mensaje
	 */
	public ValidationException(String mensaje) {
		super(mensaje);
	}

}
