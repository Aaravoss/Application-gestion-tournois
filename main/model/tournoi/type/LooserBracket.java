/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package src.main.model.tournoi.type;

import src.main.model.tournoi.Tournoi;

/**
 * 
 * @author Morgan Nayet
 */
public class LooserBracket extends Tournoi {

	/**
	 * Constructeur d'un tournoi de type LooserBracket
	 * @param nom du tournoi
	 * @author Morgan Nayet
	 */
	public LooserBracket(String nom, int nombreDEquipeACreer) {
		super(nom, nombreDEquipeACreer, 2, 1);
	}
}