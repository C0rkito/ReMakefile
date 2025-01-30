package fr.iutfbleau.izanicAissiGallego.projet.graphe;

import fr.iutfbleau.izanicAissiGallego.projet.cible.Cible;
import java.util.*;

/**
 * Représente un noeud dans une structure d'arbre. Chaque noeud contient une valeur de type {@link Cible}, une liste de fils et un mode de débogage.
 */
public class Noeud {

    private Cible valeur;
    private ArrayList<Noeud> fils;
    private boolean debugMode;

    /**
     * Constructeur de la classe Noeud.
     * 
     * @param valeur La valeur associée au noeud, de type {@link Cible}.
     * @param debugMode Un indicateur pour activer le mode débogage.
     */
    public Noeud(Cible valeur, boolean debugMode) {
        this.valeur = valeur;
        this.fils = new ArrayList<Noeud>();
        this.debugMode = debugMode;
    }

    /**
     * Ajoute un fils au noeud actuel si ce n'est pas déjà un descendant.
     * 
     * @param n Le noeud à ajouter en tant que fils.
     * @return Le noeud ajouté.
     */
    public Noeud ajouter(Noeud n) {
        if (!n.getAllFils().contains(this) && !this.getFils().contains(n)) {
            fils.add(n);
        }
        return n;
    }

    /**
     * Retourne la valeur du noeud.
     * 
     * @return La valeur associée au noeud, de type {@link Cible}.
     */
    public Cible getVal() {
        return this.valeur;
    }

    /**
     * Retourne la liste des fils du noeud.
     * 
     * @return Une liste contenant tous les noeuds fils.
     */
    public ArrayList<Noeud> getFils() {
        return this.fils;
    }

    /**
     * Met à jour le noeud et ses descendants en vérifiant leur statut.
     * Si un fichier est manquant ou obsolète, l'état du noeud est mis à jour.
     * 
     * @param visites Un ensemble des noeuds déjà visités afin d'éviter les boucles infinies.
     */
    public void mettreJour(Set<Noeud> visites) {
        if (visites.contains(this)) {
            return;
        }
        visites.add(this);

        for (Noeud fils : this.getFils()) {
            fils.mettreJour(visites);
        }

        if (!this.getVal().getFichier().existe()) {
            if (!this.getVal().estPhony()) {
                if (this.debugMode) {
                    System.out.println(this + " existe pas");
                }
            }
            this.getVal().setPasJour();
        }

        for (Noeud fils : this.getFils()) {
            if (!fils.getVal().estAJour()) {
                this.getVal().setPasJour();
            }
            if (fils.getVal().getlastModified() > this.getVal().getlastModified()) {
                if (this.debugMode) {
                    System.out.println(fils.getVal() + " modifié après " + this.getVal());
                }
                this.getVal().setPasJour();
            }
        }
    }

    /**
     * Retourne tous les fils du noeud actuel, y compris les descendants dans un ordre topologique.
     * 
     * @return Une liste de tous les fils du noeud, y compris les descendants, dans un ordre topologique.
     */
    public ArrayList<Noeud> getAllFils() {
        ArrayList<Noeud> resultat = new ArrayList<>();
        Set<Noeud> visites = new HashSet<>();
        remplirTopologique(this, resultat, visites);
        return resultat;
    }

    /**
     * Remplit la liste des noeuds descendants de manière récursive en respectant un ordre topologique.
     * 
     * @param courant Le noeud courant à traiter.
     * @param resultat La liste qui contiendra les noeuds descendants.
     * @param visites Un ensemble des noeuds déjà visités pour éviter les cycles.
     */
    private void remplirTopologique(Noeud courant, ArrayList<Noeud> resultat, Set<Noeud> visites) {
        if (visites.contains(courant)) {
            return;
        }
        visites.add(courant);

        for (Noeud fils : courant.getFils()) {
            remplirTopologique(fils, resultat, visites);
        }

        resultat.add(courant);
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères du noeud.
     * 
     * @return Une chaîne représentant le noeud.
     */
    @Override
    public String toString() {
        return "" + valeur;
    }

}
