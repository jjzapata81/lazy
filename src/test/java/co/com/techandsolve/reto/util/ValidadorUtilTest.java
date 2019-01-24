package co.com.techandsolve.reto.util;

import org.junit.Assert;
import org.junit.Test;

import co.com.techandsolve.reto.dto.ViajeDTO;
import co.com.techandsolve.reto.exception.ValidationException;

/**
 * @author Evillalba
 *
 */
public class ValidadorUtilTest {

	/**
	 * Se espera excepcion de validacion con objeto Null
	 */
	@Test
	public void validarObjetoNull() {

		try {

			ViajeDTO viajeDTO = null;
			ValidadorUtil.isNullOrEmpty(viajeDTO);
		} catch (ValidationException e) {
			Assert.assertEquals(Constantes.VALOR_NULO, e.getMessage());
		}

	}

	/**
	 * Se espera respuesta exitosa de validacion con objeto no Null
	 */
	@Test
	public void validarObjetoNotNull() {
		try {

			ViajeDTO viajeDTO = new ViajeDTO();

			Assert.assertFalse(ValidadorUtil.isNullOrEmpty(viajeDTO));
		} catch (ValidationException e) {
			Assert.fail("error");
		}
	}

	/**
	 * Se espera respuesta exitosa con la lista de objecos 
	 */
	@Test
	public void validarListaObjetosNotNullNotEmpty() {
		try {
			Assert.assertFalse(ValidadorUtil.isNullOrEmpty(new ViajeDTO(), "string"));
		} catch (ValidationException e) {
			Assert.fail("error");
		}
	}

	/**
	 * Se espera respuesta exitosa de validacion con String (!= null && != "")
	 */
	@Test
	public void validarStringNotEmptyNotNull() {
		try {
			Assert.assertFalse(ValidadorUtil.isNullOrEmpty("string"));
		} catch (ValidationException e) {
			Assert.fail("error");
		}
	}

	/**
	 * Se espera excepcion de validacion con String Null
	 */
	@Test
	public void validarStringNull() {

		try {
			String s = null;
			ValidadorUtil.isNullOrEmpty(s);
		} catch (ValidationException e) {
			Assert.assertEquals(Constantes.VALOR_NULO, e.getMessage());
		}
	}

	/**
	 * Se espera excepcion de validacion con String nulo
	 */
	@Test
	public void validarStringEmpty() {
		try {
			ValidadorUtil.isNullOrEmpty("");
		} catch (ValidationException e) {
			Assert.assertEquals(Constantes.VALOR_NULO, e.getMessage());
		}
	}

}
