package gestionstock.model;

import java.time.LocalDate;

public class SupplierOperation {

    private int id;
    private LocalDate date;
    private String libelle;
    private String article;
    private int debit;
    private int credit;
    private int solde;

    public SupplierOperation(int id, LocalDate date, String libelle, String article, int debit, int credit, int solde) {
        this.id = id;
        this.date = date;
        this.libelle = libelle;
        this.article = article;
        this.debit = debit;
        this.credit = credit;
        this.solde = solde;
    }

    public int getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getLibelle() {
        return libelle;
    }

    public String getArticle() {
        return article;
    }

    public int getDebit() {
        return debit;
    }

    public int getCredit() {
        return credit;
    }

    public int getSolde() {
        return solde;
    }
}
