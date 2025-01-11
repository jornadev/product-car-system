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
                System.out.println("\nEscolha uma opção:");
                System.out.println("1. Gerenciar carrinho");
                System.out.println("2. Gerenciar estoque");
                System.out.println("3. Sair");

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
                        System.out.println("Saindo...");
                        return;
                    default:
                        System.out.println("Opção inválida!");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
