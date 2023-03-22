/*
 * @author Morgan Nayet             								18 Feb 2023
 * Copyrights
 */
package src.main;

import java.util.ArrayList;
import src.main.model.tournoi.Tournoi;
import src.main.view.IHMMenu;

/**
 * Lance l'application
 * @author Morgan Nayet
 */
public class GestionTournois {

	private static ArrayList<Tournoi> tournois;
	
	public static void sauvegarderTournois(Tournoi tournoi) {
		tournois.add(tournoi);
	}
	
	public static ArrayList<Tournoi> getTournois() {
		return tournois;
	}
	
	/**
	 * Lance l'application et
	 * ferme l'application quand l'utilisateur le décide
	 * @param args non utilisé
	 */
	public static void main(String[] args) {

		tournois = new ArrayList<Tournoi>();
		lancerApplication();
	}

	/**
	 * Lance l'ihm correspondant au menu d'accueil de l'application
	 */
	private static void lancerApplication() {
		
		IHMMenu.main(null);
	}
}
