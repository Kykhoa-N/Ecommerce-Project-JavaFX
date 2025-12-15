package ecommerce.controller;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.*;
import java.util.*;

public class LoginController {
    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private Button loginButton;
    @FXML private Button registerPageButton;

    @FXML
    private void goToRegister() {
        try {
            Parent registerRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ecommerce/ui/register.fxml")));
            Scene loginRoot = registerPageButton.getScene();
            loginRoot.setRoot(registerRoot);
            Platform.runLater(registerRoot::requestFocus);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("Go to register page");
    }
}
