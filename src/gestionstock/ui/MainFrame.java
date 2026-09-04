package gestionstock.ui;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    public MainFrame() {
        setTitle("Gestion de stock");
        setSize(1000, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        JLabel titleLabel = new JLabel("Gestion de Stock");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 20));

        JButton stockButton = new JButton("Stock");
        JButton supplierButton = new JButton("Fournisseur");
        JButton totalsButton = new JButton("Totaux");

        stockButton.setPreferredSize(new Dimension(160, 45));
        supplierButton.setPreferredSize(new Dimension(160, 45));
        totalsButton.setPreferredSize(new Dimension(160, 45));

        menuPanel.add(stockButton);
        menuPanel.add(supplierButton);
        menuPanel.add(totalsButton);

        mainPanel.add(titleLabel, BorderLayout.NORTH);
        mainPanel.add(menuPanel, BorderLayout.CENTER);

        add(mainPanel);
    }
}