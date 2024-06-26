package ru.app.project.windows.cards.panels.iSelect;

import ru.app.project.components.JImage;
import ru.app.project.config.cards.ISelectCInfoCfg;
import ru.app.project.design.impl.panels.iSelect.BasicLeftPDBuilder;
import ru.app.project.design.interf.panels.iSelect.LeftPDBuilder;
import ru.app.project.windows.root.RootWindow;
import ru.app.project.windows.template.DynamicCard;
import ru.app.project.windows.template.DynamicPanel;
import ru.app.project.windows.template.StaticCard;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class LeftP extends JPanel implements DynamicPanel {
    private RootWindow rootWin;
    private final LeftPDBuilder designBuilder;
    private ISelectCInfoCfg.Item cfg;
    private ISelectCInfoCfg addCfg;
    private DynamicCard parent;

    private JLabel desc;
    private JLabel desc1;
    private JImage img1;
    private JLabel desc2;
    private JImage img2;

    public LeftP() {
        this.designBuilder = new BasicLeftPDBuilder(this);

        this.cfg = null;
        this.rootWin = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        desc1 = designBuilder.buildJLabel1Design();
        img1 = designBuilder.buildJImage1Design();
        desc2 = designBuilder.buildJLabel2Design();
        img2 = designBuilder.buildJImage2Design();

        desc1.setVisible(false);
        img1.getParent().setVisible(false);
        desc2.setVisible(false);
        img2.getParent().setVisible(false);
    }

    @Override
    public void applyLogic() {
    }

    @Override
    public void applyConfig() {
        boolean allInvisible = true;
        if(!cfg.getLeftDesc1().isEmpty()) {
            desc1.setText("<html>" + addCfg.getLeftDesc1Style() + cfg.getLeftDesc1() + "</html>");
            desc1.setVisible(true);
            allInvisible = false;
        }
        if(!cfg.getLeftImg1().isEmpty()) {
            addImageToFrame(img1, cfg.getLeftImg1());
            allInvisible = false;
        }
        if(!cfg.getLeftDesc2().isEmpty()) {
            desc2.setText("<html>" + addCfg.getLeftDesc2Style() + cfg.getLeftDesc2() + "</html>");
            desc2.setVisible(true);
            allInvisible = false;
        }
        if(!cfg.getLeftImg2().isEmpty()) {
            addImageToFrame(img2, cfg.getLeftImg2());
            allInvisible = false;
        }

        setVisible(!allInvisible);
    }

    @Override
    public void runOnLeaveAction() {
        desc1.setVisible(false);
        img1.removeImage();
        img1.getParent().setVisible(false);
        desc2.setVisible(false);
        img2.removeImage();
        img2.getParent().setVisible(false);
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
        this.cfg = (ISelectCInfoCfg.Item) cfg;
    }

    @Override
    public void setAddCfg(Object cfg) {
        this.addCfg = (ISelectCInfoCfg) cfg;
    }

    private void addImageToFrame(JImage jImage, String path) {
        try {
            load(jImage, path);
        } catch (IOException e) {
            JOptionPane.showMessageDialog((JFrame) rootWin, e.getMessage(),
                    "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void load(JImage jImage, String path) throws IOException {
        File imageFile = new File(path);
        BufferedImage image = ImageIO.read(imageFile);

        if(image == null) {
            throw new IOException("Image is null");
        }

        jImage.setImg(image);
        jImage.getParent().setVisible(true);
    }
}
