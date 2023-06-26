/*
 * @author Morgan Nayet              								19 Feb 2023
 * Copyrights
 */
package model.tournoi;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import erreurs.ErreurDatefermetureTournoi;
import model.equipe.Equipe;
import model.tour.Tour;

/**
 * Gestion de la donnée tournoi
 * Donnée de l'application sérialisable.
 *
 * @author Morgan Nayet
 */
public abstract class Tournoi implements Serializable {

	private String nom;
	private Calendar dateOuverture;
	private Calendar dateFermeture;
	private List<Tour> tours;
	private Equipe[] equipes;
	private int nbEquipesParMatch;
	private int nbGagnantParMatch;

	/**
	 * Créer un nouveau tournoi et
	 * affecte automatiquement la date d'ouverture
	 * 
	 * @param nom du tournoi
	 * @param nombreDEquipeACreer
	 *
	 * @author Morgan Nayet
	 */
	protected Tournoi(String nom, int nombreDEquipeACreer) {
		
		this.nom = nom;
		this.dateOuverture = Calendar.getInstance();
		this.dateFermeture = null;
		this.tours = new ArrayList<Tour>();
		this.equipes = new Equipe[nombreDEquipeACreer];
	}

	/**
	 *
	 * @param nom
	 * @param nombreDEquipeACreer
	 * @param nbEquipesParMatch
	 * @param nbGagnantParMatch
	 */
	protected Tournoi(String nom, int nombreDEquipeACreer, int nbEquipesParMatch, 
			int nbGagnantParMatch) {
		this(nom, nombreDEquipeACreer);
		this.nbEquipesParMatch = nbEquipesParMatch;
		this.nbGagnantParMatch = nbGagnantParMatch;
	}
	
	/**
	 * Clôture le tournoi
	 *
	 * @throws Error si la date de clôture est antérieure à celle d'ouverture
	 *
	 * @author Morgan Nayet
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
	 *
	 * @return le nom du tournoi
	 *
	 * @author Morgan Nayet
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter de la date d'ouverture
	 *
	 * @return un nouvelle copie de dateOuverture
	 *
	 * @author Morgan Nayet
	 */
	public Calendar getDateOuverture() {
		
		return  Calendar.getInstance(this.dateOuverture.getTimeZone());
	}

	/**
	 * Getter de la date de fermeture
	 *
	 * @return une nouvelle copie la dateFermeture <br>
	 * 		   ou null si le tournoi n'est pas terminé
	 *
	 * @author Morgan Nayet
	 */
	public Calendar getDateFermeture() {
		
		return this.dateFermeture == null 
				? null 
				: Calendar.getInstance(this.dateOuverture.getTimeZone())
				;
	}

	/**
	 * Getter des equipes
	 *
	 * @return un tableau des équipes du tournoi
	 *
	 * @author Morgan Nayet
	 */
	public Equipe[] getEquipes() {
		return this.equipes;
	}
	
	/**
	 * Setter des equipes
	 * Insère 1 à 1 les nouvelles équipes 
	 * dans la limite disponible dans le tournoi
	 * 
	 * @param nouvsEquipes à insérer dans le tournoi
	 *
	 * @author Morgan Nayet
	 */
	public void setEquipes(String[] nouvsEquipes) {
		
		for(int i = 0 ; i < this.equipes.length ; i++) {
			this.equipes[i] = new Equipe(nouvsEquipes[i]);
		}
	}

	/**
	 * Setter des équipes
	 * Ecrase les anciennes équipes pour les remplacer par les nouvelles
	 *
	 * @param nouvsEquipes du tournoi
	 *
	 * @author Morgan Nayet
	 */
	public void setEquipes(Equipe[] nouvsEquipes){

		this.equipes = nouvsEquipes;
	}
	
	/**
	 * Setter pour ajouter un nouveau tour
	 *
	 * @param tour à ajouter au tournoi
	 *
	 * @author Morgan Nayet
	 */
	public void addNewTour(Tour tour) {
		
		this.tours.add(tour);
	}

	/** 
	 * Getter du tour courant
	 * 
	 * @return le dernier tour <br>
	 * 		   null si pas de tours créés
	 *
	 * @author Morgan Nayet
	 */
	public Tour getTourCourant() {
		return this.tours.size() == 0 ? null : (Tour) this.tours.get(this.tours.size()-1);
	}

	/** 
	 * Getter du nombre d'équipes par match
	 * 
	 * @return the nbEquipesParMatch
	 *
	 * @author Morgan Nayet
	 */
	public int getNbEquipesParMatch() {
		return this.nbEquipesParMatch;
	}

	/**
	 * Getter des tours du tournoi
	 *
	 * @return une liste avec tous les tours du tournoi
	 *
	 * @author Morgan Nayet
	 */
	public List<Tour> getTours() {
		return this.tours;
	}

	/**
	 * Vérifie si le tournoi est fermé
	 *
	 * @return true si le tournoi a une date de fermeture <br>
	 * 		   false sinon
	 */
	public Boolean isFerme() {
		return this.dateFermeture != null;
	}
}
