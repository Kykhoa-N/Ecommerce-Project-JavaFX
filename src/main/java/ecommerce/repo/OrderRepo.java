package ecommerce.repo;

import ecommerce.model.*;
import java.util.*;
import ecommerce.uiHelper.*;

public class OrderRepo {

    // FIELD
    private final List<Order> repo = new ArrayList<>();;

    // DATABASE HELPER METHOD
    private static final String DATABASE_PATH = "src/main/java/ecommerce/database/OrderDatabase.txt";
    public OrderRepo() {
        repo.addAll(FileDBLoader.load(DATABASE_PATH, Order::new));
    }

    // REPO METHOD
    public boolean add(Order order) {
        repo.add(order);
        FileDBWriter.appendLine(DATABASE_PATH, order.toDataString()); // add to database
        return true;
    }

    public Order getOrder(String id) {
        for(Order order: repo) {
            if(order.getId().equals(id)){
                return order;
            }
        }
        return null;
    }

    public List<Order> getHistory(String id) {
        List<Order> history = new ArrayList<>();
        for(Order order: repo) {
            if(order.getUserId().equals(id)){
                history.add(order);
            }
        }
        return history;
    }

    public List<Order> getAll() {
        return new ArrayList<>(repo);
    }
}
