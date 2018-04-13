package com.philipfranchi.vision;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.philipfranchi.vision.painters.FloydSteinbergDitheringFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class Main {

    private final static Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {

        Webcam webcam = Webcam.getWebcams().get(2);
        webcam.setViewSize(WebcamResolution.VGA.getSize());

        WebcamPanel modifiedPanel = new WebcamPanel(webcam);
        modifiedPanel.setPainter(new FloydSteinbergDitheringFilter());

        JFrame window = new JFrame("Test webcam panel");
        window.add(modifiedPanel);
        window.setResizable(true);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setSize((int) webcam.getViewSize().getWidth() * 2, (int)  webcam.getViewSize().getHeight());
    }
}
