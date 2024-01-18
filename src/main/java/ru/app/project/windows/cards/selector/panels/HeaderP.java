package ru.app.project.windows.cards.selector.panels;

import ru.app.project.config.AppProperties;
import ru.app.project.config.window.SelectCInfoCfg;
import ru.app.project.design.select.impl.panels.BasicHeaderPDBuilder;
import ru.app.project.design.select.interf.panels.HeaderPDBuilder;
import ru.app.project.utility.TextSizeCalculator;
import ru.app.project.utility.RelativeTextSizeRatioCalculator;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.MutableComponent;
import ru.app.project.windows.RootWindow;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class HeaderP extends JPanel implements BasicPanel {
    private RootWindow rootWindow;
    private final HeaderPDBuilder designBuilder;
    private SelectCInfoCfg config;
    private MutableComponent parent;

    private JLabel description;

    private Double descriptionRatio = null;

    public HeaderP() {
        designBuilder = new BasicHeaderPDBuilder(this);

        this.rootWindow = null;
        this.config = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        this.description = designBuilder.buildJLabelDesign();
    }

    @Override
    public void applyLogic() {
        if(AppProperties.isTextDynamic()) {
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    if (descriptionRatio == null) {
                        descriptionRatio = RelativeTextSizeRatioCalculator.getJLabelTextRatio(description);
                    }

                    TextSizeCalculator.calculateJLabelTextSize(description, descriptionRatio);
                }
            });
        }
    }

    @Override
    public void runOnLeaveAction() {

    }

    @Override
    public void setParent(MutableComponent parent) {
        this.parent = parent;
    }

    @Override
    public void applyConfig() {
        description.setText(config.getHHeader());
    }

    @Override
    public void setRootWindow(RootWindow rootWindow) {
        this.rootWindow = rootWindow;
    }

    @Override
    public void setConfig(Object config) {
        this.config = (SelectCInfoCfg)config;
    }
}
