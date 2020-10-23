package es.uco.pw.bulletinBoard;

import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdHasRecipientUserDTO;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.AdType;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.data.dao.ad.AdDAO;
import es.uco.pw.bulletinBoard.data.dao.ad.AdHasRecipientUserDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.data.dao.interest.InterestDAO;
import es.uco.pw.bulletinBoard.data.dao.user.UserDAO;

public class Main {

	public static void main(String[] args) throws DAOException {
		String test = "user";
		Double random = Math.random()*10000;
		Integer randInt = random.intValue();
		
		if(test == "user") {
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
		else if(test == "userInterest") {
//			UserHasInterestDAO dao = new UserHasInterestDAO();
//			UserHasInterestDTO obj = new UserHasInterestDTO(1,2);
//			System.out.println(dao.read(1));
//			
//			obj.setInterestId(3);
//			dao.create(obj);
//			System.out.println(dao.read(obj.getId()));
//			
//			obj.setInterestId(4);
//			dao.update(obj.getId(), obj);
//			System.out.println(dao.read(obj.getId()));
//			
//			dao.delete(obj.getId());
//			System.out.println(dao.read(obj.getId()));
			
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
		else if(test == "adHasUser") {
			AdHasRecipientUserDAO dao = new AdHasRecipientUserDAO();
			System.out.println(dao.read(1));
			AdHasRecipientUserDTO object = new AdHasRecipientUserDTO(1, 3);
			
			dao.create(object);
			System.out.println(dao.read(object.getId()));
			
			object.setUserId(4);
			dao.update(object.getId(), object);
			System.out.println(dao.read(object.getId()));

			
			dao.delete(object.getId());
			System.out.println(dao.read(object.getId()));
			
		}
		
		
		
		
	} 
		
		
}
