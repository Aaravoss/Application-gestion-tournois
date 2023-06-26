/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.tour;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import model.equipe.Equipe;
import model.match.Match;
import model.score.Score;

/**
 * Gestion de la donnée Tour
 * Donnée de l'application sérialisable.
 *
 * @author Morgan Nayet
 */
public class Tour implements Serializable {
	
	private String nom;
	private List matchs;

	/**
	 * Créer un nouveau tour
	 * 
	 * @param nom du tour
	 *
	 * @author Morgan Nayet
	 */
	public Tour(String nom) {
		
		this.nom = nom;
		this.matchs = new ArrayList<Match>();
	}

	/**
	 * Setter des matchs du tour courant
	 *
	 * @param equipes participants au tour courant
	 * @param nbEquipesParMatch nombre d'équipes participant à chaque match
	 *
	 * @author Morgan Nayet
	 */
	public void setMatchs(Equipe[] equipes, int nbEquipesParMatch) {
		
		for(int i = 0 ; i < equipes.length ; i++) {
			
			Match match;
			int y;
			
			match = new Match();
			for(y = 0 ; y < nbEquipesParMatch ; y++) {
				match.addEquipeAndScore(equipes[i+y]);
			}
			i += y-1; //sinon à la fin de la boucle y = 2
			this.matchs.add(match);
		}
	}

	/**
	 * Getter des matchs du tour
	 *
	 * @return une liste des matchs du tour
	 *
	 * @author Morgan Nayet
	 */
	public List<Match> getMatchs() {

		return this.matchs;
	}

	/**
	 * Getter du nom
	 *
	 * @return le nom du tour
	 *
	 * @author Morgan Nayet
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * Getter du score d'une équipe
	 *
	 * @param equipe souhaitée
	 *
	 * @return l'objet Score lié à l'équipe souhaitée
	 *
	 * @author Morgan Nayet
	 */
	public Score getScore(Equipe equipe) {
		for(Object match : this.matchs) {
			if(((Match)match).getScore(equipe) != null ) {
				return ((Match) match).getScore(equipe);
			}
		}
		return null;
	}
}
