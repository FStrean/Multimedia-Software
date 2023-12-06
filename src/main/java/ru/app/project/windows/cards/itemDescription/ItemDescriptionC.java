package ru.app.project.windows.cards.itemDescription;

import ru.app.project.config.AppProperties;
import ru.app.project.config.window.ItemDescriptionWStateConfig;
import ru.app.project.design.itemDescription.interf.ItemDescriptionCDBuilder;
import ru.app.project.design.itemDescription.impl.BasicItemDescriptionCDBuilder;
import ru.app.project.utility.ItemDescriptionCStateUtil;
import ru.app.project.windows.BasicCard;
import ru.app.project.windows.RootWindow;
import ru.app.project.windows.cards.itemDescription.panels.FooterP;
import ru.app.project.windows.cards.itemDescription.panels.HeaderP;
import ru.app.project.windows.cards.itemDescription.panels.ImagesP;
import ru.app.project.windows.cards.mainSelector.MainSelectorC;
import uk.co.caprica.vlcj.player.component.EmbeddedMediaPlayerComponent;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class ItemDescriptionC extends JPanel implements BasicCard {
    private final RootWindow rootWindow;

    private final ItemDescriptionCStateUtil itemWindowState =
            new ItemDescriptionCStateUtil(AppProperties.getItemWindowContentConfigName());
    private ItemDescriptionWStateConfig.Item config;

    private final ItemDescriptionCDBuilder designBuilder;

    private HeaderP headerPanel;
    private ImagesP imagesPanel;
    private EmbeddedMediaPlayerComponent videoPlayerPanel;
    private FooterP footerPanel;

    public ItemDescriptionC(RootWindow rootWindow) throws HeadlessException {
        this.rootWindow = rootWindow;
        this.designBuilder = new BasicItemDescriptionCDBuilder(this);

        this.config = null;

        this.applyDesign();
        this.applyLogic();
    }

    public void showState(int id) {
        config = itemWindowState.load(id);
        imagesPanel.setConfig(config);
        headerPanel.setConfig(config);
        loadConfig();
    }

    @Override
    public void applyDesign() {
        headerPanel = designBuilder.buildHeaderPanelDesign();
        imagesPanel = designBuilder.buildImagePanelDesign();
        videoPlayerPanel = designBuilder.buildVideoPlayerPanelDesign();
        footerPanel = designBuilder.buildFooterPanelDesign();
    }

    @Override
    public void applyLogic() {
        videoPlayerPanel.setVisible(false);
        videoPlayerPanel.mediaPlayer().controls().setRepeat(true);
        videoPlayerPanel.videoSurfaceComponent().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                videoPlayerPanel.mediaPlayer().controls()
                        .setPause(videoPlayerPanel.mediaPlayer().status().isPlaying());
            }
        });

        headerPanel.setParent(this);
        imagesPanel.setParent(this);
        footerPanel.setParent(this);
        headerPanel.setRootWindow(rootWindow);
        imagesPanel.setRootWindow(rootWindow);
        footerPanel.setRootWindow(rootWindow);
    }

    @Override
    public void loadConfig() {
        headerPanel.loadConfig();

        if(!config.getVideo().isEmpty()) {
            videoPlayerPanel.setVisible(true);
            videoPlayerPanel.mediaPlayer().media().play(config.getVideo());
        }

        imagesPanel.loadConfig();
    }

    @Override
    public void runOnLeaveAction() {
        imagesPanel.runOnLeaveAction();
        this.videoPlayerPanel.mediaPlayer().controls().stop();
        this.videoPlayerPanel.setVisible(false);

        rootWindow.showCard(MainSelectorC.class);
    }
}