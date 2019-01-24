package co.com.techandsolve.reto.business.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.com.techandsolve.reto.business.IviajeBusiness;
import co.com.techandsolve.reto.dao.ILogDAO;
import co.com.techandsolve.reto.dto.ViajeDTO;
import co.com.techandsolve.reto.exception.FileException;
import co.com.techandsolve.reto.exception.RetoException;
import co.com.techandsolve.reto.file.IArchivo;
import co.com.techandsolve.reto.model.Log;
import co.com.techandsolve.reto.util.Constantes;
import co.com.techandsolve.reto.util.ValidadorUtil;

/**
 * @author Evillalba
 *
 */

@Service
public class ViajeBusinessImpl implements IviajeBusiness {

	private static final Logger LOGGER = LoggerFactory.getLogger(ViajeBusinessImpl.class);

	@Autowired
	private IArchivo archivo;

	@Autowired
	private ILogDAO logDAO;

	@Override
	public ViajeDTO calcularViaje(ViajeDTO viajeDTO) throws RetoException {
		try {
			// Se valida la información requerida
			ValidadorUtil.isNull(viajeDTO);
			ValidadorUtil.isNullOrEmpty(viajeDTO.getCedulaUsuario(), viajeDTO.getArchivo());

			// Se genera el log de cada ejecución
			generarLogEjecucion(viajeDTO.getCedulaUsuario());
			List<String> lineasArchivo = archivo.obtenerlineas(viajeDTO.getArchivo());
			List<List<Double>> items = obtenerEstructuraDatos(lineasArchivo);

			ViajeDTO viajeCalculado = new ViajeDTO();
			viajeCalculado.setArchivo(generarArchivoSalida(items));
			viajeCalculado.setCedulaUsuario(viajeDTO.getCedulaUsuario());
			return viajeCalculado;

		} catch (FileException e) {
			LOGGER.error("Ocurrio un error al leer el archivo", e);
			throw e;
		} catch (RetoException e) {
			LOGGER.error("Ocurrio un error al calcular viajes.", e);
			throw e;
		} catch (Exception e) {
			LOGGER.error("Error interno al procesar archivo.", e);
			throw new RetoException("Error interno al procesar archivo.", e);
		}
	}

	/**
	 * Crea un registro con el log de ejecución
	 * 
	 * @param cedulaUsuario
	 */
	private void generarLogEjecucion(String cedulaUsuario) {
		Log log = new Log();
		log.setCedula(cedulaUsuario);
		log.setFechaRegistro(new Date());
		this.logDAO.save(log);
	}

	/**
	 * Obtiene la estrucuta de los datos almacenados en el archivo para luego ser
	 * procesados
	 * 
	 * @param lineasArchivo
	 * @return List<List<Double>>
	 */
	private List<List<Double>> obtenerEstructuraDatos(List<String> lineasArchivo) {
		Iterator<String> i = lineasArchivo.iterator();
		List<List<Double>> items = new ArrayList<>();
		while (i.hasNext()) {
			adicionarElementos(i, items);
		}
		return items;
	}

	/**
	 * Agrupa los elementos por dìa en una lista de listas.
	 * 
	 * @param i
	 * @param items
	 */
	private void adicionarElementos(Iterator<String> i, List<List<Double>> items) {
		int diasLaborales = Integer.parseInt(i.next());
		IntStream.range(0, diasLaborales).forEach(dia -> {
			if (i.hasNext()) {
				int numeroElementos = Integer.parseInt(i.next()); // validar error null
				List<Double> elementosDia = new ArrayList<>();
				IntStream.range(0, numeroElementos).forEach(item -> elementosDia.add(Double.parseDouble(i.next())));
				items.add(elementosDia);
			}
		});
	}

	/**
	 * Genera el archivo de salida
	 * 
	 * @param items
	 * @return String
	 */
	private String generarArchivoSalida(List<List<Double>> items) {

		StringBuilder resultado = new StringBuilder("");
		items.forEach(i -> resultado
				.append(String.format(Constantes.FORMATO_LINEA, items.indexOf(i) + 1, calcularCantidadViajes(i))));

		return archivo.codificarBase64(resultado.toString());
	}

	/**
	 * Calcula la cantidad màxima de viajes
	 * 
	 * @param pesoItems
	 * @return
	 */
	private int calcularCantidadViajes(List<Double> pesoItems) {
		int numeroItemsLibres = pesoItems.size();
		int numeroViajes = 0;
		double numeroItemsRequeridos = 0;

		pesoItems.sort((Double o1, Double o2) -> (-1) * o1.compareTo(o2));
		for (Double pesoItem : pesoItems) {
			numeroItemsRequeridos = Math.ceil(Constantes.PESO_MINIMO_VIAJE / pesoItem);
			if (numeroItemsRequeridos > numeroItemsLibres || numeroItemsLibres == 0) {
				break;
			}
			numeroItemsLibres -= numeroItemsRequeridos;
			numeroViajes++;
		}
		return numeroViajes;
	}

}
