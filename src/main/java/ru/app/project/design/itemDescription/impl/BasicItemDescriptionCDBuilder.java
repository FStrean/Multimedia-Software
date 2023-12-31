package ru.app.project.design.itemDescription.impl;

import ru.app.project.design.itemDescription.interf.ItemDescriptionCDBuilder;
import ru.app.project.utility.RelativeLayout;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.cards.itemDescription.panels.*;
//import ru.app.project.windows.cards.itemDescription.panels.VideosP;

import javax.swing.*;

public class BasicItemDescriptionCDBuilder implements ItemDescriptionCDBuilder {
    private final JPanel panel;

    private final JPanel contentPanel;
    public BasicItemDescriptionCDBuilder(JPanel panel) {
        this.panel = panel;

        this.contentPanel = new JPanel();

        RelativeLayout mainLayout = new RelativeLayout(RelativeLayout.Y_AXIS);
        RelativeLayout headerLayout = new RelativeLayout(RelativeLayout.X_AXIS);
        RelativeLayout contentLayout = new RelativeLayout(RelativeLayout.X_AXIS);

        mainLayout.setFill(true);
        headerLayout.setFill(true);
        contentLayout.setFill(true);

        this.panel.setLayout(mainLayout);
        this.contentPanel.setLayout(contentLayout);
    }

    @Override
    public BasicPanel buildHeaderPanelDesign() {
        HeaderP headerPanel = new HeaderP();
        panel.add(headerPanel, 15.0f);
        return headerPanel;
    }

    @Override
    public BasicPanel buildLeftPanelDesign() {
        ImagesP imagesP = new ImagesP();
        contentPanel.add(imagesP, 35.0f);
        return imagesP;
    }

    @Override
    public BasicPanel buildRightPanelDesign() {
        DescriptionP descriptionPanel = new DescriptionP();
        contentPanel.add(descriptionPanel, 65.0f);
        panel.add(contentPanel, 75.0f);
        return descriptionPanel;
    }


    @Override
    public BasicPanel buildFooterPanelDesign() {
        FooterP header1Panel = new FooterP();
        panel.add(header1Panel, 10.0f);
        return header1Panel;
    }
}
