package controllers;

import models.Product;
import services.ProductCartService;

import java.util.List;

public class ProductCartController {

    private ProductCartService productCartService = new ProductCartService();

    public void addProductToCart(String name, String category, double price, int quantity) throws Exception {
        Product product = new Product(name, category, price, quantity);
        productCartService.addProductToCart(product);
    }

    public void updateProductQuantity(int id, int newQuantity) throws Exception {
        productCartService.updateProductQuantity(id, newQuantity);
    }

    public void removeProductFromCart(int id) throws Exception {
        productCartService.removeProductFromCart(id);
    }

    public double calculateTotalCartValue() throws Exception {
        return productCartService.calculateTotalCartValue();
    }

    public List<Product> getProductsInCart() throws Exception {
        return productCartService.getProducts();
    }
}
