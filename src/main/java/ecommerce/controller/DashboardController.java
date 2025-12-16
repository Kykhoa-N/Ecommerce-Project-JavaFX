package ecommerce.controller;

import ecommerce.model.User;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.app.*;
import javafx.scene.image.ImageView;

public class DashboardController implements UseAppContext {

    @FXML private Button logoutButton;
    private AppContext app;

    @FXML
    private void initialize() {
    }

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
    }

    @FXML
    public void onLogout() {
        app.setCurrentUser(null);
        app.switchTo("/ecommerce/ui/login.fxml");
        System.out.println("LOGOUT current user.");
    }

}
