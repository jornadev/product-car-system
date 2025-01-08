package services;

import dao.StockDAO;
import models.Product;
import java.util.List;

public class StockService {

    private StockDAO stockDAO = new StockDAO();

    public void addProductToStock(Product product) throws Exception {
        stockDAO.insertProductOnStock(product);
    }

    public void updateStockQuantity(int id, int newQuantity) throws Exception {
        stockDAO.updateQuantity(id, newQuantity);
    }

    public void removeProductFromStock(int id) throws Exception {
        stockDAO.removeProductOnStock(id);
    }

    public double calculateTotalStockValue() throws Exception {
        List<Product> products = stockDAO.listProductsOnStock();
        double totalValue = 0.0;
        for (Product product : products) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }
}
