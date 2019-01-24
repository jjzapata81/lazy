package co.com.techandsolve.reto.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.com.techandsolve.reto.business.IviajeBusiness;
import co.com.techandsolve.reto.dto.ViajeDTO;
import co.com.techandsolve.reto.exception.RetoException;
import co.com.techandsolve.reto.exception.ValidationException;
import co.com.techandsolve.reto.util.Path;

/**
 * @author Evillalba
 *
 */
@RestController
@RequestMapping("/")
public class ViajeController {

	@Autowired
	private IviajeBusiness viajeBusiness;

	/**
	 * @param response
	 * @param viajeDTO
	 * @return ViajeDTO
	 * @throws IOException
	 */
	@PostMapping(value = Path.VIAJES)
	public ViajeDTO calcularViajes(HttpServletResponse response, @RequestBody ViajeDTO viajeDTO) throws IOException {
		try {

			return viajeBusiness.calcularViaje(viajeDTO);

		} catch (ValidationException e) {
			response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
		} catch (RetoException e) {
			response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
		}
		return null;
	}

}