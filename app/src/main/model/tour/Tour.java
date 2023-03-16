/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package main.model.tour;

import java.util.ArrayList;
import java.util.List;
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

}
