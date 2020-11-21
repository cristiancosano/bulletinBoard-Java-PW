package es.uco.pw.bulletinBoard.views.ad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.business.user.UserManager;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.data.dao.ad.IndividualizedAdDAO;
import es.uco.pw.bulletinBoard.data.dao.ad.IndividualizedAdDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

public class UpdateAdIndividualized {/*
	
	public static IndividualizedAd view(ArrayList<Ad> ads) throws DAOException {
		IndividualizedAdDAO dao = new IndividualizedAdDAO();
		Ad ad = UpdateAd.view(ads);
		IndividualizedAd individualizedAd = dao.read(ad.getId());
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
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
		
		return new IndividualizedAd(ad, start, end);
	}
	*/

}
