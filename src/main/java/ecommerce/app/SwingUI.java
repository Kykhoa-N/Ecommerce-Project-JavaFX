package ecommerce.app;

import ecommerce.model.*;
import ecommerce.repo.*;
import ecommerce.service.*;
import ecommerce.ui.*;
import ecommerce.uiHelper.*;
import java.awt.*;
import javax.swing.*;

public class SwingUI extends JFrame{

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private User current_user;

    // DIMENSION
    private final int width = 1500;
    private final int height = 800;

    // PAGE FIELD
    DashboardPage dashboardPage;

    // REPO FIELD
    private final UserRepo userRepo = new UserRepo();
    private final CartRepo cartRepo = new CartRepo();
    private final OrderRepo orderRepo = new OrderRepo();
    private final ProductRepo productRepo = new ProductRepo();

    // SERVICE FIELD
    private final AuthService authService = new AuthService(userRepo);

    public SwingUI() {
        initGUI(); // Window Setup
        initScreens(); // Login Panel setup
        setVisible(true); // End
    }

    public void initGUI() {
        setTitle("Ecommerce Store");
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create the CardLayout + card panel
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        cardPanel.setBackground(Theme.BACKGROUND);

        // Use the cardPanel as the main content of the frame
        setContentPane(cardPanel);
    }

    private void initScreens() {
        LoginPage loginPage = new LoginPage(this, authService);
        RegisterPage registerPage = new RegisterPage(this, authService);
        dashboardPage = new DashboardPage(this);

        cardPanel.add(loginPage, "LOGIN");
        cardPanel.add(registerPage, "REGISTER");
        cardPanel.add(dashboardPage, "DASHBOARD");
    }

    public void showScreen(String name) {
        cardLayout.show(cardPanel, name);
    }

    public User getCurrentUser() {
        return current_user;
    }

    public void setCurrentUser(User user) {
        this.current_user = user;
    }

    public DashboardPage getDashboard() {
        return dashboardPage;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public CartRepo getCartRepo() {
        return cartRepo;
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public UserRepo getUserRepo() {
        return userRepo;
    }

}
