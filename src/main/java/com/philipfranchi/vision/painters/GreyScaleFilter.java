package com.philipfranchi.vision.painters;

import java.awt.image.BufferedImage;

public class GreyScaleFilter extends BaseFilter {

    public BufferedImage processImage(BufferedImage image) {
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        BufferedImage processedImage = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_RGB);

        int[] rgb = image.getRGB(0,0, imageWidth, imageHeight,null,0, imageWidth);
        int[] processedRgb = new int[rgb.length];
        for(int c = 0; c < rgb.length; c ++) {
            int[] pixel = rgbFromInt(rgb[c]);
            pixel[0] = pixel[1];
            pixel[2] = pixel[1];
            processedRgb[c] = rgbToInt(pixel[0],pixel[1], pixel[2]);
        }

        processedImage.setRGB(0, 0, imageWidth, imageHeight, processedRgb, 0, imageWidth);
        return processedImage;
    }

    private int rgbToInt(int r, int g, int b) {
        return (r << 16) + (g << 8) + b;
    }

    private int[] rgbFromInt(int rgb) {
        int red = (rgb >> 16) & 0xFF;
        int green = (rgb >> 8) & 0xFF;
        int blue = rgb & 0xFF;
        int[] rgbValues = new int[]{red, green, blue};
        return rgbValues;
    }
}
