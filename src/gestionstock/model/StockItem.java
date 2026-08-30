package gestionstock.model;

import java.time.LocalDate;

public class StockItem {

    private int id;
    private int numeroCommande;
    private LocalDate dateCommande;
    private String ville;
    private String article;
    private int pointure;
    private int prixVente;
    private int prixAchat;
    private int prixLivraison;
    private int benefice;
    private LocalDate dateLivraison;
    private String note;

    public StockItem(int id, int numeroCommande, LocalDate dateCommande, String ville, String article,
                     int pointure, int prixVente, int prixAchat, int prixLivraison,
                     int benefice, LocalDate dateLivraison, String note) {
        this.id = id;
        this.numeroCommande = numeroCommande;
        this.dateCommande = dateCommande;
        this.ville = ville;
        this.article = article;
        this.pointure = pointure;
        this.prixVente = prixVente;
        this.prixAchat = prixAchat;
        this.prixLivraison = prixLivraison;
        this.benefice = benefice;
        this.dateLivraison = dateLivraison;
        this.note = note;
    }

    public int getId() {
        return id;
    }

    public int getNumeroCommande() {
        return numeroCommande;
    }

    public LocalDate getDateCommande() {
        return dateCommande;
    }

    public String getVille() {
        return ville;
    }

    public String getArticle() {
        return article;
    }

    public int getPointure() {
        return pointure;
    }

    public int getPrixVente() {
        return prixVente;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public int getPrixLivraison() {
        return prixLivraison;
    }

    public int getBenefice() {
        return benefice;
    }

    public LocalDate getDateLivraison() {
        return dateLivraison;
    }

    public String getNote() {
        return note;
    }
}
