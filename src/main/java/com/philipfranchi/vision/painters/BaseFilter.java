package com.philipfranchi.vision.painters;

import com.github.sarxos.webcam.WebcamPanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class BaseFilter implements WebcamPanel.Painter {

    private static final Image noImage = new BufferedImage(1, 1, BufferedImage.TYPE_4BYTE_ABGR);


    @Override
    public void paintPanel(WebcamPanel wp, Graphics2D g2) {
        int w1 = wp.getSize().width;
        int h1 = wp.getSize().height;
        int w2 = noImage.getWidth(null);
        int h2 = noImage.getHeight(null);

        g2.setColor(wp.getBackground());
        g2.fillRect(0, 0, w1, h1);
        g2.drawImage(noImage, (w1 - w2) / 2, (h1 - h2) / 2, null);
    }

    @Override
    public void paintImage(WebcamPanel wp, BufferedImage image, Graphics2D g2) {

        BufferedImage processedImage = processImage(image);
        g2.drawImage(image,  0, 0, null);
        g2.drawImage(processedImage,image.getWidth(), 0, null);
    }

    public abstract BufferedImage processImage(BufferedImage image);
}
