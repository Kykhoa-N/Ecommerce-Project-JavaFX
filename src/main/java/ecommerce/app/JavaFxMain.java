package ecommerce.app;

import ecommerce.controller.RegisterController;
import ecommerce.model.Role;
import ecommerce.model.User;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;

import ecommerce.customUI.*;
import java.io.IOException;

import ecommerce.service.*;
import ecommerce.repo.*;


public class JavaFxMain extends Application {

    // Initialize Repo Field
    private final UserRepo userRepo = new UserRepo();
    private final ProductRepo productRepo = new ProductRepo();
    private final CartRepo cartRepo = new CartRepo();
    private final OrderRepo orderRepo = new OrderRepo();

    // Initialize Service Field
    private final AuthService authService = new AuthService(userRepo);
    private final ProductService productService = new ProductService(productRepo, orderRepo, cartRepo);
    private final CartService cartService = new CartService(cartRepo, productRepo);
    private final OrderService orderService = new OrderService(orderRepo);
    private final ReportService reportService = new ReportService(orderRepo, productRepo);

    // Initialize Context For Transfer
    private AppContext appContext;

    // Declare a Reusable Scene
    private Scene scene;

    @Override
    public void start(Stage stage) throws Exception{
        customFont.loadInter();

        // Initialize a Window Container to show Displays
        scene = new Scene(new javafx.scene.layout.StackPane(), 1500, 800);
        appContext = new AppContext(scene, authService, productService, cartService, orderService, reportService);

        stage.setScene(scene);
        stage.show();

        // Use appContext helper method to switch Displays
        appContext.setCurrentUser(new User("Ky-Anh Nguyen", "c", Role.ADMIN));
        appContext.switchTo("/ecommerce/ui/dashboard.fxml");
    }


    public void initTheme() throws IOException {
        FXMLLoader themeLoader = new FXMLLoader(getClass().getResource("/ecommerce/ui/theme.fxml"));
        Parent themeRoot = themeLoader.load();
        Scene themeScene = new Scene(themeRoot, 1500, 800);

        Stage stage = new Stage();
        stage.setScene(themeScene);
        stage.show();
        Platform.runLater(themeRoot::requestFocus);
    }

    public static void main(String[] args) {
        launch();
    }
}
