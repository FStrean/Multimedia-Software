package ru.app.project.design.itemDescription.interf;

import ru.app.project.windows.cards.itemDescription.panels.FooterP;
import ru.app.project.windows.cards.itemDescription.panels.HeaderP;
import ru.app.project.windows.cards.itemDescription.panels.ImagesP;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

public interface ItemDescriptionCDBuilder {
    HeaderP buildHeaderPanelDesign();
    ImagesP buildImagePanelDesign();
    EmbeddedMediaPlayerComponent buildVideoPlayerPanelDesign();
    FooterP buildFooterPanelDesign();

}