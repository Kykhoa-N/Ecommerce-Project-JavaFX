package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JPanel {

    private final SwingUI parent;
    private final AuthService authService;

    public LoginPage(SwingUI parent, AuthService authService) {
        this.parent = parent;
        this.authService = authService;

        setOpaque(false);
        setLayout(new GridBagLayout());
        buildUI();
    }


    private void buildUI() {

        // DATA FIELD
        RoundObject user_name_field;
        RoundObject user_id_field;
        RoundObject auth_login_button;
        RoundObject auth_register_button;

        // CREATE LOGIN PANEL
        RoundObject loginPanel = UITools.createRoundPanel(Theme.PANEL,380, 490, 40);
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        // INIT REGION
        JPanel HEADER = UITools.createXContainer(Integer.MAX_VALUE, 25);
        JPanel FORM = UITools.createYContainer(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // INIT SECTIONS
        JPanel FORMUser = UITools.createYContainer(Integer.MAX_VALUE,100);
        JPanel FORMPass = UITools.createYContainer(Integer.MAX_VALUE,100);
        JPanel FORMLogin = UITools.createYContainer(Integer.MAX_VALUE,40);
        JPanel FORMDivider = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel FORMRegister = UITools.createXContainer(Integer.MAX_VALUE,50);

        // INIT BLOCKS
        JPanel USERLabel = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel USERField = UITools.createYContainer(Integer.MAX_VALUE,50);
        JPanel PASSLabel = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel PASSField = UITools.createYContainer(Integer.MAX_VALUE,50);
        JPanel DIVLeft = UITools.createXContainer(Theme.GRAY, (int) (300*(0.425)), 1);
        JPanel DIVText = UITools.createXContainer((int) (300*(0.15)),50);
        JPanel DIVRight = UITools.createXContainer(Theme.GRAY, (int) (300*(0.425)), 1);
        JPanel REGText = UITools.createXContainer((int) (300*(0.6)), Integer.MAX_VALUE);
        JPanel REGButton = UITools.createXContainer((int) (300*(0.4)),Integer.MAX_VALUE);


        // CREATE HEADER REGION
        JLabel header_label = UITools.createLabel(HEADER,"LOGIN",25,true, Align.CENTER);

        // CREATE USER SECTION
        JLabel user_label = UITools.createLabel(USERLabel, "Name",15,true, Align.LEFT);
        user_name_field = UITools.createRoundField(USERField, ObjectType.TEXTFIELD, Integer.MAX_VALUE, 50);
        user_label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // CREATE PASSWORD SECTION
        JLabel pass_label = UITools.createLabel(PASSLabel, "User ID",15,true, Align.LEFT);
        user_id_field = UITools.createRoundField(PASSField, ObjectType.PASSFIELD, Integer.MAX_VALUE, 50);
        pass_label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // CREATE LOGIN SECTION
        auth_login_button = UITools.createRoundButton(FORMLogin, ObjectType.BUTTON, Integer.MAX_VALUE, 40, "LOGIN", 15);
        auth_login_button.setupButton(Theme.BLUE, Theme.HOVERBLUE, Theme.PANEL, Theme.PANEL);

        // CREATE DIVIDER SECTION
        JLabel divider_text = UITools.createLabel(DIVText, "OR", 15, true, Align.CENTER);
        divider_text.setForeground(Theme.GRAY);

        // CREATE REGISTER SECTION
        JLabel register_text = UITools.createLabel(REGText,"Don't have an account?",13,false, Align.RIGHT);
        auth_register_button = UITools.createRoundButton(REGButton, ObjectType.BUTTON,60,18,"SIGNUP", 13);
        auth_register_button.setupButton(Theme.TRANSPARENT, Theme.TRANSPARENT, Theme.GRAY, Theme.BLUE);
        register_text.setForeground(Theme.GRAY);

        // SECTION ORGANIZER
        loginPanel.add(HEADER);
        loginPanel.add(FORM);

        // SEGMENT ORGANIZER
        FORM.add(FORMUser);
        FORM.add(FORMPass);
        FORM.add(Box.createVerticalStrut(40));
        FORM.add(FORMLogin);
        FORM.add(Box.createVerticalStrut(20));
        FORM.add(FORMDivider);
        FORM.add(FORMRegister);

        // AREA ORGANIZER
        FORMUser.add(USERLabel);
        FORMUser.add(USERField);

        FORMPass.add(PASSLabel);
        FORMPass.add(PASSField);

        FORMDivider.add(DIVLeft);
        FORMDivider.add(DIVText);
        FORMDivider.add(DIVRight);

        FORMRegister.add(REGText);
        FORMRegister.add(REGButton);

        // DATA MANAGER
        DataTool.routeButton(parent, auth_register_button, "REGISTER");

        auth_login_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String username = user_name_field.getText();
                String password = user_id_field.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            LoginPage.this,
                            "Please fill out all fields.",
                            "Missing Information",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                User user = authService.login(username, password);

                if (user == null) {
                    JOptionPane.showMessageDialog(
                            LoginPage.this,
                            "Invalid Account",
                            "Login Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                parent.setCurrentUser(user);
                parent.getDashboard().updateUserDash(user);
                parent.showScreen("DASHBOARD");
                CardLayout cl = (CardLayout) parent.getDashboard().getContentPanel().getLayout();

                if (user.getRole() == Role.ADMIN) {
                    cl.show(parent.getDashboard().getContentPanel(), "INVENTORY");
                } else {
                    cl.show(parent.getDashboard().getContentPanel(), "STORE");
                }
            }
        });

        // ADD TO SYSTEM
        add(loginPanel);
    }
}