package ru.app.project.windows.cards;

import ru.app.project.config.cards.ISelectCInfoCfg;
import ru.app.project.design.impl.BasicISelectCDBuilder;
import ru.app.project.design.interf.ISelectCDBuilder;
import ru.app.project.utility.ConfigLoader;
import ru.app.project.windows.template.DynamicPanel;
import ru.app.project.windows.template.StaticPanel;
import ru.app.project.windows.template.DynamicCard;
import ru.app.project.windows.root.RootWindow;

import java.awt.*;
import java.util.List;

public class ISelectC extends DynamicCard {
    private final ISelectCDBuilder designBuilder;

    public ISelectC(RootWindow rootWin) {
        super(rootWin);
        this.designBuilder = new BasicISelectCDBuilder(this);
        ConfigLoader<ISelectCInfoCfg> configLoader = new ConfigLoader<>(ISelectCInfoCfg.class);
        this.cfg = null;
        this.addCfg = configLoader.getCfg();

        this.applyDesign();
        this.applyLogic();
        this.applyDynamicLogic();
        this.applyConfig();
    }

    @Override
    public void applyDesign() {
        StaticPanel headerPanel = designBuilder.buildHeaderPanelDesign();
        StaticPanel underHeaderPanel = designBuilder.buildUnderHeaderPanelDesign();
        StaticPanel leftPanel = designBuilder.buildLeftPanelDesign();
        StaticPanel rightPanel = designBuilder.buildRightPanelDesign();
        StaticPanel footerPanel = designBuilder.buildFooterPanelDesign();

        panels = List.of(footerPanel);
        dynamicPanels = List.of((DynamicPanel) headerPanel, (DynamicPanel) underHeaderPanel, (DynamicPanel) leftPanel, (DynamicPanel) rightPanel);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        designBuilder.paint(g);
    }

    @Override
    public void showState(int id) {
        cfg = ((ISelectCInfoCfg)addCfg).getItems().stream()
                .filter(listItem -> listItem.getId() == id).findFirst()
                .orElse(new ISelectCInfoCfg.Item());

        applyDynamicConfig();
    }
}