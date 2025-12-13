package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class CartPage extends JPanel {

    private final SwingUI parent;
    private final CartService cartService;

    public CartPage(SwingUI parent, CartService cartService) {
        this.parent = parent;
        this.cartService = cartService;

        setOpaque(false);
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {

        // CREATE CART PANEL
        RoundObject cartPanel = UITools.createRoundPanel(Theme.SAGE,(int) (parent.getWidth()*0.8313), (int) (parent.getHeight()*.9549), 40);
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

        add(cartPanel);
    }
}
