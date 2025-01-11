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
            System.out.println("\nGerenciamento de Carrinho:");
            System.out.println("1. Adicionar item ao carrinho");
            System.out.println("2. Remover item do carrinho");
            System.out.println("3. Alterar quantidade de produto no carrinho");
            System.out.println("4. Mostrar itens no carrinho");
            System.out.println("5. Voltar");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    stockService.listAllProducts();
                    System.out.print("Informe o ID do produto que você quer adicionar ao carrinho: ");
                    int productId = scanner.nextInt();
                    System.out.print("Informe a quantidade: ");
                    int quantity = scanner.nextInt();
                    scanner.nextLine();

                    Product product = stockService.getProductById(productId);
                    if (product != null) {
                        CartItem item = new CartItem(product.getId(), product.getName(), product.getPrice(), quantity);
                        cartService.addItemToCart(item);
                        System.out.println("Produto adicionado ao carrinho!");
                    } else {
                        System.out.println("Produto não encontrado!");
                    }
                    break;
                case 2:
                    System.out.print("Informe o ID do produto para remover: ");
                    int productIdToRemove = scanner.nextInt();
                    cartService.removeItemFromCart(productIdToRemove);
                    break;
                case 3:
                    System.out.print("Informe o ID do produto para alterar a quantidade: ");
                    int productIdToUpdate = scanner.nextInt();
                    System.out.print("Informe a nova quantidade: ");
                    int newQuantity = scanner.nextInt();
                    cartService.updateItemQuantity(productIdToUpdate, newQuantity);
                    break;
                case 4:
                    cartService.listItemsInCart();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}
