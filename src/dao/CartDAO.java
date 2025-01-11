package dao;

import models.CartItem;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CartDAO {

    private Connection connection;

    public CartDAO(Connection connection) {
        this.connection = connection;
    }

    public void addItemToCart(CartItem item) throws SQLException {
        String query = "INSERT INTO product_cart (id, name, price, quantity) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, item.getProductId());
            stmt.setString(2, item.getProductName());
            stmt.setDouble(3, item.getProductPrice());
            stmt.setInt(4, item.getQuantity());
            stmt.executeUpdate();
        }
    }

    public void removeItemFromCart(int productId) throws SQLException {
        String query = "DELETE FROM product_cart WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        }
    }

    public List<CartItem> getAllItemsInCart() throws SQLException {
        List<CartItem> cartItems = new ArrayList<>();
        String query = "SELECT * FROM product_cart";
        try (Statement stmt = connection.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int productId = rs.getInt("id");
                String productName = rs.getString("name");
                double productPrice = rs.getDouble("price");
                int quantity = rs.getInt("quantity");
                cartItems.add(new CartItem(productId, productName, productPrice, quantity));
            }
        }
        return cartItems;
    }

    public void clearCart() throws SQLException {
        String query = "DELETE FROM product_cart";
        try (Statement stmt = connection.createStatement()) {
            stmt.executeUpdate(query);
        }
    }

    public void updateItemQuantityInCart(int productId, int newQuantity) throws SQLException {
        String query = "UPDATE product_cart SET quantity = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, productId);
            stmt.executeUpdate();
        }
    }

    public CartItem getItemById(int productId) throws SQLException {
        String query = "SELECT * FROM product_cart WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, productId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("name");
                    double productPrice = rs.getDouble("price");
                    int quantity = rs.getInt("quantity");
                    return new CartItem(productId, productName, productPrice, quantity);
                }
            }
        }
        return null;
    }


}
