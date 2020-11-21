package es.uco.pw.bulletinBoard.controllers;

import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.user.*;
import es.uco.pw.bulletinBoard.views.user.UserCreate;
import es.uco.pw.bulletinBoard.views.user.UserDelete;
import es.uco.pw.bulletinBoard.views.user.UserFind;
import es.uco.pw.bulletinBoard.views.user.UserIndex;
import es.uco.pw.bulletinBoard.views.user.UserUpdate;
import es.uco.pw.bulletinBoard.views.user.UserUpdateInterest;


/**
 * The Class UserManagerController.
 */
public class UserManagerController {
	
	/** The user manager instance. */
	public static UserManager um = UserManager.getInstance();
	
	/**
	 * List all users from the user manager.
	 */
	public static void index() {
		UserIndex.view(um.toString());
	}
	
	/**
	 * Creates a user in the user manager.
	 */
	public static void create() {
		Boolean status = um.createUser(UserCreate.view());
		String message = ((status) ? "User created successfully" : "Error creating user");
		System.out.println(message);
	}
	
	/**
	 * Update a user in the user manager.
	 */
	public static void update() {
		User [] data = UserUpdate.view(um);
		if(data != null) {
			Boolean status = um.updateUser(data[0].getId(), data[1]);
			if(status) System.out.println("User updated successfully");
			else System.out.println("Error updating user");
		}
		else System.out.println("User not found");
	}
	
	/**
	 * Update user interest in the user manager.
	 * @param user the user
	 */
	public static void updateInterest(User user) {
		user.setInterests(UserUpdateInterest.view());
		um.updateUser(user.getId(), user); 
	}
	
	/**
	 * Delete a user from the user manager.
	 */
	public static void delete() {
		String email = UserDelete.view();
		if(um.deleteUser(um.findUserByEmail(email).getId())) System.out.println("User deleted succesfully");
		else System.out.println("Error deleting user");
	}
	
	/**
	 * Search a user by email.
	 */
	public static void searchByEmail() {
		String email = UserFind.view("email");
		User user = um.findUserByEmail(email);
		if(user != null) System.out.println(user.toString());
		else System.out.println("User not found");
	}
	
	/**
	 * Search a set of users by full name.
	 */
	public static void searchByFullName() {
		String fullName = UserFind.view("fullname");
		ArrayList<User> users = um.searchUserByFullName(fullName);
		if(users != null) users.forEach(user -> {System.out.println(user.toString());} );
		else System.out.println("Users not found");
	}
	
	/**
	 * Search a set of users by age.
	 */
	public static void searchByAge() {
		Integer age = Integer.parseInt(UserFind.view("age"));
		ArrayList<User> users = um.searchUserByAge(age);
		if(users != null) users.forEach(user -> {System.out.println(user.toString());} );
		else System.out.println("Users not found");
	}
	
	/**
	 * Search a set of users by interest.
	 */
	public static void searchByInterest() {
		String interest = UserFind.view("interest");
		ArrayList<User> users = um.searchUserByInterest(interest);
		if(users != null) users.forEach(user -> {System.out.println(user.toString());} );
		else System.out.println("Users not found");
	}

}
