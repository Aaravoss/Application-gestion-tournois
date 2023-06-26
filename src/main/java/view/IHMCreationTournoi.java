package view;

import controller.CreationTournoiController;
import controller.GestionApplicationController;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static utils.BusinessConstants.*;

/**
 * IHM liée à la création d'un tournoi
 *
 * @author Carolane Pulval-Dady
 * @author Touria SAYAGH
 *
 */
public class IHMCreationTournoi extends Application {

    private static final Font TEXTE = new Font("Cambria", 30);

    /**
     * Modélisation de l'IHM
     *
     * @param stage état de l'application
     *
     * @author Carolane Pulval-Dady
     */
    @Override
    public void start(Stage stage)  {
        stage.setTitle("Création d'un tournoi");
        Group root = new Group();
        String imagePath = "/creationImg.jpeg";
        Image image = new Image(this.getClass().getResourceAsStream(imagePath));
        ImageView backgroundImageView = new ImageView(image);
        backgroundImageView.setFitWidth(TAILLE_ECRAN_X);
        backgroundImageView.setFitHeight(TAILLE_ECRAN_Y);
        root.getChildren().add(backgroundImageView);
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);



        Label titre = new Label("Créer un tournoi");
        titre.setFont(new Font("Cambria", 80));
        titre.layoutXProperty().bind(scene.widthProperty().subtract(titre.widthProperty()).divide(2));
        titre.setLayoutY(100);
        titre.setTextFill(Color.WHITE);


        VBox vbox = new VBox();
        //vbox.setBackground(new Background(new BackgroundFill(Color.RED, CornerRadii.EMPTY, Insets.EMPTY)));
        vbox.setPrefWidth(TAILLE_ECRAN_X - TAILLE_ECRAN_X*0.2);
        vbox.setPadding(new Insets(35,0,35,0));
        vbox.setSpacing(80);
        vbox.setLayoutX(TAILLE_ECRAN_X * 0.1);
        vbox.setLayoutY(TAILLE_ECRAN_Y/3);
        HBox hboxNomT = new HBox();
        hboxNomT.setSpacing(110);
        hboxNomT.setAlignment(Pos.CENTER);

        Label labelNomT = new Label("Nom du tournoi");
        labelNomT.setFont(TEXTE);
        labelNomT.setTextFill(Color.WHITE);
        TextField textFieldNomT = new TextField();
        textFieldNomT.setPrefWidth(320);
        textFieldNomT.setPrefHeight(15);
        textFieldNomT.setFont(new Font("Cambria", 20));
        textFieldNomT.setPadding(new Insets(5,5,5,5));
        hboxNomT.getChildren().addAll(labelNomT, textFieldNomT);

        HBox hboxTypeT = new HBox();
        hboxTypeT.setSpacing(110);
        hboxTypeT.setAlignment(Pos.CENTER);
        Label labelTypeT = new Label("Type de tournoi");
        labelTypeT.setTextFill(Color.WHITE);
        labelTypeT.setFont(TEXTE);
        ComboBox<String> comboBoxTypeT = new ComboBox<String>();
        comboBoxTypeT.setPrefWidth(320);
        comboBoxTypeT.setPrefHeight(30);
        comboBoxTypeT.setPadding(new Insets(5,5,5,5));
        comboBoxTypeT.getItems().addAll(TYPE_CLASSIQUE, TYPE_LOSER_BRACKET);
        hboxTypeT.getChildren().addAll(labelTypeT,comboBoxTypeT);
        vbox.getChildren().addAll(hboxNomT, hboxTypeT);

        HBox hboxParticipants = new HBox();
        hboxParticipants.setSpacing(50);
        hboxParticipants.setAlignment(Pos.CENTER);
        Label labelNbParticipants = new Label("Nombre Participants");
        labelNbParticipants.setTextFill(Color.WHITE);
        labelNbParticipants.setFont(TEXTE);
        ComboBox<Integer> comboBoxNbParticipants= new ComboBox<Integer>();
        comboBoxNbParticipants.setPrefWidth(320);
        comboBoxNbParticipants.setPrefHeight(30);


        comboBoxTypeT.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {

                comboBoxNbParticipants.setPadding(new Insets(5,5,5,5));
                ObservableList tampon = comboBoxNbParticipants.getItems();

                if(TYPE_LOSER_BRACKET.equals(comboBoxTypeT.getValue())) {
                    tampon.setAll(4,8,16,32);

                } else if(TYPE_CLASSIQUE.equals(comboBoxTypeT.getValue())){
                    tampon.setAll(4,8,16,32);
                }
                comboBoxNbParticipants.setItems(tampon);
            }
        });
        hboxParticipants.getChildren().addAll(labelNbParticipants, comboBoxNbParticipants);
        vbox.getChildren().addAll(hboxParticipants);

        Button btnConfirmer = new Button();
        btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
        btnConfirmer.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnConfirmer.setText("Confirmer");
        btnConfirmer.setFont(new Font("Cambria", 10));

        btnConfirmer.setOnMouseEntered(e -> {
            btnConfirmer.setStyle("-fx-background-color: #BA27C5FF; -fx-text-fill: #ffffff;");
        });

        btnConfirmer.setOnMouseExited(e -> {
            btnConfirmer.setStyle("-fx-background-color: #B4B5B7FF; -fx-text-fill: #0A2544FF;");
        });
        btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                if( !textFieldNomT.equals(null) && !comboBoxNbParticipants.getValue().equals(0)
                	&& !"".equals(textFieldNomT.getText()) && !"".equals(comboBoxNbParticipants.getValue())) {
                	
                	String typeTournoi;
                	
                	typeTournoi = "";
                	if(comboBoxTypeT.getValue().equals("Classique")) {
                		typeTournoi = "Classique";
                        
                	} else if (comboBoxTypeT.getValue().equals("LoserBracket")) {
                		typeTournoi = "LoserBracket";
                	}
                	
                	// Création du tournoi
                	try {
						new CreationTournoiController().creerTournoi(typeTournoi, textFieldNomT.getText(), comboBoxNbParticipants.getValue(), stage);
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

        btnAnnuler.setOnMouseEntered(e -> {
            btnAnnuler.setStyle("-fx-background-color: #BA27C5FF; -fx-text-fill: #ffffff;");
        });

        btnAnnuler.setOnMouseExited(e -> {
            btnAnnuler.setStyle("-fx-background-color: #B4B5B7FF; -fx-text-fill: #0A2544FF;");
        });
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

        root.getChildren().addAll(titre, vbox,btnConfirmer, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Lance l'IHM
     *
     * @param args non utilisé
     *
     * @author Carolane Pulval-Dady
     */
    public static void main(String[] args) {
        launch();
    }
}
