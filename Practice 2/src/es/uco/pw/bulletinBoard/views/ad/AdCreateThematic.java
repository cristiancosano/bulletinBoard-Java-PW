package es.uco.pw.bulletinBoard.views.ad;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.ThematicAd;
import es.uco.pw.bulletinBoard.business.ad.factory.FactoryAd;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.controllers.LoginController;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;


/**
 * The Class AdCreateThematic.
 */
public class AdCreateThematic {
	
	/**
	 * View.
	 *
	 * @param tagging the tagging
	 * @return the thematic ad
	 */
	public static ThematicAd view() {
		
		FactoryAd factory = new FactoryAd();
		ArrayList<Interest> newInterests = new ArrayList<Interest>();
		InterestDAO dao = new InterestDAO();
		ArrayList<Interest> interests;
		try {
			interests = dao.readAll();
			Integer option = 1;
			@SuppressWarnings("resource")
			Scanner sc  = new Scanner(System.in);
			System.out.println("Introduce the title ad: ");
			String title = sc.nextLine();
			System.out.println("Introduce the body ad: ");
			String body = sc.nextLine();
			User owner = LoginController.getUser();
			
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
			
			return factory.createThematicAd(title,body, owner.getId(), AdStatus.edited, LocalDate.now(), interests);


		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;

	}


}
