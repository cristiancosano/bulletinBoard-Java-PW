package es.uco.pw.bulletinBoard.views.ad;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.controllers.LoginController;

/**
 * The Class AdUpdate.
 */
public class UpdateAd {

	/**
	 * View.
	 *
	 */
	public static Ad view(ArrayList<Ad> ads) {

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		
		Boolean status = false;
		int index = -1;
		
		while(!status) {
			
			for(int i=0; i<ads.size(); i++) {
				System.out.println(i+". "+ads.get(i).getTitle());
			}
						
			System.out.print("Introduce the ad id for update ad: ");
			int id = sc.nextInt();
			
			if(id >= 0 && id < ads.size()) {
				index = id;
				status = true;
			}
		}
		
		Ad ad = ads.get(index);
		
		System.out.println("Current data in parentheses");
		System.out.println("Introduce the new title ("+ads.get(index).getTitle()+"): ");
		ad.setTitle(sc.nextLine());
		System.out.println("Introduce the new body ("+ads.get(index).getBody()+"): ");
		ad.setBody(sc.nextLine());
		User owner = LoginController.getUser();
		ad.setOwnerUser(owner.getId());
		
		return ad;
		
	}
	
}
