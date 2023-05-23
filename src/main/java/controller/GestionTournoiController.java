/*
 * @author Morgan Nayet              								22 Mar 2023
 * Copyrights
 */
package controller;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.equipe.Equipe;
import model.match.Match;
import model.score.Score;
import model.tour.Tour;
import model.tournoi.Tournoi;
import model.tournoi.type.LoserBracket;

import java.util.ArrayList;

/**
 * 
 * @author Morgan Nayet
 */
public class GestionTournoiController {

    /**
     *
     * @author Morgan Nayet
     */
    public void gererTournoi(Stage stage, Tournoi tournoi, int[] scores) {

        int indice;

        indice = 0;
        for (Match match : tournoi.getTourCourant().getMatchs()){
            for (Score score : match.getScores()){
                score.setScore(scores[indice]);
                indice++;
            }
        }

        // si le tour courant est fini, on créé le nouveau tour avec les gagnants
        if (isTousLesScoresModifies(tournoi, scores)){
            creerNouveauTour(tournoi);
        }

        view.IHMGestion page = new  view.IHMGestion();
        page.start(stage);
    }

    private void creerNouveauTour(Tournoi tournoi){

        Tour nouveauTour;
        Equipe[] selectionnees;

        selectionnees = new Equipe[tournoi.getTourCourant().getMatchs().size()];
        //remplissage des équipes séléctionnées
        for (int i = 0 ; i < selectionnees.length ; i++){
            selectionnees[i] = tournoi.getTourCourant().getMatchs().get(i).getVainqueur();
        }
        nouveauTour = new Tour("Tour " + (tournoi.getTours().size() + 1));
        nouveauTour.setMatchs(selectionnees ,tournoi.getNbEquipesParMatch());

        tournoi.addNewTour(nouveauTour);
    }

    private boolean isTousLesScoresModifies(Tournoi tournoi, int[] scores){

        int indice;

        indice = 0;
        for (Match match : tournoi.getTourCourant().getMatchs()){
            boolean isNok;
            isNok = false;
            for (int i = 0 ; i < match.getEquipes().size()-1 && !isNok; i++){
                isNok = scores[indice] == scores[indice+1] && scores[indice] == 0;
                indice++;
            }
            if (isNok) return false;
        }
        return true;
    }

    private void sauvegarderEtatTournoi(Tournoi tournoi){


    }
}
