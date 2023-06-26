package view;

import app.GestionTournois;
import controller.GestionApplicationController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.tournoi.Tournoi;
import model.tournoi.type.Classique;
import model.tournoi.type.LoserBracket;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static utils.BusinessConstants.*;

/**
 * IHM liée à la gestion des tournois
 *
 * @author Carolane Pulval-Dady
 */
public class IHMGestion extends Application {

    private ArrayList<Tournoi> tournois;

    /**
     * Constructeur par défaut lié à l'IHMGestion
     *
     * @author Carolane Pulval-Dady
     */
    public IHMGestion() {

        this.tournois = GestionTournois.getTournois();
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
        stage.setTitle("Gestion des tournois");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);
        Image image = null;
        try {
            image = new Image(new FileInputStream("src/main/java/view/fond2.jpg"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ImageView mv = new ImageView(image);
        mv.setFitWidth(1240);
        mv.setFitHeight(724);
        root.getChildren().add(mv);
        Label titre = new Label("Listes des tournois existants");
        titre.setFont(new Font("Cambria", TAILLE_TITRE));
        DropShadow ds = new DropShadow();
        ds.setOffsetY(3);
        ds.setColor(Color.color(0.4, 0.4, 0.4));
        titre.setEffect(ds);
        titre.setLayoutX(TAILLE_ECRAN_X / 5);
        titre.setLayoutY(50);

        GridPane listeTournoiLB = new GridPane();
        listeTournoiLB.setLayoutY(150);
        listeTournoiLB.setLayoutX(10);
        listeTournoiLB.setHgap(10);
        listeTournoiLB.setVgap(10);
        Label labelLB = new Label("Tournois LoserBracket");
        labelLB.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        listeTournoiLB.addRow(0, labelLB);
        int nbTournoi = 1;
        for (Tournoi tournoi : this.tournois) {
            if (tournoi instanceof LoserBracket) {
                Button btnTournoi = new Button();
                btnTournoi.setPrefSize(180, 100);
                btnTournoi.setText(tournoi.getNom());
                btnTournoi.setFont(new Font("Cambria", 15));
                btnTournoi.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, new CornerRadii(20), Insets.EMPTY)));
                btnTournoi.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2))));
                btnTournoi.setOnMousePressed(event ->{
                    btnTournoi.setBackground(new Background(new BackgroundFill(Color.web("#5290B9"), new CornerRadii(20), Insets.EMPTY)));
                });
                if(nbTournoi < 7) {
                    listeTournoiLB.addRow(1, btnTournoi);
                } else {
                    listeTournoiLB.addRow(2, btnTournoi);
                }


                btnTournoi.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (tournoi instanceof model.tournoi.type.LoserBracket) {
                            IHMAffichageTournoi page = new IHMAffichageTournoi(tournoi);
                            page.start(stage);
                        }
                    }
                });
                nbTournoi++;
            }

        }

        GridPane listeTournoiC = new GridPane();
        listeTournoiC.setLayoutY(400);
        listeTournoiC.setLayoutX(20);
        listeTournoiC.setHgap(10);
        listeTournoiC.setVgap(10);
        Label labelC = new Label("Tournois classiques");
        labelC.setFont(Font.font("Verdana", FontWeight.BOLD, 15));
        listeTournoiC.addRow(0, labelC);
        int nbTournoiC = 1;
        for (Tournoi tournoiC : this.tournois) {
            if (tournoiC instanceof Classique) {
                Button btnTournoi = new Button();
                btnTournoi.setPrefSize(180, 100);
                btnTournoi.setText(tournoiC.getNom());
                btnTournoi.setFont(new Font("Cambria", 15));
                btnTournoi.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, new CornerRadii(20), Insets.EMPTY)));
                btnTournoi.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, new CornerRadii(20), new BorderWidths(2))));
                btnTournoi.setOnMousePressed(event ->{
                    btnTournoi.setBackground(new Background(new BackgroundFill(Color.DARKCYAN, new CornerRadii(20), Insets.EMPTY)));
                });
                if(nbTournoiC < 7) {
                    listeTournoiC.addRow(1, btnTournoi);
                } else {
                    listeTournoiC.addRow(2, btnTournoi);
                }
                btnTournoi.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (tournoiC instanceof Classique) {
                            IHMAffichageTournoi page = new IHMAffichageTournoi(tournoiC);
                            page.start(stage);
                        }
                    }
                });
                nbTournoiC++;
            }
        }

        Button btnAnnuler = new Button();
        btnAnnuler.setLayoutX(TAILLE_ECRAN_X - TAILLE_BTN_X - TAILLE_ECRAN_X * 0.05);
        btnAnnuler.setLayoutY(TAILLE_ECRAN_Y - TAILLE_BTN_Y - TAILLE_ECRAN_Y * 0.05);
        btnAnnuler.setPrefSize(TAILLE_BTN_X, TAILLE_BTN_Y);
        btnAnnuler.setText("Annuler");
        btnAnnuler.setFont(new Font("Cambria", 10));
        btnAnnuler.setBackground(new Background(new BackgroundFill(Color.LIGHTCYAN, CornerRadii.EMPTY, Insets.EMPTY)));
        btnAnnuler.setBorder(new Border(new BorderStroke(Color.BLACK, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        btnAnnuler.setOnMousePressed(event ->{
            btnAnnuler.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
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

        root.getChildren().addAll(titre, listeTournoiLB, listeTournoiC, btnAnnuler);
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
    public static void main (String[]args){
        launch();
    }
}
