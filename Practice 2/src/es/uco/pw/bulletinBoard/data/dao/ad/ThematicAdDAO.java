package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.ThematicAd;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

public class ThematicAdDAO extends AbstractDAO<ThematicAd, Integer> {
	
	private AdDAO adDAO = new AdDAO();
	
	public ThematicAdDAO() {
		this.tableName = "AD_HAS_INTEREST";
	}

	@Override
	protected void setIdStatement(PreparedStatement preparedStatement, Integer id) throws DAOException {
		try {
			preparedStatement.setInt(1, id);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setObjectStatement(PreparedStatement preparedStatement, ThematicAd thematicAd) throws DAOException {
		try {
			this.adDAO.create(thematicAd.toAd());
			if(thematicAd.getInterests() != null) this.addInterests(thematicAd.getId(), thematicAd.getInterests());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}	
		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, ThematicAd thematicAd) throws DAOException {
		try {
			if(generatedKeys.next()) {
				thematicAd.setId(generatedKeys.getInt(1));
				if(thematicAd.getInterests() != null) this.addInterests(thematicAd.getId(), thematicAd.getInterests());
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected ThematicAd readObject(ResultSet rs) throws DAOException {
		ThematicAd thematicAd = null;
		
		try {
			if(rs.next()) {		    	
		    	Ad ad = this.adDAO.readObject(rs);		    	
		    	ArrayList<Interest> interests = this.getInterests(ad.getId());
		    	
		    	thematicAd = new ThematicAd(ad, interests);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return thematicAd;
	}
	
	public int create(ThematicAd object) throws DAOException {
		int status = 0;
		try{
			Ad ad = object.toAd();
			this.adDAO.create(ad);
			System.out.println(ad);
			object.setId(ad.getId());
			
			if(object.getInterests() != null) {
				this.addInterests(object.getId(), object.getInterests());		
			}
		}
		catch(Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}
	
	public int delete(Integer id) throws DAOException {
		int status = 0;
		String queryKey = this.tableName.toUpperCase()+"_DELETE";
		String query = this.sql.getProperty(queryKey);
		try {
			PreparedStatement ps= this.connection.prepareStatement(query);
			this.setIdStatement(ps, id);
			status = ps.executeUpdate();
			System.out.println(ps);
			this.adDAO.delete(id);
		}
		catch(Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}
	
	@Override
	public ThematicAd read(Integer id) throws DAOException {
		Ad ad = this.adDAO.read(id);
		ArrayList<Interest> interests = this.getInterests(id);
		
		return new ThematicAd(ad, interests);
	}
	
	
	
	public ArrayList<Interest> getInterests(Integer adId){
    	ArrayList<Interest> interests = new ArrayList<Interest>();

		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql.getProperty(this.tableName+"_READ_INTERESTS"));
	    	ps.setInt(1, adId);
	    	ResultSet rs1 = ps.executeQuery();
	    	
	    	while(rs1.next()) {
	    		Integer interestId = rs1.getInt("id");
	    		String value = rs1.getString("value");
	    		Interest aux = new Interest(interestId, value);
	    		interests.add(aux);
	    	}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return interests;
	}
	
	public int addInterest(Integer adId, Integer interestId) {
		int status = 0;
		String query = this.sql.getProperty("AD_HAS_INTEREST_CREATE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, adId);
			ps.setInt(2, interestId);		
	
			status = ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	public int addInterests(Integer adId, ArrayList<Interest> interests) {
		int status = 0;
		
		for(int i = 0; i < interests.size(); i++) {
			int aux = this.addInterest(adId, interests.get(i).getId());
			if(status == 0) status = aux;
		}
		
		return status;
	}
	
	public ArrayList<ThematicAd> getAdByInterest(String interest) throws DAOException{
		ArrayList<ThematicAd> ads = new ArrayList<ThematicAd>();
		ThematicAd aux;
		String query = this.sql.getProperty("GET_AD_BY_INTEREST");
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(query);
			ps.setString(1, interest);
			ResultSet rs = ps.executeQuery();
			while((aux = this.readObject(rs))!=null) {
				ads.add(aux);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			throw new DAOException(e.getMessage(), e);
		}
		return ads;
	}
	
	public ArrayList<ThematicAd> getAdByInterest(ArrayList<String> interest) throws DAOException{
		ArrayList<ThematicAd> ads = new ArrayList<ThematicAd>();
		ThematicAd aux;
		String query = this.sql.getProperty("GET_AD_BY_INTEREST");
		PreparedStatement ps;
		try {
			for(int i=0; i<interest.size(); i++) {
				ps = this.connection.prepareStatement(query);
				ps.setString(1, interest.get(i));
				ResultSet rs = ps.executeQuery();
				while((aux = this.readObject(rs))!=null) {
					ads.add(aux);
				}
			}

		} catch (Exception e) {
			throw new DAOException(e.getMessage(), e);
		}
		return ads;
	}
	
}
