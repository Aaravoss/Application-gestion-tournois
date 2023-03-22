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
public class Poule extends Tournoi {
	
	/**
	 * Constructeur d'un tournoi de type Poule
	 * @param nom du tournoi
	 * @author Morgan Nayet
	 */
	public Poule(String nom, int nombreDEquipeACreer) {
		
		super(nom, nombreDEquipeACreer);
	}
	
	public Poule(String nom, int nombreDEquipeACreer, int nbEquipesParMatch, 
			int nbGagnantParPoule) {
		super(nom, nombreDEquipeACreer, nbEquipesParMatch, nbGagnantParPoule);
	}
}