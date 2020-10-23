package es.uco.pw.bulletinBoard.data.dao.common;

import java.util.ArrayList;

public interface DAO<P,K> {
	public int create(P object) throws DAOException;
	public P read(K id) throws DAOException;
	public ArrayList<P> readAll() throws DAOException;
	public int update(K id, P object) throws DAOException;
	public int delete(K id) throws DAOException;
}
