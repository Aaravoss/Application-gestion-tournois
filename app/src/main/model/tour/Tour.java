/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package main.model.tour;

import java.util.ArrayList;
import java.util.List;

import main.model.equipe.Equipe;
import main.model.match.Match;

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
		
		Match match;
		
		match = new Match();
		for(int i = 0 ; i < equipes.length ; i++) {
			for(int y = 0 ; y < nbEquipesParMatch ; y++) {
				i++;
				match.addEquipe(equipes[i]);
			}
			this.matchs.add(match);
			match = new Match();
		}
	}
}
