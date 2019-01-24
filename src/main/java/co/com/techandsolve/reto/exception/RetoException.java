package co.com.techandsolve.reto.exception;

/**
 * @author Evillalba
 *
 */
public class RetoException  extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * @param codigo
	 * @param mensaje
	 */
	public RetoException(String mensaje) {
		super(mensaje);
		
	}

	/**
	 * @param mensaje
	 * @param cause
	 */
	public RetoException(String mensaje, Throwable cause) {
		super(mensaje, cause);
	}

}
