package com.philipfranchi.vision.picture;

import java.awt.*;

public class Pixel {

    private final int x;
    private final int y;
    private Color color;

    public Pixel(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
