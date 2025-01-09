package services;

import dao.ProductCartDAO;
import models.Product;
import java.util.List;

public class StockService {

    private ProductCartDAO productCartDAO = new ProductCartDAO();

    public void addProduct(Product product) throws Exception {
        productCartDAO.insertProduct(product);
    }

    public void editProductQuantity(Product product) throws Exception {
        productCartDAO.editProductQuantity(product);
    }

    public void removeProduct(Product product) throws Exception {
        productCartDAO.removeProduct(product);
    }

    public double calculateTotalCartValue() throws Exception {
        return productCartDAO.calculateTotalCartValue();
    }

    public List<Product> getProducts() throws Exception {
        return productCartDAO.getProductsFromCart();
    }
}
