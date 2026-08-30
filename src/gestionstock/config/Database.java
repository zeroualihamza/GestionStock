package gestionstock.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Database {

    private static final String URL =
            "jdbc:mysql://localhost:3306/gestion_de_stock?useSSL=false&useUnicode=true&characterEncoding=UTF-8";

    private static final String USER = "root";
    private static final String PASSWORD = "Zrewlate";

    private Database() {
    }

    public static Connection connect() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver MySQL introuvable.", e);
        }

        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}