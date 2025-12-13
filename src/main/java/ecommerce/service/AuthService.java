package ecommerce.service;

import ecommerce.model.*;
import ecommerce.repo.*;
import java.util.*;

public class AuthService {

    // FIELD
    private final UserRepo userRepo;

    // CONSTANT
    private static final Comparator<User> SORT_BY_ID = Comparator.comparing(User::getId);
    private static final Comparator<User> SORT_BY_ROLE = Comparator.comparing(user -> user.getRole().ordinal());

    // CONSTRUCTOR
    public AuthService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    // REGISTER USER
    public boolean register(String name, String id, Role role) {
        if(name.isEmpty() || id.isEmpty()) return false;
        if(userRepo.getUser(id) == null) {
            return userRepo.add(new User(name, id, role));
        }
        return false;
    }

    // LOGIN USER
    public User login(String name, String id) {
        User user = userRepo.getUser(id);

        // if user doesn't exist or wrong name
        if(user == null || !user.getName().equals(name)) {
            return null;
        }
        return user;
    }

    // VIEW USER CATALOG
    public List<User> viewAll(User user, int view) {

        // PERMISSION
        if(user.getRole() == Role.CLIENT) return null;

        // local field
        List<User> catalog = userRepo.getAll();

        // choose view
        switch(view) {
            case 0 -> catalog.sort(SORT_BY_ID);
            case 1 -> catalog.sort(SORT_BY_ROLE);
            default -> {
            }
        }
        return catalog;
    }

}
