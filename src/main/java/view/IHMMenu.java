package view;

import controller.GestionApplicationController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.WindowEvent;

import static utils.BusinessConstants.TAILLE_BTN_MENU_X;
import static utils.BusinessConstants.TAILLE_BTN_MENU_Y;
import static utils.BusinessConstants.TAILLE_ECRAN_X;
import static utils.BusinessConstants.TAILLE_ECRAN_Y;

public class IHMMenu extends Application {

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
        btnGestion.setPrefSize(TAILLE_BTN_MENU_X, TAILLE_BTN_MENU_Y);
        btnGestion.setText("Gérer les tournois");
        btnGestion.setFont(new Font("Cambria", 30));
        btnGestion.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.IHMGestion page = new view.IHMGestion();
                page.start(stage);
            }
        });


        Button btnCreation = new Button();
        btnCreation.setLayoutX(TAILLE_ECRAN_X - TAILLE_ECRAN_X /6 - TAILLE_BTN_MENU_X);
        btnCreation.setLayoutY(TAILLE_ECRAN_Y /2);
        btnCreation.setPrefSize(TAILLE_BTN_MENU_X, TAILLE_BTN_MENU_Y);
        btnCreation.setText("Créer un tournoi");
        btnCreation.setFont(new Font("Cambria", 30));
        btnCreation.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                view.IHMCreationTournoi page = new view.IHMCreationTournoi();
                page.start(stage);
            }
        });

        // quand on quitte (l'application ? ou la fenêtre ?), la fermeture est lancée
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            new GestionApplicationController().fermerApplication();
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