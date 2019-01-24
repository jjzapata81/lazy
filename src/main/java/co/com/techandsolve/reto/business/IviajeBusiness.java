package co.com.techandsolve.reto.business;

import co.com.techandsolve.reto.dto.ViajeDTO;
import co.com.techandsolve.reto.exception.RetoException;

/**
 * @author Evillalba
 *
 */
public interface IviajeBusiness {

	/**
	 * Metodo que recibe el archivo para realizar el calculo de viajes y retorna un
	 * mismo objeto con la respuesta
	 * 
	 * @param viajeDTO
	 * @return ViajeDTO
	 * @throws RetoException
	 */
	ViajeDTO calcularViaje(ViajeDTO viajeDTO) throws RetoException;

}
