package ru.app.project.design.iDescSelect.impl.panels;

import ru.app.project.components.JImageButton;
import ru.app.project.design.iDescSelect.interf.panels.FooterPDBuilder;
import ru.app.project.components.RelativeLayout;

import javax.swing.*;

public class BasicFooterPDBuilder implements FooterPDBuilder {
    private final JPanel panel;
    public BasicFooterPDBuilder(JPanel panel) {
        this.panel = panel;
        RelativeLayout layout = new RelativeLayout(RelativeLayout.X_AXIS, 20);

        layout.setFill(true);
        this.panel.setOpaque(false);
        this.panel.setLayout(layout);
    }
    @Override
    public JLabel buildJLabelDesign() {
        JLabel text = new JLabel();

        text.setOpaque(false);
        text.setHorizontalAlignment(SwingConstants.CENTER);
        text.setVerticalAlignment(SwingConstants.CENTER);

        panel.add(text, 95.0f);

        return text;
    }

    @Override
    public JImageButton buildJButtonDesign() {
        JImageButton btn = new JImageButton("icons/home.png");

        panel.add(btn, 5.0f);

        return btn;
    }
}
