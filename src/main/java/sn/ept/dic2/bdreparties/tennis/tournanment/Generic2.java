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
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.database2;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.password2;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.port2;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.server2;
import static sn.ept.dic2.bdreparties.tennis.tournanment.Joueur.user2;

/**
 *
 * @author Mbaye SENE
 */
public class Generic2 {

    private final String url = "jdbc:postgresql://localhost/tennis_db";
    private final String user = "postgres";
    private final String password = "password";

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
            /**
             * while (resultat.next()) { String tuple = "( " +
             * resultat.getString(1) + " ," + resultat.getString(2) + ")";
             * System.out.println(tuple);
            }*
             */
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

    public Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }

    //fonction pour ajouter 100000 tuples dans ma base de données postgres
    public void addToDb() {

        loadDatabase();

        try {
            //étape 3: créer l'objet statement
            for (Integer i = 3125; i <= 100000; i++) {
                try ( PreparedStatement s = connexion.prepareStatement(
                        "INSERT INTO SPONSOR (id, nom, nationalite)\n"
                        + "VALUES (?, ?, ?)"
                )) {
                    s.setInt(1, i);
                    s.setString(2, "nom" + i);
                    s.setString(3, "Senegal" + i);
                    s.execute();
                }
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
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            String url = "jdbc:postgresql://" + server2 + ":" + port2 + "/" + database2;
            connexion = DriverManager.getConnection(url, user2, password2);
        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

}
