package co.com.techandsolve.reto.exception;

/**
 * @author Evillalba
 *
 */
public class FileException extends RetoException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * @param mensaje
	 */
	public FileException(String mensaje) {
		super(mensaje);
	}

	/**
	 * @param mensaje
	 * @param cause
	 */
	public FileException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}
}
