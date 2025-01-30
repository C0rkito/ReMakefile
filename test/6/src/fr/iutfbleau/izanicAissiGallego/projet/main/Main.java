package fr.iutfbleau.izanicAissiGallego.projet.main;

import fr.iutfbleau.izanicAissiGallego.projet.cible.Cible;
import fr.iutfbleau.izanicAissiGallego.projet.graphe.Noeud;
import fr.iutfbleau.izanicAissiGallego.projet.bakefile.BakefileParser;

import java.io.IOException;
import java.util.*;

/**
 * Classe principale pour exécuter le programme de gestion de cibles définies dans un fichier Bakefile.
 * Cette classe parse le Bakefile, analyse les cibles et leurs dépendances, et exécute les recettes nécessaires.
 */
public class Main {

	/**
	* Constructeur
	*/
	public Main(){
		
	}
    /**
     * Méthode principale du programme.
     * 
     * @param args Arguments de la ligne de commande. Peut inclure :
     *             - "-d" pour activer le mode debug.
     *             - Les noms des cibles à construire. Si aucun nom n'est fourni, la première cible définie dans le Bakefile sera utilisée.
     */
    public static void main(String[] args) {

        boolean debugMode = false;

        // Analyse des arguments pour activer le mode debug
        for (String arg : args) {
            if ("-d".equals(arg)) {
                debugMode = true;
                break;
            }
        }

        // Liste des cibles à construire
        List<String> cibleAFaire = new ArrayList<>();

        // Création et analyse du fichier Bakefile
        BakefileParser parser = new BakefileParser("bakefile", debugMode);
        Map<String, Noeud> dico = parser.parse();

        // Déterminer les cibles à construire en fonction des arguments
        if (args.length == 0 || (args.length == 1 && args[0].equals("-d"))) {
            cibleAFaire.add(parser.getPremiereCible()); // Par défaut, utiliser la première cible
        } else {
            for (String ci : args) {
                if (!ci.equals("-d")) {
                    cibleAFaire.add(ci);
                }
            }
        }

        // Initialisation des structures de gestion des dépendances
        ArrayDeque<Cible> file = new ArrayDeque<>();
        Map<String, Noeud> historique = new HashMap<>();

        // Parcourir chaque cible spécifiée
        for (String cible : cibleAFaire) {
            if (debugMode) {
                System.out.println("--------------------");
                System.out.println("[DEBUG MODE]");
                System.out.println("Cible : '" + dico.get(cible) + "'");
            }

            Noeud lacible = dico.get(cible);

            // Analyser les dépendances et mettre à jour l'état des cibles
            Set<Noeud> visites = new HashSet<>();
            ArrayList<Noeud> topologique = lacible.getAllFils();
            lacible.mettreJour(visites);

            // Ajouter les dépendances non à jour dans la file
            for (Noeud n : topologique) {
                if (!n.getVal().estAJour() && !historique.containsKey(n.getVal().getNom())) {
                    file.add(n.getVal());
                    historique.put(n.getVal().getNom(), n);
                }
            }

            // Ajouter la cible principale si elle n'est pas à jour
            if (!lacible.getVal().estAJour() && !historique.containsKey(lacible.getVal().getNom())) {
                file.add(lacible.getVal());
                historique.put(lacible.getVal().getNom(), lacible);
            }

            // Vérifier si aucune mise à jour n'est nécessaire
            if (file.isEmpty()) {
                System.out.println();
                System.out.println("bake: '" + cible + "' is up to date.");
            } else {
                // Mode debug : afficher les cibles à mettre à jour
                if (debugMode) {
                    System.out.println("\nCibles considérées pour une mise à jour : ");
                    System.out.println(file);
                    System.out.println("--------------------");
                }

                // Exécuter les recettes pour chaque cible
                while (!file.isEmpty()) {
                    Cible cibleAExec = file.poll();
                    cibleAExec.executerRecettes();
                    dico.get(cibleAExec.getNom()).getVal().setAJour();
                }
            }
        }
    }
}
