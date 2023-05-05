package view;

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
import app.GestionTournois;
import model.tournoi.Tournoi;

import java.util.ArrayList;
import java.util.List;

import static utils.BusinessConstants.*;
import static utils.BusinessConstants.TAILLE_BTN_Y;

public class IHMGestion extends Application {
	
	private ArrayList<Tournoi> tournois;
    
	//stub
	public IHMGestion() {
    	this.tournois = GestionTournois.getTournois();
	}
	
	/*
    public IHMGestion(ArrayList<Tournoi> tournois) {
    	this.tournois = tournois;
    }*/
    
    @Override
    public void start(Stage stage) {
        stage.setTitle("Gestion des tournois");
        Group root = new Group();
        Scene scene = new Scene(root, TAILLE_ECRAN_X, TAILLE_ECRAN_Y);

        Label titre = new Label("Listes des tournois existants");
        titre.setFont(new Font("Cambria", 80));
        titre.setLayoutX(TAILLE_ECRAN_X /5);
        titre.setLayoutY(100);


        GridPane listeTournoi = new GridPane();
        listeTournoi.setHgap(3);
        listeTournoi.setVgap(10);
        listeTournoi.setPadding(new Insets(50,50,50,50));
        listeTournoi.setAlignment(Pos.CENTER);
        listeTournoi.setLayoutX(50);
        listeTournoi.setLayoutY(250);
        int i;
        i = 0;
        for (Tournoi tournoi : this.tournois) {
            Button btnTournoi = new Button();
            btnTournoi.setPrefSize(200, 100);
            btnTournoi.setText(tournoi.getNom());
            btnTournoi.setFont(new Font("Cambria", 10));
            btnTournoi.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    if(tournoi instanceof model.tournoi.type.LoserBracket) {
                        view.IHMAffichageLoserBracket page = new view.IHMAffichageLoserBracket(tournoi);
                        page.start(stage);
                    } else if(tournoi instanceof model.tournoi.type.Poule) {
                        view.IHMAffichagePoule page = new view.IHMAffichagePoule();
                        page.start(stage);
                    }

                }
            });
            double ligne = i/listeTournoi.getHgap();
            double colonne = i%listeTournoi.getHgap();
            listeTournoi.add(btnTournoi, (int) colonne , (int) ligne );
            i++;

        }


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

        root.getChildren().addAll(titre,listeTournoi, btnAnnuler);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}