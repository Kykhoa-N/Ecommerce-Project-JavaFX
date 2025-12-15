package ecommerce.controller;

import ecommerce.app.AppContext;
import ecommerce.app.UseAppContext;
import ecommerce.model.Role;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;
import ecommerce.app.AppContext;

public class LoginController implements UseAppContext {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private Button loginButton;
    @FXML private Button registerPageButton;

    private AppContext app;

    @FXML
    private void initialize() {
    }

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
    }

    @FXML
    private void goToRegister() {
        app.switchTo("/ecommerce/ui/register.fxml");
        System.out.println("go to REGISTER-PAGE");
    }

}
