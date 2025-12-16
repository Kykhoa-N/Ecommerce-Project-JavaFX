package ecommerce.controller;

import ecommerce.model.User;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.app.*;
import javafx.scene.image.ImageView;

public class DashboardController implements UseAppContext {

    public Label accountName;
    public Label accountRole;

    @FXML private Button logoutButton;
    private AppContext app;

    @FXML
    private void initialize() {
    }

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;

        User user = this.app.getCurrentUser();

        accountName.setText(user.getName());
        accountRole.setText(user.getRole().getDisplayName());
    }

    @FXML
    public void onLogout() {
        app.setCurrentUser(null);
        app.switchTo("/ecommerce/ui/login.fxml");
        System.out.println("LOGOUT current user.");
    }

}
