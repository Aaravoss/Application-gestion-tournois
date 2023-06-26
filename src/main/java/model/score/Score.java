/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.score;

import java.io.Serializable;
import model.equipe.Equipe;

/**
 * Gestion de la donnée Score
 * Donnée de l'application sérialisable.
 *
 * @author Morgan Nayet
 * @author Carolane Pulval-Dady
 */
public class Score implements Serializable {
	
	private int score;
	private Equipe equipe;
	
	/**
	 * Créer un nouveau score selon l'équipe et le match
	 *
	 * @author Morgan Nayet
	 */
	public Score(Equipe equipe) {
		this.score = 0;
		this.equipe = equipe;
	}


	/**
	 * Setter du score associé à l'équipe et au match
	 *
	 * @param score nouvelle valeur de score
	 *
	 * @author Morgan nayet
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Vérifie l'équipe liée au score
	 *
	 * @param equipe renseignée
	 *
	 * @return true si l'équipe renseignée est la même que l'équipe liée au score <br>
	 * 				false sinon
	 *
	 * @author Carolane Pulval-Dady
	 */
	public boolean isSameEquipe(Equipe equipe) {

		return (equipe == this.equipe);
	}

	/**
	 * Getter du score
	 *
	 * @return la valeur numérique et entière du score
	 *
	 * @author Morgan Nayet
	 */
	public int getScore(){
		return this.score;
	}
}
