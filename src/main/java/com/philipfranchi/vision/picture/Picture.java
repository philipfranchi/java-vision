package com.philipfranchi.vision.picture;


import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Picture {

    private final BufferedImage image;
    private final int width;
    private final int height;

    private final List<Pixel> pixels;

    public Picture(BufferedImage image) {
        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();

        int[] rgb = image.getRGB(0,0,
                image.getWidth(), image.getHeight(), null, 0, image.getWidth());

        List<Pixel> pixels = new ArrayList<>();
        for(int c = 0; c < rgb.length; c++) {

            int x = c % width;
            int y = (c - x) / width;

            Color color = new Color(rgb[c]);
            Pixel pixel = new Pixel(x, y, color);
            pixels.add(pixel);
        }

        this.pixels = pixels;
    }

    public BufferedImage toBufferedImage() {
        BufferedImage converted = new BufferedImage(width, height, BufferedImage.TYPE_3BYTE_BGR);
        int[] rgb = new int[pixels.size()];
        for(int p = 0; p < rgb.length; p++) {
            rgb[p] = pixels.get(p).getColor().getRGB();
        }
        converted.setRGB(0,0,width, height, rgb, 0, width);
        return converted;
    }

    public Color getPixelColor(int x, int y) {
        return pixels.get((y*width) + x).getColor();
    }

    public void setPixelColor(int x, int y, Color color) {
        pixels.get((y*width) + x).setColor(color);
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

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
