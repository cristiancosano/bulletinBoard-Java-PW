package es.uco.pw.bulletinBoard.views.user;

import java.util.Scanner;

/**
 * The Class UserDelete shows in terminal an assistant to delete a contact.
 */
public class UserDelete {
	
	/**
	 * Shows the deleter assistant.
	 *
	 * @return the email of the contact to delete
	 */
	public static String view() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the email of the user to delete: ");
		String email = sc.nextLine();
		return email;
	}
}
