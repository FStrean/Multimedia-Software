package ru.app.project.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.ImageObserver;

public class ImageButton extends JPanel {
    private final JButton button;
    private final Image image;
    private final ImageObserver imageObserver;

    private final int imageWidth;
    private final int imageHeight;

    private int newImageWidth = -1;
    private int newImageHeight = -1;

    private int x = 0;
    private int y = 0;
    private float topRatio = 0.5f, leftRatio = 0.5f, bottomRatio = 0.5f, rightRatio = 0.5f;
    private int top = 0, left = 0, bottom = 0, right = 0;

    private boolean isJustResized = false;

    public ImageButton(String path){
        super();
        ImageIcon icon = new ImageIcon(path);
        this.image = icon.getImage();
        this.imageObserver = icon.getImageObserver();

        this.imageWidth = this.image.getWidth(this.imageObserver);
        this.imageHeight = this.image.getHeight(this.imageObserver);

        button = new JButton() {
            @Override
            public void paintComponent(Graphics g) {
                setSize(new Dimension(newImageWidth, newImageHeight));
                g.drawImage(image, x, y, newImageWidth, newImageHeight, imageObserver);
            }
        };

        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int panelWidth = ImageButton.this.getWidth();
                int panelHeight = ImageButton.this.getHeight();

                double intermediateImageWidthRatio;
                if (panelWidth == 0) {
                    intermediateImageWidthRatio = imageWidth;
                } else {
                    intermediateImageWidthRatio = (double) imageWidth / panelWidth;
                }

                double intermediateImageHeightRatio;
                if (panelHeight == 0) {
                    intermediateImageHeightRatio = imageHeight;
                } else {
                    intermediateImageHeightRatio = (double) imageHeight / panelHeight;
                }

                double imageRatio = Math.max(intermediateImageWidthRatio, intermediateImageHeightRatio);

                newImageWidth = (int) ((double) imageWidth / imageRatio);
                newImageHeight = (int) ((double) imageHeight / imageRatio);

                top = (int) ((float) (panelHeight - newImageHeight) * topRatio);
                left = (int) ((float) (panelWidth - newImageWidth) * leftRatio);
                bottom = (int) ((float) (panelHeight - newImageHeight) * bottomRatio);
                right = (int) ((float) (panelWidth - newImageWidth) * rightRatio);

                ImageButton.this.setBorder(new EmptyBorder(top, left, bottom, right));
                updateUI();
            }

        });

        this.setLayout(new BorderLayout());
        this.add(button);
    }

    public void setAlign(String xAlign, String yAlign) {
        if (xAlign.equals(BorderLayout.LINE_START)) {
            leftRatio = 0.0f;
            rightRatio = 1.0f;
        } else if (xAlign.equals(BorderLayout.CENTER)) {
            leftRatio = 0.5f;
            rightRatio = 0.5f;
        } else if (xAlign.equals(BorderLayout.LINE_END)) {
            leftRatio = 1.0f;
            rightRatio = 0.0f;
        }

        if (yAlign.equals(BorderLayout.LINE_START)) {
            topRatio = 0.0f;
            bottomRatio = 1.0f;
        } else if (yAlign.equals(BorderLayout.CENTER)) {
            topRatio = 0.5f;
            bottomRatio = 0.5f;
        } else if (yAlign.equals(BorderLayout.LINE_END)) {
            topRatio = 1.0f;
            bottomRatio = 0.0f;
        }
    }

    public void addActionListener(ActionListener l) {
        button.addActionListener(l);
    }
}
