package ecommerce.service;

import ecommerce.model.*;
import ecommerce.repo.*;
import java.util.*;

public class CartService {

    // FIELD
    private final CartRepo cartRepo;
    private final ProductRepo productRepo;

    // CONSTRUCTOR
    public CartService(CartRepo cartRepo, ProductRepo productRepo) {
        this.cartRepo = cartRepo;
        this.productRepo = productRepo;
    }


    // ADD ITEM TO CART
    public boolean add(User user, String name, int quantity) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return false;

        // local field
        Cart cart = cartRepo.getCart(user.getId());
        Product product = productRepo.getProduct(name);

        // create cart if user don't have
        if(cart == null) {
            cart = new Cart(user.getId());
            cartRepo.add(cart);
        }

        // Don't Add If: Product Non-Exist or Not Enough Stock
        cart.add(name, 0);
        if(product == null || (product.getQuantity() - cart.getProductList().get(name)) < quantity) {
            return false;
        }
        return cart.add(name, quantity);
    }

    // REMOVE ITEM FROM CART
    public boolean remove(User user, String name, int quantity) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return false;

        // remove item from user cart
        Cart cart = cartRepo.getCart(user.getId());
        return cart.remove(name, quantity);
    }

    // VIEW CART CATALOG
    public List<CartItem> viewAll(User user) {

        // PERMISSION
        if(user.getRole() == Role.ADMIN) return null;

        // local field
        List<CartItem> catalog = new ArrayList<>();
        Cart cart = cartRepo.getCart(user.getId());

        // create cart if user don't have
        if(cart == null) {
            cart = new Cart(user.getId());
            cartRepo.add(cart);
        }

        // convert hashmap to list for display
        for(Map.Entry<String, Integer> item: cart.getProductList().entrySet()) {
            catalog.add(new CartItem(item.getKey(), item.getValue()));
        }
        return catalog;
    }
}
