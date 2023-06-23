/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package model.tournoi.type;

import model.tournoi.Tournoi;

/**
 * 
 * @author Morgan Nayet
 */
public class Classique extends Tournoi {
	
	/**
	 * Constructeur d'un tournoi de type Poule
	 * @param nom du tournoi
	 * @author Morgan Nayet
	 */
	public Classique(String nom, int nombreDEquipeACreer) {
		
		super(nom, nombreDEquipeACreer);
	}
	
	public Classique(String nom, int nombreDEquipeACreer, int nbEquipesParMatch,
					 int nbGagnantParPoule) {
		super(nom, nombreDEquipeACreer, nbEquipesParMatch, nbGagnantParPoule);
	}
}