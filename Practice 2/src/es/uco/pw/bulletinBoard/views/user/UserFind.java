package es.uco.pw.bulletinBoard.views.user;

import java.util.Scanner;

/**
 * The Class UserFind shows in terminal an assistant to search / find a contact.
 */
public class UserFind {
	
	/**
	 * Shows the finder assistant.
	 *
	 * @param field the field to search by
	 * @return the string with the user response
	 */
	public static String view(String field) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the "+field+": ");
		return sc.nextLine();
	}
}
