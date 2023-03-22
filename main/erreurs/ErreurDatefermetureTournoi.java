/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package src.main.erreurs;

import java.util.Calendar;

/**
 * 
 * @author Morgan Nayet
 */
public class ErreurDatefermetureTournoi extends Error {

	/**
	 * 
	 * TODO Javadoc - expliquer ce que fait le constructeur
	 * @param dateFermetureInvalide
	 */
	public ErreurDatefermetureTournoi(Calendar dateFermetureInvalide) {
		
		this.setStackTrace(getStackTrace());
	}
}
