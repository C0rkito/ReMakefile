  /**
   * La classe Case sert a représenter une case d'une grille de sudoku.
   * 
  */

public class Case {

  /**
   * La valeur de la case
   * 
  */
  private String valeur;
    /**
   * La grille dont la case fait partit
   * 
  */
  private Grille grille;
    /**
   * boolean représentant si la case est valide ou non selon les règles du sudoku
   * 
  */
  private boolean correct;
    /**
   * boolean représentant si la case est vide
   * 
  */
  private boolean vide;
      /**
   * la valeur i de la case dans la grille au cordonnées grille[i][j][k][l]
   * 
  */
  private int i;
        /**
   * la valeur j de la case dans la grille au cordonnées grille[i][j][k][l]
   * 
  */
  private int j;
        /**
   * la valeur k de la case dans la grille au cordonnées grille[i][j][k][l]
   * 
  */
  private int k;
        /**
   * la valeur l de la case dans la grille au cordonnées grille[i][j][k][l]
   * 
  */
  private int l;


    /**
   * Constructeur qui permet de créer une case
   * @param val La valeur de la case
   * @param i la valeur i de la case dans la grille au cordonnées grille[i][j][k][l]
   * @param j la valeur j de la case dans la grille au cordonnées grille[i][j][k][l]
   * @param k la valeur k de la case dans la grille au cordonnées grille[i][j][k][l]
   * @param l la valeur l de la case dans la grille au cordonnées grille[i][j][k][l]
   * @param g objet de la classe Grille dont la case fait partit
  */
  public Case(String val,int i ,int j,int k,int l,Grille g) {
    this.valeur = val;
    this.i = i;
    this.j = j;
    this.k = k;
    this.l = l;
    this.grille = g;
    this.correct = true;
    this.vide = true;
  }

  /**
  * Renvoie la grille dont la case fait partit.
  * 
  */
  public Grille getGrille(){
    return this.grille;  
  }

  /**
   * Rendre une case vide
  * l'attributs corect et vide de Case devient true
  * 
  */
  public void set_vide(){
    this.correct = true;
    this.vide = true;
  }
  
    /**
  * Renvoie un boolean pour savoir si la case est vide
  * 
  */
  public boolean est_vide(){
    return this.vide;
  }
  
  /**
  * Renvoie la valeur  de la case
  * 
  */
  public String get_valeur() {
    return this.valeur;
  }

  /**
  * Renvoie la cordonée i de la case dans la grille
  * 
  */
  public int get_i(){
    return this.i;
  }

  /**
  * Renvoie la cordonée j de la case dans la grille
  * 
  */
  public int get_j(){
    return this.j;
  }

  /**
  * Renvoie la cordonée k de la case dans la grille
  * 
  */
  public int get_k(){
    return this.k;
  }

  /**
  * Renvoie la cordonée l de la case dans la grille
  * 
  */
  public int get_l(){
    return this.l;
  }


  /**
  * Renvoie la validitée de la case
  * 
  */
  public boolean get_validite(){
    return this.correct;
  }

  public void set_mauvais(){
    this.correct = false;
  }

  public void set_bon(){
    this.correct = true;
  }
  
  /**
  * Change la valeur de la case.
  * @param nouv_valeur la nouvelle valeur de la case
  * 
  */
  public void set_valeur(String nouv_valeur) {
    this.vide = false;
    this.valeur = nouv_valeur;
  }

}//
