package es.uco.pw.bulletinBoard.data.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uco.pw.bulletinBoard.business.AdHasRecipientUser;

public class AdHasRecipientUserDAO extends AbstractDAO<AdHasRecipientUser, Integer> {
	
	public AdHasRecipientUserDAO() {
		this.tableName = "AD_HAS_RECIPIENT_USER";
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
	protected void setObjectStatement(PreparedStatement preparedStatement, AdHasRecipientUser object) {
		try {
			preparedStatement.setInt(1, object.getAdId());
			preparedStatement.setInt(2, object.getUserId());
			if(object.getId() != null) preparedStatement.setInt(3, object.getId());
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, AdHasRecipientUser object) {
		try {
			if(generatedKeys.next()) object.setId(generatedKeys.getInt(1));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected AdHasRecipientUser readObject(ResultSet resultSet) {
		AdHasRecipientUser adHasRecipientUser = null;
		try {
			if(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer adId = resultSet.getInt("ad_id");
				Integer userId = resultSet.getInt("user_id");
		    	adHasRecipientUser = new AdHasRecipientUser(id, adId, userId);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return adHasRecipientUser;
	}

}
