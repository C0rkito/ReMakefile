package fr.iutfbleau.izanicAissiGallego.projet.processus;

import java.io.IOException;
import java.io.*;

/**
 * La classe Processus permet de gérer l'exécution de commandes système à l'aide d'un singleton.
 * Elle utilise {@link ProcessBuilder} pour exécuter des commandes sur un shell Bash.
 */
public class Processus {

    private static Processus instance;
    private static ProcessBuilder pb;

    /**
     * Constructeur privé pour implémenter le pattern Singleton.
     * Empêche l'instanciation directe de la classe.
     */
    public Processus() {
    }

    /**
     * Retourne l'instance unique de la classe Processus.
     * Si l'instance n'existe pas encore, elle est créée avec un {@link ProcessBuilder}.
     * 
     * @return L'instance unique de la classe Processus.
     */
    public static Processus instance() {
        if (instance == null) {
            instance = new Processus();
            pb = new ProcessBuilder();
        }
        return instance;
    }

    /**
     * Exécute une commande système donnée en utilisant Bash.
     * La commande est redirigée pour capturer la sortie standard et l'erreur dans le même flux.
     * 
     * @param commande La commande à exécuter sous forme de chaîne de caractères.
     */
	public void executerCommande(String commande) {
		pb.command("bash", "-c", commande);
		pb.redirectErrorStream(true);

		try {
			Process process = pb.start();
			BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			String line;

		  
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}

			int exitCode = process.waitFor(); 


			if (exitCode != 0) {
				System.exit(exitCode);
			}

			} catch (IOException e) {
				System.exit(1);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt(); 
				System.exit(1);
			}
	}

}
