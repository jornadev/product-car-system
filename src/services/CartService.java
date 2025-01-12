package services;

import dao.CartDAO;
import models.CartItem;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class CartService {

    private CartDAO cartDAO;

    public CartService(Connection connection) {
        this.cartDAO = new CartDAO(connection);
    }

    public void addItemToCart(CartItem item) {
        try {
            cartDAO.addItemToCart(item);
            System.out.println("Item adicionado ao carrinho!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar item ao carrinho: " + e.getMessage());
        }
    }

    public void removeItemFromCart(int productId) {
        try {
            cartDAO.removeItemFromCart(productId);
            System.out.println("Item removido do carrinho!");
        } catch (SQLException e) {
            System.out.println("Erro ao remover item do carrinho: " + e.getMessage());
        }
    }

    public void listItemsInCart() {
        try {
            List<CartItem> items = cartDAO.getAllItemsInCart();
            if (items.isEmpty()) {
                System.out.println("======================================");
                System.out.println("        Cart is empty");
                System.out.println("======================================");
            } else {
                double total = 0;

                System.out.println("=========================================================");
                System.out.printf("%-20s %-10s %-15s %-15s%n", "Product", "Qtt.", "Price", "Total");
                System.out.println("=========================================================");

                for (CartItem item : items) {
                    System.out.printf(
                            "%-20s %-10d R$ %-12.2f R$ %-12.2f%n",
                            item.getProductName(),
                            item.getQuantity(),
                            item.getProductPrice(),
                            item.getTotalPrice()
                    );
                    total += item.getTotalPrice();
                }

                System.out.println("==============================================================");
                System.out.printf("%-20s %-10s %-15s R$ %-12.2f%n", "Total on Cart", "", "", total);
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
}


