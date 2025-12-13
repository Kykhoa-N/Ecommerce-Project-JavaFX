package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RegisterPage extends JPanel {

    private final SwingUI parent;
    private final AuthService authService;



    public RegisterPage(SwingUI parent, AuthService authService) {
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
        JComboBox<Role> user_role_option;
        RoundObject auth_register_button;
        RoundObject login_page_button;

        // CREATE REGISTER PANEL
        RoundObject registerPanel = UITools.createRoundPanel(Theme.PANEL,380, 585, 40);
        registerPanel.setLayout(new BoxLayout(registerPanel, BoxLayout.Y_AXIS));

        // INIT REGION
        JPanel HEADER = UITools.createXContainer(Integer.MAX_VALUE, 25);
        JPanel FORM = UITools.createYContainer(Integer.MAX_VALUE, Integer.MAX_VALUE);

        // INIT SECTIONS
        JPanel FORMUser = UITools.createYContainer(Integer.MAX_VALUE,100);
        JPanel FORMPass = UITools.createYContainer(Integer.MAX_VALUE,100);
        JPanel FORMRole = UITools.createYContainer(Integer.MAX_VALUE,100);
        JPanel FORMRegister = UITools.createYContainer(Integer.MAX_VALUE,40);
        JPanel FORMDivider = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel FORMLogin = UITools.createXContainer(Integer.MAX_VALUE,50);

        // INIT BLOCKS
        JPanel USERLabel = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel USERField = UITools.createYContainer(Integer.MAX_VALUE,50);
        JPanel PASSLabel = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel PASSField = UITools.createYContainer(Integer.MAX_VALUE,50);
        JPanel ROLELabel = UITools.createXContainer(Integer.MAX_VALUE,50);
        JPanel ROLEOption = UITools.createYContainer(Integer.MAX_VALUE,50);
        JPanel DIVLeft = UITools.createXContainer(Theme.GRAY, (int) (300*(0.425)), 1);
        JPanel DIVText = UITools.createXContainer((int) (300*(0.15)),50);
        JPanel DIVRight = UITools.createXContainer(Theme.GRAY, (int) (300*(0.425)), 1);
        JPanel LOGText = UITools.createXContainer((int) (300*(0.65)), Integer.MAX_VALUE);
        JPanel LOGButton = UITools.createXContainer((int) (300*(0.35)),Integer.MAX_VALUE);

        // CREATE HEADER REGION
        JLabel header_label = UITools.createLabel(HEADER,"REGISTER",25,true, Align.CENTER);

        // CREATE USER SECTION
        JLabel user_label = UITools.createLabel(USERLabel, "Name",15,true, Align.LEFT);
        user_name_field = UITools.createRoundField(USERField, ObjectType.TEXTFIELD, Integer.MAX_VALUE, 50);
        user_label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // CREATE PASSWORD SECTION
        JLabel pass_label = UITools.createLabel(PASSLabel, "User ID",15,true, Align.LEFT);
        user_id_field = UITools.createRoundField(PASSField, ObjectType.TEXTFIELD, Integer.MAX_VALUE, 50);
        pass_label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // CREATE ROLE SECTION
        JLabel role_label = UITools.createLabel(ROLELabel, "Role",15,true, Align.LEFT);
        user_role_option = UITools.createDropBox(ROLEOption, Role.values(), Integer.MAX_VALUE, 50);
        role_label.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        // CREATE REGISTER SECTION
        auth_register_button = UITools.createRoundButton(FORMRegister, ObjectType.BUTTON, Integer.MAX_VALUE, 40, "REGISTER", 15);
        auth_register_button.setupButton(Theme.BLUE, Theme.HOVERBLUE, Theme.PANEL, Theme.PANEL);

        // CREATE DIVIDER SECTION
        JLabel divider_text = UITools.createLabel(DIVText, "OR", 15, true, Align.CENTER);
        divider_text.setForeground(Theme.GRAY);

        // CREATE LOGIN SECTION
        JLabel login_text = UITools.createLabel(LOGText,"Already have an account?",13,false, Align.RIGHT);
        login_page_button = UITools.createRoundButton(LOGButton, ObjectType.BUTTON,60,18,"LOGIN", 13);
        login_page_button.setupButton(Theme.TRANSPARENT, Theme.TRANSPARENT, Theme.GRAY, Theme.BLUE);
        login_text.setForeground(Theme.GRAY);

        // SECTION ORGANIZER
        registerPanel.add(HEADER);
        registerPanel.add(FORM);

        // SEGMENT ORGANIZER
        FORM.add(FORMUser);
        FORM.add(FORMPass);
        FORM.add(FORMRole);
        FORM.add(Box.createVerticalStrut(40));
        FORM.add(FORMRegister);
        FORM.add(Box.createVerticalStrut(20));
        FORM.add(FORMDivider);
        FORM.add(FORMLogin);

        // AREA ORGANIZER
        FORMUser.add(USERLabel);
        FORMUser.add(USERField);

        FORMPass.add(PASSLabel);
        FORMPass.add(PASSField);

        FORMRole.add(ROLELabel);
        FORMRole.add(ROLEOption);

        FORMDivider.add(DIVLeft);
        FORMDivider.add(DIVText);
        FORMDivider.add(DIVRight);

        FORMLogin.add(LOGText);
        FORMLogin.add(LOGButton);

        // DATA MANAGER
        DataTool.routeButton(parent, login_page_button, "LOGIN");

        auth_register_button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

                String username = user_name_field.getText();
                String password = user_id_field.getText();
                Role role = (Role) user_role_option.getSelectedItem();

                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(
                            RegisterPage.this,
                            "Please fill out all fields.",
                            "Missing Information",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                boolean register = authService.register(username, password, role);

                if (!register) {
                    JOptionPane.showMessageDialog(
                            RegisterPage.this,
                            "User ID already exists.",
                            "Registration Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                    return;
                }

                JOptionPane.showMessageDialog(
                        RegisterPage.this,
                        "Registration successful!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE
                );
                System.out.println("SUCCESSFULLY REGISTERED");
                parent.showScreen("LOGIN");
            }
        });

        add(registerPanel);
    }

}
