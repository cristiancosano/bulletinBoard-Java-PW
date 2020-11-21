package es.uco.pw.bulletinBoard.views.ad;

import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.AdType;



/**
 * The Class AdCreate.
 */
public class AdCreate {
	

	/**
	 * View.
	 *
	 * @return the ad
	 */
	public static AdType view() {
		
		
		AdType type = null;
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		Integer option = 0;
		
		while(option < 1 || option > 4) {
	
			System.out.println("1. General Ad");
			System.out.println("2. Individualized Ad: ");
			System.out.println("3. Flash Ad");
			System.out.println("4. Thematic Ad");
			System.out.println("Choose type ad: ");
			option = sc.nextInt();
			
		}
		
		switch(option) {
			case 1:
				type = AdType.general;
				break;
			
			case 2: 
				type = AdType.individualized;
				break;
				
			case 3: 
				type = AdType.flash;
				break;
				
			case 4: 
				type = AdType.thematic;
				break;		
		}
		
		return type;
	}

}
