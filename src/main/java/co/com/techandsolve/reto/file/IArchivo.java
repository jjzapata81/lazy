package co.com.techandsolve.reto.file;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import co.com.techandsolve.reto.exception.FileException;

/**
 * @author Evillalba
 *
 */
public interface IArchivo {

	/**
	 * Retorna una lista con el contenido de un archivo codficado en Base64
	 * 
	 * @param archivo
	 * @return List<String>
	 * @throws FileException
	 */
	List<String> obtenerlineas(String archivo) throws FileException;

	/**
	 * Codifica un string en Base64
	 * 
	 * @param contenido
	 * @return String
	 */
	default String codificarBase64(String contenido) {
		byte[] encoded = Base64.getEncoder().encode(contenido.getBytes(StandardCharsets.UTF_8));
		return new String(encoded);
	}

	/**
	 * Retorna un array de bytes de un archivo en Base64
	 * 
	 * @param archivoBase64
	 * @return byte[]
	 */
	default byte[] decodificarBase64(String archivoBase64) {
		return Base64.getDecoder().decode(archivoBase64);
	}

}
