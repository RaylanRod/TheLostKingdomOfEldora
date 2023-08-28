package com.eldoria.thelostkingdom.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Help {
    private JPanel panelMain;
    private JTextArea HelpField;
    private static String backGround = "/pictures/castleimgNofogDark.png";
    private static final Color customTextColor = new Color(236, 225, 255);

    static void openHelpDialog(String path) {
        // Create a new JDialog for the help content
        JDialog helpDialog = new JDialog();
        helpDialog.setTitle("Help");

        // Create a JLayeredPane to hold both the background image and JTextArea
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(800, 600));

        // Create a JLabel for the background image
        ImageIcon backgroundImage = new ImageIcon(Help.class.getResource(backGround));
        JLabel backgroundLabel = new JLabel(backgroundImage);
        backgroundLabel.setBounds(0, 0, helpDialog.getWidth(), helpDialog.getHeight()); // Adjust size


        // Create a JTextArea to display the help content
        JTextArea textArea = new JTextArea() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 255, 255, 0)); // Semi-transparent white color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        textArea.setEditable(false);
        textArea.setForeground(customTextColor);
        textArea.setFont(new Font("Impact", Font.HANGING_BASELINE, 16));
        textArea.setOpaque(false); // Make the text area transparent


        // Apply opacity to the text area background
        Color textAreaBackgroundColor = new Color(255, 255, 255, 10); //white
        textArea.setBackground(textAreaBackgroundColor);

        // Read content from the help file and display in the JTextArea
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(Help.class.getResourceAsStream(path)))) {
            String line;
            StringBuilder content = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            textArea.setText(content.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Create a JPanel for the semi-transparent background
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(255, 255, 255, 10)); // Semi-transparent white color
                g.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        // Layout the components
        backgroundPanel.setLayout(new BorderLayout());
        backgroundPanel.add(textArea, BorderLayout.CENTER);

        // Create a JScrollPane to wrap the JTextArea
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(20, 20, 760, 560);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false); // Set text area background color


        // Add the background label to the layered pane
        layeredPane.add(backgroundLabel, JLayeredPane.DEFAULT_LAYER); // Use DEFAULT_LAYER for background

        // Add the scroll pane to the layered pane
        layeredPane.add(scrollPane, JLayeredPane.PALETTE_LAYER); // Use PALETTE_LAYER for text area

        // Add the layered pane to the help dialog
        helpDialog.add(layeredPane);

        // Size the dialog to fit the content
        helpDialog.pack();

        // Add a component listener to dynamically update the background size when the dialog is resized
        helpDialog.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                backgroundLabel.setBounds(0, 0, helpDialog.getWidth(), helpDialog.getHeight());
            }
        });

        // Center the dialog on the screen
        helpDialog.setLocationRelativeTo(null);

        // Make the dialog visible
        helpDialog.setVisible(true);
    }


}
