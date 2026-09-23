package gestionstock.ui;

import gestionstock.dao.SupplierDao;
import gestionstock.model.SupplierOperation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.util.ArrayList;

public class SupplierPanel extends JPanel {

    private JTable table;
    private DefaultTableModel tableModel;
    private SupplierDao supplierDao;

    private JTextField searchLibelleField;
    private JTextField searchDateField;

    public SupplierPanel() {
        setLayout(new BorderLayout());

        supplierDao = new SupplierDao();

        JPanel searchPanel = new JPanel(new GridBagLayout());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(5, 15, 12, 15));

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(4, 6, 4, 6);
        c.fill = GridBagConstraints.HORIZONTAL;

        JLabel libelleLabel = new JLabel("Libelle :");
        searchLibelleField = new JTextField();

        JLabel dateLabel = new JLabel("Date :");
        searchDateField = new JTextField();

        JButton searchButton = new JButton("Rechercher");
        JButton refreshButton = new JButton("Actualiser");

        searchButton.addActionListener(e -> searchSupplierOperations());
        refreshButton.addActionListener(e -> loadSupplierOperations());

        c.gridy = 0;

        c.gridx = 0;
        c.weightx = 0;
        searchPanel.add(libelleLabel, c);

        c.gridx = 1;
        c.weightx = 1;
        searchPanel.add(searchLibelleField, c);

        c.gridx = 2;
        c.weightx = 0;
        searchPanel.add(dateLabel, c);

        c.gridx = 3;
        c.weightx = 1;
        searchPanel.add(searchDateField, c);

        c.gridx = 4;
        c.weightx = 0;
        searchPanel.add(searchButton, c);

        c.gridx = 5;
        c.weightx = 0;
        searchPanel.add(refreshButton, c);

        JLabel titleLabel = new JLabel("Gestion Fournisseur");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 0));

        tableModel = new DefaultTableModel();

        tableModel.addColumn("ID");
        tableModel.addColumn("Date");
        tableModel.addColumn("Libelle");
        tableModel.addColumn("Article");
        tableModel.addColumn("Debit");
        tableModel.addColumn("Credit");
        tableModel.addColumn("Solde");

        table = new JTable(tableModel);

        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);

        JScrollPane scrollPane = new JScrollPane(table);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(searchPanel, BorderLayout.CENTER);

        JButton addButton = new JButton("Ajouter");
        JButton editButton = new JButton("Modifier");
        JButton deleteButton = new JButton("Supprimer");
        JButton refreshButtonBottom = new JButton("Actualiser");

        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        actionPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        actionPanel.add(addButton);
        actionPanel.add(editButton);
        actionPanel.add(deleteButton);
        actionPanel.add(refreshButtonBottom);

        refreshButtonBottom.addActionListener(e -> loadSupplierOperations());

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
        add(actionPanel, BorderLayout.SOUTH);

        loadSupplierOperations();
    }

    private void loadSupplierOperations() {
        try {
            ArrayList<SupplierOperation> operations = supplierDao.findAll();

            tableModel.setRowCount(0);

            for (SupplierOperation operation : operations) {
                tableModel.addRow(new Object[]{
                        operation.getId(),
                        operation.getDate(),
                        operation.getLibelle(),
                        operation.getArticle(),
                        operation.getDebit(),
                        operation.getCredit(),
                        operation.getSolde()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de chargement : " + e.getMessage());
        }
    }

    private void searchSupplierOperations() {
        try {
            ArrayList<SupplierOperation> operations = supplierDao.search(
                    searchLibelleField.getText().trim(),
                    searchDateField.getText().trim()
            );

            tableModel.setRowCount(0);

            for (SupplierOperation operation : operations) {
                tableModel.addRow(new Object[]{
                        operation.getId(),
                        operation.getDate(),
                        operation.getLibelle(),
                        operation.getArticle(),
                        operation.getDebit(),
                        operation.getCredit(),
                        operation.getSolde()
                });
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur de recherche : " + e.getMessage());
        }
    }

    private SupplierOperation getSelectedSupplierOperation() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow == -1) {
            return null;
        }

        int row = table.convertRowIndexToModel(selectedRow);

        return new SupplierOperation(
                Integer.parseInt(tableModel.getValueAt(row, 0).toString()),
                LocalDate.parse(tableModel.getValueAt(row, 1).toString()),
                tableModel.getValueAt(row, 2).toString(),
                tableModel.getValueAt(row, 3).toString(),
                Integer.parseInt(tableModel.getValueAt(row, 4).toString()),
                Integer.parseInt(tableModel.getValueAt(row, 5).toString()),
                Integer.parseInt(tableModel.getValueAt(row, 6).toString())
        );
    }
}