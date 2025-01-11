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
            System.out.println("Produto adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar produto: " + e.getMessage());
        }
    }

    public void removeProductFromStock(int productId) {
        try {
            stockDAO.removeProduct(productId);
            System.out.println("Produto removido com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover produto: " + e.getMessage());
        }
    }

    public void updateProductQuantity(int id, int newQuantity) {
        try {
            stockDAO.updateProductQuantity(id, newQuantity);
            System.out.println("Quantidade de produto atualizada!");
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade: " + e.getMessage());
        }
    }

    public void listAllProducts() {
        try {
            List<Product> products = stockDAO.getAllProducts();
            if (products.isEmpty()) {
                System.out.println("Nenhum produto encontrado no estoque.");
            } else {
                for (Product product : products) {
                    System.out.println("ID: " + product.getId() + ", Nome: " + product.getName() +
                            ", Categoria: " + product.getCategory() + ", Preço: " + product.getPrice() +
                            ", Quantidade: " + product.getQuantity());
                }
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar produtos: " + e.getMessage());
        }
    }

    public Product getProductById(int productId) {
        try {
            Product product = stockDAO.getProductById(productId);
            if (product == null) {
                System.out.println("Produto com ID " + productId + " não encontrado.");
            } else {
                System.out.println("ID: " + product.getId() + ", Nome: " + product.getName() +
                        ", Categoria: " + product.getCategory() + ", Preço: " + product.getPrice() +
                        ", Quantidade: " + product.getQuantity());
            }
            return product;
        } catch (SQLException e) {
            System.out.println("Erro ao buscar produto: " + e.getMessage());
        }
        return null;
    }
}
