package ru.app.project.windows.cards.panels.iDesc;

import ru.app.project.algo.RingBuffer;
import ru.app.project.components.JImage;
import ru.app.project.config.AppProperties;
import ru.app.project.config.cards.IDescCInfoCfg;
import ru.app.project.design.impl.panels.iDesc.BasicImagesPDBuilder;
import ru.app.project.design.interf.panels.iDesc.ImagesPDBuilder;
import ru.app.project.windows.root.RootWindow;
import ru.app.project.windows.template.DynamicCard;
import ru.app.project.windows.template.DynamicPanel;
import ru.app.project.windows.template.StaticCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagesP extends JPanel implements DynamicPanel {
    private RootWindow rootWin;
    private final ImagesPDBuilder designBuilder;
    private IDescCInfoCfg.Item cfg;
    private DynamicCard parent;

    private RingBuffer<JImage> images;

    public ImagesP() {
        this.designBuilder = new BasicImagesPDBuilder(this);

        this.cfg = null;
        this.rootWin = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        List<JImage> jImages = new ArrayList<>(AppProperties.getMaxNumOfImgInIDesc());
        for (int i = 0; i < AppProperties.getMaxNumOfImgInIDesc(); i++) {
            JImage image = designBuilder.buildJImageDesign();
            jImages.add(image);
            image.setVisible(false);
        }

        images = new RingBuffer<>(jImages);
    }

    @Override
    public void applyLogic() {
    }

    @Override
    public void applyConfig() {
        for(String path : cfg.getImages()) {
            if(!path.isEmpty()) {
                addImageToFrame(path);
            }
        }
    }

    @Override
    public void runOnLeaveAction() {
        images.reset();
        for(JImage image : images.getElements()) {
            image.removeImage();
            image.setVisible(false);
        }
    }

    @Override
    public void setParent(StaticCard parent) {
        this.parent = (DynamicCard) parent;
    }

    @Override
    public void setRootWin(RootWindow rootWin) {
        this.rootWin = rootWin;
    }

    @Override
    public void setCfg(Object cfg) {
        this.cfg = (IDescCInfoCfg.Item) cfg;
    }
    @Override
    public void setAddCfg(Object config) {

    }

    private void addImageToFrame(String path) {
        try {
            load(path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((JFrame) rootWin, e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void load(String path) throws IOException {
        JImage jImage = images.getNext();

        File imageFile = new File(path);
        BufferedImage image = ImageIO.read(imageFile);

        if(image == null) {
            throw new IOException("Image is null");
        }

        jImage.setImg(image);
        jImage.setVisible(true);
    }
}
