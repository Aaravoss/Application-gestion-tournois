package main.view.ihmmenu;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class IHMCreationLoserBracket extends Application {

    private int nbParticipants;
    private static double TAILLE_ECRAN_X = 1440;
    private static double TAILLE_ECRAN_Y = 924;
    private static double TAILLE_BTN_X = 100;
    private static double TAILLE_BTN_Y = 30;
    private static Font TEXTE = new Font("Cambria", 30);

    public IHMCreationLoserBracket(int nbParticipants) {
        this.nbParticipants = nbParticipants;
    }

    @Override
    public void start(Stage stage)  {

        stage.setTitle("Création d'un tournoi \n Loser Bracket");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);


        Label titre = new Label("Création d'un tournoi \n Loser Bracket");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(TAILLE_ECRAN_X /5);
        titre.setLayoutY(100);

        GridPane listeMatch = new GridPane();
        listeMatch.setHgap(3);
        listeMatch.setVgap(10);
        listeMatch.setPadding(new Insets(50,50,50,50));
        listeMatch.setAlignment(Pos.CENTER);
        listeMatch.setLayoutY(300);
        int nbMatch = this.nbParticipants / 2;
        for (int i = 0; i < nbMatch ; i++ ) {
            GridPane gridMatch = new GridPane();
            gridMatch.setHgap(3);
            gridMatch.setVgap(2);
            gridMatch.setPadding(new Insets(10,10,10,10));
            Label match = new Label("Match " + (i+1));
            TextField textParticipant1 = new TextField();
            Label vs = new Label("VS");
            TextField textParticipant2 = new TextField();
            gridMatch.add(match, 1,0);
            gridMatch.add(textParticipant1, 0,1);
            gridMatch.add(vs, 1,1);
            gridMatch.add(textParticipant2, 2,1);
            double ligne = i/3;
            double colonne = i%gridMatch.getHgap();
            listeMatch.add(gridMatch, (int) colonne , (int) ligne );
        }




        Button btnConfirmer = new Button();
        btnConfirmer.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X * 2 - TAILLE_ECRAN_X * 0.10);
        btnConfirmer.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnConfirmer.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnConfirmer.setText("Confirmer");
        btnConfirmer.setFont(new Font("Cambria", 10));
        btnConfirmer.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                IHMGestion page = new IHMGestion();
                page.start(stage);
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

        root.getChildren().addAll(titre,listeMatch, btnConfirmer, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}