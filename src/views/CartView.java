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
                    cartOptionsMenu();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("invalid option");
            }
        }
    }
    private void cartOptionsMenu() {
        while (true) {
            System.out.println("\n//////////////////////////////////");
            System.out.println("////       Cart Options       ////");
            System.out.println("\n//////////////////////////////////");
            System.out.println("1. add coupon");
            System.out.println("2. finalize purchase");
            System.out.println("3. back to cart menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("enter coupon code: ");
                    String coupon = scanner.nextLine();
                    boolean isCouponValid = cartService.applyCoupon(coupon);
                    if (isCouponValid) {
                        System.out.println("coupon applied!");
                    } else {
                        System.out.println("invalid coupon.");
                    }
                    break;
                case 2:
                    finalizePurchase();
                    return;
                case 3:
                    return;
                default:
                    System.out.println("invalid option");
            }
        }
    }

    private void finalizePurchase() {
        System.out.println("\n----------------- finalizing Purchase -----------------");
        cartService.listItemsInCart();
        double totalPrice = cartService.calculateTotalPrice();
        System.out.printf("total: %.2f\n", totalPrice);
        System.out.println("thank you for your purchase!");
        System.exit(0);
    }
}

