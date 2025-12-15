package ecommerce.app;

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

    UserRepo userRepo = new UserRepo();
    CartRepo cartRepo = new CartRepo();
    ProductRepo productRepo = new ProductRepo();
    OrderRepo orderRepo = new OrderRepo();

    AuthService authService = new AuthService(userRepo);
    CartService cartService = new CartService(cartRepo, productRepo);
    ProductService productService = new ProductService(productRepo, orderRepo, cartRepo);
    OrderService orderService = new OrderService(orderRepo);
    ReportService reportService = new ReportService(orderRepo, productRepo);

    @Override
    public void start(Stage stage) throws Exception{
        customFont.loadInter();
        initScreen(stage);
    }

    public void initScreen(Stage stage) throws IOException {
        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/ecommerce/ui/register.fxml"));
        Parent loginRoot = loginLoader.load();
        Scene loginScene = new Scene(loginRoot, 1500, 800);

        stage.setScene(loginScene);
        stage.show();
        // stop auto-focus on first TextField
        Platform.runLater(loginRoot::requestFocus);
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
