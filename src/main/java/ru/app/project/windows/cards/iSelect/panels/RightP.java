package ru.app.project.windows.cards.iSelect.panels;

import ru.app.project.components.JImage;
import ru.app.project.config.window.ISelectCInfoCfg;
import ru.app.project.design.iSelect.impl.panels.BasicLeftPDBuilder;
import ru.app.project.design.iSelect.impl.panels.BasicRightPDBuilder;
import ru.app.project.design.iSelect.interf.panels.LeftPDBuilder;
import ru.app.project.design.iSelect.interf.panels.RightPDBuilder;
import ru.app.project.windows.BasicPanel;
import ru.app.project.windows.MutableComponent;
import ru.app.project.windows.RootWindow;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class RightP extends JPanel implements BasicPanel {
    private RootWindow rootWin;
    private final RightPDBuilder designBuilder;
    private ISelectCInfoCfg.Item cfg;
    private ISelectCInfoCfg addCfg;
    private MutableComponent parent;

    private JLabel desc;
    private JLabel desc1;
    private JImage img1;
    private JLabel desc2;
    private JImage img2;

    public RightP() {
        this.designBuilder = new BasicRightPDBuilder(this);

        this.cfg = null;
        this.rootWin = null;

        this.applyDesign();
        this.applyLogic();
    }

    @Override
    public void applyDesign() {
        //desc = designBuilder.buildJLabelDesign();
        desc1 = designBuilder.buildJLabel1Design();
        img1 = designBuilder.buildJImage1Design();
        desc2 = designBuilder.buildJLabel2Design();
        img2 = designBuilder.buildJImage2Design();

        //desc.setVisible(false);
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
        if(!cfg.getRightDesc1().isEmpty()) {
            desc1.setText("<html>" + addCfg.getRightDesc1Style() + cfg.getRightDesc1() + "</html>");
            desc1.setVisible(true);
            allInvisible = false;
        }
        if(!cfg.getRightImg1().isEmpty()) {
            addImageToFrame(img1, cfg.getRightImg1());
            allInvisible = false;
        }
        if(!cfg.getRightDesc2().isEmpty()) {
            desc2.setText("<html>" + addCfg.getRightDesc2Style() + cfg.getRightDesc2() + "</html>");
            desc2.setVisible(true);
            allInvisible = false;
        }
        if(!cfg.getRightImg2().isEmpty()) {
            addImageToFrame(img2, cfg.getRightImg2());
            allInvisible = false;
        }

        setVisible(!allInvisible);
    }

    @Override
    public void runOnLeaveAction() {
        //desc.getParent().setVisible(false);
        desc1.setVisible(false);
        img1.removeImage();
        img1.getParent().setVisible(false);
        desc2.setVisible(false);
        img2.removeImage();
        img2.getParent().setVisible(false);
    }

    @Override
    public void setParent(MutableComponent parent) {
        this.parent = parent;
    }

    @Override
    public void setRootWin(RootWindow rootWin) {
        this.rootWin = rootWin;
    }

    @Override
    public void setCfg(Object cfg) {
        this.cfg = (ISelectCInfoCfg.Item) cfg;
    }

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
