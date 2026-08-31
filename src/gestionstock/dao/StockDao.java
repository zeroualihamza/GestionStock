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
}
