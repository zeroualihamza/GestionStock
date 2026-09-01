package gestionstock;

import gestionstock.dao.StockDao;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        try {
            dao.delete(2);
            System.out.println("Commande supprimee avec succes !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}