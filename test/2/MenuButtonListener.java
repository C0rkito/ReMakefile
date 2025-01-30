import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;


/**
* Gere les événements des bouttons du menu
* 
*/

public class MenuButtonListener implements ActionListener {


    /**
   * Gere les événements des bouttons du menu
   * @param evenement evenement du type ActionEvent
   */
  @Override
  public void actionPerformed(ActionEvent evenement){
    if (evenement.getActionCommand() == "Partir de zéro"){
      
      Grille magrille = new Grille();
      GrilleEditeur grille_editeur = new GrilleEditeur(magrille,"edit");
      grille_editeur.afficher();
    }
    
    if (evenement.getActionCommand() == "Charger une grille"){

        JFileChooser fenetre_choix = new JFileChooser();
        
        if (fenetre_choix.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
          File fichier_grille = fenetre_choix.getSelectedFile();
          String chemin = fichier_grille.getAbsolutePath();
          if (!(chemin.endsWith(".gri"))){
            Fenetre_message.afficherErreur("Impossible de charger la grille à partir du fichier sélectionné. Aussurez que le fichier est au format approprié");
          }
          else{
            Grille magrille = new Grille(chemin);
            GrilleEditeur grille_editeur = new GrilleEditeur(magrille,"edit");
            grille_editeur.afficher();
          }
        }
    }



    if (evenement.getActionCommand() == "Manuel"){
      JFileChooser fenetre_choix = new JFileChooser();
        
      if (fenetre_choix.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
        File fichier_grille = fenetre_choix.getSelectedFile();
        String chemin = fichier_grille.getAbsolutePath();
        if (!(chemin.endsWith(".gri"))){
          Fenetre_message.afficherErreur("Impossible de charger la grille à partir du fichier sélectionné. Aussurez que le fichier est au format approprié");
        }
        else{
          Grille magrille = new Grille(chemin);
          GrilleEditeur grille_editeur = new GrilleEditeur(magrille,"jouer");
          grille_editeur.afficher();
        }
      }
    }

    if (evenement.getActionCommand() == "Automatique"){
            JFileChooser fenetre_choix = new JFileChooser();
        
      if (fenetre_choix.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
        File fichier_grille = fenetre_choix.getSelectedFile();
        String chemin = fichier_grille.getAbsolutePath();
        if (!(chemin.endsWith(".gri"))){
          Fenetre_message.afficherErreur("Impossible de charger la grille à partir du fichier sélectionné. Aussurez que le fichier est au format approprié");
        }
        else{
          Grille magrille = new Grille(chemin);
          magrille.resoudre();
          GrilleEditeur grille_editeur = new GrilleEditeur(magrille,"auto");
          grille_editeur.afficher();
        }
      }
    }
  }
}