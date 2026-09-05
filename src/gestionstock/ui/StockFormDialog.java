package gestionstock.ui;

import gestionstock.dao.StockDao;
import gestionstock.model.StockItem;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class StockFormDialog extends JDialog {

    private JTextField numeroField;
    private JTextField dateCommandeField;
    private JTextField villeField;
    private JTextField articleField;
    private JTextField pointureField;
    private JTextField prixVenteField;
    private JTextField prixAchatField;
    private JTextField prixLivraisonField;
    private JTextField beneficeField;
    private JTextField dateLivraisonField;
    private JTextField noteField;

    private StockDao stockDao;
    private boolean saved;

    public StockFormDialog(JFrame parent) {
        super(parent, "Commande stock", true);

        stockDao = new StockDao();
        saved = false;

        setSize(460, 540);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        JLabel titleLabel = new JLabel("Commande stock");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());

        numeroField = new JTextField();
        dateCommandeField = new JTextField();
        villeField = new JTextField();
        articleField = new JTextField();
        pointureField = new JTextField();
        prixVenteField = new JTextField();
        prixAchatField = new JTextField();
        prixLivraisonField = new JTextField();
        beneficeField = new JTextField();
        beneficeField.setEditable(false);
        dateLivraisonField = new JTextField();
        noteField = new JTextField();

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(5, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        addFormRow(formPanel, c, 0, "Num commande", numeroField);
        addFormRow(formPanel, c, 1, "Date commande", dateCommandeField);
        addFormRow(formPanel, c, 2, "Ville", villeField);
        addFormRow(formPanel, c, 3, "Article", articleField);
        addFormRow(formPanel, c, 4, "Pointure", pointureField);
        addFormRow(formPanel, c, 5, "Prix vente", prixVenteField);
        addFormRow(formPanel, c, 6, "Prix achat", prixAchatField);
        addFormRow(formPanel, c, 7, "Prix livraison", prixLivraisonField);
        addFormRow(formPanel, c, 8, "Benefice", beneficeField);
        addFormRow(formPanel, c, 9, "Date livraison", dateLivraisonField);
        addFormRow(formPanel, c, 10, "Note", noteField);

        JButton calculateButton = new JButton("Calculer benefice");
        JButton saveButton = new JButton("Enregistrer");
        JButton cancelButton = new JButton("Annuler");

        calculateButton.addActionListener(e -> calculateBenefit());
        saveButton.addActionListener(e -> saveStockItem());
        cancelButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        buttonPanel.add(calculateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    public boolean isSaved() {
        return saved;
    }

    private void addFormRow(JPanel panel, GridBagConstraints c, int row, String label, JTextField field) {
        c.gridx = 0;
        c.gridy = row;
        c.weightx = 0;
        panel.add(new JLabel(label), c);

        c.gridx = 1;
        c.weightx = 1;
        field.setPreferredSize(new Dimension(230, 32));
        panel.add(field, c);
    }

    private void calculateBenefit() {
        try {
            int prixVente = Integer.parseInt(prixVenteField.getText().trim());
            int prixAchat = Integer.parseInt(prixAchatField.getText().trim());
            int prixLivraison = Integer.parseInt(prixLivraisonField.getText().trim());

            int benefice = prixVente - prixAchat - prixLivraison;

            beneficeField.setText(String.valueOf(benefice));

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Prix vente, prix achat et prix livraison doivent etre des nombres.");
        }
    }

    private void saveStockItem() {
        try {
            calculateBenefit();

            StockItem item = new StockItem(
                    0,
                    Integer.parseInt(numeroField.getText().trim()),
                    LocalDate.parse(dateCommandeField.getText().trim()),
                    villeField.getText().trim(),
                    articleField.getText().trim(),
                    Integer.parseInt(pointureField.getText().trim()),
                    Integer.parseInt(prixVenteField.getText().trim()),
                    Integer.parseInt(prixAchatField.getText().trim()),
                    Integer.parseInt(prixLivraisonField.getText().trim()),
                    Integer.parseInt(beneficeField.getText().trim()),
                    LocalDate.parse(dateLivraisonField.getText().trim()),
                    noteField.getText().trim()
            );

            stockDao.insert(item);
            saved = true;

            JOptionPane.showMessageDialog(this, "Commande ajoutee avec succes !");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage());
        }
    }
}