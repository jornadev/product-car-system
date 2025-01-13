package views;

import models.CartItem;
import models.Product;
import services.CartService;
import services.StockService;

import java.util.Scanner;

public class CartView {
    private final CartService cartService;
    private final StockService stockService;
    private final Scanner scanner;

    public CartView(CartService cartService, StockService stockService, Scanner scanner) {
        this.cartService = cartService;
        this.stockService = stockService;
        this.scanner = scanner;
    }

    public void manageCart() {
        while (true) {
            System.out.println("\n///////////////////////////////////");
            System.out.println("////        Cart Manage        ////");
            System.out.println("///////////////////////////////////");
            System.out.println("\n1. add item to the cart");
            System.out.println("2. remove item from the cart");
            System.out.println("3. update quantity item from the cart");
            System.out.println("4. show the itens of the cart");
            System.out.println("5. back to the main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    stockService.listAllProducts();
                    System.out.print("ID of the product to be added: ");
                    int productId = scanner.nextInt();
                    System.out.print("quantity: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Product product = stockService.getProductById(productId);
                    if (product != null) {
                        CartItem item = new CartItem(product.getId(), product.getName(), product.getPrice(), quantity);
                        cartService.addItemToCart(item);
                        System.out.println("product added to the cart");
                    } else {
                        System.out.println("product not found");
                    }
                    break;
                case 2:
                    cartService.listItemsInCart();
                    System.out.print("ID of the product to remove: ");
                    int productIdToRemove = scanner.nextInt();
                    cartService.removeItemFromCart(productIdToRemove);
                    break;
                case 3:
                    System.out.print("ID of product to update: ");
                    int productIdToUpdate = scanner.nextInt();
                    System.out.print("new quantity: ");
                    int newQuantity = scanner.nextInt();
                    cartService.updateItemQuantity(productIdToUpdate, newQuantity);
                    break;
                case 4:
                    cartService.listItemsInCart();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("invalid option");
            }
        }
    }
}
