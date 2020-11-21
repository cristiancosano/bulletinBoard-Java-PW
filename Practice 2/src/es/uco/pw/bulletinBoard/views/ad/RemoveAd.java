package es.uco.pw.bulletinBoard.views.ad;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.Ad;


/**
 * The Class RemoveAd.
 */
public class RemoveAd {
	
	/**
	 * View.
	 *
	 * @param ads the ads
	 * @return the ad
	 */
	public static Ad view(ArrayList<Ad> ads) {
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		
		
		Boolean status = false;
		int index = -1;
		
		while(!status) {
			
			for(int i=0; i<ads.size(); i++) {
				System.out.println(ads.get(i).getId()+". "+ads.get(i).getTitle());
			}
		
			System.out.println("Introduce the ad id for delete ad: ");
			int id = sc.nextInt();
			
			for(int i=0; i<ads.size(); i++) {
				if(ads.get(i).getId() == id) {
					index = i;
					status = true;
				}
			}
		}
		return ads.get(index);
	}

}
