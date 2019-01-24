package co.com.techandsolve.reto.dao;

import org.springframework.data.repository.CrudRepository;

import co.com.techandsolve.reto.model.Log;

/**
 * @author Evillalba
 *
 */
public interface ILogDAO extends CrudRepository<Log, Long> {

}