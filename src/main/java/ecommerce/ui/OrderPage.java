package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class OrderPage extends JPanel {

    private final SwingUI parent;
    private final OrderService orderService;

    public OrderPage(SwingUI parent, OrderService orderService) {
        this.parent = parent;
        this.orderService = orderService;

        setOpaque(false);
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {

        // CREATE ORDER PANEL
        RoundObject orderPanel = UITools.createRoundPanel(Theme.NAVY,(int) (parent.getWidth()*0.8313), (int) (parent.getHeight()*.9549), 40);
        orderPanel.setLayout(new BoxLayout(orderPanel, BoxLayout.Y_AXIS));

        add(orderPanel);
    }
}
