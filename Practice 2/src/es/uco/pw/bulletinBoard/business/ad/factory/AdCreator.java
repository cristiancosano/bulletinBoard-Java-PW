package es.uco.pw.bulletinBoard.business.ad.factory;

import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.FlashAd;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.business.ad.ThematicAd;
import es.uco.pw.bulletinBoard.business.interest.Interest;
import es.uco.pw.bulletinBoard.business.user.User;

/**
 * The Class AdCreator.
 */
public abstract class AdCreator {
	
	/**
	 * Creates the general ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @return the general ad
	 */
	public abstract Ad createGeneralAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt);
	
	/**
	 * Creates the individualized ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @param recipients the recipients
	 * @return the individualized ad
	 */
	public abstract IndividualizedAd createIndividualizedAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt, ArrayList<User> recipients);
	
	/**
	 * Creates the flash ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @param startDate the start date
	 * @param endDate the end date
	 * @return the flash ad
	 */
	public abstract FlashAd createFlashAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt , LocalDate startDate, LocalDate endDate);
	
	/**
	 * Creates the thematic ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @param interest the interest
	 * @return the thematic ad
	 */
	public abstract ThematicAd createThematicAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt, ArrayList<Interest> interest);
	
}




