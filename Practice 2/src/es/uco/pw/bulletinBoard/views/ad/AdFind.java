package es.uco.pw.bulletinBoard.views.ad;

import java.util.Scanner;

/**
 * The Class AdFind.
 */
public class AdFind {
	
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
