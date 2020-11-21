package es.uco.pw.bulletinBoard.views.ad;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;

public class AdFindInterest {
	public static Interest view() {
		
		Interest interestSelect = null;
		InterestDAO dao = new InterestDAO();
		ArrayList<Interest> interests;
		try {
			interests = dao.readAll();
			Integer option = 1;
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			
			
			Integer i = 1;
			for(Interest interest : interests) {
				System.out.println(i+". "+interest);
				i++;
			}
			System.out.print("Select an interest: ");
			option = sc.nextInt();
			if(option != 0) interestSelect = interests.get(option-1);
			
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return interestSelect;

	}
}
