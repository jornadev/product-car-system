package application;

import services.CartService;
import services.StockService;
import views.CartView;
import views.StockView;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/product_cart", "root", "1203");
             Scanner scanner = new Scanner(System.in)) {

            CartService cartService = new CartService(connection);
            StockService stockService = new StockService(connection);

            CartView cartView = new CartView(cartService, stockService, scanner);
            StockView stockView = new StockView(stockService, scanner);

            while (true) {
                System.out.println("/////////////////////////////////");
                System.out.println("///    Cart Product System    ///");
                System.out.println("/////////////////////////////////");
                System.out.println("\nchoice an option: ");
                System.out.println("1. manage cart");
                System.out.println("2. manage stock");
                System.out.println("3. exit");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        cartView.manageCart();
                        break;
                    case 2:
                        stockView.manageStock();
                        break;
                    case 3:
                        System.out.println("exit...");
                        return;
                    default:
                        System.out.println("invalid option");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
