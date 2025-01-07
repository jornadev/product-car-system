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
            System.out.println("product added successfully");

        } catch (SQLException e) {
            System.out.println("error inserting product");
        }

    }

    public void editProductQuantity(Product product) throws Exception {
        String sql = "UPDATE product_cart SET quantity = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)){
                 stmt.setInt(1, product.getQuantity());
                 stmt.setInt(2, product.getId());
                 stmt.executeUpdate();
                 System.out.println("quantity updated!");
        } catch (SQLException e) {
            System.out.println("error updating quantity");
        }
    }

    public void removeProduct(Product product) throws Exception {
        String sql = "DELETE FROM product_cart WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                     stmt.setInt(1, product.getId());
                     stmt.executeUpdate();
                     System.out.println("product deleted!");
        } catch (SQLException e) {
            System.out.println("error deleting product");
        }
    }

    public void totalValue()  {
        String sql = "SELECT SUM(price * quantity) AS total FROM product_cart";
        double total = 0;

    }


}
