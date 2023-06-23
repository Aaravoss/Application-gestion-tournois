package view;

import controller.GestionApplicationController;
import controller.GestionTournoiController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.tour.Tour;
import javafx.stage.WindowEvent;
import model.tournoi.Tournoi;
import model.tournoi.type.LoserBracket;

import static utils.BusinessConstants.*;

import java.util.ArrayList;
import java.util.List;

public class IHMAffichageLoserBracket extends Application {

    private Tournoi tournoi;
    private ArrayList<TextField> scoresWB;
    private ArrayList<TextField> scoresLB;

    public IHMAffichageLoserBracket(model.tournoi.Tournoi tournoi) {

        this.tournoi = tournoi;
        this.scoresWB = new ArrayList<>();
        this.scoresLB = new ArrayList<>();
    }

    @Override
    public void start(Stage stage) {
        ScrollPane root = new ScrollPane();
        root.setFitToHeight(true);
        stage.setTitle("Affichage tournoi Loser Bracket");
        Group page = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);

        Label titre = new Label("Affichage tournoi Loser Bracket");
        titre.setFont(new Font("Cambria", TAILLE_TITRE));
        titre.setLayoutX(0);
        titre.setLayoutY(10);


        int x = 10;
        int nbTour = 0;
        for(Tour tour : tournoi.getTours()) {
            GridPane listeMatch = new GridPane();
            listeMatch.setPadding(new Insets(50,50,50,50));
            listeMatch.setAlignment(Pos.CENTER);
            listeMatch.setLayoutX(x);
            listeMatch.setLayoutY(30);
            List<model.match.Match> listeMatchs = tour.getMatchs();
            Label labelTour = new Label(tour.getNom());
            labelTour.setStyle("-fx-font-weight: bold");
            listeMatch.addColumn(nbTour, labelTour);
            for (int nbMatch = 0; nbMatch < listeMatchs.size(); nbMatch++) {
                GridPane gridMatch = new GridPane();
                gridMatch.setHgap(listeMatchs.size());
                gridMatch.setVgap(2);
                gridMatch.setPadding(new Insets(10,10,10,10));
                for(model.equipe.Equipe equipe : listeMatchs.get(nbMatch).getEquipes() ) {
                    if(tour == tournoi.getTourCourant()) {
                        HBox hBoxMatch = new HBox();
                        hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                        hBoxMatch.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                        Label labelEquipe = new Label(equipe.getNom());
                        labelEquipe.setPadding(new Insets(5,5,5,5));
                        labelEquipe.setTextFill(Color.WHITE);
                        TextField textFieldScore = new TextField();
                        hBoxMatch.getChildren().addAll(labelEquipe, textFieldScore);
                        gridMatch.addColumn(nbTour,hBoxMatch);
                        this.scoresWB.add(textFieldScore);
                    } else {
                        Label labelEquipe = new Label(equipe.getNom());
                        labelEquipe.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                        labelEquipe.setTextFill(Color.WHITE);
                        labelEquipe.setPadding(new Insets(5,5,5,5));
                        Label labelScore = new Label(""+ tour.getScore(equipe).getScore());
                        labelScore.setPadding(new Insets(5,5,5,5));
                        HBox hBoxMatch = new HBox();
                        hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                        hBoxMatch.getChildren().addAll(labelEquipe, labelScore);
                        gridMatch.addColumn(nbTour,hBoxMatch);
                    }
                }
                listeMatch.addColumn(nbTour, gridMatch);
            }

            x += 100;
            page.getChildren().add(listeMatch);
            nbTour++;
        }


        if (nbTour >= 2 && tournoi instanceof LoserBracket) {
            Tournoi tournoiLB = ((LoserBracket) tournoi).getLoserBracket();
            int nbTourLB = 0;
            double xLB= TAILLE_ECRAN_X - 250;;
            for(Tour tourLB : tournoiLB.getTours()) {
                GridPane listeMatch = new GridPane();
                listeMatch.setPadding(new Insets(50,50,50,50));
                listeMatch.setAlignment(Pos.CENTER);
                listeMatch.setLayoutX(xLB);
                listeMatch.setLayoutY(30);
                List<model.match.Match> listeMatchs = tourLB.getMatchs();
                Label labelTour = new Label(tourLB.getNom());
                labelTour.setStyle("-fx-font-weight: bold");
                listeMatch.addColumn(nbTourLB, labelTour);
                for (int nbMatch = 0; nbMatch < listeMatchs.size(); nbMatch++) {
                    GridPane gridMatch = new GridPane();
                    gridMatch.setHgap(listeMatchs.size());
                    gridMatch.setVgap(2);
                    gridMatch.setPadding(new Insets(10,10,10,10));
                    for(model.equipe.Equipe equipe : listeMatchs.get(nbMatch).getEquipes() ) {
                        if(tourLB == tournoiLB.getTourCourant()) {
                            HBox hBoxMatch = new HBox();
                            hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            hBoxMatch.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                            Label labelEquipe = new Label(equipe.getNom());
                            labelEquipe.setTextFill(Color.WHITE);
                            labelEquipe.setPadding(new Insets(5,5,5,5));
                            TextField textFieldScore = new TextField();
                            hBoxMatch.getChildren().addAll(labelEquipe, textFieldScore);
                            gridMatch.addColumn(nbTourLB,hBoxMatch);
                            this.scoresLB.add(textFieldScore);
                        } else {
                            listeMatch.setLayoutX(xLB + 100);
                            Label labelEquipe = new Label(equipe.getNom());
                            labelEquipe.setTextFill(Color.WHITE);
                            labelEquipe.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
                            labelEquipe.setPadding(new Insets(5,5,5,5));
                            Label labelScore = new Label(""+ tourLB.getScore(equipe).getScore());
                            labelScore.setPadding(new Insets(5,5,5,5));
                            HBox hBoxMatch = new HBox();
                            hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
                            hBoxMatch.getChildren().addAll(labelEquipe, labelScore);
                            gridMatch.addColumn(nbTourLB,hBoxMatch);
                        }
                    }

                    listeMatch.addColumn(nbTourLB, gridMatch);
                }

                xLB -= 100;
                page.getChildren().add(listeMatch);
                nbTourLB++;
            }

        }

        Button btnConfirmer = new Button();
        btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
        btnConfirmer.setLayoutY(25);
        btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnConfirmer.setText("Confirmer");
        btnConfirmer.setFont(new Font("Cambria", 10));
        btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                int[] scoresEquipesWB;
                int[] scoresEquipesLB;

                scoresEquipesWB = new int[scoresWB.size()];
                scoresEquipesLB = new int[scoresLB.size()];

                for(int i = 0; i < scoresWB.size() ; i++) {

                    scoresEquipesWB[i] = "".equals(scoresWB.get(i).getText()) ? 0 : (int) Integer.parseInt(scoresWB.get(i).getText());
                }

                for(int i = 0; i < scoresLB.size() ; i++) {

                    scoresEquipesLB[i] = "".equals(scoresLB.get(i).getText()) ? 0 : (int) Integer.parseInt(scoresLB.get(i).getText());
                }

                new GestionTournoiController().affecterScores(stage, tournoi,scoresEquipesWB);
                if(((LoserBracket)tournoi).getLoserBracket().getTourCourant() != null) {
                    new GestionTournoiController().affecterScores(stage, ((LoserBracket)tournoi).getLoserBracket(),scoresEquipesLB);
                }
                new GestionTournoiController().gererTournoi(stage, tournoi);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText(null);
                alert.setContentText("Les scores du tour courant ont bien été enregistrés. Un nouveau tour a été créé. ");

                alert.showAndWait();

            }
        });

        Button btnAnnuler = new Button();
        btnAnnuler.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X - TAILLE_ECRAN_X * 0.05);
        btnAnnuler.setLayoutY(25);
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

        page.getChildren().addAll(titre, btnConfirmer, btnAnnuler);
        root.setContent(page);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }}