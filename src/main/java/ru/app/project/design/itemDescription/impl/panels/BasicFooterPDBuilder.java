package ru.app.project.design.itemDescription.impl.panels;

import ru.app.project.components.ImageButton;
import ru.app.project.design.itemDescription.interf.panels.FooterPDBuilder;

import javax.swing.*;
import java.awt.*;

public class BasicFooterPDBuilder implements FooterPDBuilder {
    private final JPanel panel;
    public BasicFooterPDBuilder(JPanel panel) {
        this.panel = panel;
        this.panel.setLayout(new GridBagLayout());
    }
    @Override
    public JButton buildJButton1Design() {
        JButton button = new JButton();
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.15;
        constraints.weighty = 1;
        panel.add(button, constraints);
        return button;
    }
    @Override
    public JLabel buildJLabelDesign() {
        JLabel description = new JLabel();
        description.setHorizontalAlignment(SwingConstants.CENTER);
        description.setVerticalAlignment(SwingConstants.CENTER);
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.weightx = 0.7;
        constraints.weighty = 1;
        panel.add(description, constraints);
        return description;
    }

    @Override
    public JButton buildJButton2Design() {
        ImageButton button = new ImageButton("icons/home.png");
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.gridx = 2;
        constraints.gridy = 0;
        constraints.weightx = 0.15;
        constraints.weighty = 0.9;

        panel.add(button, constraints);
        return button;
    }
}
