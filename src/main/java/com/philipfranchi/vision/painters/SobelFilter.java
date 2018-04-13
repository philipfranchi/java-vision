package com.philipfranchi.vision.painters;

import com.philipfranchi.vision.picture.Picture;

import java.awt.image.BufferedImage;

public class SobelFilter extends BaseFilter {

    private static final GreyScaleFilter greyScaleFilter = new GreyScaleFilter();

    @Override
    public BufferedImage processImage(BufferedImage image) {
        BufferedImage greyScale = greyScaleFilter.processImage(image);
        Picture picture = new Picture(greyScale);

        for(int x = 1; x < picture.getWidth() - 1; x++) {
            for (int y = 1; y < picture.getHeight() - 1; y++) {

            }
        }

        return picture.toBufferedImage();
    }
}
