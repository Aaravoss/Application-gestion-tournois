/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package main.model.score;

import java.util.ArrayList;
import java.util.List;
import main.model.equipe.Equipe;
import main.model.match.Match;

/**
 * 
 * @author Morgan Nayet
 * @author Carolane Pulval-Dady
 */
public class Score {
	
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

}
