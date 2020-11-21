package es.uco.pw.bulletinBoard.views.user;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.user.*;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;

/**
 * The Class UserCreate shows in terminal an assistant to create a contact..
 */
public class UserCreate {
	
	/**
	 * Shows the creator assistant.
	 *
	 * @param tagging the tagging system
	 * @return the contact created
	 */
	public static User view() {
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.print("Name: ");
		String name = sc.nextLine();
		System.out.print("Lastname: ");
		String lastName = sc.nextLine();
		System.out.print("Email: ");
		String email = sc.nextLine();
		System.out.print("Password: ");
		String password = sc.nextLine();
		System.out.print("Birthday (d/MM/yyyy): ");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		LocalDate birthDay = LocalDate.parse(sc.nextLine(), formatter);
		
		
		InterestDAO dao = new InterestDAO();
		ArrayList<Interest> interests;
		ArrayList<Interest> newInterests = new ArrayList<Interest>();
		User user = null;
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
			
			user = new User(name, lastName, email, password,  birthDay, newInterests);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return user;
		
	}
}
