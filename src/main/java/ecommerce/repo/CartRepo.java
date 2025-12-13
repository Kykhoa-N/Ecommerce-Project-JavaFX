package ecommerce.repo;

import ecommerce.model.*;
import java.util.*;
import ecommerce.uiHelper.*;

public class CartRepo {

    // FIELD
    private final List<Cart> repo = new ArrayList<>();

    // DATABASE HELPER METHOD
    private static final String DATABASE_PATH = "src/main/java/ecommerce/database/CartDatabase.txt";
    public CartRepo() {
        repo.addAll(FileDBLoader.load(DATABASE_PATH, Cart::new));
    }

    // REPO METHOD
    public void add(Cart cart) {
        repo.add(cart);
        FileDBWriter.appendLine(DATABASE_PATH, cart.toDataString()); // add to database
    }

    public boolean remove(String user_id) {
        for (Cart cart : repo) {
            if (cart.getUserId().equals(user_id)) {
                return repo.remove(cart);
            }
        }
        return false;
    }

    public Cart getCart(String user_id) {
        for (Cart cart : repo) {
            if (cart.getUserId().equals(user_id)) {
                return cart;
            }
        }
        return null;
    }

    public List<Cart> getAll() {
        return new ArrayList<>(repo);
    }
}