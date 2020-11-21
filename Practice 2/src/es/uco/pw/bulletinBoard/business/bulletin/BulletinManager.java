package es.uco.pw.bulletinBoard.business.bulletin;


import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.AdType;
import es.uco.pw.bulletinBoard.business.ad.FlashAd;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.business.ad.ThematicAd;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.data.dao.ad.AdDAO;
import es.uco.pw.bulletinBoard.data.dao.ad.FlashAdDAO;
import es.uco.pw.bulletinBoard.data.dao.ad.IndividualizedAdDAO;
import es.uco.pw.bulletinBoard.data.dao.ad.ThematicAdDAO;
import es.uco.pw.bulletinBoard.data.dao.common.DAOException;


public class BulletinManager {
	
	private AdDAO adDAO; 
	private ThematicAdDAO themathicDAO;
	private FlashAdDAO flashDAO;
	private IndividualizedAdDAO individualizedADAO;
	
	/**
	 * Instantiates a new bulletin board.
	 */
	
	public BulletinManager() {
		this.adDAO = new AdDAO();
		this.themathicDAO = new ThematicAdDAO();
		this.flashDAO = new FlashAdDAO();
	};
	

	public Boolean createGeneralAd(Ad ad) throws DAOException {
		Boolean status = false;
		status = (this.adDAO.create(ad) != 0);
		return status;
	}
	
	public Boolean createThematicAd(ThematicAd ad) throws DAOException {
		Boolean status = false;
		status = (this.themathicDAO.create(ad) != 0);
		return status;
	}
	
	public Boolean createFlashAd(FlashAd ad) throws DAOException {
		Boolean status = false;
		status = (this.flashDAO.create(ad) != 0);
		return status;
	}
	
	public Boolean createIndividualizedAd(IndividualizedAd ad) throws DAOException {
		Boolean status = false;
		status = (this.individualizedADAO.create(ad) != 0);
		return status;
	}
	
	
	public Boolean updateStatus(Integer id, AdStatus adStatus) throws DAOException {
		Boolean status = false;
		Ad ad;
		ad = this.adDAO.read(id);
		ad.setStatus(adStatus);
		status = (this.adDAO.update(id, ad)!=0);
		return status;
	}
	
	
	public Boolean removeAd(Integer id) throws DAOException {
		Boolean status = false;
		status =(this.adDAO.delete(id)!=0);
		return status;
	}
	
	public Boolean removeFlashAd(Integer id) throws DAOException {
		Boolean status = false;
		status =(this.flashDAO.delete(id)!=0);
		return status;
	}
	
	public Boolean removeIndividualizedAd(Integer id) throws DAOException {
		Boolean status = false;
		status =(this.individualizedADAO.delete(id)!=0);
		return status;
	}
	
	public Boolean removeThematicAd(Integer id) throws DAOException {
		Boolean status = false;
		status =(this.themathicDAO.delete(id)!=0);
		return status;
	}
	
	public ArrayList<Ad> getAdsOwnerUser(Integer id) throws DAOException {
		return this.adDAO.getByOwner(id);
	}
		
	public ArrayList<Ad> getAdsByRecipient(User user){
		return this.individualizedADAO.getAdByRecipients(user.getId());
	}
	
	public ArrayList<Ad> getAllAds() throws DAOException{
		return this.adDAO.readAll();
	}
	
	public ArrayList<ThematicAd> getAdByInterest(String interest) throws DAOException{
		return this.themathicDAO.getAdByInterest(interest);
	}
	
	public ArrayList<ThematicAd> getAdsByInterests(ArrayList<String> interests) throws DAOException{
		return this.themathicDAO.getAdByInterest(interests);
	}
	
	public Boolean archiveAd(Ad ad) throws DAOException {
		Boolean status = false;
		ad.setStatus(AdStatus.archived);
		this.adDAO.update(ad.getId(), ad);
		return status;
	}

	
	public Boolean publishAd(Ad ad) throws DAOException {
		Boolean status = false;
		if(ad.getType()== AdType.flash) ad.setStatus(AdStatus.waiting);
		else ad.setStatus(AdStatus.published);
		this.adDAO.update(ad.getId(), ad);
		return status;
	}
	

	/**
	 * Gets the ads by publication date.
	 *
	 * @param date the date
	 * @return the ads by publication date
	 * @throws DAOException 
	 */
	public ArrayList<Ad> getAdsByPublicationDate(LocalDate date) throws DAOException{
		
		return this.adDAO.getAdByDate(date);
	}
	
	
	/**
	 * Gets the flash ads.
	 *
	 * @return the flash ads
	 */
	public ArrayList<Ad> getFlashAds(){
	
		return null;
	}
	
	/**
	 * Gets the list of ads that current user can see.
	 *
	 * @return the ads that user can see
	 */
	public ArrayList<Ad> getAdsToUser(Integer userId, String order){
		return this.adDAO.getAdsToUser(userId, order);
	}
	
	/**
	 * To string.
	 *
	 * @return the string
	 */
	public String toString() {
		StringBuffer s = new StringBuffer();
		try {
			this.adDAO.readAll().forEach(ad -> {
				s.append(ad.toString()+"\n");
			});
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return s.toString();
	}

}
