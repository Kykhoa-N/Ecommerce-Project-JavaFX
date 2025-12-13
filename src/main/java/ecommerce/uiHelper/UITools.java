package ecommerce.uiHelper;

import javax.swing.*;
import java.awt.*;

public class UITools {

    public static RoundObject createRoundPanel(Color color, int width, int height, int padding) {
        RoundObject panel = new RoundObject(ObjectType.PANEL);

        panel.setColor(color);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setBorder(BorderFactory.createEmptyBorder(padding, padding, padding, padding));
        panel.setBorderThickness(0);
        return panel;
    }

    public static RoundObject createRoundField(JPanel panel, ObjectType objectType, int width, int height) {
        RoundObject field = new RoundObject(objectType);
        field.setMaximumSize(new Dimension(width, height));
        panel.setOpaque(false);
        field.setColor(Theme.TRANSPARENT);
        panel.add(field);
        return field;
    }

    public static RoundObject createRoundButton(JPanel panel, ObjectType objectType, int width, int height, String text, int text_height) {
        RoundObject button = new RoundObject(objectType, text, text_height);
        button.setControlSize(width, height);
        panel.add(button);
        return button;
    }

    public static JLabel createLabel(JPanel panel, String text, int size, boolean bold, int align) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Segoe UI", bold ? Font.BOLD : Font.PLAIN, size));
        label.setForeground(Theme.TEXT);


        if(align == Align.LEFT) {
            label.setAlignmentX(Component.LEFT_ALIGNMENT);
            panel.add(label);
            panel.add(Box.createHorizontalGlue());
        }
        else if(align == Align.RIGHT) {
            label.setAlignmentX(Component.RIGHT_ALIGNMENT);
            panel.add(Box.createHorizontalGlue());
            panel.add(label);
        }
        else {
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(Box.createHorizontalGlue());
            panel.add(label);
            panel.add(Box.createHorizontalGlue());
        }
        return label;
    }

    public static <T> JComboBox<T> createDropBox(JPanel panel, T[] options, int width, int height) {
        JComboBox<T> box = new JComboBox<>(options);
        box.setFocusable(false);
        box.setBackground(Theme.PANEL);
        box.setPreferredSize(new Dimension(width, height));

        int VPad = 6;
        int HPad = 10;

        ((JLabel) box.getRenderer())
                .setBorder(BorderFactory.createEmptyBorder(VPad, HPad, VPad, HPad));

        box.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(
                    JList<?> list, Object value, int index,
                    boolean isSelected, boolean cellHasFocus) {

                JLabel label = (JLabel) super.getListCellRendererComponent(
                        list, value, index, isSelected, cellHasFocus
                );

                label.setBorder(BorderFactory.createEmptyBorder(VPad, HPad, VPad, HPad));
                return label;
            }
        });
        panel.add(box);
        return box;
    }

    public static JScrollPane createScrollPanel(JPanel panel) {
        JScrollPane scroll = new JScrollPane(panel);
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.getViewport().setScrollMode(JViewport.SIMPLE_SCROLL_MODE);
        scroll.getVerticalScrollBar().setUnitIncrement(16);
        return scroll;
    }

    public static JPanel createXContainer(int width, int height) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        return panel;
    }

    public static JPanel createXContainer(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(color);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMaximumSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));
        return panel;
    }

    public static JPanel createYContainer(int width, int height) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));


        return panel;
    }

    public static JPanel createYContainer(Color color, int width, int height) {
        JPanel panel = new JPanel();
        panel.setOpaque(true);
        panel.setBackground(color);
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setMaximumSize(new Dimension(width, height));
        panel.setPreferredSize(new Dimension(width, height));
        panel.setMinimumSize(new Dimension(width, height));

        return panel;
    }


}
