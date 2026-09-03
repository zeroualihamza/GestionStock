package gestionstock.ui;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Gestion de stock - Connexion");
        setSize(450, 380);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(245, 247, 250));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(30, 40, 30, 40));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;

        JLabel titleLabel = new JLabel("Gestion de Stock");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(new Color(35, 45, 65));

        c.gridy = 0;
        c.insets = new Insets(0, 0, 5, 0);
        mainPanel.add(titleLabel, c);

        JLabel subtitleLabel = new JLabel("Connexion administrateur");
        subtitleLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subtitleLabel.setForeground(new Color(100, 110, 125));

        c.gridy = 1;
        c.insets = new Insets(0, 0, 25, 0);
        mainPanel.add(subtitleLabel, c);

        JLabel usernameLabel = new JLabel("Nom d'utilisateur");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        c.gridy = 2;
        c.insets = new Insets(0, 0, 6, 0);
        mainPanel.add(usernameLabel, c);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        usernameField.setPreferredSize(new Dimension(340, 38));

        c.gridy = 3;
        c.insets = new Insets(0, 0, 14, 0);
        mainPanel.add(usernameField, c);

        JLabel passwordLabel = new JLabel("Mot de passe");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));

        c.gridy = 4;
        c.insets = new Insets(0, 0, 6, 0);
        mainPanel.add(passwordLabel, c);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        passwordField.setPreferredSize(new Dimension(340, 38));

        c.gridy = 5;
        c.insets = new Insets(0, 0, 22, 0);
        mainPanel.add(passwordField, c);

        JButton loginButton = new JButton("Se connecter");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 15));
        loginButton.setBackground(new Color(45, 95, 170));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setPreferredSize(new Dimension(340, 42));

        loginButton.addActionListener(e -> login());

        c.gridy = 6;
        c.insets = new Insets(0, 0, 0, 0);
        mainPanel.add(loginButton, c);

        add(mainPanel);
        getRootPane().setDefaultButton(loginButton);
    }

    private void login() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.equals("admin") && password.equals("admin")) {
            JOptionPane.showMessageDialog(this, "Connexion reussie !");
        } else {
            JOptionPane.showMessageDialog(this, "Identifiants incorrects !");
            passwordField.setText("");
        }
    }
}