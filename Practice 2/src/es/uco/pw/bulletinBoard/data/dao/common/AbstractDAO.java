package es.uco.pw.bulletinBoard.data.dao.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.common.PropsManager;

public abstract class AbstractDAO<P,K> implements DAO<P,K> {
	
	
	  //private static final int UPDATE_EXECUTED_SUCCESSFULLY = 1;
	  protected String tableName;
	  //protected abstract String getCreateQuery(P object);
	  // protected abstract String getUpdateQuery();
	  //protected abstract String getSelectByIdQuery();
	  //protected abstract String getDeleteQuery();
	  
	  protected abstract void setIdStatement(PreparedStatement preparedStatement, K id) throws DAOException;
	  protected abstract void setObjectStatement(PreparedStatement preparedStatement, P object) throws DAOException;
	  protected abstract void updateIdFromGeneratedKeys(ResultSet generatedKeys, P object) throws DAOException;
	  protected abstract P readObject(ResultSet resultSet) throws DAOException;
	  protected PropsManager sql;
		
	  //protected abstract String getCountRowsQuery();
	  
	  protected Connection connection;
	  
	  
	  protected AbstractDAO() {
		  this.connection = DBConnection.getConnection();
		  this.sql = new PropsManager("sql.properties");
	  }

	@Override
	public int create(P object) throws DAOException {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_CREATE";
		String query = this.sql.getProperty(queryKey);
		try{
			
			PreparedStatement ps= this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			this.setObjectStatement(ps, object);
			status = ps.executeUpdate();
			this.updateIdFromGeneratedKeys(ps.getGeneratedKeys(), object);
		}
		catch(Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}

	@Override
	public P read(K id) throws DAOException {
		P object = null;
		String queryKey = this.tableName.toUpperCase()+"_READ";
		String query = this.sql.getProperty(queryKey);
		try{
			PreparedStatement ps = this.connection.prepareStatement(query);
		    this.setIdStatement(ps, id);
		    ResultSet rs = ps.executeQuery();
		    object = this.readObject(rs);
		}
		catch(Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return object;
	}
	
	@Override
	public ArrayList<P> readAll() throws DAOException {
		ArrayList<P> array = new ArrayList<P>();
		String query = "SELECT * FROM "+this.tableName;
		try{
			PreparedStatement ps = this.connection.prepareStatement(query);
		    ResultSet rs = ps.executeQuery();
		    P aux = this.readObject(rs);
		    while((aux != null)) {
		    	array.add(aux);
		    	aux = this.readObject(rs);
		    }
		    
		}
		catch(Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return array;
	}

	@Override
	public int update(K id, P object) throws DAOException {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_UPDATE";
		String query = this.sql.getProperty(queryKey);
		try {
			PreparedStatement ps= this.connection.prepareStatement(query);
			this.setObjectStatement(ps, object);
			status = ps.executeUpdate();
		}
		catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return status;
	}

	@Override
	public int delete(K id) throws DAOException {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_DELETE";
		String query = this.sql.getProperty(queryKey);
		try {
			PreparedStatement ps= this.connection.prepareStatement(query);
			this.setIdStatement(ps, id);
			status = ps.executeUpdate();
		}
		catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}

}
