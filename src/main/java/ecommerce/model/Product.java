package ecommerce.model;

public class Product {

    // FIELD
    private final String id;
    private final String name;
    private String category;
    private double price;
    private int quantity;

    // CONSTRUCTOR
    public Product(String name, String category, double price, int quantity) {
        this.name = name;
        this.id = category + name;
        this.category = category;
        this.price = price;
        this.quantity = quantity;
    }

    // DATABASE HELPER METHOD
    public Product(String csvLine) {
        String[] parts = csvLine.split(",");
        this.name = parts[0];
        this.id = parts[1];
        this.category = parts[2];
        this.price = Double.parseDouble(parts[3]);
        this.quantity = Integer.parseInt(parts[4]);
    }
    public String toDataString() {
        return name + "," + id + "," + category  + "," + price   + "," + quantity ;
    }

    // GETTER METHOD
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    // SETTER METHOD
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
