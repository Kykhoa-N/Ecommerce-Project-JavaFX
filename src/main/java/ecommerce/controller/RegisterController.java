package ecommerce.controller;

import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javafx.application.Platform;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.*;
import java.util.*;

import ecommerce.service.AuthService;

public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private PasswordField passwordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Button registerButton;
    @FXML private Button loginPageButton;

    @FXML
    private void initialize() {
    }

    @FXML
    private void onRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirm = confirmPasswordField.getText().trim();

        if(name.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            System.out.println("Please fill all field");
            return;
        }

        if(!(password.equals(confirm))) {
            System.out.println("Passwords do+ not match");
            return;
        }

        
        System.out.println("Register clicked");
    }

    @FXML
    private void goToLogin() {
        try {
            Parent loginRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ecommerce/ui/login.fxml")));
            Scene scene = loginPageButton.getScene();
            scene.setRoot(loginRoot);
            Platform.runLater(loginRoot::requestFocus);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Go to login page");
    }

}
