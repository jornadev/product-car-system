package controllers;

import models.Product;
import services.StockService;
import java.util.List;

public class StockController {

    private StockService stockService = new StockService();

    // Adiciona um produto ao estoque
    public void addProductToStock(String name, String category, double price, int quantity) throws Exception {
        Product product = new Product(name, category, price, quantity);
        stockService.addProduct(product);
    }

    // Atualiza a quantidade de um produto no estoque
    public void updateProductQuantity(int id, int newQuantity) throws Exception {
        Product product = new Product();
        product.setId(id);
        product.setQuantity(newQuantity);
        stockService.editProductQuantity(product);
    }

    // Remove um produto do estoque
    public void removeProductFromStock(int id) throws Exception {
        Product product = new Product();
        product.setId(id);
        stockService.removeProduct(product);
    }

    // Calcula o valor total do estoque
    public double calculateTotalStockValue() throws Exception {
        return stockService.calculateTotalCartValue();
    }

    // Lista os produtos no estoque
    public List<Product> getProductsInStock() throws Exception {
        return stockService.getProducts();
    }
}
