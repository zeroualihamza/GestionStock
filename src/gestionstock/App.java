package gestionstock;

import gestionstock.dao.StockDao;
import gestionstock.model.StockItem;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        try {
            ArrayList<StockItem> items = dao.findAll();

            for (StockItem item : items) {
                System.out.println(item.getNumeroCommande() + " - " + item.getArticle() + " - " + item.getBenefice() + " Dhs");
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}