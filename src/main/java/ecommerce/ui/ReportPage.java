package ecommerce.ui;

import ecommerce.model.*;
import ecommerce.service.*;
import ecommerce.uiHelper.*;
import ecommerce.app.SwingUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class ReportPage extends JPanel {

    private final SwingUI parent;
    private final ReportService reportService;

    public ReportPage(SwingUI parent, ReportService reportService) {
        this.parent = parent;
        this.reportService = reportService;

        setOpaque(false);
        setLayout(new GridBagLayout());
        buildUI();
    }

    private void buildUI() {

        // CREATE REPORT PANEL
        RoundObject reportPanel = UITools.createRoundPanel(Color.ORANGE,(int) (parent.getWidth()*0.8313), (int) (parent.getHeight()*.9549), 40);
        reportPanel.setLayout(new BoxLayout(reportPanel, BoxLayout.Y_AXIS));

        add(reportPanel);
    }
}
