package es.uco.pw.bulletinBoard.controllers;

import java.util.ArrayList;
import java.util.Scanner;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdType;
import es.uco.pw.bulletinBoard.business.ad.ThematicAd;
import es.uco.pw.bulletinBoard.business.bulletin.BulletinManager;
import es.uco.pw.bulletinBoard.business.user.UserManager;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;
import es.uco.pw.bulletinBoard.views.ad.AdCreateFlash;
import es.uco.pw.bulletinBoard.views.ad.AdCreateGeneral;
import es.uco.pw.bulletinBoard.views.ad.AdCreateIndividualized;
import es.uco.pw.bulletinBoard.views.ad.AdCreateThematic;
import es.uco.pw.bulletinBoard.views.ad.AdFindDate;
import es.uco.pw.bulletinBoard.views.ad.AdFindInterest;
import es.uco.pw.bulletinBoard.views.ad.ArchiveAd;
import es.uco.pw.bulletinBoard.views.ad.PublishAd;
import es.uco.pw.bulletinBoard.views.ad.RemoveAd;
import es.uco.pw.bulletinBoard.views.user.UserFind;

public class BulletinBoardController {
	
	private static BulletinManager bm = new BulletinManager();

	private static UserManager um  = UserManager.getInstance();

	
	
	/**
	 * Creates an ad in the bulletin board.
	 * @throws DAOException 
	 */
	public static void create(AdType type) throws DAOException {
		
		if(type == AdType.general) bm.createGeneralAd(AdCreateGeneral.view());
		else if(type == AdType.flash) bm.createFlashAd(AdCreateFlash.view());
		else if(type == AdType.individualized) bm.createIndividualizedAd(AdCreateIndividualized.view());
		else if(type == AdType.thematic) bm.createThematicAd(AdCreateThematic.view());
	}
	
	/**
	 * Publish an ad in the bulletin board.
	 * @throws DAOException 
	 */
	public static void publish() throws DAOException {
		bm.publishAd(PublishAd.view(bm.getAdsOwnerUser(LoginController.getUser().getId())));
	}
	
	/**
	 * Removes an ad from the bulletin board.
	 * @throws DAOException 
	 */
	public static void remove() throws DAOException {
		Ad ad = RemoveAd.view(bm.getAdsOwnerUser(LoginController.getUser().getId()));
		if(ad.getType() == AdType.general) bm.removeAd(ad.getId());
		else if(ad.getType() == AdType.flash) bm.removeFlashAd(ad.getId());
		else if(ad.getType() == AdType.individualized) bm.removeIndividualizedAd(ad.getId());
		else if(ad.getType() == AdType.thematic) bm.removeThematicAd(ad.getId());
	}
	
	/**
	 * Archived an ad from the bulletin board.
	 * @throws DAOException 
	 */
	public static void archived() throws DAOException {
		bm.archiveAd(ArchiveAd.view(bm.getAdsOwnerUser(LoginController.getUser().getId())));
	}
	
	/**
	 * Index all published ads.
	 */
	public static void index() {
		Integer order = 0;
		String orderBy;

		while(order<1 || order>2) {
			System.out.println("1. By publication date: ");
			System.out.println("2. By owner user: ");
			System.out.print("Select order: ");
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			order = sc.nextInt();
		}
		if(order == 1) orderBy = "create_at";
		else orderBy = "owner_user";
		
		bm.getAdsToUser(LoginController.getUser().getId(), orderBy).forEach(ad -> {System.out.println(ad.toString());});	
	}
	
	/**
	 * Edits an ad.
	 */
	public static void edit() {
		//UpdateAd.view();
	}
	
	/**
	 * Search ads by interest.
	 * @throws DAOException 
	 */
	public static void searchByInterest() throws DAOException {
		ArrayList<ThematicAd> ads = null;
		ads  = bm.getAdByInterest(AdFindInterest.view().getValue());
		if(ads.size()>0) ads.forEach(ad -> {System.out.println(ad.toString());} );
		else System.out.println("Ads not found");
	}
	
	
	/**
	 * Search ads by date.
	 * @throws DAOException 
	 */
	public static void searchByDate() throws DAOException {
		ArrayList<Ad> ads = null;
		ads = bm.getAdsByPublicationDate(AdFindDate.view());
		if(ads.size()>0) ads.forEach(ad -> {System.out.println(ad.toString());} );
		else System.out.println("Ads not found");
	}
	
	/**
	 * Search ads by owner.
	 * @throws DAOException 
	 */
	public static void searchByOwner() throws DAOException {
		ArrayList<Ad> ads = null;
		String email = UserFind.view("email owner");
		
		ads = bm.getAdsOwnerUser(um.findUserByEmail(email).getId());
		if(ads.size()>0) ads.forEach(ad -> {System.out.println(ad.toString());} );
		else System.out.println("Ads not found");
	}
	
	/**
	 * Search ads by recipient.
	 */
	public static void searchByRecipient() {
		ArrayList<Ad> ads = null;
		String email = UserFind.view("email recipients");
		ads = bm.getAdsByRecipient(um.findUserByEmail(email));
		if(ads.size()>0) ads.forEach(ad -> {System.out.println(ad.toString());} );
		else System.out.println("Ads not found");
	}

}
