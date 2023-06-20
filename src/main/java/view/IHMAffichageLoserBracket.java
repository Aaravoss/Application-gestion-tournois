package view;

import controller.GestionApplicationController;
import controller.GestionTournoiController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.tournoi.Tournoi;
import static utils.BusinessConstants.TAILLE_ECRAN_X;
import static utils.BusinessConstants.TAILLE_ECRAN_Y;
import static utils.BusinessConstants.TAILLE_BTN_X;
import static utils.BusinessConstants.TAILLE_BTN_Y;

import java.util.ArrayList;
import java.util.List;

public class IHMAffichageLoserBracket extends Application {

    private Tournoi tournoi;
    private ArrayList<TextField> scores;

    public IHMAffichageLoserBracket(model.tournoi.Tournoi tournoi) {

        this.tournoi = tournoi;
        this.scores = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        stage.setTitle("Affichage tournoi Loser Bracket");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);

        Label titre = new Label("Affichage tournoi Loser Bracket");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(TAILLE_ECRAN_X /5);
        titre.setLayoutY(100);

        GridPane listeMatch = new GridPane();
        listeMatch.setHgap(3);
        listeMatch.setVgap(10);
        listeMatch.setPadding(new Insets(50,50,50,50));
        listeMatch.setAlignment(Pos.CENTER);
        listeMatch.setLayoutX(50);
        listeMatch.setLayoutY(250);
        List<model.match.Match> listeMatchs = tournoi.getTourCourant().getMatchs();
        Label labelTour = new Label(tournoi.getTourCourant().getNom());
        listeMatch.add(labelTour, 0,0 );
        int i;
        for (int j = 0; j < listeMatchs.size(); j++) {
            GridPane gridMatch = new GridPane();
            gridMatch.setHgap(listeMatchs.size());
            gridMatch.setVgap(2);
            gridMatch.setPadding(new Insets(10,10,10,10));


            i = 0;
            for(model.equipe.Equipe equipe : listeMatchs.get(j).getEquipes() ) {
                Label labelEquipe = new Label(equipe.getNom());
                TextField textFieldScore = new TextField();
                double ligne = i/gridMatch.getHgap();
                double colonne = i%gridMatch.getHgap();
                gridMatch.add(labelEquipe,(int) ligne, (int) colonne );
                gridMatch.add(textFieldScore, (int) ligne+1, (int) colonne);
                this.scores.add(textFieldScore);
                i++;
            }

            double ligne = j/gridMatch.getHgap();
            double colonne = j%gridMatch.getHgap();
            listeMatch.add(gridMatch, (int) ligne , (int) colonne +1);


        }

        Button btnConfirmer = new Button();
        btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
        btnConfirmer.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnConfirmer.setText("Confirmer");
        btnConfirmer.setFont(new Font("Cambria", 10));
        btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int[] scoresEquipes;

                scoresEquipes = new int[scores.size()];

                for(int i = 0 ; i < scores.size() ; i++) {

                    scoresEquipes[i] = "".equals(scores.get(i).getText()) ? 0 : (int) Integer.parseInt(scores.get(i).getText());
                }

                new GestionTournoiController().gererTournoi(stage, tournoi, scoresEquipes);

            }
        });

        Button btnAnnuler = new Button();
        btnAnnuler.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X - TAILLE_ECRAN_X * 0.05);
        btnAnnuler.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnAnnuler.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnAnnuler.setText("Annuler");
        btnAnnuler.setFont(new Font("Cambria", 10));
        btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                view.IHMGestion page = new  view.IHMGestion();
                page.start(stage);
            }
        });

        // quand on quitte (l'application ? ou la fenêtre ?), la fermeture est lancée
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            new GestionApplicationController().fermerApplication();
        });

        root.getChildren().addAll(titre,listeMatch, btnConfirmer, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }}