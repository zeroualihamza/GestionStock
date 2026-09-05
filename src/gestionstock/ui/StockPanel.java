package gestionstock.ui;

import gestionstock.dao.StockDao;
import gestionstock.model.StockItem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StockPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private StockDao stockDao;
    private JTextField searchNumeroField;
    private JTextField searchNoteField;

    public StockPanel() {
        setLayout(new BorderLayout());

        stockDao = new StockDao();

        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 12, 15));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel numeroLabel = new JLabel("Num commande :");
        searchNumeroField = new JTextField();

        JLabel noteLabel = new JLabel("Note :");
        searchNoteField = new JTextField();

        JButton searchButton = new JButton("Rechercher");
        JButton refreshButton = new JButton("Actualiser");

        searchButton.addActionListener(e -> searchStockItems());
        refreshButton.addActionListener(e -> loadStockItems());

        c.gridy = 0;

        c.gridx = 0;
        c.weightx = 0;
        searchPanel.add(numeroLabel, c);

        c.gridx = 1;
        c.weightx = 1;
        searchPanel.add(searchNumeroField, c);

        c.gridx = 2;
        c.weightx = 0;
        searchPanel.add(noteLabel, c);

        c.gridx = 3;
        c.weightx = 1;
        searchPanel.add(searchNoteField, c);

        c.gridx = 4;
        c.weightx = 0;
        searchPanel.add(searchButton, c);

        c.gridx = 5;
        c.weightx = 0;
        searchPanel.add(refreshButton, c);

        JLabel titleLabel = new JLabel("Gestion du Stock");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Num commande");
        tableModel.addColumn("Date commande");
        tableModel.addColumn("Ville");
        tableModel.addColumn("Article");
        tableModel.addColumn("Pointure");
        tableModel.addColumn("Prix vente");
        tableModel.addColumn("Prix achat");
        tableModel.addColumn("Prix livraison");
        tableModel.addColumn("Benefice");
        tableModel.addColumn("Date livraison");
        tableModel.addColumn("Note");

        table = new JTable(tableModel);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Ajouter");
        JButton editButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");
        JButton refreshButtonBottom = new JButton("Actualiser");

        addButton.addActionListener(e -> {
            JFrame parent = (JFrame) SwingUtilities.getWindowAncestor(this);
            StockFormDialog dialog = new StockFormDialog(parent);
            dialog.setVisible(true);

            if (dialog.isSaved()) {
                loadStockItems();
            }
        });

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        actionPanel.add(addButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);
        actionPanel.add(refreshButtonBottom);

        refreshButtonBottom.addActionListener(e -> loadStockItems());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        loadStockItems();
    }

    private void loadStockItems() {
        try {
            ArrayList<StockItem> items = stockDao.findAll();

            tableModel.setRowCount(0);

            for (StockItem item : items) {
                tableModel.addRow(new Object[]{
                        item.getId(),
                        item.getNumeroCommande(),
                        item.getDateCommande(),
                        item.getVille(),
                        item.getArticle(),
                        item.getPointure(),
                        item.getPrixVente(),
                        item.getPrixAchat(),
                        item.getPrixLivraison(),
                        item.getBenefice(),
                        item.getDateLivraison(),
                        item.getNote()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage());
        }
    }

    private void searchStockItems() {
        try {
            ArrayList<StockItem> items = stockDao.search(
                    searchNumeroField.getText().trim(),
                    searchNoteField.getText().trim()
            );

            tableModel.setRowCount(0);

            for (StockItem item : items) {
                tableModel.addRow(new Object[]{
                        item.getId(),
                        item.getNumeroCommande(),
                        item.getDateCommande(),
                        item.getVille(),
                        item.getArticle(),
                        item.getPointure(),
                        item.getPrixVente(),
                        item.getPrixAchat(),
                        item.getPrixLivraison(),
                        item.getBenefice(),
                        item.getDateLivraison(),
                        item.getNote()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de recherche : " + e.getMessage());
        }
    }
}