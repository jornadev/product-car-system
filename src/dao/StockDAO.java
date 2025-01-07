package dao;

import db.DatabaseConnection;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StockDAO {
    public void insertProductOnStock(Product product) throws Exception {
        String sql = "INSERT INTO stock (name, category, price, quantity) VALUES (?, ?, ?, ?)";

        try(Connection conn = DatabaseConnection.createConnectionToMySQL();
        PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.executeUpdate();
            System.out.println("stock inserted");
        } catch (SQLException e){
            System.out.println("error inserting stock");
        }
    }

    public void updateQuantity(int produtoId, int novaQuantidade) throws Exception {
        String sql = "UPDATE product_stock SET quantity = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, novaQuantidade);
            stmt.setInt(2, produtoId);

            stmt.executeUpdate();
            System.out.println("uptade quantity!");

        } catch (SQLException e) {
            System.out.println("error updating quantity");
        }
    }

    
}
