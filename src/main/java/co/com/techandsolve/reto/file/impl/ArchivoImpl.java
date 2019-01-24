package co.com.techandsolve.reto.file.impl;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import co.com.techandsolve.reto.exception.FileException;
import co.com.techandsolve.reto.file.IArchivo;

/**
 * @author Evillalba
 *
 */
@Service
public class ArchivoImpl implements IArchivo {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArchivoImpl.class);

	@Override
	public List<String> obtenerlineas(String archivo) throws FileException {
		List<String> lineas = new ArrayList<>();
		try (InputStreamReader instream = new InputStreamReader(new ByteArrayInputStream(decodificarBase64(archivo)));
				BufferedReader buffer = new BufferedReader(instream)) {

			String linea;
			while ((linea = buffer.readLine()) != null) {
				lineas.add(linea);
			}

		} catch (Exception e) {
			LOGGER.error("Error al leer archivo", e);
			throw new FileException("Error al leer archivo", e);
		}
		return lineas;
	}

}
