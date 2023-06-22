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
	 * @author Morgan Nayet
	 */
	protected Tournoi(String nom, int nombreDEquipeACreer) {
		
		this.nom = nom;
		this.dateOuverture = Calendar.getInstance();
		this.dateFermeture = null;
		this.tours = new ArrayList<Tour>();
		this.equipes = new Equipe[nombreDEquipeACreer];
	}
	
	protected Tournoi(String nom, int nombreDEquipeACreer, int nbEquipesParMatch, 
			int nbGagnantParMatch) {
		this(nom, nombreDEquipeACreer);
		this.nbEquipesParMatch = nbEquipesParMatch;
		this.nbGagnantParMatch = nbGagnantParMatch;
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

	/**
	 * Getter des equipes
	 * @return the equipes
	 */
	public Equipe[] getEquipes() {
		return this.equipes;
	}
	
	/**
	 * Setter des equipes
	 * Insère 1 à 1 les nouvelles équipes 
	 * dans la limite disponible dans le tournoi
	 * 
	 * @param nouvsEquipes
	 */
	public void setEquipes(String[] nouvsEquipes) {
		
		for(int i = 0 ; i < this.equipes.length ; i++) {
			this.equipes[i] = new Equipe(nouvsEquipes[i]);
		}
	}

	public void setEquipes(Equipe[] nouvsEquipes){

		this.equipes = nouvsEquipes;
	}
	
	/**
	 * Setter pour ajouter un nouveau tour
	 * @param tour
	 */
	public void addNewTour(Tour tour) {
		
		this.tours.add(tour);
	}

	/** 
	 * tours attribute getter
	 * 
	 * @return le dernier tour
	 * 		   ou null si pas de tours créés
	 */
	public Tour getTourCourant() {
		return this.tours.size() == 0 ? null : (Tour) this.tours.get(this.tours.size()-1);
	}

	/** 
	 * nbEquipesParMatch attribute getter
	 * 
	 * @return the nbEquipesParMatch
	 */
	public int getNbEquipesParMatch() {
		return this.nbEquipesParMatch;
	}

	public List<Tour> getTours() {
		return this.tours;
	}
}
