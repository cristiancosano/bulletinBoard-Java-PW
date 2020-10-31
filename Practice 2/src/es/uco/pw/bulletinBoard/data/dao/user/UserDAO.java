package es.uco.pw.bulletinBoard.data.dao.user;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.data.dao.common.AbstractDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

public class UserDAO extends AbstractDAO<User, Integer> {
		
	public UserDAO(){
		this.tableName = "USER";
	}
	
//	@Override
//	public User read(Integer id) {
//		User object = null;
//		String queryKey = this.tableName.toUpperCase()+"_READ";
//		String query = this.sql.getProperty(queryKey);
//		try{
//			PreparedStatement ps = this.connection.prepareStatement(query);
//		    this.setIdStatement(ps, id);
//		    ResultSet rs = ps.executeQuery();
//		    object = this.readObject(rs);
//		    
//		}
//		catch(SQLException e){
//			e.printStackTrace();
//		}
//		return object;
//	}

	@Override
	protected void setIdStatement(PreparedStatement ps, Integer id) {
		try {
			ps.setInt(1, id);
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void setObjectStatement(PreparedStatement ps, User user) {
		try {
			ps.setString(1,user.getName());
			ps.setString(2,user.getLastName());
			ps.setString(3,user.getEmail());
			ps.setString(4,user.getPassword());
			ps.setDate(5, Date.valueOf(user.getDateOfBirth()));
			if(user.getId() != null) {
				ps.setInt(6, user.getId());
				if(user.getInterests() != null) this.addInterests(user.getId(), user.getInterests());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, User user) {
		try {
			if(generatedKeys.next()) {
				user.setId(generatedKeys.getInt(1));
				if(user.getInterests() != null) this.addInterests(user.getId(), user.getInterests());
			}
			
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected User readObject(ResultSet rs) {
		User user = null;
		try {
			if(rs.next()) {
				Integer id = rs.getInt("id");
				String name = rs.getString("name");
		    	String lastName = rs.getString("last_name");
		    	String email = rs.getString("email");
		    	String password = rs.getString("password");
		    	LocalDate dateOfBirth = rs.getDate("date_of_birth").toLocalDate();
		    	
		    	ArrayList<Interest> interests = this.getInterests(id);
		    	
		    	user = new User(id, name, lastName, email, password, dateOfBirth, interests);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return user;
	}
	
	public ArrayList<Interest> getInterests(Integer userId){
    	ArrayList<Interest> interests = new ArrayList<Interest>();

		try {
			PreparedStatement ps = this.connection.prepareStatement(this.sql.getProperty(this.tableName+"_READ_INTERESTS"));
	    	ps.setInt(1, userId);
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
	
	public int addInterest(Integer userId, Integer interestId) {
		int status = 0;
		String query = this.sql.getProperty("USER_HAS_INTEREST_CREATE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, userId);
			ps.setInt(2, interestId);		
	    	//System.out.println(ps);

			status = ps.executeUpdate();
			//ResultSet rs = ps.getGeneratedKeys();
			
			//if(rs.next()) id = rs.getInt(1);
			
		}
		catch(SQLException e) {
			System.err.println(e.getMessage());
		}
		
		return status;
	}
	
	public int addInterests(Integer userId, ArrayList<Interest> interests) {
		int status = 0;
		
		for(int i = 0; i < interests.size(); i++) {
			int aux = this.addInterest(userId, interests.get(i).getId());
			if(status == 0) status = aux;
		}
		
		return status;
	}
	
	public int removeInterest(Integer userId, Integer interestId) {
		int status = 0;
		String query = this.sql.getProperty("USER_DELETE_INTEREST");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, userId);
			ps.setInt(2, interestId);
			status = ps.executeUpdate();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		
		return status;
	}
	
	public User getUserByEmail(String email) throws DAOException {
		User user = null;
		String query = this.sql.getProperty("USER_READ_BY_EMAIL");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, email);
			user = this.readObject(ps.executeQuery());
		} catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return user;
	}
	
	public ArrayList<User> getUserByFullName(String fullName) throws DAOException {
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
		String query = this.sql.getProperty("USER_READ_BY_FULLNAME");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, fullName);
			ResultSet rs = ps.executeQuery();
			while((user = this.readObject(rs)) != null) {
				users.add(user);
			}
		} catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return users;
	}
	
	public ArrayList<User> getUserByInterest(String interest) throws DAOException{
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
		String query = this.sql.getProperty("USER_READ_BY_INTEREST");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setString(1, interest);
			ResultSet rs = ps.executeQuery();
			while((user = this.readObject(rs)) != null) {
				users.add(user);
			}
		} catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return users;
	}
	
	public ArrayList<User> getUserByAge(Integer age) throws DAOException{
		ArrayList<User> users = new ArrayList<User>();
		User user = null;
		String query = this.sql.getProperty("USER_READ_BY_AGE");
		try {
			PreparedStatement ps = this.connection.prepareStatement(query);
			ps.setInt(1, age);
			ResultSet rs = ps.executeQuery();
			while((user = this.readObject(rs)) != null) {
				users.add(user);
			}
		} catch(SQLException e) {
			throw new DAOException(e.getMessage(), e);
		}
		return users;
	}
	
}
