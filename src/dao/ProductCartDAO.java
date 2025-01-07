package dao;

import db.DatabaseConnection;
import models.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class ProductCartDAO {

    public void save(Product product) throws Exception {
        String sql = "INSERT INTO product_cart(name, category, price, quantity) VALUES (?, ?, ?, ?)";

        Connection conn = null;
        PreparedStatement pstm = null;

        try{
            conn = DatabaseConnection.createConnectionToMySQL();

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, product.getName());
            pstm.setString(2, product.getCategory());
            pstm.setDouble(3, product.getPrice());
            pstm.setInt(4, product.getQuantity());

            pstm.execute();
            System.out.println("Salvo com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally{

        }
    }

}
