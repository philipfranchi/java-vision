package com.philipfranchi.vision;

import org.opencv.calib3d.Calib3d;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Size;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

public class CameraCalibrator {

    private static final Logger LOGGER = LoggerFactory.getLogger(CameraCalibrator.class);

    public static void main(String[] args) throws IOException {
        System.loadLibrary(org.opencv.core.Core.NATIVE_LIBRARY_NAME);
        Mat intrinsic = new Mat(3, 3, CvType.CV_32FC1);

        BufferedImage bufferedImage = ImageIO.read(new File("board.jpg"));
        Mat image = bufferedImageToMat(bufferedImage);

        MatOfPoint2f corners = new MatOfPoint2f();
        Calib3d.findChessboardCorners(image, new Size(1280, 720), corners);

        LOGGER.info("Corners: {}", corners);
    }

    public static Mat bufferedImageToMat(BufferedImage bi) {
        Mat mat = new Mat(bi.getHeight(), bi.getWidth(), CvType.CV_8UC3);
        byte[] data = ((DataBufferByte) bi.getRaster().getDataBuffer()).getData();
        mat.put(0, 0, data);
        return mat;
    }
}
