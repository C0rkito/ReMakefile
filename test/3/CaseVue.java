import javax.swing.*;
import java.awt.*;



  /**
   * Hérite de Jtextfield et permet d'afficher une case.
   * 
   */
public class CaseVue extends JTextField {

  /**
   * objet de la classe Case
   */
  private Case la_case;
    /**
   * boolean si la vue de la case est correct
   */
  private boolean correct;



    /**
    *  constructeur
   * @param une_case créer la vue d'une case, c'est à dire un JTextfield qui sera lié avec une case
   * 
   */
  public CaseVue(Case une_case){
    super(une_case.get_valeur());
    this.la_case =  une_case;
    this.setFont(new Font("Courier", Font.BOLD, 50));   
  }
  
  /**
  * Modifie la taille de la police
  */
  public void changerTaille(int nouv ){
    this.setFont(new Font("Courier", Font.BOLD,nouv));
  }

  /**
  * @return Retourne l'objet de la classe case correspondant a ce JTextfield
  */
  public Case getCase(){
    return this.la_case;
  }


    /**
   * Permet de bloquer l'entrée du JTextfield
   * 
   */
  public void blocked(){
    this.setBackground(Color.GRAY);
    this.setEditable(false);
    this.correct = true;
  }

    /**
   * Change la couleur du fond en rouge
   * 
   */
  public void mauvais(){
    this.correct = false;
    this.setBackground(Color.RED);
  }

  /**
   * Change la couleur du fond en  vert
   * 
   */
  public void bon(){
    this.correct = true;
    this.setBackground(Color.GREEN);
  }

  /**
   * Change la couleur du fond en blanc
   * 
   */
  public void vide(){
    this.correct = false;
    this.setBackground(Color.WHITE);
  }
}