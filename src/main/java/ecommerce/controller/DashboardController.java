package ecommerce.controller;

import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.model.User;
import ecommerce.app.*;


public class DashboardController implements UseAppContext {

    @FXML private Label accountName;
    @FXML private Label accountRole;
    @FXML private StackPane centerPane;

    private AppContext app;

    @FXML
    private void initialize() {
        System.out.println("Dashboard initialized");
    }

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;

        User user = this.app.getCurrentUser();
        accountName.setText(user.getName());
        accountRole.setText(user.getRole().getDisplayName());

        app.setCenter(centerPane, "/ecommerce/ui/inventory.fxml");
    }

    @FXML private void viewInventory() {
        app.setCenter(centerPane, "/ecommerce/ui/inventory.fxml");
        System.out.println("view INVENTORY PAGE.");
    }

    @FXML private void viewOrders() {
        app.setCenter(centerPane, "/ecommerce/ui/orders.fxml");
        System.out.println("view ORDERS PAGE.");
    }

    @FXML private void viewReport() {
        app.setCenter(centerPane, "/ecommerce/ui/report.fxml");
        System.out.println("view REPORT PAGE.");
    }

    @FXML private void viewStore() {
        app.setCenter(centerPane, "/ecommerce/ui/store.fxml");
        System.out.println("view STORE PAGE.");
    }

    @FXML private void viewCart() {
        app.setCenter(centerPane, "/ecommerce/ui/cart.fxml");
        System.out.println("view CART PAGE.");
    }

    @FXML
    public void onLogout() {
        app.setCurrentUser(null);
        app.switchTo("/ecommerce/ui/login.fxml");
        System.out.println("LOGOUT current user.");
    }

}
