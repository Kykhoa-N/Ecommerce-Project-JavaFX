package ecommerce.model;

import java.util.*;
import java.time.*;

public class Order {

    // FIELD
    private final String id;
    private final String user_id;
    private final Map<String, Integer> product_list;
    private final double total_price;
    private final LocalDateTime timestamp;
    private OrderStatus status;

    // CONSTRUCTOR
    public Order(int id_number,String user_id, Map<String, Integer> product_list, double total_price) {
        this.id = id_number + user_id;
        this.user_id = user_id;
        this.total_price = total_price;
        this.timestamp = LocalDateTime.now();
        this.status = OrderStatus.PROCESSED;
        this.product_list = product_list;
    }

    // DATABASE HELPER METHOD
    public Order(String csvLine) {
        String[] parts = csvLine.split(",");

        this.id     = parts[0];
        this.user_id = parts[1];
        this.total_price  = Double.parseDouble(parts[2]);
        this.timestamp = LocalDateTime.parse(parts[3]);
        this.status = OrderStatus.valueOf(parts[4]);

        this.product_list = new HashMap<>();

        if (parts.length > 5 && !parts[5].isBlank()) {
            String[] items = parts[5].split("\\|");
            for (String item : items) {
                String[] pair = item.split(":");
                this.product_list.put(pair[0], Integer.parseInt(pair[1]));
            }
        }
    }

    public String toDataString() {
        StringBuilder sb = new StringBuilder();

        sb.append(id).append(",");
        sb.append(user_id).append(",");
        sb.append(total_price).append(",");
        sb.append(timestamp).append(",");
        sb.append(status).append(",");

        // product list (last column)
        if (!product_list.isEmpty()) {
            for (Map.Entry<String, Integer> entry : product_list.entrySet()) {
                sb.append(entry.getKey())
                        .append(":")
                        .append(entry.getValue())
                        .append("|");
            }
            sb.deleteCharAt(sb.length() - 1); // remove trailing |
        }

        return sb.toString();
    }

    // GETTER METHOD
    public String getId() {
        return id;
    }

    public  String getUserId() {
        return user_id;
    }

    public Map<String, Integer> getProductList() {
        return product_list;
    }

    public double getTotalPrice() {
        return total_price;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public OrderStatus getStatus() {
        return status;
    }

    // SETTER METHOD
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
