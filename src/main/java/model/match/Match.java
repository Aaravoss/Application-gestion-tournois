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
 * Gestion de la donnée Match
 * Donnée de l'application sérialisable.
 *
 * @author Morgan Nayet
 */
public class Match implements Serializable {
	
	private List<Equipe> equipes;
	private List<Score> scores;
	
	/**
	 * Créer un nouveau match par défaut
	 *
	 * @author Morgan Nayet
	 */
	public Match() {
		this.equipes = new ArrayList<Equipe>();
		this.scores = new ArrayList<Score>();
	}
	
	/**
	 * Setter des équipes particiants au match
	 *
	 * @param equipe ajoutée au match
	 *
	 * @author Morgan Nayet
	 */
	public void addEquipeAndScore(Equipe equipe) {
		this.equipes.add(equipe);
		this.scores.add(new Score(equipe));
	}

	/**
	 * Getter des scores
	 *
	 * @return une liste des scores du match
	 *
	 * @author Morgan Nayet
	 */
	public List<Score> getScores() { return this.scores;}

	public Score getScore(Equipe equipe) {
		for(Score score : this.scores ) {
			if(score.isSameEquipe(equipe)) {
				return score;
			}
		}
		return null;
	}

	/**
	 * Getter des équipes
	 *
	 * @return une liste des équipes du match
	 *
	 * @author Morgan Nayet
	 */
	public List<Equipe> getEquipes() { return this.equipes; }

	/**
	 * Fournit en retour l'équipe vaincqueure du match après affectation des nouveaux scores
	 *
	 * @return le vaincqueur du match
	 *
	 * @author Morgan Nayet
	 */
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
	 *
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
