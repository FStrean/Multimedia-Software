package ru.app.project.design.desc.impl;

import ru.app.project.design.desc.interf.DescCDBuilder;
import ru.app.project.components.RelativeLayout;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.cards.desc.panels.ButtonsP;
import ru.app.project.windows.cards.desc.panels.DescriptionP;
import ru.app.project.windows.cards.desc.panels.FooterP;
import ru.app.project.windows.cards.desc.panels.HeaderP;

import javax.swing.*;
import java.awt.*;

public class BasicDescCDBuilder implements DescCDBuilder {
    private final JPanel panel;
    private final JPanel contentPanel;
    public BasicDescCDBuilder(JPanel panel) {
        this.panel = panel;
        this.contentPanel = new JPanel();
        RelativeLayout mainLayout = new RelativeLayout(RelativeLayout.Y_AXIS, 5);
        RelativeLayout contentLayout = new RelativeLayout(RelativeLayout.X_AXIS, 20);

        mainLayout.setFill(true);
        contentLayout.setFill(true);
        this.panel.setBackground(Color.WHITE);
        this.panel.setLayout(mainLayout);
        this.contentPanel.setOpaque(false);
        this.contentPanel.setLayout(contentLayout);
    }

    @Override
    public BasicPanel buildHeaderPanelDesign() {
        HeaderP hp = new HeaderP();

        panel.add(hp, 15.0f);

        return hp;
    }

    @Override
    public BasicPanel buildLeftPanelDesign() {
        ButtonsP bp = new ButtonsP();
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        JPanel p3 = new JPanel();

        p1.setOpaque(false);
        RelativeLayout l = new RelativeLayout(RelativeLayout.Y_AXIS);
        l.setFill(true);
        p1.setLayout(l);
        p2.setOpaque(false);
        p3.setOpaque(false);

        p1.add(p2, 10.0f);
        p1.add(bp, 80.0f);
        p1.add(p3, 10.0f);
        contentPanel.add(p1, 15.0f);

        return bp;
    }

    @Override
    public BasicPanel buildRightPanelDesign() {
        DescriptionP dp = new DescriptionP();

        contentPanel.add(dp, 85.0f);
        panel.add(contentPanel, 75.0f);

        return dp;
    }

    @Override
    public BasicPanel buildFooterPanelDesign() {
        FooterP fp = new FooterP();

        panel.add(fp, 10.0f);

        return fp;
    }

    @Override
    public void paint(Graphics g) {
    }
}
