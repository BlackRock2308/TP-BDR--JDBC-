/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sn.ept.dic2.bdreparties.tennis.tournanment;

import java.sql.Connection;
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
public class Generic {

    Connection connexion = null;
    Statement statement = null;
    ResultSet resultat = null;

    public void genericRequest(String selectQuery) {
        loadDatabase();
        selectQuery = selectQuery.toUpperCase();
        if (!selectQuery.startsWith("SELECT")) {
            throw new IllegalArgumentException("This method only accepts SELECT queries. You tried: " + selectQuery);
        }
        try {
            //étape 3: créer l'objet statement
            PreparedStatement preparedStatement = connexion.prepareStatement(selectQuery);
            resultat = preparedStatement.executeQuery();
            //étape 4: exécuter la requête
            while (resultat.next()) {
                String tuple = "( " + resultat.getString(1) + " ,"
                        + resultat.getString(2) + ")";
                System.out.println(tuple);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultat != null) {
                    resultat.close();
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
