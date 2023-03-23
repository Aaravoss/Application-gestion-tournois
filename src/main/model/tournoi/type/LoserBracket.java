/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package main.model.tournoi.type;

import main.model.tournoi.Tournoi;

/**
 * 
 * @author Morgan Nayet
 */
public class LoserBracket extends Tournoi {

	/**
	 * Constructeur d'un tournoi de type LooserBracket
	 * @param nom du tournoi
	 * @author Morgan Nayet
	 */
	public LoserBracket(String nom, int nombreDEquipeACreer) {
		super(nom, nombreDEquipeACreer, 2, 1);
	}
}