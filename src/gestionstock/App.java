package gestionstock;

import gestionstock.model.StockItem;
import gestionstock.model.SupplierOperation;

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

        SupplierOperation operation = new SupplierOperation(
                1,
                LocalDate.of(2026, 8, 30),
                "Achat fournisseur",
                "Nike Air Max",
                500,
                200,
                300
        );

        System.out.println("Article stock : " + item.getArticle());
        System.out.println("Benefice : " + item.getBenefice() + " Dhs");

        System.out.println("Operation fournisseur : " + operation.getLibelle());
        System.out.println("Solde fournisseur : " + operation.getSolde() + " Dhs");
    }
}