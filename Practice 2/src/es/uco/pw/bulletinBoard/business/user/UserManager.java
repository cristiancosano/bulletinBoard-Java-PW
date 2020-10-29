package es.uco.pw.bulletinBoard.business.user;

import java.util.ArrayList;

import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.user.UserDAO;

public class UserManager {
	
	private static UserManager instance = null;
	private UserDAO userDAO;
	
	private UserManager() {
		userDAO = new UserDAO();
	}
	
	public static UserManager getInstance() {
		if(instance == null) instance = new UserManager();
		return instance;
	}
	
	public Boolean createUser(User c) {
		Boolean status = false;
		try {
			status = (userDAO.create(c) != 0);
		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}
		return status;
	}

	public Boolean deleteUser(Integer id) {
		Boolean deleted = false;
		try {
			deleted = (userDAO.delete(id) != 0);
		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}
		return deleted;
	}
	
	public Boolean updateUser(Integer id, User user) {
		Boolean updated = false;
		try {
			updated = (userDAO.update(id, user) != 0);
		} catch (DAOException e) {
			System.err.println(e.getMessage());
		}
		return updated;
	}
	
	public ArrayList<User> getAllUsers() {
		ArrayList<User> users = null;
		try {
			users = userDAO.readAll();
		} catch (DAOException e) {
			System.err.println(e.getMessage());
			e.printStackTrace();
		}
		return users;
	}
	
	public User findContactByEmail(String email) {
		User finded = null;
		
		return finded;
	}
	

	public ArrayList<User> searchContactByFullName(String fullName) {
		ArrayList<User> users = null;
		
		return users;
	}
	
	
	public ArrayList<User> searchContactByInterest(String interest){
		ArrayList<User> users = null;
		
		return users;
	}
	
	public ArrayList<User> searchContactByAge(int age){
		ArrayList<User> users = null;
		
		return users;
	}
}
	

