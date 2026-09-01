package gestionstock;

import gestionstock.dao.StockDao;
import gestionstock.model.StockItem;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        try {
            ArrayList<StockItem> items = dao.search("1002", "Livree");

            for (StockItem item : items) {
                System.out.println(
                        item.getId() + " - "
                                + item.getNumeroCommande() + " - "
                                + item.getArticle() + " - "
                                + item.getNote()
                );
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}