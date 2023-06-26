package view;

import controller.GestionApplicationController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.geometry.Insets;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import static utils.BusinessConstants.*;

/**
 * IHM liée à l'affichage du menu principal
 *
 * @author Carolane Pulval-Dady, Touria SAYAGH
 */
public class IHMMenu extends Application {

    /**
     * Modélisation de l'IHM
     *
     * @param stage état de l'application
     *
     * @author Carolane Pulval-Dady
     */
    @Override
    public void start(Stage stage) {
        stage.setTitle("Menu");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);
        String imagePath = "src/main/java/view/MenuImg.jpeg";
        Image image = null;
        try {
            image = new Image(new FileInputStream(imagePath));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView backgroundImageView = new ImageView(image);
        backgroundImageView.setFitWidth(TAILLE_ECRAN_X);
        backgroundImageView.setFitHeight(TAILLE_ECRAN_Y);
        root.getChildren().add(backgroundImageView);
        Label titre = new Label("Bienvenue sur l'application \n Gestion de tournoi");
        titre.setTextFill(Color.rgb(10,37,68));
        titre.setFont(new Font("Cambria", TAILLE_TITRE));
        titre.setLayoutX(TAILLE_ECRAN_X /9);
        titre.setLayoutY(10);




        Button btnCreation = new Button();
        btnCreation.setLayoutX(TAILLE_ECRAN_X /6 - 100 );
        btnCreation.setLayoutY(TAILLE_ECRAN_Y /4 + TAILLE_BTN_MENU_Y);
        btnCreation.setPrefSize(TAILLE_BTN_MENU_X, TAILLE_BTN_MENU_Y);
        btnCreation.setText("Créer un tournoi");
        btnCreation.setFont(new Font("Cambria", 30));
        btnCreation.setStyle("-fx-background-color: #0A2544FF; -fx-text-fill: #ffffff;");
        btnCreation.setOnMouseEntered(e -> {
            btnCreation.setStyle("-fx-background-color: #0A2544FF; -fx-text-fill: #ffffff;");
        });

        btnCreation.setOnMouseExited(e -> {
            btnCreation.setStyle("-fx-background-color: transparent; -fx-text-fill: #0A2544FF;");
        });
        btnCreation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.IHMCreationTournoi page = new view.IHMCreationTournoi();
                page.start(stage);
            }
        });


        Button btnGestion = new Button();
        btnGestion.setLayoutX(TAILLE_ECRAN_X /6 -100);
        btnGestion.setLayoutY(TAILLE_ECRAN_Y /4);
        btnGestion.setPrefSize(TAILLE_BTN_MENU_X, TAILLE_BTN_MENU_Y);
        btnGestion.setText("Gérer les tournois");
        btnGestion.setFont(new Font("Cambria", 30));
        btnGestion.setStyle("-fx-background-color: transparent; -fx-text-fill: #0A2544FF;");
        btnGestion.setOnMouseEntered(e -> {
            btnGestion.setStyle("-fx-background-color: #0A2544FF; -fx-text-fill: #ffffff;");
        });

        btnGestion.setOnMouseExited(e -> {
            btnGestion.setStyle("-fx-background-color: transparent; -fx-text-fill: #0A2544FF;");
        });

        btnGestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.IHMGestion page = new view.IHMGestion();
                page.start(stage);
            }
        });

        // quand on quitte l'application, la fermeture est lancée
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            new GestionApplicationController().fermerApplication();
        });

        root.getChildren().addAll(titre,btnGestion, btnCreation);
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