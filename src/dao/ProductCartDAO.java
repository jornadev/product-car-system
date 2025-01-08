package dao;

import db.DatabaseConnection;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public double calculateTotalCartValue() throws Exception {
        String sql = "SELECT price, quantity FROM product_cart";
        double totalValue = 0.0;

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                totalValue += price * quantity;
            }

            System.out.println("total cart value: " + totalValue);

        } catch (SQLException e) {
            System.out.println("error calculating total cart value" );
        }

        return totalValue;
    }

    public List<Product> getProductsFromCart() throws Exception {
        String sql = "SELECT * FROM product_cart";
        List<Product> products = new ArrayList<>();

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");

                Product product = new Product(id, name, category, price, quantity);
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("error retrieving products from cart");
        }

        return products;
    }

}



