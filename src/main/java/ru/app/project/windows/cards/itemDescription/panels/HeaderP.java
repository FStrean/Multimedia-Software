package ru.app.project.windows.cards.itemDescription.panels;

import ru.app.project.components.ImageButton;
import ru.app.project.config.AppProperties;
import ru.app.project.config.window.ItemDescriptionCStateConfig;
import ru.app.project.design.itemDescription.impl.panels.BasicHeaderPDBuilder;
import ru.app.project.design.itemDescription.interf.panels.HeaderPDBuilder;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.MutableComponent;
import ru.app.project.windows.RootWindow;
import ru.app.project.windows.cards.itemDescription.ItemDescriptionC;

import javax.swing.*;

public class HeaderP extends JPanel implements BasicPanel {
    private RootWindow rootWindow;
    private final HeaderPDBuilder designBuilder;
    private ItemDescriptionCStateConfig.Item config;
    private MutableComponent parent;

    private ImageButton buttonLeft;
    private ImageButton buttonRight;
    private JLabel description;

    private int id;

    public HeaderP() {
        this.designBuilder = new BasicHeaderPDBuilder(this);

        this.config = null;
        this.rootWindow = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        buttonLeft = designBuilder.buildJButton1Design();
        buttonRight = designBuilder.buildJButton2Design();
        description = designBuilder.buildJLabelDesign();
    }

    @Override
    public void applyLogic(){
        int maxId = AppProperties.getNumberOfItemsInItemDescriptionWindow();
        buttonLeft.addActionListener(event -> {
            parent.runOnLeaveAction();
            rootWindow.showCard(ItemDescriptionC.class, (id - 1) < 1 ? maxId : (id - 1));
        });
        buttonRight.addActionListener(event -> {
            parent.runOnLeaveAction();
            rootWindow.showCard(ItemDescriptionC.class, (id + 1) > maxId ? 1 : (id + 1));
        });
    }

    @Override
    public void applyConfig() {
        id = config.getId();
        description.setText(config.getDescription());
    }

    @Override
    public void runOnLeaveAction() {

    }

    @Override
    public void setParent(MutableComponent parent) {
        this.parent = parent;
    }

    @Override
    public void setRootWindow(RootWindow rootWindow) {
        this.rootWindow = rootWindow;
    }

    @Override
    public void setConfig(Object config) {
        this.config = (ItemDescriptionCStateConfig.Item)config;
    }
}
