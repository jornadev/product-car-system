package dao;

import db.DatabaseConnection;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void updateQuantity(int id, int newQuantity) throws Exception {
        String sql = "UPDATE stock SET quantity = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, newQuantity);
            stmt.setInt(2, id);

            stmt.executeUpdate();
            System.out.println("uptade quantity!");

        } catch (SQLException e) {
            System.out.println("error updating quantity");
        }
    }

    public void removeProductOnStock(int id) throws Exception {
        String sql = "DELETE FROM stock WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, id);
            stmt.executeUpdate();
            System.out.println("product removed");
        } catch (SQLException e) {
            System.out.println("error removing product");
        }
    }

    public List<Product> listProductsOnStock() throws Exception {
        String sql = "SELECT * FROM stock";
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

                Product product = new Product(name, category, price, quantity);
                products.add(product);
            }

        } catch (SQLException e) {
            System.out.println("Error listing products");
            e.printStackTrace();
        }

        return products;
    }



}
