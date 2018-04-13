package com.philipfranchi.vision.painters.util;

import com.philipfranchi.vision.picture.Picture;

import java.awt.*;
import java.util.HashMap;

public class PaletteCreator {

    public static Color[] defaultPalette = new Color[]{
        new Color(  0,  0,  0), //Black
                new Color(128,128,128), //Gray
                new Color(192,192,192), //Silver
                new Color(255,255,255), //White
                new Color(255,  0,  0), //Red
                new Color(128,128,  0), //Olive
                new Color(255,255,  0), //Yellow
                new Color(  0,255,  0), //Lime
                new Color(  0,128,  0), //Green
                new Color(  0,128,128), //Teal
                new Color(  0,255,255), //Aqua
                new Color(  0,  0,128), //Navy
                new Color(  0,  0,255), //Blue
                new Color(128,  0,128), //Purple
                new Color(128,  0,  0), //Maroon
                new Color(255,  0,255), //Fuchsia
    };

    public static Color[] identifyBestPalette(Picture picture, int palleteSize) {
        Color[] palette = new Color[palleteSize];

        Histogram<Color> histogram = new Histogram<>();

        for(int x = 0; x < picture.getWidth(); x++) {
            for(int y = 0; y < picture.getHeight(); y++) {
                Color curColor = picture.getPixelColor(x, y);
                histogram.increment(curColor, 1);
            }
        }


        return palette;
    }
}
