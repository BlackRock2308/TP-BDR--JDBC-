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
import java.util.Scanner;

/**
 *
 * @author ASUS
 */
public class Joueur {

    static String server = "localhost";
    static String port = "3306";
    static String database = "base_tennis";
    static String user = "root";
    static String password = "Lifeisqgift#)";
    static String requete = "select LIEUTOURNOI,ANNEE from Gain";

    Connection connexion = null;
    Statement statement = null;
    ResultSet resultat = null;

    public void maxPrime() {
        Scanner myObj = new Scanner(System.in);
        System.out.println("Enter une annee : ");
        int year = myObj.nextInt();
        System.out.println("l'annee saisie est: " + year);
        //String request = "SELECT ANNEE, PRIME FROM Gain WHERE annee = '" + year + "' ";
        String request2 = "SELECT ANNEE, MAX(PRIME) AS max_items FROM Gain WHERE annee = ? ";
        //String request1 = "SELECT ANNEE, MAX(PRIME) AS max_items FROM Gain WHERE annee = '" + year + "' ";

        loadDatabase();

        try {
            //étape 3: créer l'objet statement
            PreparedStatement preparedStatement = connexion.prepareStatement(request2);
            preparedStatement.setInt(1, year);
            resultat = preparedStatement.executeQuery();
            //étape 4: exécuter la requête
            while (resultat.next()) {
                String tuple = "La prime totale de l'annee " + resultat.getString(1) + " est : "
                        + resultat.getString(2);
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

    public float maxPrime2() {

        while (true) {
            Scanner myObj = new Scanner(System.in);
            System.out.println("Enter une annee : ");
            int year = myObj.nextInt();
            System.out.println("l'annee saisie est: " + year);
            //String request = "SELECT ANNEE, PRIME FROM Gain WHERE annee = '" + year + "' ";
            String request2 = "SELECT ANNEE, MAX(PRIME) AS max_items FROM Gain WHERE annee = ? ";
            //String request1 = "SELECT ANNEE, MAX(PRIME) AS max_items FROM Gain WHERE annee = '" + year + "' ";

            loadDatabase();

            try {
                //étape 3: créer l'objet statement
                PreparedStatement preparedStatement = connexion.prepareStatement(request2);
                preparedStatement.setInt(1, year);
                resultat = preparedStatement.executeQuery();
                //étape 4: exécuter la requête
                while (resultat.next()) {
                    String tuple = "La prime totale de l'annee " + resultat.getString(1) + " est : "
                            + resultat.getString(2);
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
    }

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
