package ru.app.project.design.interf;

import ru.app.project.windows.BasicPanel;

import java.awt.*;

public interface MSelectCDBuilder {
    BasicPanel buildHeaderPanelDesign();
    BasicPanel buildCenterPanelDesign();
    BasicPanel buildFooterPanelDesign();
    void paint(Graphics g);
}