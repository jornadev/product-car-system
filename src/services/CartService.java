package services;

import dao.CartDAO;
import models.CartItem;
import models.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartService {

    private CartDAO cartDAO;
    private StockService stockService;

    public CartService(Connection connection) {
        this.cartDAO = new CartDAO(connection);
        this.stockService = new StockService(connection);
    }

    public void addItemToCart(CartItem item) {
        try {
            Product product = stockService.getProductById(item.getProductId());

            if (product == null) {
                System.out.println("product not found");
                return;
            }

            if (item.getQuantity() > product.getQuantity()) {
                System.out.println("quantity is greater than product quantity.");
                return;
            }

            cartDAO.addItemToCart(item);
            stockService.updateProductQuantity(product.getId(), product.getQuantity() - item.getQuantity());

        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void removeItemFromCart(int productId) {
        try {
            cartDAO.removeItemFromCart(productId);
            System.out.println("item removed from the cart!");
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void listItemsInCart() {
        try {
            List<CartItem> items = cartDAO.getAllItemsInCart();
            if (items.isEmpty()) {
                System.out.println("======================================");
                System.out.println("           Cart is empty");
                System.out.println("======================================");
            } else {
                double total = 0;

                System.out.println("=============================================================");
                System.out.printf("%-10s %-20s %-10s %-15s %-15s%n", "ID", "Product", "Qtt.", "Price", "Total");
                System.out.println("=============================================================");

                for (CartItem item : items) {
                    System.out.printf(
                            "%-10d %-20s %-10d R$ %-12.2f R$ %-12.2f%n",
                            item.getProductId(),
                            item.getProductName(),
                            item.getQuantity(),
                            item.getProductPrice(),
                            item.getTotalPrice()
                    );
                    total += item.getTotalPrice();
                }

                System.out.println("==============================================================");
                System.out.printf("%-10s %-20s %-10s %-15s $ %-12.2f%n", "", "Total on Cart", "", "", total);
                System.out.println("==============================================================");
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void clearCart() {
        try {
            cartDAO.clearCart();
            System.out.println("cart clear!");
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }

    public void updateItemQuantity(int productId, int newQuantity) {
        try {
            if (newQuantity <= 0) {
                System.out.println("the new quantity is less than zero!.");
                return;
            }

            CartItem existingItem = cartDAO.getItemById(productId);
            if (existingItem == null) {
                System.out.println("product not found.");
            } else {
                cartDAO.updateItemQuantityInCart(productId, newQuantity);
                System.out.println("quantity of product updated to: " + newQuantity + ".");
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
    }
    public boolean applyCoupon(String coupon) {
        try {
            if (coupon.equals("discount10")) {
                double discount = 0.10;
                List<CartItem> items = cartDAO.getAllItemsInCart();

                for (CartItem item : items) {
                    double newPrice = item.getProductPrice() * (1 - discount);
                    item.setProductPrice(newPrice);
                    cartDAO.updateItemPriceInCart(item.getProductId(), newPrice);
                }
                return true;
            }
        } catch (SQLException e) {
            System.out.println("error applying coupon: " + e.getMessage());
        }
        return false;
    }
    public double calculateTotalPrice() {
        double total = 0;
        try {
            List<CartItem> items = cartDAO.getAllItemsInCart();
            for (CartItem item : items) {
                total += item.getProductPrice() * item.getQuantity();
            }
        } catch (SQLException e) {
            System.out.println("error: " + e.getMessage());
        }
        return total;
    }



}


