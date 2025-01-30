import javax.swing.*;
import java.awt.*;

/**
 * 
 * Classe Menu qui servira à afficher un menu
 */
public class Menu {


  /**
 * 
 * Premier boutton du menu
 */
  private JButton boutton_1;
    /**
 * 
 * deuxième boutton du menu
 */
  private JButton boutton_2;


  private JFrame  fenetre;
  private String  img;

  
  /**
 * 
 * @param fen intitulé de la fentetre qui va afficher le menu
 * @param btn1 intitulé du premier boutton
 * @param btn2 intitulé du deuxième boutton
 * @param img  chemin vers l'image de fond du menu
 */

  public Menu(String fen,String btn1,String btn2,String img){
    this.fenetre = new JFrame(fen);
    this.boutton_1 = new JButton(btn1);
    this.boutton_2 = new JButton(btn2);
    this.img = img;
  }


  /**
 * 
 * Affiche le menu
 */
  public void afficher() {
    this.fenetre.setSize(642, 400);
    this.fenetre.setLocation(100, 100);
    this.fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ImageIcon imageFond = new ImageIcon(this.img);
    JLabel labelImage = new JLabel(imageFond);
    this.fenetre.setContentPane(labelImage);
    labelImage.setLayout(new BoxLayout(labelImage, BoxLayout.Y_AXIS)); // Utilisation du BoxLayout avec l'axe Y_AXIS

    // Ajout d'un espace vide en haut pour déplacer les boutons vers le haut
    labelImage.add(Box.createVerticalStrut(50));




    MenuButtonListener btn_obs = new MenuButtonListener();
    this.boutton_1.addActionListener(btn_obs);
    this.boutton_2.addActionListener(btn_obs);

    labelImage.add(this.boutton_1);
    labelImage.add(this.boutton_2);

    this.fenetre.setVisible(true);
  }

}
