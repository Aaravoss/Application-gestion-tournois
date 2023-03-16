package com.example.ihmmenu;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;


import java.io.IOException;

public class IHMMenu extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        double tailleEcranX = 1440;
        double tailleEcranY = 924;
        double tailleBtnX = 400;
        double tailleBtnY = 200;
        stage.setTitle("Menu");
        Group root = new Group();
        Scene scene = new Scene(root, tailleEcranX, tailleEcranY);

        Label titre = new Label("Bienvenue sur l'application \n de gestion de tournoi");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(tailleEcranX/5);
        titre.setLayoutY(100);


        Button btnGestion = new Button();
        btnGestion.setLayoutX(tailleEcranX/6);
        btnGestion.setLayoutY(tailleEcranY/2);
        btnGestion.setPrefSize(tailleBtnX,tailleBtnY);
        btnGestion.setText("Gérer les tournois");
        btnGestion.setFont(new Font("Cambria", 30));


        Button btnCreation = new Button();
        btnCreation.setLayoutX(tailleEcranX-tailleEcranX/6 - tailleBtnX);
        btnCreation.setLayoutY(tailleEcranY/2);
        btnCreation.setPrefSize(tailleBtnX,tailleBtnY);
        btnCreation.setText("Créer un tournoi");
        btnCreation.setFont(new Font("Cambria", 30));

        root.getChildren().addAll(titre,btnGestion, btnCreation);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}