package ru.app.project.windows.cards.mSelect.panels;

import ru.app.project.config.AppProperties;
import ru.app.project.config.window.MSelectCInfoCfg;
import ru.app.project.design.mSelect.impl.panels.BasicButtonsPDBuilder;
import ru.app.project.design.mSelect.interf.panels.ButtonsPDBuilder;
import ru.app.project.utility.TextSizeCalculator;
import ru.app.project.utility.RelativeTextSizeRatioCalculator;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.MutableComponent;
import ru.app.project.windows.RootWindow;
import ru.app.project.windows.cards.desc.DescC;
import ru.app.project.windows.cards.iDescSelect.IDescSelectC;
import ru.app.project.windows.cards.selector.SelectC;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class ButtonsP extends JPanel implements BasicPanel {
    private RootWindow rootWindow;
    private final ButtonsPDBuilder designBuilder;
    private MSelectCInfoCfg config;
    private MutableComponent parent;

    private final List<JButton> buttons;

    private Double buttonsRatio = null;

    public ButtonsP() {
        this.designBuilder = new BasicButtonsPDBuilder(this);
        this.buttons = new ArrayList<>(3);

        this.rootWindow = null;
        this.config = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        for(int i = 0; i < 3; i++) {
            JButton jButton = designBuilder.buildJButtonDesign();
            buttons.add(jButton);
        }
    }

    @Override
    public void applyLogic() {
        buttons.get(0).addActionListener(event -> rootWindow.showCard(DescC.class));
        buttons.get(1).addActionListener(event -> rootWindow.showCard(IDescSelectC.class));
        buttons.get(2).addActionListener(event -> rootWindow.showCard(SelectC.class));

        if(AppProperties.isTextDynamic()) {
            this.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    if (buttonsRatio == null) {
                        buttonsRatio = RelativeTextSizeRatioCalculator.getJButtonTextRatio(buttons.get(0));
                    }
                    TextSizeCalculator.calculateJButtonTextSize(buttons.get(0), buttonsRatio);
                    TextSizeCalculator.calculateJButtonTextSize(buttons.get(1), buttonsRatio);
                    TextSizeCalculator.calculateJButtonTextSize(buttons.get(2), buttonsRatio);
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
        buttons.get(0).setText(config.getButton1());
        buttons.get(1).setText(config.getButton2());
        buttons.get(2).setText(config.getButton3());
    }

    @Override
    public void setRootWindow(RootWindow rootWindow) {
        this.rootWindow = rootWindow;
    }

    @Override
    public void setConfig(Object config) {
        this.config = (MSelectCInfoCfg)config;
    }
}
