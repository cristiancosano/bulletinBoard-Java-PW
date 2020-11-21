package es.uco.pw.bulletinBoard.views.ad;

import java.time.LocalDate;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.factory.FactoryAd;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.controllers.LoginController;



/**
 * The Class AdCreateGeneral.
 */
public class AdCreateGeneral {
	
	/**
	 * View.
	 *
	 * @return the general ad
	 */
	public static Ad view() {
		
		FactoryAd factory = new FactoryAd();
		@SuppressWarnings("resource")
		Scanner sc  = new Scanner(System.in);
		System.out.println("Introduce the title ad: ");
		String title = sc.nextLine();
		System.out.println("Introduce the body ad: ");
		String body = sc.nextLine();
		User owner = LoginController.getUser();
		
		
		return factory.createGeneralAd(title, body, owner.getId(), AdStatus.edited, LocalDate.now());
	}


}
