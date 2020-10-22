package es.uco.pw.bulletinBoard.data.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import es.uco.pw.utils.MySQLManager;
import es.uco.pw.utils.PropsManager;

public abstract class AbstractDAO<P,K> implements IDAO<P,K> {
	
	
	  //private static final int UPDATE_EXECUTED_SUCCESSFULLY = 1;
	  protected String tableName;
	  //protected abstract String getCreateQuery(P object);
	  // protected abstract String getUpdateQuery();
	  //protected abstract String getSelectByIdQuery();
	  //protected abstract String getDeleteQuery();
	  
	  protected abstract void setIdStatement(PreparedStatement preparedStatement, K id);
	  protected abstract void setObjectStatement(PreparedStatement preparedStatement, P object);
	  protected abstract void updateIdFromGeneratedKeys(ResultSet generatedKeys, P object);
	  protected abstract P readObject(ResultSet resultSet);
	  protected PropsManager sql;
		
	  //protected abstract String getCountRowsQuery();
	  
	  private Connection connection;
	  
	  
	  protected AbstractDAO() {
		  this.connection = MySQLManager.getInstance().getConnection();
		  this.sql = new PropsManager("sql.properties");
	  }

	@Override
	public int create(P object) {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_CREATE";
		String query = this.sql.getProperty(queryKey);
		try{
			
			PreparedStatement ps= this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			this.setObjectStatement(ps, object);
			status = ps.executeUpdate();
			this.updateIdFromGeneratedKeys(ps.getGeneratedKeys(), object);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return status;
	}

	@Override
	public P read(K id) {
		P object = null;
		String queryKey = this.tableName.toUpperCase()+"_READ";
		String query = this.sql.getProperty(queryKey);
		try{
			PreparedStatement ps = this.connection.prepareStatement(query);
		    this.setIdStatement(ps, id);
		    ResultSet rs = ps.executeQuery();
		    object = this.readObject(rs);
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public int update(K id, P object) {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_UPDATE";
		String query = this.sql.getProperty(queryKey);
		try {
			PreparedStatement ps= this.connection.prepareStatement(query);
			this.setObjectStatement(ps, object);
			//this.setIdStatement(ps, id);
			//System.out.println(ps);
			status = ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		return status;
	}

	@Override
	public int delete(K id) {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_DELETE";
		String query = this.sql.getProperty(queryKey);
		try {
			PreparedStatement ps= this.connection.prepareStatement(query);
			this.setIdStatement(ps, id);
			status = ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

}
