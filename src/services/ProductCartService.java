package services;

import dao.ProductCartDAO;
import models.Product;

import java.util.List;

public class ProductCartService {

    private ProductCartDAO productCartDAO = new ProductCartDAO();

    public void addProductToCart(Product product) throws Exception {
        productCartDAO.insertProduct(product);
    }

    public void updateProductQuantity(int id, int newQuantity) throws Exception {
        Product product = new Product();
        product.setId(id);
        product.setQuantity(newQuantity);
        productCartDAO.editProductQuantity(product);
    }

    public void removeProductFromCart(int id) throws Exception {
        Product product = new Product();
        product.setId(id);
        productCartDAO.removeProduct(product);
    }

    public double calculateTotalCartValue() throws Exception {
        return productCartDAO.calculateTotalCartValue();
    }
}
