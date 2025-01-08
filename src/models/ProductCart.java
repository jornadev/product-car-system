package models;

public class ProductCart {
    private Product product;
    private int quantity;

    public ProductCart(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    public ProductCart(){

    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public double totalPrice(){
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return "ProductCart "
                + "product="
                + product.getName()
                + ", quantity="
                + quantity
                + ", totalPrice="
                + totalPrice();
    }
}




