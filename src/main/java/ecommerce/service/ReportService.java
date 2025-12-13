package ecommerce.service;

import ecommerce.model.*;
import ecommerce.repo.*;
import java.util.*;

public class ReportService {

    // FIELD
    private final OrderRepo orderRepo;
    private final ProductRepo productRepo;

    // CONSTRUCTOR
    public ReportService(OrderRepo orderRepo, ProductRepo productRepo) {
        this.orderRepo = orderRepo;
        this.productRepo = productRepo;
    }

    // VIEW EMPTY STOCKS
    public List<Product> viewEmpty(User user) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return null;

        // filter only empty stocks
        return productRepo.getAll().stream()
                .filter(product -> product.getQuantity() <= 0)
                .toList();
    }

    // GET TOTAL ORDERS
    public int totalOrder(User user) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return -1;

        // count orders place
        return orderRepo.getAll().size();
    }

    // GET FREQUENT ORDER
    public Product getFrequent(User user) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return null;

        // local field
        List<Order> catalog = orderRepo.getAll();
        Cart cart = new Cart(user.getId());

        if(catalog.isEmpty()) return null;

        // count all orders product into cart
        for(Order order: catalog) {
            for(Map.Entry<String, Integer> item: order.getProductList().entrySet()) {
                cart.add(item.getKey(), item.getValue());
            }
        }

        // grab product with the largest quantity
        String frequent = cart.getProductList().entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse(null);
        return productRepo.getProduct(frequent);
    }

    // CALCULATE REVENUE
    public Double totalRevenue(User user) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return -1.0;

        // sum all the orders total price
        return orderRepo.getAll().stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }
}
