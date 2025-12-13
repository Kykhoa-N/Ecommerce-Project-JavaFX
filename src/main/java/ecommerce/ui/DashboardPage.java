package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.repo.CartRepo;
import ecommerce.repo.OrderRepo;
import ecommerce.repo.ProductRepo;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class DashboardPage extends JPanel {

    // FIELD
    private final SwingUI parent;

    private JPanel SIDEBAR;
    private JPanel CONTENT;
    private CardLayout contentLayout;

    private final CartRepo cartRepo = new CartRepo();
    private final OrderRepo orderRepo = new OrderRepo();
    private final ProductRepo productRepo = new ProductRepo();

    private final CartService cartService;
    private final OrderService orderService;
    private final ProductService productService;
    private final ReportService reportService;

    // PAGES FIELD
    InventoryPage inventoryPage;
    OrderPage orderPage;
    ReportPage reportPage;
    StorePage storePage;
    CartPage cartPage;

    // CUSTOM SECTIONS FIELD
    private JPanel SIDEProfile;
    private JPanel SIDEService;

    // CUSTOM BLOCKS FIELD
    private JPanel SERVInventory;
    private JPanel SERVOrders;
    private JPanel SERVReports;
    private JPanel SERVStore;
    private JPanel SERVCart;

    public DashboardPage(SwingUI parent) {
        this.parent = parent;
        cartService = new CartService(parent.getCartRepo(), parent.getProductRepo());
        orderService = new OrderService(parent.getOrderRepo());
        productService = new ProductService(parent.getProductRepo(), parent.getOrderRepo(), parent.getCartRepo());
        reportService = new ReportService(parent.getOrderRepo(), parent.getProductRepo());

        setOpaque(false);
        setLayout(new BorderLayout());

        contentLayout = new CardLayout();
        CONTENT = new JPanel(contentLayout);
        CONTENT.setBackground(Theme.BACKGROUND);

        inventoryPage = new InventoryPage(parent, productService);
        orderPage = new OrderPage(parent, orderService);
        reportPage = new ReportPage(parent, reportService);
        storePage = new StorePage(parent, productService);
        cartPage = new CartPage(parent, cartService);

        CONTENT.add(inventoryPage, "INVENTORY");
        CONTENT.add(orderPage, "ORDER");
        CONTENT.add(reportPage, "REPORT");
        CONTENT.add(storePage, "STORE");
        CONTENT.add(cartPage, "CART");

        buildUI();
    }

    private void buildUI() {

        // DIMENSION FIELD
        int sidebar_width = 240;
        int button_width = sidebar_width-35;
        int button_height = 40;
        int service_height = 55;

        // CREATE REGION
        SIDEBAR = UITools.createYContainer(Theme.DARKNAVY, sidebar_width, Integer.MAX_VALUE);

        // CREATE SECTIONS
        SIDEProfile = UITools.createYContainer(Integer.MAX_VALUE, (int)(parent.getHeight()*0.28));
        SIDEService = UITools.createYContainer(sidebar_width, (int)(parent.getHeight()*0.57));
        JPanel SIDESetting = UITools.createYContainer(Integer.MAX_VALUE, (int)(parent.getHeight()*0.15));

        // CREATE BLOCKS
        SERVInventory = UITools.createYContainer(Integer.MAX_VALUE, service_height);
        SERVOrders = UITools.createYContainer(Integer.MAX_VALUE, service_height);
        SERVReports = UITools.createYContainer(Integer.MAX_VALUE, service_height);
        SERVStore = UITools.createYContainer(Integer.MAX_VALUE, service_height);
        SERVCart = UITools.createYContainer(Integer.MAX_VALUE, service_height);
        JPanel SETLogout = UITools.createYContainer(Integer.MAX_VALUE, service_height);;

        // CREATE ADMIN SERVICES
        RoundObject inventory_button = UITools.createRoundButton(SERVInventory, ObjectType.BUTTON, button_width, button_height, "Inventory", 15);
        inventory_button.setupButton(Theme.SHADENAVY, Theme.BLUE, Theme.PANEL, Theme.PANEL);
        RoundObject orders_button = UITools.createRoundButton(SERVOrders, ObjectType.BUTTON, button_width, button_height, "Orders", 15);
        orders_button.setupButton(Theme.SHADENAVY, Theme.BLUE, Theme.PANEL, Theme.PANEL);
        RoundObject reports_button = UITools.createRoundButton(SERVReports, ObjectType.BUTTON, button_width, button_height, "Reports", 15);
        reports_button.setupButton(Theme.SHADENAVY, Theme.BLUE, Theme.PANEL, Theme.PANEL);

        // CREATE CLIENT SERVICES
        RoundObject store_button = UITools.createRoundButton(SERVStore, ObjectType.BUTTON, button_width, button_height, "Store", 15);
        store_button.setupButton(Theme.SHADENAVY, Theme.BLUE, Theme.PANEL, Theme.PANEL);
        RoundObject cart_button = UITools.createRoundButton(SERVCart, ObjectType.BUTTON, button_width, button_height, "Cart", 15);
        cart_button.setupButton(Theme.SHADENAVY, Theme.BLUE, Theme.PANEL, Theme.PANEL);

        // CREATE LOGOUT SECTION
        RoundObject logout_button = UITools.createRoundButton(SETLogout, ObjectType.BUTTON, button_width, button_height, "LOGOUT", 15);
        logout_button.setupButton(Theme.BLUE, Theme.HOVERBLUE, Theme.PANEL, Theme.PANEL);

        JLabel placeholder = new JLabel("Welcome!", SwingConstants.CENTER);
        placeholder.setFont(new Font("Arial", Font.BOLD, 24));
        CONTENT.add(placeholder);


        // ORGANIZE SECTIONS
        SIDEBAR.setLayout(new BoxLayout(SIDEBAR, BoxLayout.Y_AXIS));
        SIDEBAR.add(SIDEProfile);
        SIDEBAR.add(SIDEService);
        SIDEBAR.add(SIDESetting);

        // ORGANIZE BLOCKS
        SIDESetting.add(Box.createVerticalStrut(25));
        SIDESetting.add(SETLogout);

        // DATA MANAGER
        DataTool.routeButton(parent, logout_button, "LOGIN");
        DataTool.routeButton(contentLayout, CONTENT, inventory_button, "INVENTORY");
        DataTool.routeButton(contentLayout, CONTENT, orders_button, "ORDER");
        DataTool.routeButton(contentLayout, CONTENT, reports_button, "REPORT");
        DataTool.routeButton(contentLayout, CONTENT, store_button, "STORE");
        DataTool.routeButton(contentLayout, CONTENT, cart_button, "CART");

        // ADD TO SYSTEM
        add(SIDEBAR, BorderLayout.WEST);
        add(CONTENT, BorderLayout.CENTER);
    }

    public void updateUserDash(User current_user) {
        if (current_user == null) return;

        // UPDATE PROFILE
        SIDEProfile.removeAll();
        JLabel name_label = UITools.createLabel(SIDEProfile, current_user.getName(), 17, true, Align.LEFT);
        JLabel id_label = UITools.createLabel(SIDEProfile, current_user.getId(), 17, true, Align.LEFT);
        JLabel role_label = UITools.createLabel(SIDEProfile, current_user.getRole().name(), 13, true, Align.LEFT);
        name_label.setBorder(BorderFactory.createEmptyBorder(75, 45, 0, 0));
        id_label.setBorder(BorderFactory.createEmptyBorder(0, 45, 0, 0));
        role_label.setBorder(BorderFactory.createEmptyBorder(5, 45, 0, 0));

        name_label.setForeground(Theme.PANEL);
        id_label.setForeground(Theme.PANEL);
        role_label.setForeground(Theme.GRAY);

        // UPDATE SERVICES
        SIDEService.removeAll();
        if(current_user.getRole() == Role.ADMIN) {
            SIDEService.add(SERVInventory);
            SIDEService.add(SERVOrders);
            SIDEService.add(SERVReports);
        } else {
            SIDEService.add(SERVStore);
            SIDEService.add(SERVCart);
        }

        // REPAINT
        SIDEProfile.revalidate();
        SIDEProfile.repaint();
        SIDEService.revalidate();
        SIDEService.repaint();
    }

    public JPanel getContentPanel() {
        return this.CONTENT;
    }
}
