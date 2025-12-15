package ecommerce.controller;

import javafx.scene.control.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.*;
import java.util.*;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.repo.*;



public class RegisterController {

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private ComboBox<Role> roleComboBox;
    @FXML private Button registerButton;
    @FXML private Button loginPageButton;

    private AuthService authService;

    @FXML
    private void initialize() {
        roleComboBox.getItems().addAll(Role.values());
        roleComboBox.setValue(Role.CLIENT); // Default Selection
    }

    @FXML
    private void onRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        Role role = roleComboBox.getValue();

        boolean success = authService.register(name, email, role);

        if(!success) {
            System.out.println("Wrong Input, Try Again");
            return;
        }
        else {
            try {
                Parent loginRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/ecommerce/ui/login.fxml")));
                Scene scene = loginPageButton.getScene();
                scene.setRoot(loginRoot);
                Platform.runLater(loginRoot::requestFocus);
            }
            catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("REGISTERED account");
        }

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
        System.out.println("go to LOGIN-PAGE");
    }


    public void loadInfo(AuthService authService) {
        this.authService = authService;
    }

}
