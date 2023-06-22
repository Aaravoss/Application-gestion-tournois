/*
 * @author Morgan Nayet              								16 Mar 2023
 * Copyrights
 */
package model.match;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import model.equipe.Equipe;
import model.score.Score;

/**
 * 
 * @author Morgan Nayet
 */
public class Match implements Serializable {
	
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

	public Equipe getVainqueur(){

		Equipe vainqueur;

		vainqueur = this.equipes.get(0);
		for(int i = 1 ; i < this.equipes.size() ; i++){
			if (this.scores.get(i-1).getScore() < this.scores.get(i).getScore()){
				vainqueur = this.equipes.get(i);
			}
		}
		return vainqueur;
	}

	/**
	 * Récupère l'équipe ayant fais le score le plus bas
	 * Utilisable en l'état que pour les tournois de type LooserBracket
	 *
	 * @return le perdant avec le score le plus bas
	 * @author Morgan Nayet
	 */
	public Equipe getPerdant(){

		Equipe perdant;

		perdant = this.equipes.get(0);
		for(int i = 1 ; i < this.equipes.size() ; i++){
			if (this.scores.get(i-1).getScore() > this.scores.get(i).getScore()){
				perdant = this.equipes.get(i);
			}
		}
		return perdant;
	}
}
