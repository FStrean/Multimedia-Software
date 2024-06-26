package ru.app.project.design.interf;

import ru.app.project.windows.template.StaticPanel;

import java.awt.*;

public interface VideoCDBuilder {
    StaticPanel buildHeaderPanelDesign();
    StaticPanel buildCenterPanelDesign();
    StaticPanel buildFooterPanelDesign();

    void paint(Graphics g);
}
