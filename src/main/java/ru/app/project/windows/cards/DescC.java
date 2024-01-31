package ru.app.project.windows.cards;

import ru.app.project.config.cards.DescCInfoCfg;
import ru.app.project.design.impl.BasicDescCDBuilder;
import ru.app.project.design.interf.DescCDBuilder;
import ru.app.project.utility.ConfigLoader;
import ru.app.project.windows.StaticCard;
import ru.app.project.windows.StaticPanel;
import ru.app.project.windows.RootWindow;

import java.awt.*;
import java.util.List;

public class DescC extends StaticCard {
    private final DescCDBuilder designBuilder;

    public DescC(RootWindow rootWin) {
        super(rootWin);
        this.designBuilder = new BasicDescCDBuilder(this);
        ConfigLoader<DescCInfoCfg> configLoader = new ConfigLoader<>(DescCInfoCfg.class);
        this.cfg = configLoader.getCfg();

        this.applyDesign();
        this.applyLogic();
        this.applyConfig();
    }

    @Override
    public void applyDesign() {
        StaticPanel headerPanel = designBuilder.buildHeaderPanelDesign();
        StaticPanel buttonsPanel = designBuilder.buildLeftPanelDesign();
        StaticPanel descriptionPanel = designBuilder.buildRightPanelDesign();
        StaticPanel footerPanel = designBuilder.buildFooterPanelDesign();

        panels = List.of(headerPanel, buttonsPanel, descriptionPanel, footerPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        designBuilder.paint(g);
    }
}
