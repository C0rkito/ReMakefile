package fr.iutfbleau.izanicAissiGallego.projet.bakefile;

import fr.iutfbleau.izanicAissiGallego.projet.graphe.Noeud;
import fr.iutfbleau.izanicAissiGallego.projet.cible.Cible;
import java.util.*;
import java.io.*;

/**
 * La classe BakefileParser analyse un fichier de configuration de type "bakefile" 
 * pour en extraire les cibles, leurs dépendances et leurs recettes.
 * Elle gère également les variables définies dans le fichier.
 */
public class BakefileParser {
    private File bakefile;
    private Map<String, Noeud> cibles;
    private Variables variablesManager;
    private Noeud premiereCible;
    private boolean debugMode;

    /**
     * Constructeur de la classe BakefileParser.
     * 
     * @param cheminFichier Le chemin vers le fichier bakefile à analyser.
     * @param debugMode Mode de débogage activé ou désactivé (true pour activer, false sinon).
     */
    public BakefileParser(String cheminFichier, boolean debugMode) {
        this.bakefile = new File(cheminFichier);
        this.cibles = new HashMap<>();
        this.variablesManager = new Variables();
        this.debugMode = debugMode;
    }

    /**
     * Analyse le fichier bakefile et extrait les cibles, leurs dépendances et leurs recettes.
     * 
     * @return Une map contenant les cibles extraites avec leur nom comme clé et leur {@link Noeud} correspondant comme valeur.
     */
    public Map<String, Noeud> parse() {
        try (BufferedReader reader = new BufferedReader(new FileReader(bakefile))) {
            String line;
            Noeud currentCible = null;
            StringBuilder recette = new StringBuilder();

            while ((line = reader.readLine()) != null) {

                // Remplacement des variables dans les lignes contenant des variables
                if (Variables.contientVariable(line)) {
                    line = variablesManager.modifierLigne(line);
                }

                // Ignorer les lignes vides ou les commentaires
                if (line.isEmpty() || line.startsWith("#")) {
                    continue;
                }

                // Gestion des déclarations de variables
                if (Variables.estDeclarationVariable(line)) {
                    String ligne = line.replaceAll("#.*", "").trim();
                    this.variablesManager.ajouterVariable(ligne);
                } else {
                    // Gestion des cibles et dépendances
                    if (line.contains(":") && !line.startsWith("\t")) {
                        // Si une cible précédente existe, enregistre ses recettes
                        if (currentCible != null && recette.length() > 0) {
                            ArrayList<String> recettes = new ArrayList<>();
                            recettes.add(recette.toString().trim());
                            currentCible.getVal().setRecettes(recettes);
                            if (this.premiereCible == null) {
                                this.premiereCible = currentCible;
                            }
                        }

                        // Analyse de la ligne pour extraire le nom de la cible et ses dépendances
                        String[] parts = line.split(":");
                        String cibleName = parts[0].trim();
                        String[] dependances = parts.length > 1 ? parts[1].trim().split("\\s+") : new String[0];

                        // Recherche ou création de la cible
                        if (cibles.containsKey(cibleName)) {
                            currentCible = cibles.get(cibleName);
                        } else {
                            currentCible = new Noeud(new Cible(cibleName), debugMode);
                            cibles.put(cibleName, currentCible);
                        }

                        // Gestion des dépendances
                        for (String dep : dependances) {
                            if (!dep.isEmpty()) {
                                Noeud depCible;
                                if (cibles.containsKey(dep)) {
                                    depCible = cibles.get(dep);
                                } else {
                                    depCible = new Noeud(new Cible(dep), debugMode);
                                    cibles.put(dep, depCible);
                                }
                                if (currentCible.getVal().getNom().equals(".PHONY")) {
                                    depCible.getVal().setEstPhony();
                                }
                                currentCible.ajouter(depCible);
                            }
                        }

                        recette = new StringBuilder();
                    }
                }

                // Ajout des recettes associées à la cible courante
                if (line.startsWith("\t") && currentCible.getVal() != null) {
                    recette.append(line.substring(1)).append("\n");
                }
            }

            // Enregistrement de la dernière recette après la lecture complète du fichier
            if (currentCible.getVal() != null && recette.length() > 0) {
                ArrayList<String> recettes = new ArrayList<>();
                recettes.add(recette.toString().trim());
                currentCible.getVal().setRecettes(recettes);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return cibles;
    }

    /**
     * Retourne le nom de la première cible trouvée dans le fichier bakefile.
     * 
     * @return Le nom de la première cible sous forme de chaîne de caractères.
     */
    public String getPremiereCible() {
        return this.premiereCible.getVal().getNom();
    }
}
