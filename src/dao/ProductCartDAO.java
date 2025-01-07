package dao;

import db.DatabaseConnection;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProductCartDAO {

    public void insertProduct(Product product) throws Exception {
        String sql = "INSERT INTO product_cart(name, category, price, quantity) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());

            stmt.executeUpdate();
            System.out.println("produto adicionado ao carrinho!");

        } catch (SQLException e) {
            System.out.println("Erro ao adicionar o produto ao carrinho: " + e.getMessage());
        }

    }

    

}
