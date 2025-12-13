package ecommerce.service;

import ecommerce.model.*;
import ecommerce.repo.*;
import java.util.*;

public class ProductService {

    // FIELD
    private final ProductRepo productRepo;
    private final OrderRepo orderRepo;
    private final CartRepo cartRepo;

    // CONSTANT
    private static final Comparator<Product> SORT_BY_NAME = Comparator.comparing(Product::getName);
    private static final Comparator<Product> SORT_BY_PRICE = Comparator.comparing(Product::getPrice);
    private static final Comparator<Product> SORT_BY_CATEGORY = Comparator.comparing(Product::getCategory);

    // CONSTRUCTOR
    public ProductService(ProductRepo productRepo, OrderRepo orderRepo, CartRepo cartRepo) {
        this.productRepo = productRepo;
        this.orderRepo = orderRepo;
        this.cartRepo = cartRepo;
    }

    // ADD PRODUCT
    public boolean add(User user, String name, String category, double price, int quantity) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return false;

        // doesn't create product if wrong parameters or product already existed
        if (name == null || category == null || price < 0 || quantity < 0 || !(productRepo.getProduct(name) == null)) {
            return false;
        }

        // add new product to store
        return productRepo.add(new Product(name, category, price, quantity));
    }

    // UPDATE PRODUCT
    public boolean update(User user, String name, String new_category, Double new_price, Integer new_quantity) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return false;

        // local field
        Product product = productRepo.getProduct(name);

        // doesn't update unavailable product
        if (product == null) return false;

        // update chosen information
        if (new_category != null) product.setCategory(new_category);
        if (new_price != null && new_price >= 0) product.setPrice(new_price);
        if (new_quantity != null && new_quantity >= 0) product.setQuantity(new_quantity);
        return true;
    }

    // REMOVE PRODUCT
    public boolean remove(User user, String name) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return false;

        // remove product from store
        return productRepo.remove(name);
    }

    // SEARCH FOR PRODUCT
    public Product search(User user, String name) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return null;

        // search product by string text
        return productRepo.getProduct(name);
    }

    // SORT PRICE
    public List<Product> sortPrice(User user) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return null;

        // sort catalog by price
        List<Product> catalog = productRepo.getAll();
        catalog.sort(SORT_BY_PRICE);
        return catalog;
    }

    // VIEW PRODUCT CATALOG
    public List<Product> viewAll(User user, int view) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return null;

        // local field
        List<Product> catalog = productRepo.getAll();

        // choose view
        switch(view) {
            case 0 -> catalog.sort(SORT_BY_CATEGORY);
            case 1 -> catalog.sort(SORT_BY_NAME);
            default -> {
            }
        }
        return catalog;
    }

    // VIEW STORE
    public List<Product> viewAvailable(User user) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return null;

        // filter only available stocks
        return productRepo.getAll().stream()
                .filter(product -> product.getQuantity() > 0)
                .toList();
    }

    // FILTER PRICE BY BUDGET
    public List<Product> filterPrice(User user, double max_price) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return null;

        // filter only products under price range
        return productRepo.getAll().stream()
                .filter(product -> product.getQuantity() > 0)
                .filter(product -> product.getPrice() < max_price)
                .toList();
    }

    // CHECKOUT CLIENT CART
    public boolean checkout(User user, String tax) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return false;

        // local field
        Cart cart = cartRepo.getCart(user.getId());

        // prohibit checkout of an unavailable cart or an empty cart
        if(cart == null || cart.getProductList().isEmpty()) {
            return false;
        }

        // checkout process
        else {

            // total price
            double price = 0.00;

            for(Map.Entry<String, Integer> item: cart.getProductList().entrySet()) {
                Product product = productRepo.getProduct(item.getKey());

                // add item price to total price
                double item_price = product.getPrice();
                price += item_price * item.getValue();

                // reduce product quantity from stock
                product.setQuantity(product.getQuantity()-item.getValue());
            }

            // calculate tax
            price *= (1 + (Double.parseDouble(tax)/100));

            // adds order to catalog and remove cart
            Order order = new Order(orderRepo.getHistory(user.getId()).size() + 1, user.getId(), cart.getProductList(), price);
            orderRepo.add(order);
            return cartRepo.remove(user.getId());
        }
    }

    // GET CLIENT ORDER HISTORY
    public List<Order> orderHistory(User user) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return null;

        // grab all previously placed orders
        return orderRepo.getHistory(user.getId());
    }
}
