package ecommerce.model;

import java.util.*;


public class Cart {

    // FIELD
    private final String user_id;
    private final Map<String, Integer> product_list;

    // CONSTRUCTOR
    public Cart(String user_id) {
        this.user_id = user_id;
        this.product_list = new HashMap<>();
    }

    // DATABASE HELPER METHOD
    public static Cart fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        Cart cart = new Cart(parts[0]);
        if (parts.length < 2 || parts[1].isBlank()) {
            return cart;
        }
        String[] items = parts[1].split("\\|");
        for (String item : items) {
            String[] pair = item.split(":");
            cart.product_list.put(
                    pair[0],
                    Integer.parseInt(pair[1])
            );
        }
        return cart;
    }

    public String toDataString() {
        StringBuilder sb = new StringBuilder();

        sb.append(user_id);
        sb.append(",");

        if (!product_list.isEmpty()) {
            product_list.forEach((product, quantity) -> {
                sb.append(product)
                        .append(":")
                        .append(quantity)
                        .append("|");
            });

            // remove trailing '|'
            sb.setLength(sb.length() - 1);
        }

        return sb.toString();
    }

    // GETTER METHOD
    public String getUserId() {
        return user_id;
    }

    public Map<String, Integer> getProductList() {
        return product_list;
    }

    // UTILITY METHOD
    public boolean add(String product, int quantity) {
        product_list.put(product, product_list.getOrDefault(product, 0) + quantity);
        return true;
    }

    public boolean remove(String product, int quantity) {
        if(product_list.get(product) == null) {
            return false;
        }
        else {
            int difference = product_list.get(product) - quantity;
            if(difference > 0) {
                product_list.put(product, difference);
            } else {
                product_list.remove(product);
            }
            return true;
        }
    }

}
