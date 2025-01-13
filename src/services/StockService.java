package services;

import dao.StockDAO;
import models.Product;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class StockService {

    private StockDAO stockDAO;

    public StockService(Connection connection) {
        this.stockDAO = new StockDAO(connection);
    }

    public void addProductToStock(Product product) {
        try {
            stockDAO.addProduct(product);
            System.out.println("product added to stock!");
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void removeProductFromStock(int productId) {
        try {
            stockDAO.removeProduct(productId);
            System.out.println("product removed from stock!");
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void updateProductQuantity(int id, int newQuantity) {
        try {
            stockDAO.updateProductQuantity(id, newQuantity);
            System.out.println("quantity updated!");
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void listAllProducts() {

        try {
            List<Product> products = stockDAO.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("======================================");
                System.out.println("          Empty Stock");
                System.out.println("======================================");
            } else {
                System.out.println("======================================");
                System.out.println("         Products on Stock");
                System.out.println("======================================");
                for (Product product : products) {
                    System.out.println("ID: " + product.getId());
                    System.out.println("name: " + product.getName());
                    System.out.println("category: " + product.getCategory());
                    System.out.println("price: $ " + String.format("%.2f", product.getPrice()));
                    System.out.println("quantity: " + product.getQuantity());
                    System.out.println("--------------------------------------");
                }
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public Product getProductById(int productId) {
        try {
            Product product = stockDAO.getProductById(productId);
            if (product == null) {
                System.out.println("product with ID " + productId + " not found.");
            } else {
                System.out.println("ID: " + product.getId() + ", name: " + product.getName() +
                        ", category: " + product.getCategory() + ", price: " + product.getPrice() +
                        ", quantity: " + product.getQuantity());
            }
            return product;
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return null;
    }
}
