package es.uco.pw.bulletinBoard.views.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.business.user.UserManager;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;




/**
 * The Class UserUpdate shows in terminal an assistant to update a user.
 */
public class UserUpdate {
	
	/**
	 * Shows the updater assistant.
	 *
	 * @param cm the user manager
	 * @param tagging the tagging
	 * @return the user[] with the currentEmail and the user updated
	 */
	public static User[] view(UserManager cm) {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Current user email: ");
		String currentEmail = sc.nextLine();
		User currentUser = cm.findUserByEmail(currentEmail);
		if(currentUser != null) {
			System.out.print("New name (Current: "+currentUser.getName()+"): ");
			String name = sc.nextLine();
			System.out.print("New lastname (Current: "+currentUser.getLastName()+"): ");
			String lastName = sc.nextLine();
			System.out.print("New password (Current: "+currentUser.getPassword()+"): ");
			String password = sc.nextLine();
			System.out.print("New birthday (Current: "+currentUser.getDateOfBirth().format(DateTimeFormatter.ofPattern("dd/MM/YYYY"))+"): ");
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			LocalDate birthDay = LocalDate.parse(sc.nextLine(), formatter);
			System.out.print("New email (Current: "+currentUser.getEmail()+"): ");
			String email = sc.nextLine();
			System.out.println("New interests (Current: "+currentUser.getInterests()+"): ");
			
			ArrayList<Interest> newInterests = new ArrayList<Interest>();
			InterestDAO dao = new InterestDAO();
			ArrayList<Interest> interests;
			try {
				interests = dao.readAll();
				Integer option = 1;
				
				while(!option.equals(0)) {
					Integer i = 1;
					for(Interest interest : interests) {
						System.out.println(i+". "+interest);
						i++;
					}
					System.out.print("Select an interest (type 0 to exit): ");
					option = sc.nextInt();
					if(option != 0) newInterests.add(interests.get(option-1));
				}
				User newUser = new User(email, name, lastName, password, birthDay, newInterests);
				User[] array = {currentUser, newUser};
				return array;
				
			} catch (DAOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

}
