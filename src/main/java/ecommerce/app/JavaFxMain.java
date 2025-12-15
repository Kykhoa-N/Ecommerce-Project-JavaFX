package ecommerce.app;

import ecommerce.controller.RegisterController;
import javafx.application.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import ecommerce.customUI.*;
import java.io.IOException;

import ecommerce.service.*;
import ecommerce.repo.*;
import ecommerce.model.*;


public class JavaFxMain extends Application {

    private final UserRepo userRepo = new UserRepo();
    private final CartRepo cartRepo = new CartRepo();
    private final ProductRepo productRepo = new ProductRepo();
    private final OrderRepo orderRepo = new OrderRepo();

    private final AuthService authService = new AuthService(userRepo);
    private final CartService cartService = new CartService(cartRepo, productRepo);
    private final ProductService productService = new ProductService(productRepo, orderRepo, cartRepo);
    private final OrderService orderService = new OrderService(orderRepo);
    private final ReportService reportService = new ReportService(orderRepo, productRepo);

    @Override
    public void start(Stage stage) throws Exception{
        customFont.loadInter();
        initScreen(stage);
    }

    public void initScreen(Stage stage) throws IOException {
        FXMLLoader registerLoader = new FXMLLoader(getClass().getResource("/ecommerce/ui/register.fxml"));
        Parent registerRoot = registerLoader.load();
        Scene loginScene = new Scene(registerRoot, 1500, 800);

        RegisterController registerController = registerLoader.getController();
        registerController.loadInfo(authService);

        stage.setScene(loginScene);
        stage.show();
        Platform.runLater(registerRoot::requestFocus);
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
