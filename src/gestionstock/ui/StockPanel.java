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

    public StockPanel() {
        setLayout(new BorderLayout());

        stockDao = new StockDao();

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

        add(titleLabel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

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
}