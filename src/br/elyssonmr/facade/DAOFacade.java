package br.elyssonmr.facade;

import java.util.List;
import java.util.Map;

import br.elyssonmr.dao.exception.DAOException;

public interface DAOFacade<E> {
	
public long create(E entity) throws DAOException;
	
	public E readById(long id) throws DAOException;
	
	public List<E> readByCriteria(Map<String, Object> criterias) throws DAOException;
	
	public E update(E entity) throws DAOException;
	
	public boolean delete(E entity) throws DAOException;

}
