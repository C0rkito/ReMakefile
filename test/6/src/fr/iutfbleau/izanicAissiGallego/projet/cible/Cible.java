package fr.iutfbleau.izanicAissiGallego.projet.cible;


import fr.iutfbleau.izanicAissiGallego.projet.processus.Processus;
import java.util.ArrayList;


/**
 * Représente une cible dans un système de construction.
 * Une cible correspond à un fichier ou une ressource ayant un ensemble de recettes pour être construite ou mise à jour.
 */
public class Cible {

    private String nom;
    private Fichier fichier;
    private ArrayList<String> recettes;
    private boolean estPhony;
    private boolean aJour;

    /**
     * Constructeur de la classe Cible.
     * 
     * @param nom Le nom de la cible, correspondant généralement au nom d'un fichier ou d'une ressource.
     */
    public Cible(String nom) {
        this.recettes = new ArrayList<String>();
        this.nom = nom;
        this.fichier = new Fichier(nom);
        this.aJour = true;
    }

    /**
     * Retourne les recettes associées à la cible.
     * 
     * @return Une liste contenant les commandes à exécuter pour construire ou mettre à jour la cible.
     */
    public ArrayList<String> getRecettes() {
        return this.recettes;
    }

    /**
     * Ajoute une recette à la liste des recettes de la cible.
     * 
     * @param recette La commande à ajouter à la liste des recettes.
     */
    public void addRecettes(String recette) {
        this.recettes.add(recette);
    }

    /**
     * Définit la liste complète des recettes pour la cible.
     * 
     * @param recette Une liste de commandes à associer à la cible.
     */
    public void setRecettes(ArrayList<String> recette) {
        this.recettes = recette;
    }

    /**
     * Retourne le fichier associé à la cible.
     * 
     * @return Un objet {@link Fichier} représentant le fichier ou la ressource associée à la cible.
     */
    public Fichier getFichier() {
        return this.fichier;
    }

    /**
     * Vérifie si la cible est à jour.
     * 
     * @return {@code true} si la cible est à jour, sinon {@code false}.
     */
    public boolean estAJour() {
        return this.aJour;
    }

    /**
     * Marque la cible comme non à jour.
     */
    public void setPasJour() {
        this.aJour = false;
    }

    /**
     * Marque la cible comme à jour.
     */
    public void setAJour() {
        this.aJour = true;
    }

    /**
     * Définit la cible comme étant "phony", c'est-à-dire qu'elle ne représente pas un fichier réel mais une règle virtuelle.
     */
    public void setEstPhony() {
        this.estPhony = true;
    }

    /**
     * Vérifie si la cible est "phony".
     * 
     * @return {@code true} si la cible est phony, sinon {@code false}.
     */
    public boolean estPhony() {
        return this.estPhony;
    }

    /**
     * Retourne la dernière date de modification du fichier associé à la cible.
     * 
     * @return Un timestamp représentant la dernière modification du fichier.
     */
    public long getlastModified() {
        return this.fichier.lastModified();
    }

    /**
     * Retourne le nom de la cible.
     * 
     * @return Le nom de la cible sous forme de chaîne de caractères.
     */
    public String getNom() {
        return this.nom;
    }

    /**
     * Exécute les recettes associées à la cible.
     * Chaque recette est exécutée comme une commande système.
     */
    public void executerRecettes() {
        Processus p = Processus.instance();

        for (String rec : this.recettes) {
            System.out.println(rec);
            p.executerCommande(rec);
        }
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de la cible.
     * 
     * @return Le nom de la cible.
     */
    @Override
    public String toString() {
        return this.nom;
    }
}

