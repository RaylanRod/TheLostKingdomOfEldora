package com.eldoria.thelostkingdom.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;


public class CastleMap {
    private JPanel panelMain;
    private JTextArea MapField;
    private static String backGround = "/pictures/CastleMap.png";
    private static final Color customTextColor = new Color(236, 225, 255);

    // Method to create and show the main GUI
    static void createAndShowGUI() {
        // Create a main JFrame
        JFrame frame = new JFrame("Map");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a button to open the map dialog
        JButton mapButton = new JButton("Open Map");
        // Add an ActionListener to the button to open the map dialog
        mapButton.addActionListener(e -> {
            openMapRef();
        });

        frame.add(mapButton);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    static void openMapRef() {
        JDialog mapRef = new JDialog();
        mapRef.setTitle("Map");

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        ImageIcon backgroundImage = new ImageIcon(Objects.requireNonNull(CastleMap.class.getResource(backGround)));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, mapRef.getWidth(), mapRef.getHeight()); // Adjust size

        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER); // Use DEFAULT_LAYER for background
        mapRef.add(layeredPane);
        mapRef.pack();
        mapRef.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                backgroundLabel.setBounds(0, 0, mapRef.getWidth(), mapRef.getHeight());
            }
        });
        mapRef.setLocationRelativeTo(null);
        mapRef.setVisible(true);
    }
}
