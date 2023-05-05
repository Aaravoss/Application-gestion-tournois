package view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import controller.CreationTournoiController;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import static utils.BusinessConstants.*;

public class IHMCreationTournoi extends Application {

    private static final Font TEXTE = new Font("Cambria", 30);

    @Override
    public void start(Stage stage)  {

        stage.setTitle("Création d'un tournoi");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);


        Label titre = new Label("Création d'un tournoi");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(TAILLE_ECRAN_X /5);
        titre.setLayoutY(100);

        VBox vbox = new VBox();
        vbox.setPrefWidth(TAILLE_ECRAN_X - TAILLE_ECRAN_X*0.2);
        vbox.setSpacing(100);
        vbox.setLayoutX(TAILLE_ECRAN_X * 0.1);
        vbox.setLayoutY(TAILLE_ECRAN_Y/3);

        HBox hboxNomT = new HBox();
        hboxNomT.setSpacing(250);
        Label labelNomT = new Label("Nom du tournoi");
        labelNomT.setFont(TEXTE);
        TextField textFieldNomT = new TextField();
        textFieldNomT.setPrefWidth(350);
        textFieldNomT.setPrefHeight(15);
        textFieldNomT.setFont(new Font("Cambria", 20));
        textFieldNomT.setPadding(new Insets(5,5,5,5));
        hboxNomT.getChildren().addAll(labelNomT, textFieldNomT);

        HBox hboxTypeT = new HBox();
        hboxTypeT.setSpacing(250);
        Label labelTypeT = new Label("Nom du tournoi");
        labelTypeT.setFont(TEXTE);
        ComboBox<String> comboBoxTypeT = new ComboBox<String>();
        comboBoxTypeT.setPrefWidth(350);
        comboBoxTypeT.setPrefHeight(30);
        comboBoxTypeT.setPadding(new Insets(5,5,5,5));
        comboBoxTypeT.getItems().addAll(TYPE_POULE, TYPE_LOSER_BRACKET);
        hboxTypeT.getChildren().addAll(labelTypeT,comboBoxTypeT);

        HBox hboxParticipants = new HBox();
        hboxParticipants.setSpacing(150);
        Label labelNbParticipants = new Label("Nombre de participants");
        labelNbParticipants.setFont(TEXTE);
        TextField textFieldNbParticipants = new TextField();
        textFieldNbParticipants.setPrefWidth(350);
        textFieldNbParticipants.setPrefHeight(30);
        textFieldNbParticipants.setFont(new Font("Cambria", 20));
        textFieldNbParticipants.setPadding(new Insets(5,5,5,5));
        hboxParticipants.getChildren().addAll(labelNbParticipants, textFieldNbParticipants);

        vbox.getChildren().addAll(hboxNomT, hboxTypeT, hboxParticipants);

        Button btnConfirmer = new Button();
        btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
        btnConfirmer.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnConfirmer.setText("Confirmer");
        btnConfirmer.setFont(new Font("Cambria", 10));
        btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if( !textFieldNomT.equals(null) && !textFieldNbParticipants.equals(null)
                	&& !"".equals(textFieldNomT.getText()) && !"".equals(textFieldNbParticipants.getText())) {
                	
                	String typeTournoi;
                	
                	typeTournoi = "";
                	if(comboBoxTypeT.getValue().equals("Poule")) {
                		typeTournoi = "Poule";
                        
                	} else if (comboBoxTypeT.getValue().equals("LoserBracket")) {
                		typeTournoi = "LoserBracket";
                	}
                	
                	// Création du tournoi
                	try {
						new CreationTournoiController().creerTournoi(typeTournoi, textFieldNomT.getText(), Integer.parseInt(textFieldNbParticipants.getText()), stage);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                }
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
                view.IHMMenu page = new view.IHMMenu();
                page.start(stage);
            }
        });

        root.getChildren().addAll(titre, vbox,btnConfirmer, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}