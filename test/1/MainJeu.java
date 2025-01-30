  /**
* 
* Classe principale du programme permettant de jouer
* 
*/
public class MainJeu {

      /**
  * 
  * affiche le menu pour jouer
  * 
  */
  public static void main(String[] args) {
    
    Menu fenetre =  new Menu("Sudoku - JOUER","Manuel","Automatique","sudokuoff.jpg");
    
    fenetre.afficher();
  }
}