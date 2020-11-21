package es.uco.pw.bulletinBoard.controllers;

import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.user.*;

/**
 * The Class LoginController.
 */
public class LoginController {
	
	/** The logged user. */
	private static User user = null;
	
	/**
	 * Login.
	 */
	public static void login() {
		
		System.out.print("LOGIN (Introduce your email): ");
		
		@SuppressWarnings("resource")
		Scanner scc = new Scanner(System.in);
		String email = scc.nextLine();
		
		UserManager um = UserManager.getInstance();
		user = um.findUserByEmail(email);
	}
	
	/**
	 * Gets the logged user.
	 *
	 * @return the user
	 */
	public static User getUser() {
		return user;
	}
	
	/**
	 * Checks if the user is logged.
	 *
	 * @return the boolean (true if user is logged or false if not)
	 */
	public static Boolean isLogged() {
		return (user!=null);
	}

}
