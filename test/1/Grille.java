import java.io.IOException;
import java.io.Writer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Random;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

   /**
    * Représente une grille de sudoku 
    * 
   */
public class Grille {
  


   private Case[][][][] grille;
   private double temps;
  
   // creer une grille a partir d'une grille vide

   /**
    * Constructeur pour créer une grille vide. 
    * 
   */
   public Grille() {
      Case[][][][] tab = new Case[3][3][3][3];
      for (int i = 0; i < 3; i++) {
         for (int j = 0; j < 3; j++) {
            for (int k = 0; k < 3; k++) {
               for (int l = 0; l < 3; l++) {
                  tab[i][j][k][l] = new Case("",i,j,k,l,this);
                  tab[i][j][k][l].set_vide();
               }

            }
         }
      }

      this.grille = tab;
   }



   // lire une grille à partir d'un fichier
   /**
    * Constructeur pour créer une grille à partir d'un fichier. 
    * @param chemin chemin du fichier contenant la grille
    * 
   */
   public Grille(String chemin){
     String ligne = "";

     try{

         FileInputStream fis = new FileInputStream(chemin);
         DataInputStream fichier = new DataInputStream(fis);


         Case[][][][] tab = new Case[3][3][3][3];

         for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               for (int k = 0; k < 3; k++) {
                  for (int l = 0; l < 3; l++) {
                     tab[i][j][k][l] = new Case("",i,j,k,l,this);
                  }

               }
            }
         }

      this.grille = tab;
         int entier = 0;
         for (int l=0;l<3;l++){
            for (int k=0;k<3;k++){
               ligne = Integer.toString(fichier.readInt());
               
               while (ligne.length() < 9 ){
                  ligne = "0" + ligne;
               }

               

               for (int i=0;i<3;i++){
                  for (int j=0;j<3;j++){
                     String chiffre = String.valueOf(ligne.charAt(entier%9));
                     if (chiffre.equals("0")){
                        this.grille[l][i][k][j].set_valeur("");
                        this.grille[l][i][k][j].set_vide();
                     }
                     else{
                        this.grille[l][i][k][j].set_valeur(chiffre);
                        this.grille[l][i][k][j].set_bon();
                     }
                     entier = entier+1;
                  }
               }  
            }
         }
      
               
         
            
         

      
      fichier.close();
      }catch (IOException e){
         System.err.println("Erreur fichier "+e);
      }
   }

   /**
    * Renvoie les coordonnées de la première case vide d'une grille
    * @return int[i,j,k,l]
    *
    * 
   */
   private int[] caseVide(){
      int[] retour = new int[4];
      for (int i = 0;i<3;i++){
         for (int j = 0;j<3;j++){
            for (int k = 0;k<3;k++){
               for (int l = 0;l<3;l++){
                  if (this.grille[i][j][k][l].get_valeur().equals("")){
                     retour[0] = i;
                     retour[1] = j;
                     retour[2] = k;
                     retour[3] = l;
                     return retour;
                  }
               }
            }
         }
      }
      return null;
   }
   // Vérifier si on peut ajouter value à grile[i][j][k][l]
   /**
    * Résout la grille automatiquement
    * 
    * 
   */
   public boolean resoudre(){
      long debut = System.nanoTime();
      int [] coord = this.caseVide();
      if (coord == null){
         return true;
      }

      int i = coord[0];
      int j = coord[1];
      int k = coord[2];
      int l = coord[3];

      for (int num=1;num <=9;num++){
         if(this.add_condition(i,j,k,l,String.valueOf(num))){
            this.grille[i][j][k][l].set_valeur(String.valueOf(num));
            if (this.resoudre()){
               return true;
            }
            this.grille[i][j][k][l].set_valeur("");
         }
      }

   long fin = System.nanoTime();
   this.temps = (fin-debut);
   return false;


   }

   /**
    * Renvoie le temps en nanosecondes de la résolution automatique de la grille
    * @return temps en nanosecondes pour résoudre la grille automatiquement
    * 
   */
   public double getTemps(){
      return this.temps;
   }


   /**
    * @return Renvoie boolean si this.grille[i][j][k][l] peut contenir la valeur valeur
    * @param i re
    * 
   */
   public boolean add_condition(int i ,int j,int k,int l,String valeur) {

    if (valeur.length() > 1){
      return false;
    }
     
    // Vérifie si dans le meme carré
    for (int k_detect = 0; k_detect < 3; k_detect++){
       for (int l_detect = 0; l_detect < 3; l_detect++) {
          if (this.grille[i][j][k_detect][l_detect].get_valeur().equals(valeur) && !(k_detect == k && l_detect == l )) {
             return false;
          }

       }
    }

    // Vérifie si même ligne
    for (int j_detect = 0;j_detect<3;j_detect++){
       for (int l_detect = 0;l_detect<3;l_detect++){
          if (this.grille[i][j_detect][k][l_detect].get_valeur().equals(valeur) && !(j_detect == j && l_detect == l)){
             return false;
          }
       }
    }

    // Vérifie si même colonne
    for (int i_detect = 0;i_detect<3;i_detect++){
       for (int k_detect = 0;k_detect<3;k_detect++){
          if (this.grille[i_detect][j][k_detect][l].get_valeur().equals(valeur) && !(i_detect == i && k_detect == k)){
             return false;
          }
       }
    }

       
    return true;
   }
   

    /**
     * @param i la valeur i de la case dans la grille au cordonnées grille[i][j][k][l]
     * @param j la valeur j de la case dans la grille au cordonnées grille[i][j][k][l]
     * @param k la valeur k de la case dans la grille au cordonnées grille[i][j][k][l]
     * @param l la valeur l de la case dans la grille au cordonnées grille[i][j][k][l]
    * 
    * @return la Case au cordonné grille[i][j][k][l]
    * 
   */
   public Case getCases(int i, int j, int k, int l) {
      return this.grille[i][j][k][l];
   }

   /**
    * Vérifie si une grille est valide pour pouvoir l'exporter.
    * @return boolean pour savoir si la grille ne contredit pas les regles du sudoku pour etre sauvegarder
    * 
   */
   public boolean grille_valide(){
   // vérifie si c'est toute les cases sont valide
     for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
           for (int k = 0; k < 3; k++) {
              for (int l = 0; l < 3; l++) {
                 if (!(this.grille[i][j][k][l].get_validite())){
                   return false;
                 }
              }
           }
        }
     }
   return true;
   }

   /**
    * Vérifie si une grille est finit.
    * @return boolean true si la grille est complete false sinon
    * 
   */
   public boolean grille_win(){
   // vérifie si c'est toute les cases sont valide
     for (int i = 0; i < 3; i++) {
        for (int j = 0; j < 3; j++) {
           for (int k = 0; k < 3; k++) {
              for (int l = 0; l < 3; l++) {
                 if (!(this.grille[i][j][k][l].get_validite() && !(this.grille[i][j][k][l].est_vide()))){
                   return false;
                 }
              }
           }
        }
     }
   return true;
   }


   /**
    * Ecrit la grille dans un fichier
    * 
    * @param path chemin du fichier ou écrire la grille.
    * 
   */
   public void write(String path) {

      if (this.grille_valide()){
         try{
            String ligne = "";
            FileOutputStream fw = new FileOutputStream(path);
            DataOutputStream fichier = new DataOutputStream(fw);


         for (int l = 0; l < 3; l++) {
            for (int j = 0; j < 3; j++) {
               ligne = "";
               for (int k = 0; k < 3; k++) {
                  for (int i = 0; i < 3; i++) {
                     if (this.grille[l][k][j][i].est_vide()){
                        ligne = ligne + "0";
                     }
                     else{
                        ligne = ligne + this.grille[l][k][j][i].get_valeur();
                     }
                     
                  }
               }
               fichier.writeInt(Integer.parseInt(ligne));
            }
         }
         
         try{
            fichier.close();
         }catch (IOException e){
            System.err.println("Erreur lors de la fermeture");
         }
         }catch (IOException e){
            System.err.println(e);
         }
      }
   }
}