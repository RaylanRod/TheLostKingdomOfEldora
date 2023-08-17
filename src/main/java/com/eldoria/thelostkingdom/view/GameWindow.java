package com.eldoria.thelostkingdom.view;

import com.eldoria.thelostkingdom.display.DisplayMethods;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public class GameWindow extends JFrame{

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton clickToStartButton;
    private JTextArea textArea;

    public GameWindow() {
        setTitle("Lost Kingdom of Eldoria");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainWindow = this.getContentPane();
        mainWindow.setLayout(new BorderLayout());

        topPanel = new JPanel(new GridBagLayout());
        topPanel.setPreferredSize(new Dimension(800, 400));
        topPanel.setBackground(Color.BLACK);

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setPreferredSize(new Dimension(800, 200));
        bottomPanel.setBackground(Color.DARK_GRAY);

        mainWindow.add(topPanel, BorderLayout.NORTH);

        clickToStartButton = new JButton("Click to Start");
        clickToStartButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "welcome to Eldoria!"));
        topPanel.add(clickToStartButton);

        textArea = new JTextArea();
        textArea.setPreferredSize(new Dimension(600, 100));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        constraints.fill = GridBagConstraints.BOTH;
        bottomPanel.add(scrollPane, constraints);

        addDialogueText("textFiles/intro.txt");

        mainWindow.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);

    }

// ________________________________________________

//    public GameWindow() {
//        // Game Window JFrame setup
//        setTitle("Lost Kingdom of Eldoria");
//        this.setSize(800, 600);
//        this.setLocationRelativeTo(null);
//        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        // get Main frame contentPane
//        Container mainWindow = this.getContentPane();
//        mainWindow.setLayout(new BorderLayout());
//
//
//        // Panel for game pictures
//        topPanel = new JPanel(new GridBagLayout());
//        topPanel.setPreferredSize(new Dimension(800, 400));
//        topPanel.setBackground(Color.BLACK); // Set your background color or add images here
//
//        // Panel for text content
//        bottomPanel = new JPanel(new GridBagLayout());
//        bottomPanel.setPreferredSize(new Dimension(800, 200));
//        bottomPanel.setBackground(Color.DARK_GRAY); // Set your background color
//
//        mainWindow.add(topPanel, BorderLayout.NORTH);
//
//        clickToStartButton = new JButton("Click to Start");
//
//        // add actionListener to show a message
//        clickToStartButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "welcome to Eldoria!"));
//        topPanel.add(clickToStartButton);
//
//        textArea = new JTextArea();
//        textArea.setPreferredSize(new Dimension(600, 100)); // Set dimensions here
//        textArea.setLineWrap(true); // Enable line wrapping
//        textArea.setWrapStyleWord(true); // Wrap at word boundaries
//        textArea.setEditable(false);
//        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
//        JScrollPane scrollPane = new JScrollPane(textArea);
//        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
//
//        // GridBag Constraints to set specific details
//        GridBagConstraints constraints = new GridBagConstraints();
//        constraints.gridx = 0;
//        constraints.gridy = 400;
//        constraints.insets = new Insets(5, 5, 5, 5); // Add spacing
//
//
//        GridBagConstraints gbc = new GridBagConstraints();
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.weightx = 1.0;
//        gbc.weighty = 1.0;
//        gbc.fill = GridBagConstraints.BOTH;
//        bottomPanel.add(scrollPane, gbc);
//
//        addDialogueText("textFiles/intro.txt");
//
//        this.getContentPane().add(bottomPanel, BorderLayout.SOUTH);
//
//        this.setVisible(true);
//    }

// ________________________________________________

    public void addDialogueText(String fileName) {
        textArea.append(DisplayMethods.readFromResourceFile(fileName) + "\n"); // Append the new text with a newline
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Scroll to the bottom
    }
}