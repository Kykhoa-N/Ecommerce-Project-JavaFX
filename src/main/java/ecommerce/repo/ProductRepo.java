package ecommerce.repo;

import ecommerce.model.*;
import java.util.*;
import ecommerce.uiHelper.*;

public class ProductRepo {

    // FIELD
    private final List<Product> repo = new ArrayList<>();

    // DATABASE HELPER METHOD
    private static final String DATABASE_PATH = "src/main/java/ecommerce/database/ProductDatabase.txt";
    public ProductRepo() {
        repo.addAll(FileDBLoader.load(DATABASE_PATH, Product::new));
    }

    // REPO METHOD
    public boolean add(Product product) {
        repo.add(product);
        FileDBWriter.appendLine(DATABASE_PATH, product.toDataString()); // add to database
        return true;
    }

    public boolean remove(String name) {
        for(Product product: repo) {
            if(product.getName().equals(name)) {
                return repo.remove(product);
            }
        }
        return false;
    }

    public Product getProduct(String name) {
        for(Product product: repo) {
            if(product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }

    public List<Product> getAll() {
        return new ArrayList<>(repo);
    }
}
