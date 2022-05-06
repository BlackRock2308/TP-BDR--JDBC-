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
        
        System.out.println("*******************2.1 Fonction maxPrime***********************\n \n");
        
        //joueur.maxPrime();
        
        //System.out.println("*******************2.2 Fonction maxPrime2***********************/n");
        
        //joueur.maxPrime2();
        
        System.out.println("*******************3.2 Requete Generique***********************\n");
        
        String selectQuery = "SELECT * from Joueur WHERE AnNaiss = 1972";
        
        //joueur.genericRequest(selectQuery);
        
        System.out.println("******3.3 Compléter le programme pour afficher en en-tête le nom des attributs du résultat*******\n");
        
        String selectQueryComplete = "SELECT * from Joueur WHERE AnNaiss = 1972";
        
        joueur.genericRequestComplete(selectQueryComplete);
        
        System.out.println("*******************4° Schema d'une relation ***********************\n \n");

        Schema schema1 = new Schema();
        
        schema1.displaySchema();
        
        System.out.println("*******************5° Jointure Inter-bases ***********************\n \n");

       

    }
        
}
