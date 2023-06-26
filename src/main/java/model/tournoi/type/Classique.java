/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package model.tournoi.type;

import model.tournoi.Tournoi;

/**
 * Gestion de la donnée du tournoi Classique
 * Donnée de l'application sérialisable.
 *
 * @author Morgan Nayet
 */
public class Classique extends Tournoi {
	
	/**
	 * Constructeur par défaut d'un tournoi de type Classique
	 *
	 * @param nom du tournoi
	 *
	 * @author Morgan Nayet
	 * @author Carolane Pulval-Dady
	 */
	public Classique(String nom, int nombreDEquipeACreer) {
		
		super(nom, nombreDEquipeACreer);
	}

	/**
	 * Constructeur du tournoi de type Classique
	 *
	 * @param nom du tournoi
	 * @param nombreDEquipeACreer au total
	 * @param nbEquipesParMatch
	 * @param nbGagnantParMatch
	 *
	 * @author Morgan Nayet
	 * @author Carolane Pulval-Dady
	 */
	public Classique(String nom, int nombreDEquipeACreer, int nbEquipesParMatch,
					 int nbGagnantParMatch) {
		super(nom, nombreDEquipeACreer, nbEquipesParMatch, nbGagnantParMatch);
	}
}