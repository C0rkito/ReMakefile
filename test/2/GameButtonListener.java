import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFileChooser;
import java.io.File;


/**
* Gere les événements des bouttons en jeu pour enrengistrer une grille
* 
*/
public class GameButtonListener implements ActionListener {

  /**
* grille qui sera enrengistrée
* 
*/
  private Grille une_grille;
  
    /**
* Constructeur
* @param g grille qui sera enrengistrée
*/
  public GameButtonListener(Grille g){
    this.une_grille = g;
  }

  
  /**
  * permet de gérer les événement avec le boutton 
  * @param evenement evenement du type ActionEvent 
  */
  @Override
  public void actionPerformed(ActionEvent evenement){

  if (this.une_grille.grille_valide()){

    JFileChooser fenetre_choix = new JFileChooser();
      
    if (fenetre_choix.showSaveDialog(null) == JFileChooser.APPROVE_OPTION){
      File fichier_grille = fenetre_choix.getSelectedFile();
      String chemin = fichier_grille.getAbsolutePath();
      if (!(chemin.endsWith(".gri"))){
        chemin = chemin + ".gri";
      }
      this.une_grille.write(chemin);
      Fenetre_message.afficherInformations("Grille sauvegarder avec succès dans le fichier.");
    }
  }
  else{
    Fenetre_message.afficherErreur("La grille est incomplète ou incorrecte. Veuillez remplir toutes les cases correctement avant de valider.");
  }
    
    
  }
}