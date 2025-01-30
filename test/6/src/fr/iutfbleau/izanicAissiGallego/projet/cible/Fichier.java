package fr.iutfbleau.izanicAissiGallego.projet.cible;

import java.io.File;

/**
 * La classe Fichier étend {@link File} pour fournir des méthodes supplémentaires
 * spécifiques à la gestion de fichiers dans le projet.
 */
public class Fichier extends File {

    /**
     * Constructeur de la classe Fichier.
     * 
     * @param path Le chemin du fichier, sous forme de chaîne de caractères.
     */
    public Fichier(String path) {
        super(path);
    }

    /**
     * Retourne la dernière date de modification du fichier.
     * 
     * @return Un timestamp représentant la date de la dernière modification du fichier.
     */
    public long getlastModified() {
        return this.lastModified();
    }

    /**
     * Vérifie si le fichier existe.
     * 
     * @return {@code true} si le fichier existe, sinon {@code false}.
     */
    public boolean existe() {
        return this.exists();
    }

    /**
     * Vérifie si le fichier est considéré comme à jour.
     * Cette méthode retourne la même valeur que {@link #existe()}.
     * 
     * @return {@code true} si le fichier existe (donc considéré comme à jour), sinon {@code false}.
     */
    public boolean estAJour() {
        return this.exists();
    }
}
