package co.com.techandsolve.reto.business;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.techandsolve.reto.business.impl.ViajeBusinessImpl;
import co.com.techandsolve.reto.dao.ILogDAO;
import co.com.techandsolve.reto.dto.ViajeDTO;
import co.com.techandsolve.reto.exception.FileException;
import co.com.techandsolve.reto.exception.RetoException;
import co.com.techandsolve.reto.exception.ValidationException;
import co.com.techandsolve.reto.file.IArchivo;

/**
 * @author Evillalba
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ViajeBusinessTest {

	private static final String ARCHIVO_PROCESADO = "Q2FzZSAjMTogMgo=";
	
	@Mock
	private ILogDAO logDAO;

	@Mock
	private IArchivo archivo;

	@InjectMocks
	private ViajeBusinessImpl viajeBusiness;


	/**
	 * @throws RetoException
	 */
	@Test(expected = ValidationException.class)
	public void seEsperaValidationExceptionPorParametroNull() throws RetoException {

		this.viajeBusiness.calcularViaje(null);
	}

	/**
	 * @throws RetoException
	 */
	@Test(expected = ValidationException.class)
	public void seEsperaValidationExceptionPorAtributoNull() throws RetoException {
		this.viajeBusiness.calcularViaje(new ViajeDTO());
	}

	/**
	 * @throws RetoException
	 */
	@Test(expected = FileException.class)
	public void seEsperaFileException() throws RetoException {
		ViajeDTO viajeDTO = new ViajeDTO();
		viajeDTO.setArchivo("ssddd");
		viajeDTO.setCedulaUsuario("1234");
		Mockito.when(this.archivo.obtenerlineas(Mockito.anyString()))
				.thenThrow(new FileException("error lectura archivo"));
		this.viajeBusiness.calcularViaje(viajeDTO);
	}

	/**
	 * Se espera Exception
	 */
	@Test
	public void seEsperaException() {
		try {
			ViajeDTO viajeDTO = new ViajeDTO();
			viajeDTO.setArchivo("ssddd");
			viajeDTO.setCedulaUsuario("1234");
			Mockito.when(this.archivo.obtenerlineas(Mockito.anyString())).thenReturn(null);
			this.viajeBusiness.calcularViaje(viajeDTO);
		} catch (Exception e) {
			Assert.assertEquals("Error interno al procesar archivo.", e.getMessage());
		}
	}

	/**
	 * @throws RetoException
	 */
	@Test
	public void seEsperaQueElProcesoSeaExitoso() throws RetoException {
            
			ViajeDTO viajeDTO = new ViajeDTO();
			viajeDTO.setArchivo("ssddd");
			viajeDTO.setCedulaUsuario("1234");
			Mockito.when(this.archivo.obtenerlineas(Mockito.anyString())).thenReturn(obtenerLineasArchivo());
			Mockito.when(this.archivo.codificarBase64(Mockito.anyString())).thenReturn(ARCHIVO_PROCESADO);
			ViajeDTO respuesta = this.viajeBusiness.calcularViaje(viajeDTO);
		    Assert.assertEquals(ARCHIVO_PROCESADO, respuesta.getArchivo());
 
	}

	private List<String> obtenerLineasArchivo() {
		return Arrays.asList("3", "4", "30", "30", "1", "1","3","20","20","20");
	}

}
