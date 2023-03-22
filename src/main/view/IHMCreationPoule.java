package src.main.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import src.main.controller.CreationTournoiController;
import src.main.model.tournoi.type.Poule;
import javafx.scene.layout.Border;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import static src.main.utils.BusinessConstants.*;

import java.util.ArrayList;

public class IHMCreationPoule extends Application {
	
    private Poule tournoi;
    private ArrayList<TextField> participants;
    private static Font TEXTE = new Font("Cambria", 30);

    public IHMCreationPoule(Poule tournoi) {
        this.tournoi = tournoi;
    }

    private boolean isMatchsRemplis() {
    	
    	for(TextField tf : this.participants) {
    		if(tf == null || "".equals(tf)) {
    			return false;
    		}
    	}
    	return true;
    }
    
    @Override
    public void start(Stage stage)  {

        stage.setTitle("Création d'un tournoi \n Poule");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);


        Label titre = new Label("Création d'un tournoi avec poules");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(TAILLE_ECRAN_X * 0.1);
        titre.setLayoutY(100);

        VBox vBoxPoule = new VBox();
        vBoxPoule.setLayoutY(TAILLE_ECRAN_Y * 0.3);
        vBoxPoule.setLayoutX(TAILLE_ECRAN_X * 0.1);
        vBoxPoule.setSpacing(30);

        HBox hboxNbPoule = new HBox();
        hboxNbPoule.setSpacing(250);

        Label labelNbPoule = new Label("Nombre de poules ");
        labelNbPoule.setFont(TEXTE);

        TextField textFieldNbPoule = new TextField();
        textFieldNbPoule.setPrefWidth(350);
        textFieldNbPoule.setPrefHeight(15);
        textFieldNbPoule.setFont(new Font("Cambria", 20));
        textFieldNbPoule.setPadding(new Insets(5,5,5,5));

        Button btnPoule = new Button();
        btnPoule.setPrefSize(150,50);
        btnPoule.setText("Générer les poules");
        btnPoule.setFont(new Font("Cambria", 15));

        HBox hBoxPoule = new HBox();
        hBoxPoule.setLayoutX(TAILLE_ECRAN_X * 0.1);
        hBoxPoule.setLayoutY(TAILLE_ECRAN_Y/2);

        int nbParticipant;
        nbParticipant = this.tournoi.getEquipes().length;
        btnPoule.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                hBoxPoule.getChildren().clear();
                for(int a = 0; a < Integer.parseInt(textFieldNbPoule.getText()); a++) {
                    VBox VboxPouleP = new VBox();
                    vBoxPoule.setSpacing(20);
                    VboxPouleP.setPrefSize(100, TAILLE_ECRAN_Y/3 );
                    VboxPouleP.setBorder(new Border(new BorderStroke(Color.BLACK,
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));

                    Label labelPoule = new Label(TYPE_POULE + " " + (a+1));
                    VboxPouleP.getChildren().add(labelPoule);

                    int nbParticipantParPoule;
                    nbParticipantParPoule = nbParticipant / Integer.parseInt(textFieldNbPoule.getText());
                    for(int i = 0; i < nbParticipantParPoule; i++) {
                        TextField textFieldParticipant = new TextField();
                        VboxPouleP.getChildren().add(textFieldParticipant);
                    }
                    hBoxPoule.getChildren().add(VboxPouleP);
                }
                root.getChildren().add(hBoxPoule);
            }
        });
        hboxNbPoule.getChildren().addAll(labelNbPoule, textFieldNbPoule, btnPoule);

        HBox hboxNbQualifie = new HBox();
        hboxNbQualifie.setSpacing(100);
        Label labelNbQualifie = new Label("Nombre de qualifiés par poule ");
        labelNbQualifie.setFont(TEXTE);
        TextField textFieldNbQualifie = new TextField();
        textFieldNbQualifie.setPrefWidth(350);
        textFieldNbQualifie.setPrefHeight(15);
        textFieldNbQualifie.setFont(new Font("Cambria", 20));
        textFieldNbQualifie.setPadding(new Insets(5,5,5,5));
        hboxNbQualifie.getChildren().addAll(labelNbQualifie, textFieldNbQualifie);

        vBoxPoule.getChildren().addAll(hboxNbPoule,hboxNbQualifie);

        Button btnConfirmer = new Button();
        btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
        btnConfirmer.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnConfirmer.setText("Confirmer");
        btnConfirmer.setFont(new Font("Cambria", 10));
        btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
            	
            	if(isMatchsRemplis()) {
            		
            		String[] nomsEquipes;
            		
            		nomsEquipes = new String[participants.size()];
            		
            		for(int i = 0 ; i < participants.size() ; i++) {
            			nomsEquipes[i] = participants.get(i).getText();
            		}
            		
            		new CreationTournoiController().attribuerEquipes(stage, tournoi, nomsEquipes, 2, 1);
            	}

                //TODO remplacer ça par le controleur
            	new CreationTournoiController()
            	.attribuerEquipes(stage, tournoi, null, 
            			nbParticipant, nbParticipant);
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
                IHMCreationTournoi page = new IHMCreationTournoi();
                page.start(stage);
            }
        });

        root.getChildren().addAll(titre,vBoxPoule, btnConfirmer, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}

