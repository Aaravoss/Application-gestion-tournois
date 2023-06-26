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
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import model.equipe.Equipe;
import model.tour.Tour;
import javafx.stage.WindowEvent;
import model.tournoi.Tournoi;
import model.tournoi.type.LoserBracket;

import static utils.BusinessConstants.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carolane Pulval-Dady
 */


public class IHMAffichageTournoi extends Application {

    private Tournoi tournoi;
    private ArrayList<TextField> scoresWB;
    private ArrayList<TextField> scoresLB;

    public IHMAffichageTournoi(model.tournoi.Tournoi tournoi) {

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
        Image image = null;
        try {
            image = new Image(new FileInputStream("src/main/java/view/fond2.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView mv = new ImageView(image);
        mv.setFitWidth(2500);
        mv.setFitHeight(2500);
        mv.setPreserveRatio(true);
        page.getChildren().add(mv);
        Label titre = new Label("Tournoi  " + this.tournoi.getNom());
        titre.setFont(Font.font("Verdana", FontWeight.EXTRA_LIGHT, TAILLE_TITRE));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        titre.setEffect(ds);
        titre.setLayoutX(20);
        titre.setLayoutY(10);

        int x = 10;
        int nbTour = 0;
        for(Tour tour : tournoi.getTours()) {
            GridPane listeMatch = new GridPane();
            listeMatch.setPadding(new Insets(50,50,50,50));
            listeMatch.setAlignment(Pos.CENTER);
            listeMatch.setLayoutX(x);
            listeMatch.setLayoutY(50);
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
                        if(tournoi.isFerme()) {
                            listeMatch.setLayoutX(x + 50);
                            Label labelEquipe = new Label(equipe.getNom());
                            labelEquipe.setTextFill(Color.WHITE);
                            labelEquipe.setMinWidth(20);
                            labelEquipe.setBackground(new Background(new BackgroundFill(Color.GOLDENROD, new CornerRadii(5), Insets.EMPTY)));
                            labelEquipe.setPadding(new Insets(5,5,5,5));
                            Label labelScore = new Label("Vainqueur");
                            labelScore.setPadding(new Insets(5,5,5,5));
                            HBox hBoxMatch = new HBox();
                            hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                            hBoxMatch.getChildren().addAll(labelEquipe, labelScore);
                            gridMatch.addColumn(nbTour,hBoxMatch);
                        } else {
                            HBox hBoxMatch = new HBox();
                            hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                            hBoxMatch.setBackground(new Background(new BackgroundFill(Color.web("#59A1D0"), new CornerRadii(5), Insets.EMPTY)));
                            Label labelEquipe = new Label(equipe.getNom());
                            labelEquipe.setMinWidth(20);
                            labelEquipe.setPadding(new Insets(5,5,5,5));
                            labelEquipe.setTextFill(Color.WHITE);
                            TextField textFieldScore = new TextField();
                            textFieldScore.setMaxWidth(50);
                            hBoxMatch.getChildren().addAll(labelEquipe, textFieldScore);
                            gridMatch.addColumn(nbTour,hBoxMatch);
                            this.scoresWB.add(textFieldScore);
                        }
                    } else {
                        Equipe equipeP = listeMatchs.get(nbMatch).getPerdant();
                        Label labelEquipe = new Label(equipe.getNom());
                        labelEquipe.setMinWidth(20);
                        labelEquipe.setTextFill(Color.WHITE);
                        labelEquipe.setPadding(new Insets(5,5,5,5));
                        Label labelScore = new Label(""+ tour.getScore(equipe).getScore());
                        labelScore.setPadding(new Insets(5,5,5,5));
                        HBox hBoxMatch = new HBox();
                        hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                        if(equipeP == equipe) {
                            labelEquipe.setBackground(new Background(new BackgroundFill(Color.web("#989998"), new CornerRadii(5), Insets.EMPTY)));
                        } else {
                            labelEquipe.setBackground(new Background(new BackgroundFill(Color.web("#43BF05"), new CornerRadii(5), Insets.EMPTY)));
                        }
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
                listeMatch.setLayoutY(50);
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
                        if(tourLB == tournoiLB.getTourCourant() && !tournoiLB.isFerme()) {
                            if(tournoi.isFerme()) {
                                listeMatch.setLayoutX(x + 100);
                                Label labelEquipe = new Label(equipe.getNom());
                                labelEquipe.setTextFill(Color.WHITE);
                                labelEquipe.setMinWidth(20);
                                labelEquipe.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(5), Insets.EMPTY)));
                                labelEquipe.setPadding(new Insets(5,5,5,5));
                                Label labelScore = new Label("Vainqueur");
                                labelScore.setPadding(new Insets(5,5,5,5));
                                HBox hBoxMatch = new HBox();
                                hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                                hBoxMatch.getChildren().addAll(labelEquipe, labelScore);
                                gridMatch.addColumn(nbTour,hBoxMatch);
                            } else {
                                HBox hBoxMatch = new HBox();
                                hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                                hBoxMatch.setBackground(new Background(new BackgroundFill(Color.web("#59A1D0"), new CornerRadii(5), Insets.EMPTY)));
                                Label labelEquipe = new Label(equipe.getNom());
                                labelEquipe.setTextFill(Color.WHITE);
                                labelEquipe.setPadding(new Insets(5, 5, 5, 5));
                                TextField textFieldScore = new TextField();
                                textFieldScore.setMaxWidth(50);
                                hBoxMatch.getChildren().addAll(labelEquipe, textFieldScore);
                                gridMatch.addColumn(nbTourLB, hBoxMatch);
                                this.scoresLB.add(textFieldScore);
                            }
                        } else {
                            Equipe equipeP = listeMatchs.get(nbMatch).getPerdant();
                            listeMatch.setLayoutX(xLB + 100);
                            Label labelEquipe = new Label(equipe.getNom());
                            labelEquipe.setTextFill(Color.WHITE);
                            labelEquipe.setMinWidth(20);
                            labelEquipe.setPadding(new Insets(5,5,5,5));
                            Label labelScore = new Label(""+ tourLB.getScore(equipe).getScore());
                            labelScore.setPadding(new Insets(5,5,5,5));
                            HBox hBoxMatch = new HBox();
                            hBoxMatch.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(5), new BorderWidths(2))));
                            if(equipeP == equipe) {
                                labelEquipe.setBackground(new Background(new BackgroundFill(Color.web("#989998"), new CornerRadii(5), Insets.EMPTY)));
                            } else {
                                labelEquipe.setBackground(new Background(new BackgroundFill(Color.web("#43BF05"), new CornerRadii(5), Insets.EMPTY)));
                            }
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

        if(!tournoi.isFerme()) {
            Button btnConfirmer = new Button();
            btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
            btnConfirmer.setLayoutY(25);
            btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
            btnConfirmer.setText("Confirmer");
            btnConfirmer.setFont(new Font("Cambria", 10));
            btnConfirmer.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
            btnConfirmer.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
            btnConfirmer.setOnMousePressed(event ->{
                btnConfirmer.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            });
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
                    if(tournoi instanceof LoserBracket && !((LoserBracket) tournoi).getLoserBracket().isFerme() && ((LoserBracket)tournoi).getLoserBracket().getTourCourant() != null) {
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
            page.getChildren().add(btnConfirmer);
        }


        Button btnAnnuler = new Button();
        btnAnnuler.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X - TAILLE_ECRAN_X * 0.05);
        btnAnnuler.setLayoutY(25);
        btnAnnuler.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnAnnuler.setText("Annuler");
        btnAnnuler.setFont(new Font("Cambria", 10));
        btnAnnuler.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        btnAnnuler.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        btnAnnuler.setOnMousePressed(event ->{
            btnAnnuler.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        });
        btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                view.IHMGestion page = new  view.IHMGestion();
                page.start(stage);
            }
        });

        // quand on quitte l'application, la fermeture est lancée
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            new GestionApplicationController().fermerApplication();
        });

        page.getChildren().addAll(titre, btnAnnuler);
        root.setContent(page);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }}