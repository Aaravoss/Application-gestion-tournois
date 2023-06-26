/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.equipe;

import java.io.Serializable;

/**
 * Gestion de la donnée Equipe
 * Donnée de l'application sérialisable.
 *
 * @author Morgan Nayet
 */
public class Equipe implements Serializable {

	private String nom;
	
	/**
	 * Créé une équipe participant au tournoi
	 * 
	 * @param nom de l'équipe
	 *
	 * @author Morgan Nayet
	 */
	public Equipe(String nom) {

		this.nom = nom;
	}

	/**
	 * Getter du nom d'équipe
	 *
	 * @return le nom de l'équipe
	 *
	 * @author Morgan Nayet
	 */
	public String getNom() {
		return this.nom;
	}

}
