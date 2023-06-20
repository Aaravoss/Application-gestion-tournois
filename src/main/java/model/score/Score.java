/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.score;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.equipe.Equipe;
import model.match.Match;
import model.equipe.Equipe;

/**
 * 
 * @author Morgan Nayet
 * @author Carolane Pulval-Dady
 */
public class Score implements Serializable {
	
	private int score;
	private Equipe equipe;
	
	/**
	 * Créer un nouveau score selon l'équipe et le match
	 */
	public Score(Equipe equipe) {
		this.score = 0;
		this.equipe = equipe;
	}


	/**
	 * Setter du score associé à l'équipe et au match
	 * @param score nouvelle valeur de score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	public boolean isSameEquipe(Equipe equipe) {

		return (equipe == this.equipe);
	}

	public int getScore(){
		return this.score;
	}
}
