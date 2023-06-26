package view;

import controller.CreationTournoiController;
import controller.GestionApplicationController;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.tournoi.type.Classique;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static utils.BusinessConstants.*;

/**
 * IHM liée à la création d'un tournoi Classique
 *
 * @author Carolane Pulval-Dady
 * @author Touria SAYAGH
 */
public class IHMCreationTournoiClassique extends Application {
	
    private Classique tournoi;
    private ArrayList<TextField> participants;
    private static Font TEXTE = new Font("Cambria", 30);

    /**
     * Constructeur de l'IHMCreationTournoiClassique
     *
     * @param tournoi à créer
     *
     * @author Carolane Pulval-Dady
     */
    public IHMCreationTournoiClassique(Classique tournoi) {
        this.tournoi = tournoi;
        this.participants = new ArrayList<>();
    }

    /**
     * Vérifie si tous les champs sont remplis
     *
     * @return  true si tous les matchs sont remplis <br>
     *          false sinon
     *
     * @author Carolane Pulval-Dady
     */
    private boolean isMatchsRemplis() {
    	
    	for(TextField tf : this.participants) {
    		if(tf == null || "".equals(tf)) {
    			return false;
    		}
    	}
    	return true;
    }

    /**
     * Modélisation de l'IHM
     *
     * @param stage état de l'application
     *
     * @author Carolane Pulval-Dady
     */
    @Override
    public void start(Stage stage)  {

        stage.setTitle("Création d'un tournoi classique");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);
        String imagePath = "src/main/java/view/creationImg.jpeg";
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

        Label titre = new Label("Création d'un tournoi classique");
        titre.setFont(new Font("Cambria", TAILLE_TITRE));
        titre.layoutXProperty().bind(scene.widthProperty().subtract(titre.widthProperty()).divide(2));
        titre.setLayoutY(50);
        titre.setTextFill(Color.WHITE);

        this.participants = new ArrayList<>();
        GridPane listeMatch = new GridPane();
        listeMatch.setHgap(3);
        listeMatch.setVgap(10);
        listeMatch.setPadding(new Insets(50,50,50,50));
        listeMatch.setAlignment(Pos.CENTER);
        listeMatch.setLayoutY(100);
        int nbMatch = this.tournoi.getEquipes().length / 2;
        for (int i = 0; i < nbMatch ; i++ ) {
            GridPane gridMatch = new GridPane();
            gridMatch.setHgap(3);
            gridMatch.setVgap(2);
            gridMatch.setPadding(new Insets(10,10,10,10));
            Label match = new Label("Match " + (i+1));
            match.setTextFill(Color.WHITE);
            TextField textParticipant1 = new TextField();
            this.participants.add(textParticipant1);
            Label vs = new Label("    VS");
            vs.setTextFill(Color.WHITE);
            TextField textParticipant2 = new TextField();
            this.participants.add(textParticipant2);
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
        btnConfirmer.setOnMouseEntered(e -> {
            btnConfirmer.setStyle("-fx-background-color: #BA27C5FF; -fx-text-fill: #ffffff;");
        });

        btnConfirmer.setOnMouseExited(e -> {
            btnConfirmer.setStyle("-fx-background-color: #B4B5B7FF; -fx-text-fill: #0A2544FF;");
        });
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
                view.IHMCreationTournoi page = new view.IHMCreationTournoi();
                page.start(stage);
            }
        });

        // quand on quitte (l'application ? ou la fenêtre ?), la fermeture est lancée
        stage.addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, e -> {
            new GestionApplicationController().fermerApplication();
        });

        root.getChildren().addAll(titre,listeMatch, btnConfirmer, btnAnnuler);
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

