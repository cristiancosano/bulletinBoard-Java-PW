package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.AdType;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;

public class AdDAO extends AbstractDAO<Ad, Integer> {
	
	public AdDAO() {
		this.tableName = "AD";
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
	protected void setObjectStatement(PreparedStatement preparedStatement, Ad ad) {
		try {
			preparedStatement.setString(1, ad.getTitle());
			preparedStatement.setString(2, ad.getBody());
			preparedStatement.setInt(3, ad.getOwnerUser());
			preparedStatement.setString(4, ad.getType().name());
			preparedStatement.setString(5, ad.getStatus().name());
			preparedStatement.setDate(6, Date.valueOf(ad.getDateOfExpiry()));
			if(ad.getId() != null)  preparedStatement.setInt(7, ad.getId());
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, Ad ad) {
		try {
			if(generatedKeys.next()) ad.setId(generatedKeys.getInt(1));
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected Ad readObject(ResultSet resultSet) {
		Ad ad = null;
		try {
			if(resultSet.next()) {
				Integer id = resultSet.getInt("id");
				String title = resultSet.getString("title");
		    	String body = resultSet.getString("body");
		    	Integer owner_user = resultSet.getInt("owner_user");
		    	AdType type = AdType.valueOf(resultSet.getString("type"));
		    	AdStatus status = AdStatus.valueOf(resultSet.getString("status"));
		    	LocalDate dateOfExpiry  = null;
		    	if(resultSet.getDate("date_of_expiry") != null)
		    		dateOfExpiry = resultSet.getDate("date_of_expiry").toLocalDate();
		    	ad = new Ad(id, title, body, owner_user, type, status, dateOfExpiry);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;
	}
	
}
