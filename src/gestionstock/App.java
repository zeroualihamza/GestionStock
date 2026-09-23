package gestionstock;

import gestionstock.dao.SupplierDao;
import gestionstock.model.SupplierOperation;

import java.time.LocalDate;

public class App {
    public static void main(String[] args) {

        SupplierDao dao = new SupplierDao();

        SupplierOperation operation = new SupplierOperation(
                0,
                LocalDate.of(2026, 9, 23),
                "Achat fournisseur test",
                "Nike Air Max",
                500,
                200,
                300
        );

        try {
            dao.insert(operation);
            System.out.println("Operation fournisseur ajoutee avec succes !");
        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}