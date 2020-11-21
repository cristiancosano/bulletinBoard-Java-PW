package es.uco.pw.bulletinBoard.views.ad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.business.ad.factory.FactoryAd;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.business.user.UserManager;
import es.uco.pw.bulletinBoard.controllers.LoginController;

/**
 * The Class AdCreateIndividualized.
 */
public class AdCreateIndividualized {
	
	/**
	 * View.
	 *
	 * @return the individualized ad
	 */
	public static IndividualizedAd view() {
		ArrayList<User> recipients = new ArrayList<User>();
		FactoryAd factory = new FactoryAd();
		@SuppressWarnings("resource")
		Scanner sc  = new Scanner(System.in);
		System.out.print("Introduce the title ad: ");
		String title = sc.nextLine();
		System.out.print("Introduce the body ad: ");
		String body = sc.nextLine();
		User owner = LoginController.getUser();
		
		String option = "Y";
		while(!option.equals("N")) {
			User a = null;
			while(a == null) {
				System.out.print("Introduce the email recipient: ");
				String email = sc.nextLine();
				UserManager cm = UserManager.getInstance();
				a = cm.findUserByEmail(email);
				if(a == null) System.out.println("User not found. Try again.");
			}
			if (!recipients.contains(a)) recipients.add(a);
			else System.out.println("The contact was already in the system.");
			System.out.print("Do you want add more recipients? (Y/N): ");
			option = sc.nextLine();
		}
		
		return factory.createIndividualizedAd(title,  body, owner.getId(), AdStatus.edited, LocalDate.now(), recipients);
	}

}
