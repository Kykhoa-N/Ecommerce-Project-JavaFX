package ecommerce.uiHelper;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.*;

public class RoundObject extends JPanel {

    // FIELD
    private JComponent component;
    private Color color = null;

    private final ObjectType objectType;
    private int textHeight = 0;
    private int borderThickness = 1;
    private Color colorBorder = Theme.TRANSPARENT;

    // CONSTRUCTOR FOR PANEL AND FIELD
    public RoundObject(ObjectType objectType) {
        this(objectType, null, 0);
    }

    // CONSTRUCTOR FOR BUTTON
    public RoundObject(ObjectType objectType, String text, int height) {
        this.objectType = objectType;
        this.textHeight = height;
        setOpaque(false);

        configureByType(text);
    }

    // INITIALIZE BY TYPE
    private void configureByType(String text) {

        // set up by object type
        if(objectType.equals(ObjectType.PANEL)) {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        }
        else {
            setLayout(new BorderLayout());

            switch (objectType) {

                case TEXTFIELD -> {
                    component = styleTextComponent(new JTextField());
                    focusColor(component);
                    lockHeight(45);
                }

                case PASSFIELD -> {
                    component = styleTextComponent(new JPasswordField());
                    focusColor(component);
                    lockHeight(45);
                }

                case BUTTON -> {
                    component = new JLabel(text != null ? text : "BUTTON", SwingConstants.CENTER);
                    component.setFont(new Font("Segoe UI", Font.BOLD, textHeight));
                    lockHeight(45);
                }
            }

            add(component, BorderLayout.CENTER);
        }
    }

    // HIGHLIGHT FIELD WHEN CLICKED
    private void focusColor(JComponent component) {
        colorBorder = Theme.GRAY;
        repaint();
        component.addFocusListener(
            new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    colorBorder = Theme.BLUE;
                    repaint();
                }
                @Override
                public void focusLost(FocusEvent e) {
                    colorBorder = Theme.GRAY;
                    repaint();
                }
            }
        );
    }

    // SETUP BUTTON COLOR
    public void setupButton(Color colorNormalButton, Color colorHoverButton, Color colorNormalText, Color colorHoverText) {
        color = colorNormalButton;
        component.setForeground(colorNormalText);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Apply hover color changes
                component.setForeground(colorHoverText);
                color = colorHoverButton;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Restore normal colors
                component.setForeground(colorNormalText);
                color = colorNormalButton;
                repaint();
            }
        });
    }

    private JComponent styleTextComponent(JTextComponent comp) {
        comp.setOpaque(false);
        comp.setBorder(BorderFactory.createEmptyBorder(8, 12, 8, 12));
        comp.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        return comp;
    }

    private void lockHeight(int h) {
        setPreferredSize(new Dimension(Integer.MAX_VALUE, h));
        setMaximumSize(new Dimension(Integer.MAX_VALUE, h));
        setMinimumSize(new Dimension(Integer.MAX_VALUE, h));
    }


    public void setBorderThickness(int t) {
        this.borderThickness = t;
        repaint();
    }

    public void setControlSize(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setMaximumSize(new Dimension(width, height));
        setMinimumSize(new Dimension(width, height));
        revalidate();
        repaint();
    }

    // PAINT TOOL
    public void setColor(Color c) {
        this.color = c;
        repaint();
    }

    public void setColorBorder(Color c) {
        this.colorBorder = c;
        repaint();
    }

    // PAINT COMPONENT
    @Override
    protected void paintComponent(Graphics g) {
        int border_roundness = 15;

        Graphics2D g2 = (Graphics2D) g;

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        // Background
        g2.setColor(color);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), border_roundness, border_roundness);

        // Border with thickness
        g2.setColor(colorBorder);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(
                borderThickness / 2,
                borderThickness / 2,
                getWidth() - borderThickness,
                getHeight() - borderThickness,
                border_roundness,
                border_roundness
        );

        super.paintComponent(g);
    }

    // --------- PUBLIC HELPERS ---------

    // For TEXT_FIELD / PASSWORD
    public String getText() {
        if (objectType == ObjectType.TEXTFIELD && component instanceof JTextField tf) {
            return tf.getText();
        }
        if (objectType == ObjectType.PASSFIELD && component instanceof JPasswordField pf) {
            return new String(pf.getPassword());
        }
        return "";
    }
}
