/*
 * @author Morgan Nayet              19 Feb 2023
 * Copyrights
 */
package model.tournoi.type;

import erreurs.ErreurDatefermetureTournoi;
import model.equipe.Equipe;
import model.tournoi.Tournoi;

import java.util.Calendar;

/**
 *
 * @author Morgan Nayet
 */
public class LoserBracket extends Tournoi {

	private LoserBracket tournoisPerdants;

	/**
	 * Constructeur d'un tournoi de type WinnerBracket qui contient le tournoi des perdants (looserbracket)
	 * Le nombre d'équipe du LooserBracket est divisé par 2 car le nombre d'équipes du WinnerBracket
	 * créé est un exposant de 2 et seule la moitié est perdante
	 *
	 * @param nom du tournoi
	 * @param nombreDEquipeACreer
	 * @author Morgan Nayet
	 */
	public LoserBracket(String nom, int nombreDEquipeACreer) {

		super(nom, nombreDEquipeACreer, 2, 1);
		this.tournoisPerdants = new LoserBracket(this.getNom() + " - Tournoi des perdants", nombreDEquipeACreer/2, true);
	}

	/**
	 * Constructeur d'un tournoi de type looserBracket
	 *
	 * @param nom
	 * @param nombreDEquipeACreer
	 * @param tournoiPerdants param supplémentaire pour différencier le constructeur des WinnerBracket et LooserBracket
	 * @author Morgan Nayet
	 */
	private LoserBracket(String nom, int nombreDEquipeACreer, boolean tournoiPerdants){
		super(nom, nombreDEquipeACreer, 2, 1);
		this.tournoisPerdants = null; //on laisse la variable à null
	}

	/**
	 * Getter de l'attribut tournoisPerdants
	 * @return le tournois des perdants
	 */
	public LoserBracket getLoserBracket(){

		return this.tournoisPerdants;
	}

	/**
	 * Effectue la fermeture du tournoi WinnerBracket et de son tournoi LooserBracket
	 */
	public void fermer() {

		super.fermer();
		if (this.tournoisPerdants != null && !this.tournoisPerdants.isFerme()) {
			this.tournoisPerdants.fermer();
		}
	}
}