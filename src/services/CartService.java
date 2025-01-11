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
                System.out.println("O carrinho está vazio.");
            } else {
                double total = 0;
                for (CartItem item : items) {
                    System.out.println("Produto: " + item.getProductName() + ", Quantidade: " + item.getQuantity() +
                            ", Preço Unitário: " + item.getProductPrice() + ", Total: " + item.getTotalPrice());
                    total += item.getTotalPrice();
                }
                System.out.println("Total do Carrinho: " + total);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar itens do carrinho: " + e.getMessage());
        }
    }

    public void clearCart() {
        try {
            cartDAO.clearCart();
            System.out.println("Carrinho limpo!");
        } catch (SQLException e) {
            System.out.println("Erro ao limpar o carrinho: " + e.getMessage());
        }
    }

    public void updateItemQuantity(int productId, int newQuantity) {
        try {
            if (newQuantity <= 0) {
                System.out.println("A quantidade deve ser maior que 0.");
                return;
            }

            CartItem existingItem = cartDAO.getItemById(productId);
            if (existingItem == null) {
                System.out.println("Produto não encontrado no carrinho.");
            } else {
                cartDAO.updateItemQuantityInCart(productId, newQuantity);
                System.out.println("Quantidade do produto no carrinho foi atualizada para " + newQuantity + ".");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar quantidade do item: " + e.getMessage());
        }
    }
}


