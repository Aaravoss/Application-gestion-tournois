package com.example.ihmmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;

public class IHMMenu extends Application {

    private static double TAILLE_ECRAN_X = 1440;
    private static double TAILLE_ECRAN_Y = 924;
    private static double TAILLE_BTN_X = 400;
    private static double TAILLE_BTN_Y = 200;
    @Override
    public void start(Stage stage) {
        stage.setTitle("Menu");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);

        Label titre = new Label("Bienvenue sur l'application \n de gestion de tournoi");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(TAILLE_ECRAN_X /5);
        titre.setLayoutY(100);


        Button btnGestion = new Button();
        btnGestion.setLayoutX(TAILLE_ECRAN_X /6);
        btnGestion.setLayoutY(TAILLE_ECRAN_Y /2);
        btnGestion.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnGestion.setText("Gérer les tournois");
        btnGestion.setFont(new Font("Cambria", 30));
        btnGestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IHMGestion page = new IHMGestion();
                page.start(stage);
            }
        });


        Button btnCreation = new Button();
        btnCreation.setLayoutX(TAILLE_ECRAN_X - TAILLE_ECRAN_X /6 - TAILLE_BTN_X);
        btnCreation.setLayoutY(TAILLE_ECRAN_Y /2);
        btnCreation.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnCreation.setText("Créer un tournoi");
        btnCreation.setFont(new Font("Cambria", 30));
        btnCreation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IHMCreationTournoi page = new IHMCreationTournoi();
                page.start(stage);
            }
        });

        root.getChildren().addAll(titre,btnGestion, btnCreation);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}