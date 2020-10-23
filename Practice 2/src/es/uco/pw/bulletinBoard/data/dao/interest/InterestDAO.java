package es.uco.pw.bulletinBoard.data.dao.interest;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;

public class InterestDAO extends AbstractDAO<Interest, Integer> {
	
	public InterestDAO() {
		this.tableName = "INTEREST";
	}
	
	@Override
	protected void setIdStatement(PreparedStatement preparedStatement, Integer id) {
		try {
			preparedStatement.setInt(1, id);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void setObjectStatement(PreparedStatement preparedStatement, Interest object) {
		try {
			preparedStatement.setString(1, object.getValue());
			if(object.getId() != null) preparedStatement.setInt(2, object.getId());	
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, Interest object) {
		try {
			if(generatedKeys.next()) object.setId(generatedKeys.getInt(1));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected Interest readObject(ResultSet resultSet) {
		Interest interest = null;
		try {
			if(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String value = resultSet.getString("value");
		    	interest = new Interest(id, value);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return interest;
	}

}
