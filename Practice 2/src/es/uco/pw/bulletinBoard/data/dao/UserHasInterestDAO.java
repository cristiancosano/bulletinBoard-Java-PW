package es.uco.pw.bulletinBoard.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uco.pw.bulletinBoard.business.UserHasInterest;

public class UserHasInterestDAO extends AbstractDAO<UserHasInterest, Integer> {
	
	public UserHasInterestDAO() {
		this.tableName="USER_HAS_INTEREST";
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
	protected void setObjectStatement(PreparedStatement preparedStatement, UserHasInterest object) {
		try {
			preparedStatement.setInt(1, object.getUserId());
			preparedStatement.setInt(2, object.getInterestId());
			if(object.getId() != null) preparedStatement.setInt(3, object.getId());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, UserHasInterest object) {
		try {
			if(generatedKeys.next()) object.setId(generatedKeys.getInt(1));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected UserHasInterest readObject(ResultSet resultSet) {
		UserHasInterest userHasInterest = null;
		try {
			if(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer userId = resultSet.getInt("user_id");
				Integer interestId = resultSet.getInt("interest_id");
		    	userHasInterest = new UserHasInterest(id, userId, interestId);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return userHasInterest;
	}
}
