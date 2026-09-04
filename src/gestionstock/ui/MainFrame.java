package gestionstock.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private JPanel contentPanel;

    public MainFrame() {
        setTitle("Gestion de stock");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Gestion de Stock");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 15, 0));

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton stockButton = new JButton("Stock");
        JButton supplierButton = new JButton("Fournisseur");
        JButton totalsButton = new JButton("Totaux");

        stockButton.setPreferredSize(new Dimension(160, 40));
        supplierButton.setPreferredSize(new Dimension(160, 40));
        totalsButton.setPreferredSize(new Dimension(160, 40));

        menuPanel.add(stockButton);
        menuPanel.add(supplierButton);
        menuPanel.add(totalsButton);

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(titleLabel, BorderLayout.NORTH);
        topPanel.add(menuPanel, BorderLayout.CENTER);

        contentPanel = new JPanel(new BorderLayout());
        contentPanel.add(new JLabel("Choisis une section", SwingConstants.CENTER), BorderLayout.CENTER);

        stockButton.addActionListener(e -> {
            contentPanel.removeAll();
            contentPanel.add(new StockPanel(), BorderLayout.CENTER);
            contentPanel.revalidate();
            contentPanel.repaint();
        });

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(contentPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}