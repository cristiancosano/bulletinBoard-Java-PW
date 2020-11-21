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
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

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
			preparedStatement.setDate(6, Date.valueOf(ad.getDateOfPublication()));
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
		    	
		    	ad = new Ad(id, title, body, owner_user, type, status);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return ad;
	}
	
	public ArrayList<Ad> getAdsToUser(Integer userId, String order){
		ArrayList<Ad> ads = new ArrayList<Ad>();
		Ad aux;
		String query = this.sql.getProperty(this.tableName+"_FOR_USER");
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, userId);
			ps.setString(3, "general");
			ps.setString(4, order);
			System.out.println(ps);
			ResultSet rs = ps.executeQuery();
			while((aux = this.readObject(rs))!=null) {
				ads.add(aux);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return ads;
	}
	
	
	public ArrayList<Ad> getByOwner(Integer ownerId) throws DAOException{
		ArrayList<Ad> ads = new ArrayList<Ad>();
		Ad aux;
		String query = this.sql.getProperty("AD_READ_BY_OWNER");
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(query);
			ps.setInt(1, ownerId);
			ResultSet rs = ps.executeQuery();
			while((aux = this.readObject(rs))!=null) {
				ads.add(aux);
			}
		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		
		return ads;
	}

	public ArrayList<Ad> getAdByDate(LocalDate date) throws DAOException{
		ArrayList<Ad> ads = new ArrayList<Ad>();
		Ad ad = null;
		String query = this.sql.getProperty("AD_READ_BY_DATE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setDate(1, Date.valueOf(date));
			ResultSet rs = ps.executeQuery();
			while((ad = this.readObject(rs)) != null) {
				ads.add(ad);
			}
		} catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return ads;
	}
	
}
