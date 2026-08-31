package gestionstock;

import gestionstock.dao.StockDao;
import gestionstock.model.StockItem;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        StockItem item = new StockItem(
                0,
                1002,
                LocalDate.of(2026, 8, 30),
                "Rabat",
                "Adidas Samba",
                41,
                800,
                450,
                40,
                310,
                LocalDate.of(2026, 9, 3),
                "En cours"
        );

        try {
            dao.insert(item);
            System.out.println("Commande ajoutee avec succes !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}