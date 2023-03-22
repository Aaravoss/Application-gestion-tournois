/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package main.controller;

import javafx.stage.Stage;
import main.GestionTournois;
import main.model.tour.Tour;
import main.model.tournoi.Tournoi;
import main.model.tournoi.type.LooserBracket;
import main.model.tournoi.type.Poule;
import main.view.ihmmenu.IHMCreationLoserBracket;
import main.view.ihmmenu.IHMCreationPoule;
import main.view.ihmmenu.IHMGestion;
import static main.utils.BusinessConstants.TYPE_LOSER_BRACKET;
import static main.utils.BusinessConstants.TYPE_POULE;

/**
 * 
 * @author Morgan Nayet
 */
public class CreationTournoiController {

	/**
	 * Fourni un tournoi à l'ihm spécialisée dans le type de tournoi désiré
	 * 
	 * @param nom du tournoi
	 * @param nombreDEquipeACreer composant le tournoi
	 */
	public void creerTournoi(String typeTournoi, String nom, 
			int nombreEquipeACreer, Stage stage)  
			throws Exception {
		
		switch(typeTournoi) {
			case TYPE_LOSER_BRACKET :
				new IHMCreationLoserBracket(new LooserBracket(nom, nombreEquipeACreer))
					.start(stage);
				break;
			case TYPE_POULE :
				new IHMCreationPoule(new Poule(nom, nombreEquipeACreer))
				.start(stage);
				break;
			default :
				throw new Exception("Mauvais type de tournoi fourni ! Erreur = " 
						+ typeTournoi);
		}
	}
	
	/**
	 * Attribue les équipes au tournoi
	 * 
	 * @param stage 
	 * @param tournoi en cour de création
	 * @param nomEquipes ensemble des noms de toutes les équipes du tournoi
	 */
	public void attribuerEquipes(Stage stage, Tournoi tournoi, String[] nomEquipes,
								int nbEquipesParMatch, int nbGagnantParMatch) {
		
		//re-création des objets avec le nb d'équipes et de gagnants par match
		if (tournoi instanceof Poule) {
			
			tournoi = new Poule(tournoi.getNom(), tournoi.getEquipes().length, 
					nbEquipesParMatch, nbGagnantParMatch);
		}
		
		//création des équipes
		tournoi.setEquipes(nomEquipes);
		
		this.atribuerMatchs1erTour(stage, tournoi);
		GestionTournois.sauvegarderTournois(tournoi);
		
		new IHMGestion().start(stage);
	}
	
	public void atribuerMatchs1erTour(Stage stage, Tournoi tournoi) {
		
		Tour tour;
		
		tour = new Tour("1er tour");
		tour.setMatchs(tournoi.getEquipes(), tournoi.getNbEquipesParMatch());
		tournoi.addNewTour(tour);
	}
}
