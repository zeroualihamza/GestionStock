package gestionstock.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class StockPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;

    public StockPanel() {
        setLayout(new BorderLayout());

        JLabel titleLabel = new JLabel("Gestion du Stock");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Num com");
        tableModel.addColumn("Date com");
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
    }
}
