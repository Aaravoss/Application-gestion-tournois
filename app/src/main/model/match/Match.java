/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package main.model.match;

import java.util.ArrayList;
import java.util.List;
import main.model.equipe.Equipe;

/**
 * 
 * @author Morgan Nayet
 */
public class Match {
	
	private List equipes;
	
	/**
	 * Créer un nouveau match par défaut
	 */
	public Match() {
		this.equipes = new ArrayList<Equipe>();
	}
	
	/**
	 * Setter des équipes particiants au match
	 * @param equipe ajoutée au match
	 */
	public void addEquipe(Equipe equipe) {
		this.equipes.add(equipe);
	}
}
