/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package controller;

import javafx.stage.Stage;
import app.GestionTournois;
import exceptions.TournoiException;
import model.tour.Tour;
import model.tournoi.Tournoi;
import model.tournoi.type.Classique;
import view.IHMCreationLoserBracket;
import view.IHMCreationTournoiClassique;
import view.IHMGestion;
import static utils.BusinessConstants.TYPE_LOSER_BRACKET;
import static utils.BusinessConstants.TYPE_CLASSIQUE;

/**
 * Classe de Création de tout tournoi. S'occupe de créer et d'instancier l'état initial des tournois de tout type.
 *
 * Fonctionnalités :
 * Création d'un tournoi
 * Attribution de ses équipes
 * Attribution de ses matchs
 *
 * @author Morgan Nayet
 */
public class CreationTournoiController {

	/**
	 * Fourni un tournoi à l'ihm spécialisée dans le type de tournoi désiré
	 *
	 * @param typeTournoi
	 * @param nom du tournoi
	 * @param nombreEquipeACreer composant le tournoi
	 * @param stage l'état de l'application transféré
	 * @throws TournoiException si le type de tournoi renseigné n'existe pas (attention : fournir une constante pour le type)
	 *
	 * @author Morgan Nayet
	 */
	public void creerTournoi(String typeTournoi, String nom, 
			int nombreEquipeACreer, Stage stage)  
			throws TournoiException {
		
		switch(typeTournoi) {
			case TYPE_LOSER_BRACKET :
				new IHMCreationLoserBracket(new model.tournoi.type.LoserBracket(nom, nombreEquipeACreer))
					.start(stage);
				break;
			case TYPE_CLASSIQUE :
				new IHMCreationTournoiClassique(new Classique(nom, nombreEquipeACreer))
				.start(stage);
				break;
			default :
				throw new TournoiException("Mauvais type de tournoi fourni ! Erreur = " 
						+ typeTournoi);
		}
	}
	
	/**
	 * Création des objets équipes et attribution de ces objets au tournoi
	 *
	 * @param nbEquipesParMatch nombre de candidats (équipe) par match
	 * @param nbGagnantParMatch nombre de gagnants pour 1 match donné
	 * @param nomEquipes ensemble des noms de toutes les équipes du tournoi
	 * @param stage l'état de l'application transféré
	 * @param tournoi en cour de création
	 *
	 * @author Morgan Nayet
	 */
	public void attribuerEquipes(Stage stage, Tournoi tournoi, String[] nomEquipes,
								int nbEquipesParMatch, int nbGagnantParMatch) {
		
		//re-création des objets avec le nb d'équipes et de gagnants par match
		if (tournoi instanceof Classique) {
			
			tournoi = new Classique(tournoi.getNom(), tournoi.getEquipes().length,
					nbEquipesParMatch, nbGagnantParMatch);
		}
		
		//création des équipes
		tournoi.setEquipes(nomEquipes);
		
		this.atribuerMatchs1erTour(tournoi);
		GestionTournois.sauvegarderTournois(tournoi);
		
		new IHMGestion().start(stage);
	}
	
	/**
	 * Répartition des équipes dans les différents matchs qui se dérouleront au 1er tour
	 *
	 * @param tournoi en cours de création
	 *
	 * @author Morgan Nayet
	 */
	public void atribuerMatchs1erTour(Tournoi tournoi) {
		
		Tour tour;
		
		tour = new Tour("Tour 1");
		tour.setMatchs(tournoi.getEquipes(), tournoi.getNbEquipesParMatch());
		tournoi.addNewTour(tour);
	}
}
