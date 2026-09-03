package gestionstock;

import gestionstock.dao.StockDao;

import java.util.ArrayList;

public class App {
    public static void main(String[] args) {

        StockDao dao = new StockDao();

        try {
            ArrayList<String> notes = dao.findNotes();

            for (String note : notes) {
                System.out.println("Note : " + note);
            }

        } catch (Exception e) {
            System.out.println("Erreur : " + e.getMessage());
        }
    }
}