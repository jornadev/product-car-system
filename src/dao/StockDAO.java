package dao;

import models.Product;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockDAO {

    private Connection connection;

    public StockDAO(Connection connection) {
        this.connection = connection;
    }

    public void addProduct(Product product) throws SQLException {
        String query = "INSERT INTO stock (name, category, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getCategory());
            stmt.setDouble(3, product.getPrice());
            stmt.setInt(4, product.getQuantity());
            stmt.executeUpdate();
        }
    }

    public List<Product> getAllProducts() throws SQLException {
        List<Product> products = new ArrayList<>();
        String query = "SELECT * FROM stock";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String category = rs.getString("category");
                double price = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                products.add(new Product(id, name, category, price, quantity));
            }
        }
        return products;
    }

    public Product getProductById(int productId) throws SQLException {
        String query = "SELECT * FROM stock WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    String category = rs.getString("category");
                    double price = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    return new Product(id, name, category, price, quantity);
                }
            }
        }
        return null;
    }

    public void removeProduct(int productId) throws SQLException {
        String query = "DELETE FROM stock WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    public void updateProductQuantity(int productId, int quantity) throws SQLException {
        String query = "UPDATE stock SET quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, quantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }
}
