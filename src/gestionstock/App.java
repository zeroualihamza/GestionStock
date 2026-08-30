package gestionstock;

import gestionstock.model.StockItem;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        StockItem item = new StockItem(
                1,
                1001,
                LocalDate.of(2026, 8, 30),
                "Casablanca",
                "Nike Air Max",
                42,
                900,
                500,
                50,
                350,
                LocalDate.of(2026, 9, 2),
                "Livree"
        );

        System.out.println("Article : " + item.getArticle());
        System.out.println("Benefice : " + item.getBenefice() + " Dhs");
    }
}
