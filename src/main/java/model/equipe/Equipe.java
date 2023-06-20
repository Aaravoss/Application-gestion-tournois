/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.equipe;

import java.io.Serializable;

/**
 * 
 * @author Morgan Nayet
 */
public class Equipe implements Serializable {

	private String nom;
	
	/**
	 * Créé une équipe participant au tournoi
	 * 
	 * @param nom de l'équipe
	 */
	public Equipe(String nom) {

		this.nom = nom;
	}

	public String getNom() {
		return this.nom;
	}

}
