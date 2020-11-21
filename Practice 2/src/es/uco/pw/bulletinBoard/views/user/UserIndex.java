package es.uco.pw.bulletinBoard.views.user;

/**
 * The Class UserIndex shows in terminal a list of users.
 */
public class UserIndex {
	
	/**
	 * Shows the list of users.
	 *
	 * @param list the list of users
	 * @return true, if successful
	 */
	public static boolean view(String list) {
		if(list.length() == 0) list = "No users at list. Add users to list them.";
		System.out.println(list);
		return true;
	}

}
