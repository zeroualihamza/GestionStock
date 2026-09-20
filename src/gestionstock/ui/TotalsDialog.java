package gestionstock.ui;

import gestionstock.dao.StockDao;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;

public class TotalsDialog extends JDialog {

    private JTextField dateField;
    private JLabel totalVenteLabel;
    private JLabel totalAchatLabel;
    private JLabel totalLivraisonLabel;
    private JLabel totalBeneficeLabel;

    private StockDao stockDao;

    public TotalsDialog(JFrame parent) {
        super(parent, "Totaux du stock", true);

        stockDao = new StockDao();

        setSize(420, 300);
        setLocationRelativeTo(parent);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 25, 20, 25));

        JLabel titleLabel = new JLabel("Totaux par date");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 22));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));

        JPanel formPanel = new JPanel(new GridBagLayout());

        dateField = new JTextField();

        totalVenteLabel = new JLabel("0 Dhs");
        totalAchatLabel = new JLabel("0 Dhs");
        totalLivraisonLabel = new JLabel("0 Dhs");
        totalBeneficeLabel = new JLabel("0 Dhs");

        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        addRow(formPanel, c, 0, "Date commande", dateField);
        addRow(formPanel, c, 1, "Total prix vente", totalVenteLabel);
        addRow(formPanel, c, 2, "Total prix achat", totalAchatLabel);
        addRow(formPanel, c, 3, "Total livraison", totalLivraisonLabel);
        addRow(formPanel, c, 4, "Total benefice", totalBeneficeLabel);

        JButton calculateButton = new JButton("Calculer");
        JButton closeButton = new JButton("Fermer");

        calculateButton.addActionListener(e -> calculateTotals());
        closeButton.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 12, 5));
        buttonPanel.add(calculateButton);
        buttonPanel.add(closeButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(mainPanel);
    }

    private void addRow(JPanel panel, GridBagConstraints c, int row, String label, JComponent component) {
        c.gridx = 0;
        c.gridy = row;
        c.weightx = 0;
        panel.add(new JLabel(label), c);

        c.gridx = 1;
        c.weightx = 1;

        if (component instanceof JTextField) {
            component.setPreferredSize(new Dimension(180, 32));
        }

        panel.add(component, c);
    }

    private void calculateTotals() {
        try {
            LocalDate date = LocalDate.parse(dateField.getText().trim());

            int[] totals = stockDao.totalsByDate(date);

            totalVenteLabel.setText(totals[0] + " Dhs");
            totalAchatLabel.setText(totals[1] + " Dhs");
            totalLivraisonLabel.setText(totals[2] + " Dhs");
            totalBeneficeLabel.setText(totals[3] + " Dhs");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erreur : verifie la date au format yyyy-MM-dd.");
        }
    }
}