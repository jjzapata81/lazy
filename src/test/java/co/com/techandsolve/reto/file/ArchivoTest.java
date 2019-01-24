package co.com.techandsolve.reto.file;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import co.com.techandsolve.reto.exception.FileException;
import co.com.techandsolve.reto.file.impl.ArchivoImpl;

/**
 * @author Evillalba
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class ArchivoTest {

	private static final String ARCHIVO_BASE64 = "MQo0CjMwCjMwCjEKMQ==";
	private static final String ARCHIVO_BASE64_RESULT = "Q2FzZSAjMTogMg==";
	private static final String CONTENIDO_ARCHIVO = "Case #1: 2";

	@InjectMocks
	private ArchivoImpl archivoImpl;

	/**
	 * @throws FileException
	 */
	@Test
	public void seEsperaObtenerLasLineasDelArchivo() throws FileException {
		List<String> result = archivoImpl.obtenerlineas(ARCHIVO_BASE64);
		Assert.assertArrayEquals(Arrays.asList("1", "4", "30", "30", "1", "1").toArray(), result.toArray());
	}

	/**
	 * @throws FileException
	 */
	@Test(expected = FileException.class)
	public void seEsperaObtenerFileException() throws FileException {
		archivoImpl.obtenerlineas(null);
	}

	/**
	 * codificarBase64
	 */
	@Test
	public void codificarBase64() {
		String result = archivoImpl.codificarBase64(CONTENIDO_ARCHIVO);
		Assert.assertEquals(ARCHIVO_BASE64_RESULT, result);
	}

	/**
	 * decodificarBase64
	 */
	@Test
	public void decodificarBase64() {
		archivoImpl.decodificarBase64("");
	}

}
