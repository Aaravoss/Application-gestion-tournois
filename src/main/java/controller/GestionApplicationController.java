package controller;

import java.io.*;
import model.tournoi.Tournoi;

/**
 * Classe de gestion des fonctionnalités de l'application.
 *
 * Fonctionnalités :
 *  - Initialisation de l'application
 *  - Fermeture de l'application
 */
public class GestionApplicationController {

    /**
     * Initialise l'état de l'application lors de la dernière utilisation de l'utilisateur
     *
     * Fonctionnalités :
     *  - Chargement des données
     */
    public void initialisationApplication() {

        chargerDonnees();
    }

    /**
     * Charge les tournois enregistrés dans le fichier de sauvegarde
     */
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

    /**
     * Actions effectuées lors de la fermeture de l'application :
     *  - Sauvegarder l'état de l'application
     */
    public void fermerApplication() {

        sauvegarderEtatApplication();
    }

    /**
     * Sauvegarde les données lors de la fermeture de l'application en les sérialisant et en les stockant dans un fichier
     * binaire "sauv"
     */
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
