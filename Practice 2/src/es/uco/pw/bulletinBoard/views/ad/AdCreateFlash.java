package es.uco.pw.bulletinBoard.views.ad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.FlashAd;
import es.uco.pw.bulletinBoard.business.ad.factory.FactoryAd;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.controllers.LoginController;


/**
 * The Class AdCreateFlash.
 */
public class AdCreateFlash {

	/**
	 * View.
	 *
	 * @return the flash ad
	 */
	public static FlashAd view() {
		FactoryAd factory = new FactoryAd();
		@SuppressWarnings("resource")
		Scanner sc  = new Scanner(System.in);
		System.out.println("Introduce the title ad: ");
		String title = sc.nextLine();
		System.out.println("Introduce the body ad: ");
		String body = sc.nextLine();
		User owner = LoginController.getUser();
		System.out.println("Introduce the start date: (dd/MM/yyyy) ");
		String startDate = sc.nextLine();
		System.out.println("Introduce the end date: (dd/MM/yyyy)");
		String endDate = sc.nextLine();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			
		LocalDate start = LocalDate.parse(startDate, formatter);
	
		LocalDate end = LocalDate.parse(endDate, formatter);
	
		
		return factory.createFlashAd(title, body, owner.getId(), AdStatus.edited, LocalDate.now(), start, end);
	}
}
