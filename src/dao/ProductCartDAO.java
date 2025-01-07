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
            System.out.println("Erro ao adicionar o produto ao carrinho");
        }

    }

    public void editProductQuantity(Product product) throws Exception {
        String sql = "UPDATE product_cart SET quantity = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
             PreparedStatement stmt = conn.prepareStatement(sql)){
                 stmt.setInt(1, product.getQuantity());
                 stmt.setInt(2, product.getId());
                 stmt.executeUpdate();
                 System.out.println("quantidade atualizada!");
        } catch (SQLException e) {
            System.out.println("erro ao atualiuzar a quantidade do produto");
        }
    }

    public void removeProduct(Product product) throws Exception {
        String sql = "DELETE FROM product_cart WHERE id = ?";

        try (Connection conn = DatabaseConnection.createConnectionToMySQL();
                PreparedStatement stmt = conn.prepareStatement(sql)){
                     stmt.setInt(1, product.getId());
                     stmt.executeUpdate();
                     System.out.println("produto excluido!");
        } catch (SQLException e) {
            System.out.println("erro ao excluir o produto");
        }
    }


}
