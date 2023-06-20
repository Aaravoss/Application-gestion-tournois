package controller;

import model.tournoi.Tournoi;

import java.io.*;
import java.util.List;

public class GestionApplicationController {

    /**
     * Charge les tournois enregistrés dans le fichier de sauvegarde
     * ou créé ce fichier s'il est introuvable
     */
    public void initialisationApplication() {

        //extraireEtat()
    }

    public void fermerApplication() {

        sauvegarderEtatApplication();
    }

    private void sauvegarderEtatApplication() {

        try {
            ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream("sauv"));
            for (Tournoi tournoi : app.GestionTournois.getTournois()) {
                writer.writeObject(tournoi);
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
