import javax.swing.*;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.LayoutManager;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

  /**
  * Cette classe affiche une grille de sudoku.
  * 
  */
public class GrilleEditeur{


  /**
    * 
    * grille de sudoku a modifier
    * 
   */
  private Grille grille;

    /**
    * 
    * mode jeu "auto","joueur","manuel"
    * 
   */
  private String mode;


    /**
    * 
    * Constructeur 
    * @param g Grille qui sera modifier
    * @param mode mode jeu: "auto","joueur","manuel"
    * 
   */
  public GrilleEditeur(Grille g,String mode){
    this.grille = g;
    this.mode = mode;
  }

  /**
  * Permet d'afficher une grille de sudoku 
  * 
  */
  private JPanel affichegrille(){
    JPanel grille_panel = new JPanel();
    GridLayout gestionnaire = new GridLayout(3,3);
    grille_panel.setLayout(gestionnaire);

    for (int i=0;i<3;i++){
      for (int j=0;j<3;j++){
        JPanel o = new JPanel( new GridLayout(3, 3));
        Border blackline = BorderFactory.createLineBorder(Color.black,2);
        o.setBorder(blackline);
        for (int k=0;k<3;k++){
          for (int l =0;l<3;l++){
            CaseVue c = new CaseVue(this.grille.getCases(i,j,k,l));
            if (!(c.getCase().est_vide()) && (this.mode.equals("jouer") || this.mode.equals("auto") )){
              c.blocked();
            }
            else{
              CaseListener cobs = new CaseListener(c.getCase());
              c.addKeyListener(cobs);
              c.addActionListener(cobs);
            }
            o.add(c);
          }
          grille_panel.add(o);
        
        }
      }
    }
  return grille_panel;
  }

  /**
  * Affiche la fenetre qui contiendra la grille
  * 
  */
  public void afficher(){
    JFrame fenetre = new JFrame("Editeur de grille - " + this.mode);
    fenetre.setSize(500, 500);
    fenetre.setLocation(100, 100);
    fenetre.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    
    
    
    
    


    JPanel grille_panel = this.affichegrille();
    
    
    fenetre.add(grille_panel);

    if (this.mode.equals("edit")){
      GameButtonListener btnobs = new GameButtonListener(this.grille);
      JButton fin = new JButton("SAUVEGARDER");
      fin.addActionListener(btnobs);
      fenetre.add(fin,BorderLayout.SOUTH);
    }
    if (this.mode.equals("auto")){
      JPanel panel_temps = new JPanel();
      JLabel temps_label = new JLabel("Résolue en "+this.grille.getTemps()+" nanosecondes");
      panel_temps.add(temps_label);
      fenetre.add(panel_temps,BorderLayout.SOUTH);
    }
    
    fenetre.setVisible(true);
 
  }
  

}