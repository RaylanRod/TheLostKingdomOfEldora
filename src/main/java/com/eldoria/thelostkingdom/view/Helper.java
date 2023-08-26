package com.eldoria.thelostkingdom.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class Helper {

    public static JPanel createImagePanel(String filePath) {

        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                // Load and scale the image
                BufferedImage image = loadImage(filePath);
                if (image != null) {
                    int maxWidth = getWidth();
                    int maxHeight = getHeight();

                    int imageWidth = image.getWidth();
                    int imageHeight = image.getHeight();

                    // Calculate scaling factors while maintaining aspect ratio
                    double scale = Math.min((double) maxWidth / imageWidth, (double) maxHeight / imageHeight);
                    int scaledWidth = (int) (imageWidth * scale);
                    int scaledHeight = (int) (imageHeight * scale);

                    // Draw the scaled image
                    g.drawImage(image, (maxWidth - scaledWidth) / 2, (maxHeight - scaledHeight) / 2, scaledWidth, scaledHeight, null);
                }
            }
        };

        return imagePanel;
    }

    private static BufferedImage loadImage(String filePath) {
        try {
            return ImageIO.read(CastleMap.class.getResource(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String readFromResourceFile(String fileName) {

        String fileContent = "";

        // First, try to access the resource from the classpath (e.g., JAR file)
        InputStream inputStream = GameWindow.class.getResourceAsStream("/" + fileName);

        if (inputStream != null) {
            // Resource is found (in JAR), process the input stream as needed
            try {
                // Read content from the resource
                byte[] resourceBytes = inputStream.readAllBytes();
                fileContent = new String(resourceBytes);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return fileContent + "\n";
    }

    public static Color randomColor() {
        Random rand = new Random();
        return new Color(rand.nextInt(256), rand.nextInt(256), rand.nextInt(256));
    }
}