/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package sn.ept.dic2.bdreparties.tennis.tournanment;

/**
 *
 * @author ASUS
 */
public class TennisTournanment {

        public static void main(String[] args) {
            
       
        
        Joueur joueur = new Joueur();
        
        System.out.println("*******************Fonction maxPrime***********************\n \n");
        
        joueur.maxPrime();
        
        //System.out.println("*******************Fonction maxPrime2***********************/n");
        
        //joueur.maxPrime2();
        
        System.out.println("*******************Requete Generique***********************\n");
        
        String selectQuery = "SELECT * from Joueur WHERE AnNaiss = 1972";
        
        joueur.genericRequest(selectQuery);
       

    }
        
}
