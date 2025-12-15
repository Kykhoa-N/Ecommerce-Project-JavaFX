package ecommerce.app;

import ecommerce.service.*;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
import java.io.*;

public class AppContext {

    // Scene Shell
    private Scene scene;

    // Service Shell
    private final AuthService authService;
    private final ProductService productService;
    private final CartService cartService;
    private final OrderService orderService;
    private final ReportService reportService;

    // Constructor to Fill Shell with Context
    public AppContext(Scene scene, AuthService auth, ProductService product, CartService cart, OrderService order, ReportService report) {
        this.scene = scene;
        this.authService = auth;
        this.productService = product;
        this.cartService = cart;
        this.orderService = order;
        this.reportService = report;
    }

    // Getter Methods
    public AuthService getAuthService() {
        return authService;
    }

    public ProductService getProductService() {
        return productService;
    }

    public CartService getCartService() {
        return cartService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ReportService getReportService() {
        return reportService;
    }

    // Switch scene method
    public void switchTo(String fxmlPath) {
        try {
            // Initialize the FXML Screen
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlPath));
            Parent root = loader.load();

            // Retrieve Page Controller
            Object controller = loader.getController();

            // If the controller has loadInfo(AppContext), inject it
            if (controller instanceof UseAppContext c) {
                c.loadInfo(this);
            }

            scene.setRoot(root);
            Platform.runLater(root::requestFocus);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
