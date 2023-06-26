/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package erreurs;

import java.util.Calendar;

/**
 * Exception pour la date de fermeture
 *
 * @author Morgan Nayet
 */
public class ErreurDatefermetureTournoi extends Error {

	/**
	 * Exception lancée si la date de fermeture est supérieure à la date d'ouverture
	 *
	 * @param dateFermetureInvalide date de fermeture invalide affetée à un tournoi
	 *
	 * @author Morgan Nayet
	 */
	public ErreurDatefermetureTournoi(Calendar dateFermetureInvalide) {
		
		this.setStackTrace(getStackTrace());
	}
}
