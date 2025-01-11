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
            System.out.println("\nGerenciamento de Estoque:");
            System.out.println("1. Adicionar produto ao estoque");
            System.out.println("2. Remover produto do estoque");
            System.out.println("3. Alterar informações de produto");
            System.out.println("4. Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Informe o nome do produto: ");
                    String name = scanner.nextLine();
                    System.out.print("Informe a categoria do produto: ");
                    String category = scanner.nextLine();
                    System.out.print("Informe o preço do produto: ");
                    double price = scanner.nextDouble();
                    System.out.print("Informe a quantidade: ");
                    int quantity = scanner.nextInt();

                    Product product = new Product(name, category, price, quantity);
                    stockService.addProductToStock(product);
                    System.out.println("Produto adicionado ao estoque!");
                    break;
                case 2:
                    System.out.print("Informe o ID do produto para remover: ");
                    int productId = scanner.nextInt();
                    stockService.removeProductFromStock(productId);
                    break;
                case 3:
                    System.out.print("Informe o ID do produto para alterar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Informe a nova quantidade: ");
                    int newQuantity = scanner.nextInt();
                    stockService.updateProductQuantity(id, newQuantity);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
