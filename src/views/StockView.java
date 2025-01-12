package views;

import models.Product;
import services.StockService;

import java.util.Scanner;

public class StockView {
    private final StockService stockService;
    private final Scanner scanner;

    public StockView(StockService stockService, Scanner scanner) {
        this.stockService = stockService;
        this.scanner = scanner;
    }

    public void manageStock() {
        while (true) {
            System.out.println("//////////////////////////////////");
            System.out.println("////       Stock Manage       ////");
            System.out.println("//////////////////////////////////");
            System.out.println("\n1. add product to stock");
            System.out.println("2. remove product from stock");
            System.out.println("3. change the informations product in stock");
            System.out.println("4. back to main menu");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("product name: ");
                    String name = scanner.nextLine();
                    System.out.print("category: ");
                    String category = scanner.nextLine();
                    System.out.print("price: ");
                    double price = scanner.nextDouble();
                    System.out.print("quantity: ");
                    int quantity = scanner.nextInt();

                    Product product = new Product(name, category, price, quantity);
                    stockService.addProductToStock(product);
                    System.out.println("product added to stock!");
                    break;
                case 2:
                    System.out.print("ID of the product to remove: ");
                    int productId = scanner.nextInt();
                    stockService.removeProductFromStock(productId);
                    break;
                case 3:
                    System.out.print("ID of the product to update: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("new quantity: ");
                    int newQuantity = scanner.nextInt();
                    stockService.updateProductQuantity(id, newQuantity);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("invalid option");
            }
        }
    }
}
