package ru.app.project.design.iDescSelect.impl;

import ru.app.project.design.iDescSelect.interf.IDescSelectCDBuilder;
import ru.app.project.components.RelativeLayout;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.cards.iDescSelect.panels.ButtonsP;
import ru.app.project.windows.cards.iDescSelect.panels.FooterP;
import ru.app.project.windows.cards.iDescSelect.panels.HeaderP;

import javax.swing.*;
import java.awt.*;

public class BasicIDescSelectCDBuilder implements IDescSelectCDBuilder {
    private final JPanel panel;

    public BasicIDescSelectCDBuilder(JPanel panel) {
        this.panel = panel;
        RelativeLayout layout = new RelativeLayout(RelativeLayout.Y_AXIS, 5);

        layout.setFill(true);
        this.panel.setLayout(layout);
        this.panel.setBackground(Color.WHITE);
    }

    @Override
    public BasicPanel buildHeaderPanelDesign() {
        HeaderP hp = new HeaderP();

        panel.add(hp, 15.0f);

        return hp;
    }

    @Override
    public BasicPanel buildCenterPanelDesign() {
        ButtonsP bp = new ButtonsP();

        panel.add(bp, 75.0f);

        return bp;
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
