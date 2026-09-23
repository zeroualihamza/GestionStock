package gestionstock.dao;

import gestionstock.config.Database;
import gestionstock.model.SupplierOperation;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SupplierDao {

    public ArrayList<SupplierOperation> findAll() throws SQLException {

        ArrayList<SupplierOperation> operations = new ArrayList<>();

        Connection connection = Database.connect();

        String sql = "SELECT * FROM fournisseur ORDER BY id_four DESC";

        PreparedStatement statement = connection.prepareStatement(sql);

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            SupplierOperation operation = new SupplierOperation(
                    result.getInt("id_four"),
                    result.getDate("date").toLocalDate(),
                    result.getString("libelle"),
                    result.getString("article"),
                    result.getInt("debit"),
                    result.getInt("credit"),
                    result.getInt("solde")
            );

            operations.add(operation);
        }

        result.close();
        statement.close();
        connection.close();

        return operations;
    }

    public void insert(SupplierOperation operation) throws SQLException {

        Connection connection = Database.connect();

        String sql = "INSERT INTO fournisseur(date, libelle, article, debit, credit, solde) "
                + "VALUES (?, ?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDate(1, Date.valueOf(operation.getDate()));
        statement.setString(2, operation.getLibelle());
        statement.setString(3, operation.getArticle());
        statement.setInt(4, operation.getDebit());
        statement.setInt(5, operation.getCredit());
        statement.setInt(6, operation.getSolde());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void update(SupplierOperation operation) throws SQLException {

        Connection connection = Database.connect();

        String sql = "UPDATE fournisseur SET date = ?, libelle = ?, article = ?, debit = ?, credit = ?, solde = ? "
                + "WHERE id_four = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setDate(1, Date.valueOf(operation.getDate()));
        statement.setString(2, operation.getLibelle());
        statement.setString(3, operation.getArticle());
        statement.setInt(4, operation.getDebit());
        statement.setInt(5, operation.getCredit());
        statement.setInt(6, operation.getSolde());
        statement.setInt(7, operation.getId());

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public void delete(int id) throws SQLException {

        Connection connection = Database.connect();

        String sql = "DELETE FROM fournisseur WHERE id_four = ?";

        PreparedStatement statement = connection.prepareStatement(sql);

        statement.setInt(1, id);

        statement.executeUpdate();

        statement.close();
        connection.close();
    }

    public ArrayList<SupplierOperation> search(String libelle, String date) throws SQLException {

        ArrayList<SupplierOperation> operations = new ArrayList<>();

        Connection connection = Database.connect();

        String sql = "SELECT * FROM fournisseur WHERE 1=1";

        ArrayList<Object> params = new ArrayList<>();

        if (!libelle.isEmpty()) {
            sql = sql + " AND libelle LIKE ?";
            params.add("%" + libelle + "%");
        }

        if (!date.isEmpty()) {
            sql = sql + " AND date = ?";
            params.add(Date.valueOf(date));
        }

        sql = sql + " ORDER BY id_four DESC";

        PreparedStatement statement = connection.prepareStatement(sql);

        for (int i = 0; i < params.size(); i++) {
            statement.setObject(i + 1, params.get(i));
        }

        ResultSet result = statement.executeQuery();

        while (result.next()) {
            SupplierOperation operation = new SupplierOperation(
                    result.getInt("id_four"),
                    result.getDate("date").toLocalDate(),
                    result.getString("libelle"),
                    result.getString("article"),
                    result.getInt("debit"),
                    result.getInt("credit"),
                    result.getInt("solde")
            );

            operations.add(operation);
        }

        result.close();
        statement.close();
        connection.close();

        return operations;
    }
}