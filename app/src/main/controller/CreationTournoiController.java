/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package main.controller;

import javafx.stage.Stage;
import main.GestionTournois;
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
	
	public void attribuerEquipes(Stage stage, Tournoi tournoi, String[] nomEquipes) {
		
		//sauvegarde du tournoi
		tournoi.setEquipes(nomEquipes);
		GestionTournois.sauvegarderTournois(tournoi);
		
		new IHMGestion().start(stage);
	}
}
