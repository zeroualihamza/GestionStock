package gestionstock;

import gestionstock.dao.StockDao;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        try {
            int[] totals = dao.totalsByDate(LocalDate.of(2026, 8, 31));

            System.out.println("Total prix vente : " + totals[0] + " Dhs");
            System.out.println("Total prix achat : " + totals[1] + " Dhs");
            System.out.println("Total livraison : " + totals[2] + " Dhs");
            System.out.println("Total benefice : " + totals[3] + " Dhs");

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}