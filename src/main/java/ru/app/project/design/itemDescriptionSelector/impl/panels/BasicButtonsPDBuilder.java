package ru.app.project.design.itemDescriptionSelector.impl.panels;

import ru.app.project.design.itemDescriptionSelector.interf.panels.ButtonsPDBuilder;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BasicButtonsPDBuilder implements ButtonsPDBuilder {
    private JPanel panel;
    public BasicButtonsPDBuilder(JPanel panel) {
        this.panel = panel;
        this.panel.setLayout(new GridLayout(0,5));
    }

    @Override
    public JButton buildJButtonDesign() {
        JButton button = new JButton();
        button.setHorizontalAlignment(SwingConstants.CENTER);
        button.setVerticalAlignment(SwingConstants.CENTER);
        JPanel jPanel = new JPanel(new BorderLayout());
        jPanel.add(button);
        jPanel.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(jPanel);
        return button;
    }
}
