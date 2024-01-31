package ru.app.project.design.interf;

import ru.app.project.windows.StaticPanel;

import java.awt.*;

public interface MSelectCDBuilder {
    StaticPanel buildHeaderPanelDesign();
    StaticPanel buildCenterPanelDesign();
    StaticPanel buildFooterPanelDesign();
    void paint(Graphics g);
}
