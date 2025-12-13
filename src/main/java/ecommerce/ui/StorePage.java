package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class StorePage extends JPanel {

    private final SwingUI parent;
    private final ProductService productService;

    public StorePage(SwingUI parent, ProductService productService) {
        this.parent = parent;
        this.productService = productService;

        setOpaque(false);
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {

        // CREATE STORE PANEL
        RoundObject storePanel = UITools.createRoundPanel(Theme.NAVY,(int) (parent.getWidth()*0.8313), (int) (parent.getHeight()*.9549), 40);
        storePanel.setLayout(new BoxLayout(storePanel, BoxLayout.Y_AXIS));

        add(storePanel);
    }
}
