package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.repo.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;


public class InventoryPage extends JPanel {

    private final SwingUI parent;
    private final ProductService productService;
    private ProductRepo productRepo;

    private JPanel TOOLBAR;
    private JPanel CONTENT;

    public InventoryPage(SwingUI parent, ProductService productService) {
        this.parent = parent;
        this.productService = productService;
        this.productRepo = parent.getProductRepo();

        setLayout(new BorderLayout());
        buildUI();
        refresh();
    }

    private void buildUI() {
        removeAll();

        // CREATE REGION
        TOOLBAR = UITools.createXContainer(Theme.GRAY, Integer.MAX_VALUE, 100);
        CONTENT = UITools.createYContainer(Integer.MAX_VALUE, (int) (parent.getHeight()*.8299));
        CONTENT.setLayout(new BoxLayout(CONTENT, BoxLayout.Y_AXIS));
        JScrollPane scroll = UITools.createScrollPanel(CONTENT);

        // CREATE SECTION
        JPanel TOOLSearch = UITools.createXContainer(Theme.NAVY1, 700, Integer.MAX_VALUE);

        // CREATE BLOCKS
        JPanel SEARCHField = UITools.createYContainer(Theme.NAVY1, 530, Integer.MAX_VALUE);
        JPanel SEARCHButton = UITools.createYContainer(Theme.NAVY1, 100, Integer.MAX_VALUE);
        JPanel TOOLSort = UITools.createYContainer(Theme.NAVY1, 100, Integer.MAX_VALUE);
        JPanel TOOLView = UITools.createYContainer(Theme.NAVY1, 100, Integer.MAX_VALUE);
        JPanel TOOLAdd = UITools.createYContainer(Theme.NAVY1, 248, Integer.MAX_VALUE);
        SEARCHField.add(Box.createVerticalStrut(30));
        SEARCHButton.add(Box.createVerticalStrut(30));
        TOOLSort.add(Box.createVerticalStrut(30));
        TOOLView.add(Box.createVerticalStrut(30));
        TOOLAdd.add(Box.createVerticalStrut(30));

        // CREATE SEARCH
        RoundObject search_field = UITools.createRoundField(SEARCHField, ObjectType.TEXTFIELD,500,40);
        search_field.setBackground(Theme.PANEL);
        search_field.setOpaque(true);
        RoundObject search_button = UITools.createRoundButton(SEARCHButton, ObjectType.BUTTON, 100, 40, "SEARCH", 15);
        search_button.setupButton(Theme.LIGHTGRAY, Theme.GRAY, Theme.TEXT, Theme.TEXT);

        // CREATE SORT
        RoundObject sort_button = UITools.createRoundButton(TOOLSort, ObjectType.BUTTON, 60, 40, "SORT", 15);
        sort_button.setupButton(Theme.NAVY1HOVER, Theme.SAGEHOVER, Theme.PANEL, Theme.PANEL);

        // CREATE VIEW
        RoundObject view_button = UITools.createRoundButton(TOOLView, ObjectType.BUTTON, 60, 40, "VIEW", 15);
        view_button.setupButton(Theme.NAVY1HOVER, Theme.SAGEHOVER, Theme.PANEL, Theme.PANEL);

        // CREATE ADD
        RoundObject add_button = UITools.createRoundButton(TOOLAdd, ObjectType.BUTTON, 100, 40, "ADD", 15);
        add_button.setupButton(Theme.SAGE, Theme.SAGEHOVER, Theme.PANEL, Theme.PANEL);

        // ORGANIZE SECTIONS
        TOOLBAR.add(UITools.createYContainer(Theme.NAVY1,100,Integer.MAX_VALUE));
        TOOLBAR.add(TOOLSearch);
        TOOLBAR.add(TOOLSort);
        TOOLBAR.add(TOOLView);
        TOOLBAR.add(TOOLAdd);

        // ORGANIZE BLOCKS
        TOOLSearch.add(SEARCHField);
        TOOLSearch.add(SEARCHButton);

        // DATA MANAGER

        add(TOOLBAR, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
    }


    public void refresh() {
        CONTENT.removeAll();

        for (Product p : productRepo.getAll()) {
            JPanel ROW = UITools.createXContainer(Integer.MAX_VALUE, 55);
            Color row_color = Theme.PANEL;

            JPanel ROWCategory = UITools.createXContainer(row_color, 250, Integer.MAX_VALUE);
            JPanel ROWQuantity = UITools.createXContainer(row_color, 120, Integer.MAX_VALUE);
            JPanel ROWName = UITools.createXContainer(row_color, 300, Integer.MAX_VALUE);
            JPanel ROWPrice = UITools.createXContainer(row_color, 300, Integer.MAX_VALUE);

            JPanel ROWUpdate = UITools.createYContainer(Theme.LIGHTGRAY, 200, Integer.MAX_VALUE);
            JPanel ROWRemove = UITools.createYContainer(Theme.PANEL, 80, Integer.MAX_VALUE);
            ROWRemove.add(Box.createVerticalStrut(10));
            ROWUpdate.add(Box.createVerticalStrut(10));

            JLabel category  = UITools.createLabel(ROWCategory, p.getCategory(), 15, true,  Align.LEFT);
            JLabel name  = UITools.createLabel(ROWName, p.getName(), 15, true,  Align.LEFT);
            JLabel qty   = UITools.createLabel(ROWQuantity, "Qty: " + p.getQuantity(), 13, false, Align.LEFT);
            JLabel price    = UITools.createLabel(ROWPrice, "Price: $" + p.getPrice(), 13, false, Align.LEFT);
            RoundObject update_button = UITools.createRoundButton(ROWUpdate, ObjectType.BUTTON, 100, 35, "UPDATE", 15);
            RoundObject remove_button = UITools.createRoundButton(ROWRemove, ObjectType.BUTTON, 35, 35, "X", 15);

            update_button.setupButton(Theme.BLUE, Theme.HOVERBLUE, Theme.PANEL, Theme.PANEL);
            remove_button.setupButton(Theme.RED, Theme.HOVERRED, Theme.PANEL, Theme.PANEL);
            category.setBorder(BorderFactory.createEmptyBorder(0, 50, 0, 0));

            ROW.add(ROWCategory);
            ROW.add(ROWQuantity);
            ROW.add(ROWName);
            ROW.add(ROWPrice);
            ROW.add(ROWUpdate);
            ROW.add(ROWRemove);


            CONTENT.add(ROW);
            CONTENT.add(Box.createVerticalStrut(1));
        }

        CONTENT.revalidate();
        CONTENT.repaint();
    }

}
