package es.uco.pw.bulletinBoard.business.ad.factory;

import java.time.LocalDate;
import java.util.ArrayList;

import es.uco.pw.bulletinBoard.business.ad.Ad;
import es.uco.pw.bulletinBoard.business.ad.AdStatus;
import es.uco.pw.bulletinBoard.business.ad.FlashAd;
import es.uco.pw.bulletinBoard.business.ad.IndividualizedAd;
import es.uco.pw.bulletinBoard.business.ad.ThematicAd;
import es.uco.pw.bulletinBoard.business.user.User;
import es.uco.pw.bulletinBoard.business.interest.Interest;


/**
 * The Class FactoryAd.
 */
public class FactoryAd extends AdCreator {

	/**
	 * Creates the general ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @return the general ad
	 */
	@Override
	public Ad createGeneralAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt) {
		Ad ad = new Ad(title, body, owner, status, createAt);
		return ad;
	}

	/**
	 * Creates the individualized ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @param recipients the recipients
	 * @return the individualized ad
	 */
	@Override
	public IndividualizedAd createIndividualizedAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt, ArrayList<User> recipients) {
		IndividualizedAd ad = new IndividualizedAd(title, body, owner, status, createAt, recipients);
		return ad;
	}

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
	@Override
	public FlashAd createFlashAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt, LocalDate startDate, LocalDate endDate) {
		FlashAd ad = new FlashAd(title, body, owner, status, createAt, startDate, endDate);
		return ad;
	}
	
	/**
	 * Creates the thematic ad.
	 *
	 * @param title the title
	 * @param owner the owner
	 * @param body the body
	 * @param interest the interest
	 * @return the thematic ad
	 */
	@Override
	public ThematicAd createThematicAd(String title, String body, Integer owner, AdStatus status, LocalDate createAt, ArrayList<Interest> interest) {
		ThematicAd ad = new ThematicAd(title, body, owner, status, createAt, interest);
		return ad;
	}

}
