/*
 * @author Morgan Nayet              								19 Feb 2023
 * Copyrights
 */
package main.model.tournoi;

import java.util.Calendar;

import main.erreurs.ErreurDatefermetureTournoi;

/**
 * 
 * @author Morgan Nayet
 */
public abstract class Tournoi {

	private String nom;
	private Calendar dateOuverture;
	private Calendar dateFermeture;

	/**
	 * Créer un nouveau tournoi et affecte automatiquement la date d'ouverture
	 * @param nom du tournoi
	 * @param nombreDEquipeACreer
	 * @author Morgan Nayet
	 */
	public Tournoi(String nom) {
		
		this.nom = nom;
		this.dateOuverture = Calendar.getInstance();
		this.dateFermeture = null;
	}
	
	/**
	 * Clôture le tournoi
	 * @throws Error si la date de clôture est antérieure à celle d'ouverture
	 */
	public void fermer() {
		
		Calendar calendrierFermeture;
		
		calendrierFermeture = Calendar.getInstance();
		if(calendrierFermeture.before(this.dateOuverture)) {
			throw new ErreurDatefermetureTournoi(calendrierFermeture);
		}
		
		this.dateFermeture = calendrierFermeture;
	}

	/**
	 * Getter du nom
	 * @return le nom du tournoi
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter de la date d'ouverture
	 * @return un nouvelle copie de dateOuverture
	 */
	public Calendar getDateOuverture() {
		
		return  Calendar.getInstance(this.dateOuverture.getTimeZone());
	}

	/**
	 * Getter de la date de fermeture
	 * @return une nouvelle copie la dateFermeture
	 * 		   ou null si le tournoi n'est pas terminé
	 */
	public Calendar getDateFermeture() {
		
		return this.dateFermeture == null 
				? null 
				: Calendar.getInstance(this.dateOuverture.getTimeZone())
				;
	}
}
