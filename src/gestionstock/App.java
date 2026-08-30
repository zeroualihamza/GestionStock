package gestionstock;

import gestionstock.config.Database;

import java.sql.Connection;

public class App {
    public static void main(String[] args) {
        try {
            Connection connection = Database.connect();
            System.out.println("Connexion MySQL reussie !");
            connection.close();
        } catch (Exception e) {
            System.out.println("Erreur de connexion : " + e.getMessage());
        }
    }
}
