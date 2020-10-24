package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.AdType;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.user.UserDAO;

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
		    	ArrayList<User> recipients = new ArrayList<User>();
		    	
		    	boolean hasNext = false;
		    	do {
		    		Integer userId = resultSet.getInt("user_id");
		    		String name = resultSet.getString("name");
			    	String lastName = resultSet.getString("last_name");
			    	String email = resultSet.getString("email");
			    	String password = resultSet.getString("password");
			    	LocalDate dateOfBirth = resultSet.getDate("date_of_birth").toLocalDate();
			    	ArrayList<Interest> interests = new ArrayList<Interest>();
		    		
			    	while((hasNext = resultSet.next()) && userId != null && resultSet.getInt("user_id") == userId) {
			    		Integer interestId = resultSet.getInt("interest_id");
			    		String value = resultSet.getString("interest");
			    		if(interestId != null)interests.add(new Interest(interestId, value));
			    	}
			    	
			    	if(userId != null) {
			    		recipients.add(new User(userId, name, lastName, email, password, dateOfBirth, interests));
			    	}
			    	
		    		
		    	} while(hasNext);
		    	ad = new Ad(id, title, body, owner_user, type, status, dateOfExpiry, recipients);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;
	}
	
//	public ArrayList<Interest> getRecipients(Integer adId){
//    	ArrayList<User> recipients = new ArrayList<User>();
//
//		try {
//			PreparedStatement ps = this.connection.prepareStatement(this.sql.getProperty(this.tableName+"_READ_RECIPIENTS"));
//	    	ps.setInt(1, adId);
//	    	ResultSet rs = ps.executeQuery();
//
//	    	while(rs.next()) {
//	    		Integer userId = rs.getInt("id");
//				String name = rs.getString("name");
//		    	String lastName = rs.getString("last_name");
//		    	String email = rs.getString("email");
//		    	String password = rs.getString("password");
//		    	LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
//		    	ArrayList<Interest> interests = new ArrayList<Interest>();
//		    	
//		    	PreparedStatement ps1 = this.connection.prepareStatement(this.sql.getProperty("USER_READ_INTERESTS"));
//		    	ps.setInt(1, userId);
//		    	ResultSet rs1 = ps.executeQuery();
//		    	
//
//		    	while(rs1.next()) {
//		    		Integer interestId = rs1.getInt("id");
//		    		String value = rs1.getString("value");
//		    		Interest aux = new Interest(interestId, value);
//		    		interests.add(aux);
//		    	}
//		    	
//	    		User aux = new Interest(adId, value);
//	    		interests.add(aux);
//	    	}
//		}
//		catch(SQLException e){
//			e.printStackTrace();
//		}
//		
//		return interests;
//	}
	
}
