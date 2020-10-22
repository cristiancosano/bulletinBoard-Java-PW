package es.uco.pw.bulletinBoard;

import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.Ad;
import es.uco.pw.bulletinBoard.business.AdStatus;
import es.uco.pw.bulletinBoard.business.AdType;
import es.uco.pw.bulletinBoard.business.Interest;
import es.uco.pw.bulletinBoard.business.User;
import es.uco.pw.bulletinBoard.business.UserHasInterest;
import es.uco.pw.bulletinBoard.data.dao.AdDAO;
import es.uco.pw.bulletinBoard.data.dao.InterestDAO;
import es.uco.pw.bulletinBoard.data.dao.UserDAO;
import es.uco.pw.bulletinBoard.data.dao.UserHasInterestDAO;

public class Main {

	public static void main(String[] args) {
		/*
		 * User u = new User("antonioharo@gmail.com", "Antonio", "Desaparecido",
		 * LocalDate.parse("2000-04-29"), new ArrayList<String>());
		 * 
		 * UserDAO d = new UserDAO(); d.create(u);
		 */
		
		String test = "userInterest";
		Double random = Math.random()*10000;
		Integer randInt = random.intValue();
		


		
		if(test == "user") {
			UserDAO u = new UserDAO();
			System.out.println(u.read(1));
			LocalDate d = LocalDate.parse("2000-04-29");
			String email = "antonioharo"+randInt+"@gmail.com";
			User user = new User("Antonio", "Haro", email, "password",
					d, new ArrayList<String>());
			u.create(user);
			System.out.println(user);
			
			user.setLastName("Haro Sanchez");
		
			u.update(user.getId(), user);
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
		else if(test == "userInterest") {
			UserHasInterestDAO dao = new UserHasInterestDAO();
			UserHasInterest obj = new UserHasInterest(1,2);
			System.out.println(dao.read(1));
			
			obj.setInterestId(3);
			dao.create(obj);
			System.out.println(dao.read(obj.getId()));
			
			obj.setInterestId(4);
			dao.update(obj.getId(), obj);
			System.out.println(dao.read(obj.getId()));
			
			dao.delete(obj.getId());
			System.out.println(dao.read(obj.getId()));
			
		}
		else if(test == "ad") {
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
			
			
		}
		
		
		
		
	} 
		
		
}
