/*
 * @author Morgan Nayet              								22 Mar 2023
 * Copyrights
 */
package controller;

import javafx.stage.Stage;
import model.equipe.Equipe;
import model.match.Match;
import model.score.Score;
import model.tour.Tour;
import model.tournoi.Tournoi;
import model.tournoi.type.LoserBracket;

import java.util.List;

/**
 * 
 * @author Morgan Nayet
 */
public class GestionTournoiController {

    /**
     * Affecte les scores entrés au tournoi
     * Vérifie si le tour courant du tournoi est fini pour en recréer un nouveau
     *
     * @param stage page de l'IHM de l'application
     * @param tournoi dont on va mettre les scores à jour
     * @author Morgan Nayet
     */
    public void gererTournoi(Stage stage, Tournoi tournoi) {

        Equipe[] perdantsPourLoserBracket;

        perdantsPourLoserBracket = null;
        // si le tour courant est fini, on créé le nouveau tour avec les gagnants
        if (isTousLesScoresModifies(tournoi)){
            if (tournoi.getTourCourant().getMatchs().size() > 1) {
                perdantsPourLoserBracket = creerNouveauTour(tournoi);
            }
        }

        if (tournoi instanceof LoserBracket && isTousLesScoresModifies(((LoserBracket) tournoi).getLoserBracket())){
            LoserBracket loserBracket;

            loserBracket = ((LoserBracket) tournoi).getLoserBracket();

            if (loserBracket.getTourCourant() == null || loserBracket.getTourCourant().getMatchs().size() > 1) {
                creerNouveauTourLooserBracket(((LoserBracket) tournoi).getLoserBracket(), perdantsPourLoserBracket);
            }
        }

        view.IHMGestion page = new  view.IHMGestion();
        page.start(stage);
    }

    public void affecterScores(Stage stage, Tournoi tournoi, int[] scores){

        int indice;

        indice = 0;
        for (Match match : tournoi.getTourCourant().getMatchs()){
            for (Score score : match.getScores()){
                score.setScore(scores[indice]);
                indice++;
            }
        }

        view.IHMGestion page = new  view.IHMGestion();
        page.start(stage);
    }

    /**
     *
     * @param tournoi
     * @return les équipes perdantes
     */
    private Equipe[] creerNouveauTour(Tournoi tournoi){

        Tour nouveauTour;
        Equipe[] selectionnees;

        selectionnees = new Equipe[tournoi.getTourCourant().getMatchs().size()];
        //remplissage des équipes séléctionnées
        for (int i = 0 ; i < selectionnees.length ; i++){
            selectionnees[i] = tournoi.getTourCourant().getMatchs().get(i).getVainqueur();
        }

        Equipe[] perdants;

        perdants = null;
        //si le tour courant du tournoi n'est pas la finale, on ajoute les équipes
        if(tournoi.getTourCourant().getMatchs().size() > 1) {
            perdants = new Equipe[selectionnees.length];
            // Récupère les perdants pour les mettre dans la looser bracket
            for (int i = 0; i < perdants.length; i++) {
                perdants[i] = tournoi.getTourCourant().getMatchs().get(i).getPerdant();
            }
        }

        //création nouveau tour du tournoi
        nouveauTour = new Tour("Tour " + (tournoi.getTours().size() + 1));
        nouveauTour.setMatchs(selectionnees ,tournoi.getNbEquipesParMatch());

        tournoi.addNewTour(nouveauTour);

        return perdants;
    }

    private void creerNouveauTourLooserBracket(Tournoi tournoi, Equipe[] equipesAAjouterAuTournoi) {

        Tour nouveauTour;
        Equipe[] selectionnees;

        //cas où le tournoi WinnerBracket est en finale et on ne peut pas rajouter le perdant de la finale dans la LooserBracket
        if(equipesAAjouterAuTournoi == null){

            selectionnees = new Equipe[tournoi.getTourCourant().getMatchs().size()];

            for (int i = 0 ; i < selectionnees.length ; i++){
                selectionnees[i] = tournoi.getTourCourant().getMatchs().get(i).getVainqueur();
            }

        //cas du premier tour de tournoi et donc première insertion des équipes dans le looser bracket
        } else if (tournoi.getTours().size() == 0){

            selectionnees = new Equipe[equipesAAjouterAuTournoi.length];

            //remplissage des équipes séléctionnées
            for (int i = 0 ; i < selectionnees.length ; i++){
                selectionnees[i] = equipesAAjouterAuTournoi[i];
            }

        //cas des n tour
        } else {

            selectionnees = new Equipe[tournoi.getTourCourant().getMatchs().size() + equipesAAjouterAuTournoi.length];


            //remplissage des équipes séléctionnées avec mélange des gagnants LooserBracket et perdant WinnerBracket
            for (int i = 0 ; i < selectionnees.length/2 ; i++){
                selectionnees[i*2] = tournoi.getTourCourant().getMatchs().get(i).getVainqueur();
                selectionnees[i*2+1] = equipesAAjouterAuTournoi[i];
            }
        }

        nouveauTour = new Tour("Tour " + (tournoi.getTours().size() + 1));
        nouveauTour.setMatchs(selectionnees ,tournoi.getNbEquipesParMatch());
        tournoi.addNewTour(nouveauTour);
    }

    private boolean isTousLesScoresModifies(Tournoi tournoi){

        if (tournoi.getTourCourant() == null) return true; //cas du looserbracket lors du premier tour de son tournoi
        for (Match match : tournoi.getTourCourant().getMatchs()){
            boolean isNok;
            List<Score> scores;

            scores = match.getScores();
            isNok = false;
            for (int i = 0 ; i < match.getEquipes().size()-1 && !isNok; i++){
                isNok = isNok
                        || (scores.get(i).getScore() == scores.get(i+1).getScore() && scores.get(i).getScore() == 0)
                        ;
            }
            if (isNok) return false;
        }
        return true;
    }
}
