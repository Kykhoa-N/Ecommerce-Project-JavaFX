package ecommerce.model;

public class CartItem {

    // FIELD
    private final String product;
    private final int quantity;

    // CONSTRUCTOR
    public CartItem(String product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    // GETTER METHOD
    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}
