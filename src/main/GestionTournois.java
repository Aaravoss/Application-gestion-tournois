/*
 * @author Morgan Nayet             								18 Feb 2023
 * Copyrights
 */
package main;

import java.util.ArrayList;
import main.model.tournoi.Tournoi;
import main.view.ihmmenu.IHMMenu;

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
