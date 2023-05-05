/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.match;

import java.util.ArrayList;
import java.util.List;
import model.equipe.Equipe;
import model.score.Score;

/**
 * 
 * @author Morgan Nayet
 */
public class Match {
	
	private List<Equipe> equipes;
	private List<Score> scores;
	
	/**
	 * Créer un nouveau match par défaut
	 */
	public Match() {
		this.equipes = new ArrayList<Equipe>();
		this.scores = new ArrayList<Score>();
	}
	
	/**
	 * Setter des équipes particiants au match
	 * @param equipe ajoutée au match
	 */
	public void addEquipeAndScore(Equipe equipe) {
		this.equipes.add(equipe);
		this.scores.add(new Score(equipe));
	}

	public List<Score> getScores() { return this.scores;}

	public Score getScore(Equipe equipe) {
		for(Score score : this.scores ) {
			if(score.isSameEquipe(equipe)) {
				return score;
			}
		}
		return null;
	}

	public List<Equipe> getEquipes() { return this.equipes; }
}
