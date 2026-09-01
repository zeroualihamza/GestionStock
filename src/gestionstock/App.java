package gestionstock;

import gestionstock.dao.StockDao;
import gestionstock.model.StockItem;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        StockItem item = new StockItem(
                1,
                1002,
                LocalDate.of(2026, 8, 31),
                "Rabat",
                "Adidas Samba",
                41,
                850,
                450,
                40,
                360,
                LocalDate.of(2026, 9, 3),
                "Livree"
        );

        try {
            dao.update(item);
            System.out.println("Commande modifiee avec succes !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}