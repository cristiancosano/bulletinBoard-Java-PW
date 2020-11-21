package es.uco.pw.bulletinBoard;

import java.time.LocalDate;
import java.util.ArrayList;


import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.business.user.UserManager;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;
import es.uco.pw.bulletinBoard.data.dao.user.UserDAO;

public class Main {

	public static void main(String[] args) throws DAOException {
		
		String test = "ad";
		Double random = Math.random()*10000;
		Integer randInt = random.intValue();
		
		if(test == "user") {
			UserManager um = UserManager.getInstance();
			System.out.println(um.getAllUsers());
			UserDAO u = new UserDAO();
			System.out.println(u.read(1));
			LocalDate d = LocalDate.parse("2000-04-29");
			String email = "antonioharo"+randInt+"@gmail.com";
			
			InterestDAO idao = new InterestDAO();
			ArrayList<Interest> interests = new ArrayList<Interest>();
			
			interests.add(idao.read(1));
			interests.add(idao.read(2));

			User user = new User("Antonio", "Haro", email, "password",d, interests);
			u.create(user);
			System.out.println(user);
			
			user.setLastName("Haro Sanchez");
			u.addInterest(user.getId(), 3);
			System.out.println(u.getInterests(user.getId()));
			
			user.getInterests().add(idao.read(5));
			u.update(user.getId(), user);
			System.out.println(u.read(user.getId()));
			
			u.removeInterest(user.getId(), 1);
			System.out.println(u.read(user.getId()));
			
			u.delete(user.getId());
			System.out.println(u.read(user.getId()));
		}
		else if(test == "interest") {
			InterestDAO dao = new InterestDAO();
			System.out.println(dao.read(1));
			
			Interest obj = new Interest("Interes"+randInt);
			dao.create(obj);
			System.out.println(dao.read(obj.getId()));
			
			obj.setValue("Interes1"+randInt+1);
			dao.update(obj.getId(), obj);
			System.out.println(dao.read(obj.getId()));
			
			dao.delete(obj.getId());
			System.out.println(dao.read(obj.getId()));
			
			
		}
		else if(test == "ad") {
			/*
			AdDAO dao = new AdDAO();
			System.out.println(dao.read(1));
			
			Ad object = new Ad("Titulo "+randInt, "Body "+randInt, 1, AdType.flash, AdStatus.edited, LocalDate.now());
			dao.create(object);
			System.out.println(dao.read(object.getId()));

			
			object.setTitle("Prueba1"+randInt);
			dao.update(object.getId(), object);
			System.out.println(dao.read(object.getId()));

			
			dao.delete(object.getId());
			System.out.println(dao.read(object.getId()));
			
			
			
			UserDAO u = new UserDAO();
			ArrayList<User> user = new ArrayList<User>();
			user.add(u.read(1));
			user.add(u.read(2));
			*/
			

			//ThematicAdDAO dao = new ThematicAdDAO();
			InterestDAO idao = new InterestDAO();
			ArrayList<Interest> interests = new ArrayList<Interest>();
			
			interests.add(idao.read(1));
			interests.add(idao.read(2));
			//LocalDate date = LocalDate.now();
			//ThematicAd ad = new ThematicAd("Titulo "+randInt, "Body "+randInt, 1, AdStatus.edited, date, interests);
		}
	} 		
}
