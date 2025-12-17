package ecommerce.controller;

import javafx.scene.control.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.io.*;
import java.util.*;

import ecommerce.model.*;
import ecommerce.app.*;


public class RegisterController implements UseAppContext{

    @FXML private TextField nameField;
    @FXML private TextField emailField;
    @FXML private ComboBox<Role> roleComboBox;

    private AppContext app;


    @FXML
    private void initialize() {
        roleComboBox.getItems().addAll(Role.values());
        roleComboBox.setValue(Role.CLIENT); // Default Selection
    }


    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
    }


    @FXML
    private void onRegister() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        Role role = roleComboBox.getValue();

        boolean success = app.getAuthService().register(name, email, role);

        if(!success) {
            System.out.println("!! INVALID INPUT, TRY AGAIN");
            return;
        }
        else {
            app.switchTo("/ecommerce/ui/login.fxml");
        }

        System.out.println("REGISTERED account");
    }


    @FXML
    private void goToLogin() {
        app.switchTo("/ecommerce/ui/login.fxml");
        System.out.println("go to LOGIN-PAGE.");
    }
}
