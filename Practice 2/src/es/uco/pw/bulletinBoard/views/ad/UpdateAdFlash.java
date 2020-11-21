package es.uco.pw.bulletinBoard.views.ad;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.FlashAd;
import es.uco.pw.bulletinBoard.data.dao.ad.FlashAdDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;

public class UpdateAdFlash {
	
	public static FlashAd view(ArrayList<Ad> ads) throws DAOException {
		FlashAdDAO dao = new FlashAdDAO();
		Ad ad = UpdateAd.view(ads);
		FlashAd flashAd = dao.read(ad.getId());
		
		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduce the start date: (dd/MM/yyyy) ("+flashAd.getStartDate()+")");
		String startDate = sc.nextLine();
		System.out.println("Introduce the end date: (dd/MM/yyyy) ("+flashAd.getEndDate()+")");
		String endDate = sc.nextLine();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
			
		LocalDate start = LocalDate.parse(startDate, formatter);
	
		LocalDate end = LocalDate.parse(endDate, formatter);
		
		return new FlashAd(ad, start, end);
	}

}
