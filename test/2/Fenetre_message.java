import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Arrays;


/**
 * 
 * la Classe <code> Fenetre_message </code> permet d'afficher du texte dans une petite fenetre
 * 
 */
public class Fenetre_message{

        /**
     * 
     *  afficher une erreur dans une fenetre
     * @param message message à afficher 
     * 
     */
    public static void afficherErreur(String message) {
        JOptionPane.showMessageDialog(null, message, "Erreur", JOptionPane.ERROR_MESSAGE);
    }


    /**
     * 
     *  afficher un message dans une fenetre
     * @param message intitulé de l'erreur a afficher à afficher 
     * 
     */
    public static void afficherInformations(String message) {
        JOptionPane.showMessageDialog(null, message, "Informations", JOptionPane.INFORMATION_MESSAGE);
    }
}
