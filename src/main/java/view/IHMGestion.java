package view;

import controller.GestionApplicationController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import app.GestionTournois;
import javafx.stage.WindowEvent;
import model.tournoi.Tournoi;
import model.tournoi.type.Classique;
import model.tournoi.type.LoserBracket;

import java.util.ArrayList;

import static utils.BusinessConstants.*;

/**
 *
 * @author Carolane Pulval-Dady
 */

public class IHMGestion extends Application {

    private ArrayList<Tournoi> tournois;

    //stub
    public IHMGestion() {

        this.tournois = GestionTournois.getTournois();
    }
	
	/*
    public IHMGestion(ArrayList<Tournoi> tournois) {
    	this.tournois = tournois;
    }*/

    @Override
    public void start(Stage stage) {
        stage.setTitle("Gestion des tournois");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);

        Label titre = new Label("Listes des tournois existants");
        titre.setFont(new Font("Cambria", TAILLE_TITRE));
        titre.setLayoutX(TAILLE_ECRAN_X / 5);
        titre.setLayoutY(50);

        GridPane listeTournoiLB = new GridPane();
        listeTournoiLB.setLayoutY(150);
        listeTournoiLB.setLayoutX(10);
        Label labelLB = new Label("Tournois LoserBracket");
        labelLB.setStyle("-fx-font-weight: bold");
        listeTournoiLB.addRow(0, labelLB);
        for (Tournoi tournoi : this.tournois) {
            if (tournoi instanceof LoserBracket) {
                Button btnTournoi = new Button();
                btnTournoi.setPrefSize(200, 100);
                btnTournoi.setText(tournoi.getNom());
                btnTournoi.setFont(new Font("Cambria", 10));
                listeTournoiLB.addRow(1, btnTournoi);
                btnTournoi.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (tournoi instanceof model.tournoi.type.LoserBracket) {
                            IHMAffichageTournoi page = new IHMAffichageTournoi(tournoi);
                            page.start(stage);
                        }
                    }
                });
            }
        }

        GridPane listeTournoiC = new GridPane();
        listeTournoiC.setLayoutY(400);
        listeTournoiC.setLayoutX(20);
        Label labelC = new Label("Tournois classiques");
        labelC.setStyle("-fx-font-weight: bold");
        listeTournoiC.addRow(0, labelC);
        for (Tournoi tournoiC : this.tournois) {
            if (tournoiC instanceof Classique) {
                Button btnTournoi = new Button();
                btnTournoi.setPrefSize(200, 100);
                btnTournoi.setText(tournoiC.getNom());
                btnTournoi.setFont(new Font("Cambria", 10));
                listeTournoiC.addRow(1, btnTournoi);
                btnTournoi.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (tournoiC instanceof Classique) {
                            IHMAffichageTournoi page = new IHMAffichageTournoi(tournoiC);
                            page.start(stage);
                        }
                    }
                });
            }
        }

        Button btnAnnuler = new Button();
        btnAnnuler.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X - TAILLE_ECRAN_X * 0.05);
        btnAnnuler.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnAnnuler.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnAnnuler.setText("Annuler");
        btnAnnuler.setFont(new Font("Cambria", 10));
        btnAnnuler.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.IHMMenu page = new view.IHMMenu();
                page.start(stage);
            }
        });

        // quand on quitte l'application, la fermeture est lancée
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            new GestionApplicationController().fermerApplication();
        });

        root.getChildren().addAll(titre, listeTournoiLB, listeTournoiC, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main (String[]args){
        launch();
    }
}
