package fr.iutfbleau.izanicAissiGallego.projet.bakefile;

import java.util.HashMap;
import java.util.Map;

/**
 * La classe Variables gère les variables définies dans un fichier de configuration.
 * Elle permet d'ajouter, de remplacer et de récupérer les variables utilisées dans des lignes de texte.
 */
public class Variables {

    private Map<String, String> variables;

    /**
     * Constructeur par défaut de la classe Variables.
     * Initialise une nouvelle map pour stocker les variables.
     */
    public Variables() {
        this.variables = new HashMap<>();
    }

    /**
     * Ajoute une variable à partir d'une ligne de type "nom=valeur".
     * 
     * @param ligne La ligne contenant la déclaration de la variable.
     * @throws IllegalArgumentException Si la ligne est nulle ou si elle ne contient pas le symbole "=".
     */
    public void ajouterVariable(String ligne) {
        if (ligne == null || !ligne.contains("=")) {
            throw new IllegalArgumentException("Ligne invalide pour ajouter une variable : " + ligne);
        }
        String[] nomValeur = ligne.split("=", 2);
        String nom = nomValeur[0].trim();
        String valeur = nomValeur[1].trim();
        this.put(nom, valeur);
    }

    /**
     * Vérifie si une ligne est une déclaration de variable (contient le symbole "=").
     * 
     * @param ligne La ligne à analyser.
     * @return {@code true} si la ligne est une déclaration de variable, {@code false} sinon.
     */
    public static boolean estDeclarationVariable(String ligne) {
        return ligne != null && ligne.contains("=");
    }

    /**
     * Vérifie si une ligne contient une référence à une variable au format `$(...)` ou `${...}`.
     * 
     * @param ligne La ligne à analyser.
     * @return {@code true} si une variable est détectée, {@code false} sinon.
     */
    public static boolean contientVariable(String ligne) {
        return ligne != null && (ligne.contains("$(") || ligne.contains("${"));
    }

    /**
     * Remplace les variables référencées dans une ligne de texte par leurs valeurs correspondantes.
     * Les variables sont au format `$(nom)` ou `${nom}`.
     * 
     * @param ligne La ligne contenant des variables.
     * @return Une nouvelle ligne avec les variables remplacées par leurs valeurs, ou intacte si aucune variable n'a été trouvée.
     */
    public String modifierLigne(String ligne) {
        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < ligne.length()) {
            char c = ligne.charAt(i);

            // Rechercher une variable au format `$(...)` ou `${...}`
            if (c == '$' && i + 1 < ligne.length()) {
                char nextChar = ligne.charAt(i + 1);
                if (nextChar == '(' || nextChar == '{') {
                    int startIndex = i + 2;
                    int endIndex = (nextChar == '(') ? ligne.indexOf(')', startIndex) : ligne.indexOf('}', startIndex);

                    if (endIndex != -1) {
                        // Extraire le nom de la variable entre $(...) ou ${...}
                        String variable = ligne.substring(startIndex, endIndex);
                        String valeur = this.get(variable); // Récupérer la valeur de la variable

                        if (valeur != null) {
                            result.append(valeur); // Ajouter la valeur substituée
                        } else {
                            result.append("$").append(nextChar).append(variable).append((nextChar == '(') ? ")" : "}"); // Garder la variable non remplacée
                        }

                        i = endIndex + 1; // Passer à la fin de la variable
                        continue;
                    }
                }
            }

            // Ajouter le caractère actuel si ce n'est pas une variable
            result.append(c);
            i++;
        }

        return result.toString();
    }

    /**
     * Ajoute une variable avec son nom et sa valeur. 
     * Le nom de la variable est automatiquement converti en majuscules pour uniformité.
     * 
     * @param nom Le nom de la variable.
     * @param valeur La valeur associée à la variable.
     */
    private void put(String nom, String valeur) {
        this.variables.put(nom.toUpperCase(), valeur);
    }

    /**
     * Récupère la valeur d'une variable à partir de son nom.
     * Le nom est recherché en majuscules pour assurer la cohérence.
     * 
     * @param nom Le nom de la variable.
     * @return La valeur de la variable, ou {@code null} si elle n'existe pas.
     */
    private String get(String nom) {
        return this.variables.get(nom.toUpperCase());
    }

    /**
     * Affiche toutes les variables stockées et leurs valeurs dans la console.
     */
    public void afficher() {
        System.out.println(this.variables);
    }
}
