package ecommerce.controller;

import ecommerce.model.User;
import javafx.scene.control.*;
import javafx.fxml.*;
import javafx.scene.*;
import java.util.*;

import ecommerce.app.*;

public class LoginController implements UseAppContext {
    @FXML private TextField nameField;
    @FXML private TextField emailField;

    private AppContext app;

    @FXML
    private void initialize() {
    }

    @Override
    public void loadInfo(AppContext appContext) {
        this.app = appContext;
    }

    @FXML
    public void onLogin() {
        String name = nameField.getText();
        String email = emailField.getText();

        User current_user = app.getAuthService().login(name, email);

        if(current_user == null) {
            System.out.println("!! INVALID CREDENTIAL, TRY AGAIN");
        }
        else {
            app.setCurrentUser(current_user);
            app.switchTo("/ecommerce/ui/dashboard.fxml");

            System.out.println("LOGIN current user, go to DASHBOARD PAGE.");
        }
    }

    @FXML
    private void goToRegister() {
        app.switchTo("/ecommerce/ui/register.fxml");
        System.out.println("go to REGISTER-PAGE.");
    }

}
