package com.philipfranchi.vision.painters;

import com.philipfranchi.vision.painters.util.PaletteCreator;
import com.philipfranchi.vision.picture.Picture;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.regex.Pattern;

public class FloydSteinbergDitheringFilter extends BaseFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(FloydSteinbergDitheringFilter.class);

    private static final Color[] palette = PaletteCreator.defaultPalette;

    private Color closestPaletteColor(Color c) {
        Color bestColor = Color.WHITE;
        for(Color curColor : palette) {
            if(distanceBetweenColor(c, curColor) < distanceBetweenColor(c, bestColor)) {
                bestColor = curColor;
            }
        }
        //LOGGER.info("{}", bestColor);
        return bestColor;
    }

    private double distanceBetweenColor(Color c1, Color c2){
        double diffB = Math.pow( c1.getBlue() - c2.getBlue(), 2);
        double diffG = Math.pow( c1.getGreen() - c2.getGreen(), 2);
        double diffR = Math.pow( c1.getRed() - c2.getRed(), 2);

        //LOGGER.info("DIST: {}", Math.sqrt(diffR + diffB + diffG));
        return Math.sqrt(diffR + diffB + diffG);
    }

    private double[] computeColorError(Color oldColor, Color newColor) {
        return new double[]{
                (oldColor.getRed() - newColor.getRed()),
                (oldColor.getGreen() - newColor.getGreen()),
                (oldColor.getBlue() - newColor.getBlue())
            };
    }

    private Color passError(Color pixelColor, double[] error, double modifier) {
        int r = (int) (pixelColor.getRed() + (modifier * error[0]));
        int g = (int) (pixelColor.getGreen() + (modifier * error[1]));
        int b = (int) (pixelColor.getBlue() + (modifier * error[2]));
        return new Color(
                boundNumber(255,r, 0),
                boundNumber(255,g, 0),
                boundNumber(255,b, 0));
    }

    private int boundNumber(int high, int val, int low) {
        return Math.min(high, Math.max(low, val));
    }

    @Override
    public BufferedImage processImage(BufferedImage image) {
        Picture picture = new Picture(image);
        for(int y = 1; y < picture.getHeight() - 1; y++) {
            for(int x = 1; x < picture.getWidth() - 1; x++ ) {
                Color oldPixelColor = picture.getPixelColor(x, y);
                Color newPixelColor = closestPaletteColor(oldPixelColor);
                picture.setPixelColor(x, y, newPixelColor);

                double[] error = computeColorError(oldPixelColor, newPixelColor);
                Color right = passError(picture.getPixelColor(x + 1, y), error, 7.0/16.0);
                Color bottomLeft = passError(picture.getPixelColor(x - 1, y + 1), error, 3.0/16.0);
                Color down = passError(picture.getPixelColor(x, y + 1), error, 5.0/16.0);
                Color bottomRight = passError(picture.getPixelColor(x + 1, y + 1), error, 1.0/16.0);


                picture.setPixelColor(x + 1,    y, right);
                picture.setPixelColor(x - 1, y + 1, bottomLeft);
                picture.setPixelColor(x       , y + 1, down);
                picture.setPixelColor(x + 1, y + 1, bottomRight);
            }
        }
        return picture.toBufferedImage();
    }
}
