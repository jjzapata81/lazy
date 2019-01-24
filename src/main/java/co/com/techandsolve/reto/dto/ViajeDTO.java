package co.com.techandsolve.reto.dto;

import java.io.Serializable;

/**
 * @author Evillalba
 *
 */
public class ViajeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String cedulaUsuario;
	private String archivo;

	/**
	 * @return archivo
	 */
	public String getArchivo() {
		return archivo;
	}

	/**
	 * @param archivo
	 */
	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	/**
	 * @return cedulaUsuario
	 */
	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	/**
	 * @param cedulaUsuario
	 */
	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

}
