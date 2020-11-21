package es.uco.pw.bulletinBoard.data.dao.ad;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.user.UserDAO;

public class IndividualizedAdDAO extends AbstractDAO<IndividualizedAd, Integer>{
	
	private AdDAO adDAO = new AdDAO();
	private UserDAO userDAO = new UserDAO();
	
	
	public IndividualizedAdDAO() {
		this.tableName = "AD_HAS_RECIPIENT_USER";
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
	protected void setObjectStatement(PreparedStatement ps, IndividualizedAd individualizedAd) throws DAOException {
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, IndividualizedAd object) throws DAOException {

	}

	@Override
	protected IndividualizedAd readObject(ResultSet rs) throws DAOException {
		return null;
	}
	
	@Override
	public int create(IndividualizedAd object) throws DAOException {
		int status = 0;
		try{
			Ad ad = object.toAd();
			this.adDAO.create(ad);
			System.out.println(ad);
			object.setId(ad.getId());
			
			if(object.getRecipients() != null) {
				this.addRecipients(object.getId(), object.getRecipients());		
			}
		}
		catch(Exception e){
			throw new DAOException(e.getMessage(), e);
		}
		return status;
	}
	
	public int update(Integer id, IndividualizedAd object) throws DAOException {
		int status = 0;
		Ad ad = object.toAd();
		this.adDAO.update(id, ad);
		return status;
	}
	
	@Override
	public IndividualizedAd read(Integer id) throws DAOException {
		
		Ad ad = this.adDAO.read(id);
		ArrayList<User> users = this.getRecipients(id);
		
		return new IndividualizedAd(ad, users);
	}
	
	@Override
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
	
	
	public int addRecipient(Integer adId, Integer userId) {
		int status = 0;
		String query = this.sql.getProperty("AD_HAS_RECIPIENT_USER_CREATE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, adId);
			ps.setInt(2, userId);		
	
			status = ps.executeUpdate();
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	
	public int addRecipients(Integer adId, ArrayList<User> recipients) {
		int status = 0;
		
		for(int i = 0; i < recipients.size(); i++) {
			int aux = this.addRecipient(adId, recipients.get(i).getId());
			if(status == 0) status = aux;
		}
		
		return status;
	}
	
	
	public int removeRecipients(Integer adId, Integer userId) {
		int status = 0;
		String query = this.sql.getProperty("AD_DELETE_RECIPIENT");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, adId);
			ps.setInt(2, userId);
			status = ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public ArrayList<User> getRecipients(Integer adId){
    	ArrayList<User> recipients = new ArrayList<User>();

		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql.getProperty(this.tableName+"_READ_RECIPIENTS"));
	    	ps.setInt(1, adId);
	    	ResultSet rs1 = ps.executeQuery();
	    	
	    	while(rs1.next()) {
	    		Integer userId = rs1.getInt("id");
	    		String name = rs1.getString("name");
	    		String lastname = rs1.getString("last_name");
	    		String email = rs1.getString("email");
	    		String password = rs1.getString("password");
		    	LocalDate dateOfBirth = rs1.getDate("date_of_birth").toLocalDate();
		    	ArrayList<Interest> interests = this.userDAO.getInterests(userId);

	    		User aux = new User(userId, name, lastname, email, password, dateOfBirth, interests);
	    		recipients.add(aux);
	    	}
		}
		catch(SQLException e){
			e.printStackTrace();
		}
		
		return recipients;
	}
	
	public ArrayList<Ad> getAdByRecipients(Integer userId){
		ArrayList<Ad> ads = new ArrayList<Ad>();
		AdDAO dao = new AdDAO();
		PreparedStatement ps;
		try {
			ps = this.connection.prepareStatement(this.sql.getProperty("AD_READ_BY_RECIPIENTS"));
			ps.setInt(1, userId);
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ads.add(dao.readObject(rs));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

		return ads;
	}
	
}
