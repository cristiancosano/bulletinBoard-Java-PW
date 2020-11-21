package es.uco.pw.bulletinBoard.views.user;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.controllers.LoginController;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;
import es.uco.pw.bulletinBoard.data.dao.user.UserDAO;



public class UserUpdateInterest {
	
	public static ArrayList<Interest> view() {
		
		ArrayList<Interest> newInterests = new ArrayList<Interest>();
		InterestDAO dao = new InterestDAO();
		UserDAO daoUser  = new UserDAO();
		ArrayList<Interest> interestUser = daoUser.getInterests(LoginController.getUser().getId());
		ArrayList<Interest> interestDontHaveUser = daoUser.getInterestsDontHaveUser(LoginController.getUser().getId());
		
		/*
		try {
			interests = dao.readAll();
			Integer option = 1;
			
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			
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
			
		} catch (DAOException e) {
			e.printStackTrace();
		}
		*/
		return newInterests;

	}

}
