package es.uco.pw.bulletinBoard.data.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.User;

public class UserDAO extends AbstractDAO<User, Integer> {
		
	public UserDAO(){
		this.tableName = "USER";
	}

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
			if(user.getId() != null) ps.setInt(6, user.getId());
			
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}		
	}

	@Override
	protected void updateIdFromGeneratedKeys(ResultSet generatedKeys, User user) {
		try {
			if(generatedKeys.next()) user.setId(generatedKeys.getInt(1));
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
		    	ArrayList<String> interests = new ArrayList<String>();
		    	user = new User(id, name, lastName, email, password, dateOfBirth, interests);
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
    	
		return user;
	}

}
