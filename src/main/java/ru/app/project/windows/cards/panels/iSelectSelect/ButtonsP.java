package ru.app.project.windows.cards.panels.iSelectSelect;

import ru.app.project.config.AppProperties;
import ru.app.project.config.cards.ISelectSelectCInfoCfg;
import ru.app.project.design.impl.panels.iSelectSelect.BasicButtonsPDBuilder;
import ru.app.project.design.interf.panels.iSelectSelect.ButtonsPDBuilder;
import ru.app.project.utility.RelTSRatioCalc;
import ru.app.project.utility.TSCalc;
import ru.app.project.windows.template.StaticCard;
import ru.app.project.windows.template.StaticPanel;
import ru.app.project.windows.root.RootWindow;
import ru.app.project.windows.cards.ISelectC;

import javax.swing.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import java.util.List;

public class ButtonsP extends JPanel implements StaticPanel {
    private RootWindow rootWin;
    private final ButtonsPDBuilder designBuilder;
    private ISelectSelectCInfoCfg cfg;
    private StaticCard parent;

    private JLabel desc;

    private final List<JButton> buttons;

    private Double descRatio = null;
    private Double btnRatio = null;

    public ButtonsP() {
        this.designBuilder = new BasicButtonsPDBuilder(this);
        this.buttons = new ArrayList<>();

        this.rootWin = null;
        this.cfg = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        desc = designBuilder.buildJLabelDesign();
        for(int i = 0; i < AppProperties.getNumOfItemsInISelectSelect(); i++) {
            JButton jButton = designBuilder.buildJButtonDesign();
            buttons.add(jButton);
        }
    }

    @Override
    public void applyLogic() {
        if(AppProperties.isTextDynamic()) {
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    if (descRatio == null) {
                        descRatio = RelTSRatioCalc.getTextRatio(desc);
                        btnRatio = RelTSRatioCalc.getTextRatio(buttons.get(0));
                    }
                }
            });
        }

        if(AppProperties.isTextDynamic()) {
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    TSCalc.calcTextSize(desc, descRatio);
                }
            });
        }
        for(int i = 0; i < AppProperties.getNumOfItemsInISelectSelect();) {
            int id = i + 1;
            buttons.get(i).addActionListener(event -> rootWin.showCard(parent, ISelectC.class, id));

            if(AppProperties.isTextDynamic()) {
                int finalI = i;
                addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        TSCalc.calcTextSize(buttons.get(finalI), btnRatio);
                    }
                });
            }
            //In case if item has several pages
            i += AppProperties.getNumOfPagesInISelect()[i];
        }
    }

    @Override
    public void runOnLeaveAction() {

    }

    @Override
    public void setParent(StaticCard parent) {
        this.parent = parent;
    }

    @Override
    public void applyConfig() {
        desc.setText(cfg.getBtnHeader());
        for(int i = 0; i < AppProperties.getNumOfItemsInISelectSelect(); i++) {
            buttons.get(i).setText(cfg.getSelectButtons().get(i));
        }
    }

    @Override
    public void setRootWin(RootWindow rootWin) {
        this.rootWin = rootWin;
    }

    @Override
    public void setCfg(Object cfg) {
        this.cfg = (ISelectSelectCInfoCfg) cfg;
    }
}
