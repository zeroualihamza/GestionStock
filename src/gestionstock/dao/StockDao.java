package gestionstock.dao;

import gestionstock.config.Database;
import gestionstock.model.StockItem;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StockDao {

    public ArrayList<StockItem> findAll() throws SQLException {

        ArrayList<StockItem> items = new ArrayList<>();

        Connection connection = Database.connect();

        Statement statement = connection.createStatement();

        ResultSet result = statement.executeQuery("SELECT * FROM stock ORDER BY id DESC");

        while (result.next()) {
            StockItem item = new StockItem(
                    result.getInt("id"),
                    result.getInt("num_com"),
                    result.getDate("date_com").toLocalDate(),
                    result.getString("ville"),
                    result.getString("article"),
                    result.getInt("pointure"),
                    result.getInt("prix_vente"),
                    result.getInt("prix_achat"),
                    result.getInt("prix_livr"),
                    result.getInt("benefice"),
                    result.getDate("date_livr").toLocalDate(),
                    result.getString("note")
            );

            items.add(item);
        }

        result.close();
        statement.close();
        connection.close();

        return items;
    }

    public void insert(StockItem item) throws SQLException {

        Connection connection = Database.connect();

        String sql = "INSERT INTO stock(num_com, date_com, ville, article, pointure, prix_vente, prix_achat, prix_livr, benefice, date_livr, note) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        java.sql.PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, item.getNumeroCommande());
        statement.setDate(2, java.sql.Date.valueOf(item.getDateCommande()));
        statement.setString(3, item.getVille());
        statement.setString(4, item.getArticle());
        statement.setInt(5, item.getPointure());
        statement.setInt(6, item.getPrixVente());
        statement.setInt(7, item.getPrixAchat());
        statement.setInt(8, item.getPrixLivraison());
        statement.setInt(9, item.getBenefice());
        statement.setDate(10, java.sql.Date.valueOf(item.getDateLivraison()));
        statement.setString(11, item.getNote());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void update(StockItem item) throws SQLException {

        Connection connection = Database.connect();

        String sql = "UPDATE stock SET num_com = ?, date_com = ?, ville = ?, article = ?, pointure = ?, "
                + "prix_vente = ?, prix_achat = ?, prix_livr = ?, benefice = ?, date_livr = ?, note = ? "
                + "WHERE id = ?";

        java.sql.PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, item.getNumeroCommande());
        statement.setDate(2, java.sql.Date.valueOf(item.getDateCommande()));
        statement.setString(3, item.getVille());
        statement.setString(4, item.getArticle());
        statement.setInt(5, item.getPointure());
        statement.setInt(6, item.getPrixVente());
        statement.setInt(7, item.getPrixAchat());
        statement.setInt(8, item.getPrixLivraison());
        statement.setInt(9, item.getBenefice());
        statement.setDate(10, java.sql.Date.valueOf(item.getDateLivraison()));
        statement.setString(11, item.getNote());
        statement.setInt(12, item.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }
}
