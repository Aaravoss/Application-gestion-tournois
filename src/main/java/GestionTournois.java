package app;/*
 * @author Morgan Nayet             								18 Feb 2023
 * Copyrights
 */

import java.util.ArrayList;

import controller.GestionApplicationController;
import model.tournoi.Tournoi;
import view.IHMMenu;

/**
 * Lance l'application
 *
 * @author Morgan Nayet
 */
public class GestionTournois {

	private static ArrayList<Tournoi> tournois;

	/**
	 * Ajoute un tournoi nouvellement créé à l'ensemble des tournois de l'application
	 *
	 * @param tournoi nouvellement créé
	 *
	 * @author Morgan Nayet
	 */
	public static void sauvegarderTournois(Tournoi tournoi) {
		tournois.add(tournoi);
	}

	/**
	 * Getter des tournois de l'application
	 *
	 * @return une liste des tournois de l'application
	 *
	 * @author Morgan Nayet
	 */
	public static ArrayList<Tournoi> getTournois() {
		return tournois;
	}
	
	/**
	 * Lance l'application et
	 * ferme l'application quand l'utilisateur le décide
	 *
	 * @param args non utilisé
	 *
	 * @author Morgan Nayet
	 */
	public static void main(String[] args) {

		tournois = new ArrayList<Tournoi>();
		lancerApplication();
	}

	/**
	 * Lance l'ihm correspondant au menu d'accueil de l'application
	 *
	 * @author Morgan Nayet
	 */
	private static void lancerApplication() {

		new GestionApplicationController().initialisationApplication();
		IHMMenu.main(null);
	}
}
