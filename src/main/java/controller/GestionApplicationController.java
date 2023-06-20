package controller;

import model.tour.Tour;
import model.tournoi.Tournoi;

import java.io.*;
import java.util.List;

public class GestionApplicationController {

    /**
     * Charge les tournois enregistrés dans le fichier de sauvegarde
     * ou créé ce fichier s'il est introuvable
     */
    public void initialisationApplication() {

        chargerDonnees();
    }

    private void chargerDonnees() {

        try {
            ObjectInputStream reader = new ObjectInputStream(new FileInputStream("sauv"));
            Object lu;

            lu = reader.readObject();
            while (lu != null) {
                if (lu instanceof Tournoi) {
                    app.GestionTournois.getTournois().add((Tournoi) lu);
                }
                lu = reader.readObject();
            }
            reader.close();
        } catch (IOException e) {
            //loading fonctionnel mais throw une erreur java.io.EOFException pour rien TODO analyse à continier
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
