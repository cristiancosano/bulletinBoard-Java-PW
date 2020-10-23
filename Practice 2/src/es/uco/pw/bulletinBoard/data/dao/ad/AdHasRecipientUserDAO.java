package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import es.uco.pw.bulletinBoard.business.ad.AdHasRecipientUserDTO;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;

public class AdHasRecipientUserDAO extends AbstractDAO<AdHasRecipientUserDTO, Integer> {
	
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
	protected void setObjectStatement(PreparedStatement preparedStatement, AdHasRecipientUserDTO object) {
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
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, AdHasRecipientUserDTO object) {
		try {
			if(generatedKeys.next()) object.setId(generatedKeys.getInt(1));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	protected AdHasRecipientUserDTO readObject(ResultSet resultSet) {
		AdHasRecipientUserDTO adHasRecipientUser = null;
		try {
			if(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				Integer adId = resultSet.getInt("ad_id");
				Integer userId = resultSet.getInt("user_id");
		    	adHasRecipientUser = new AdHasRecipientUserDTO(id, adId, userId);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return adHasRecipientUser;
	}

}
