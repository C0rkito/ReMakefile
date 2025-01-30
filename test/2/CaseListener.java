import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import java.awt.*;


  /**
   * Permet de gérer les événements d'un objet CaseVue
   * 
   */
public class CaseListener implements ActionListener,KeyListener{



    /**
    * 
   * objet de la classe Case 
   * 
   */
  private Case la_case;

    /**
    * 
   * objet de la classe grille qui contient la case la_case
   * 
   */
  private Grille la_grille;
  

    /**
    * 
   * @param une_case prend un objet de la classe Case
   * 
   */
  public CaseListener(Case une_case){
    this.la_case = une_case;
  }


  /**
   * lorsque un chiffre est ajouter modifie la taille de la police
   * @param evenement
   */
  
  @Override
  public void keyPressed(KeyEvent evenement){
    CaseVue source = (CaseVue) evenement.getSource();
    if (!(source.getText().length()+1 > 4)){
      source.changerTaille(50-(source.getText().length()*10));
    }
    
  }

    /**
   * 
   * @param evenement evenement du type KeyEvent
   */
  @Override
  public void keyReleased(KeyEvent evenement){

  }


  /**
   * lorsque un chiffre est ajouter modifie la taille de la police
   * @param evenement evenement de type KeyEvent
   */
  public void keyTyped(KeyEvent evenement){

  CaseVue source = (CaseVue) evenement.getSource();
  String chiffre = String.valueOf(evenement.getKeyChar());

  source.changerTaille(50-(source.getText().length()*10));
  if (source.getText().isEmpty()){
    la_case.set_valeur("");
    la_case.set_vide();
    source.vide();
  }

  if (source.getText().length()+1 > 4 || (!(chiffre.equals("1") || chiffre.equals("2")|| chiffre.equals("3")|| chiffre.equals("4")|| chiffre.equals("5")|| chiffre.equals("6")|| chiffre.equals("7")|| chiffre.equals("8")|| chiffre.equals("9")))){
    evenement.consume();

  }
  else{
    
    la_case.set_valeur("");
    la_case.set_vide();
    source.vide();
      

  }
  
  }


  /**
   *  qaund la touche entrée est apuyée modifie la case si cela ne contredit pas à la contrainte d'unicité
   *  @param evenement evenement de type KeyEvent
   */
  @Override
  public void actionPerformed(ActionEvent evenement){

    Grille la_grille = la_case.getGrille();

    CaseVue source = (CaseVue) evenement.getSource();
    String valeur = evenement.getActionCommand().trim();

    
    


    if (valeur.equals("")){
      la_case.set_valeur("");
      la_case.set_vide();
      source.vide();
    }
    
    else{

      this.la_case.set_valeur(valeur);
      if (la_grille.grille_win()){
        Fenetre_message.afficherInformations("Gagner !");
      }
      if (la_grille.add_condition(this.la_case.get_i(),this.la_case.get_j(),this.la_case.get_k(),this.la_case.get_l(),la_case.get_valeur())){
        la_case.set_bon();
        source.bon();
      }

      else{
        source.mauvais();
        la_case.set_mauvais();

      }
    }
  }
}
  