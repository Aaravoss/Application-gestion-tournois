/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package src.main.model.tour;

import java.util.ArrayList;
import java.util.List;

import src.main.model.equipe.Equipe;
import src.main.model.match.Match;

/**
 * 
 * @author Morgan Nayet
 */
public class Tour {
	
	private String nom;
	private List matchs;

	/**
	 * Créer un nouveau tour
	 * 
	 * @param nom du tour
	 * @author Morgan Nayet
	 */
	public Tour(String nom) {
		
		this.nom = nom;
		this.matchs = new ArrayList<Match>();
	}

	/**
	 * Setter des matchs du tour courant
	 * @param equipes participants au tour courant
	 * @param nbEquipesParMatch nombre d'équipes participant à chaque match
	 */
	public void setMatchs(Equipe[] equipes, int nbEquipesParMatch) {
		
		for(int i = 0 ; i < equipes.length ; i++) {
			
			Match match;
			int y;
			
			match = new Match();
			for(y = 0 ; y < nbEquipesParMatch ; y++) {
				match.addEquipe(equipes[i+y]);
			}
			i += y-1; //sinon à la fin de la boucle y = 2
			this.matchs.add(match);
		}
	}
}
