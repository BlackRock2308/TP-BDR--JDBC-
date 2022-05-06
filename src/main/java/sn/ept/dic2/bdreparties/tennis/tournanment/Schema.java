/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.dic2.bdreparties.tennis.tournanment;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.database;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.password;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.port;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.server;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.user;

/**
 *
 * @author ASUS
 */
public class Schema {

    Connection connexion = null;
    Statement statement = null;
    ResultSet columns = null;

    public void displaySchema() {
        // COnnexion to the database
        loadDatabase();
        try {
            //On stocke les metadata de la connexion
            DatabaseMetaData metaData = connexion.getMetaData();
            //Récupération des colonnes de la base de données
            columns = metaData.getColumns(null, null, "Joueur", null);
            
            System.out.println("\n NOM\t            TYPE\t");
            System.out.println("--------------------------------------------------------------------------------");
            while (columns.next()) {
                String tuple = columns.getString("COLUMN_NAME")+ "            \t"
                        + columns.getString("TYPE_NAME");
                       
                System.out.println(tuple);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (columns != null) {
                    columns.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connexion != null) {
                    connexion.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();

            }
        }
    }

    
    
    private void loadDatabase() {
        //chargement du Driver
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

        }
        try {
            String url = "jdbc:mysql://" + server + ":" + port + "/" + database;
            connexion = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
