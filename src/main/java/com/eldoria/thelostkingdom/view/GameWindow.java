package com.eldoria.thelostkingdom.view;

import com.eldoria.thelostkingdom.character.Character;
import com.eldoria.thelostkingdom.display.DisplayMethods;
import com.eldoria.thelostkingdom.music.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Map;

public class GameWindow extends JFrame {

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton clickToStartButton;
    private JTextArea textArea;
    private JPanel titlePanel;
    private JTextArea titleTextArea;
    private JTextField inputField;
    private MusicPlayer musicPlayer;
    private Character inventory = new Character();
    private Map<String, Object> inventoryItems;
    private JPanel inventoryPanel;
    private static GameWindow instance;


    public GameWindow() {
        setTitle("Lost Kingdom of Eldoria");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container mainWindow = this.getContentPane();
        mainWindow.setLayout(new BorderLayout());

        titlePanel = new JPanel(new GridBagLayout());


        ImageIcon originalImage = new ImageIcon(getClass().getResource("/pictures/EldoriaTitle.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(800, 600, Image.SCALE_SMOOTH);
        ImageIcon titleImage = new ImageIcon(scaledImage);
        JLabel imageLabel = new JLabel(titleImage);

// Optionally, if you want to ensure the image scales within a certain size:
        int width = 780;  // Adjust based on your desired width
        int height = 580; // Adjust based on your desired height
        imageLabel.setPreferredSize(new Dimension(width, height));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        titlePanel.add(imageLabel);

        mainWindow.add(titlePanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Make the component expand in both directions
        gbc.weightx = 1.0; // Allocate as much horizontal space as possible to this component
        gbc.weighty = 1.0; // Allocate as much vertical space as possible to this component


        titlePanel.setFocusable(true);
        titlePanel.requestFocusInWindow();
        titlePanel.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    removeTitlePanel(GameWindow.this);
                    //remove key listener after it has served its purpose
                    titlePanel.removeKeyListener(this);
                }
            }
        });

        topPanel = new JPanel(new GridBagLayout());
        topPanel.setPreferredSize(new Dimension(800, 400));
        topPanel.setBackground(Color.BLACK);

        bottomPanel = new JPanel(new GridBagLayout());
        bottomPanel.setPreferredSize(new Dimension(800, 200));
        bottomPanel.setBackground(Color.DARK_GRAY);
        inventoryItems = inventory.getInventory();
        inventoryPanel = createInventoryPanel(inventoryItems);
        bottomPanel.add(inventoryPanel, createGridBagConstraints(0, 3, GridBagConstraints.CENTER));

        inputField = new JTextField();
        inputField.setPreferredSize(new Dimension(300, 25));

        // Add JTextField to the bottomPanel using GridBagConstraints
        GridBagConstraints inputFieldConstraints = new GridBagConstraints();
        inputFieldConstraints.gridx = 0;
        inputFieldConstraints.gridy = 1; // Setting it below the text area
        inputFieldConstraints.weightx = 1.0;
        inputFieldConstraints.fill = GridBagConstraints.HORIZONTAL;
        bottomPanel.add(inputField, inputFieldConstraints);

        inputField.addActionListener(e -> {
            String input = inputField.getText();
            // Here you can handle the input text.
            // For example, appending it to your textArea:
            textArea.append("User: " + input + "\n");
            inputField.setText("");  // Clear the input field after processing
        });

        clickToStartButton = new JButton("Click to Start");
        clickToStartButton.addActionListener(e -> JOptionPane.showMessageDialog(this, "Welcome to Eldoria!"));
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

        JButton helpButton = new JButton("Help");
        helpButton.addActionListener(e -> {
            Help.openHelpDialog();
        });
        //bottomPanel = new JPanel(new GridBagLayout());
        //bottomPanel.setPreferredSize(new Dimension(200, 200));
        // bottomPanel.setBackground(Color.DARK_GRAY);

        GridBagConstraints helpButtonConstraints = new GridBagConstraints();
        helpButtonConstraints.gridx = 1;
        helpButtonConstraints.gridy = 0;
        helpButtonConstraints.anchor = GridBagConstraints.LINE_END; // Align to the right
        bottomPanel.add(helpButton, helpButtonConstraints);

        //adding music player menu
        try {
            musicPlayer = new MusicPlayer("music", "audioFiles/hauntedCastle.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton launchButton = new JButton("Sound");
        GridBagConstraints launchButtonConstraints = new GridBagConstraints();
        launchButtonConstraints.gridx = 0;  // Same column as the help button
        launchButtonConstraints.gridy = 1;  // One row below the help button
        launchButtonConstraints.anchor = GridBagConstraints.LINE_END; // Align to the right
        bottomPanel.add(launchButton, launchButtonConstraints);


        JPopupMenu menu = new JPopupMenu();

        //music on
        JMenuItem musicOn = new JMenuItem("Turn Music On");
        musicOn.addActionListener(e -> musicPlayer.play("music"));
        menu.add(musicOn);

        //music off
        JMenuItem musicOff = new JMenuItem("Turn Music Off");
        musicOff.addActionListener(e -> musicPlayer.stop());
        menu.add(musicOff);

        //sound on
        JMenuItem fxOn = new JMenuItem("Turn FX On");
        fxOn.addActionListener(e -> musicPlayer.play("fx"));
        menu.add(fxOn);

        //sound off
        JMenuItem fxOff = new JMenuItem("Turn FX Off");
        fxOff.addActionListener(e -> musicPlayer.stop());
        menu.add(fxOff);


        // Add a slider for music volume
        JSlider musicVolume = new JSlider(0, 100, 50);  // Initial volume set to 50
        musicVolume.addChangeListener(e -> musicPlayer.setVolume("music", musicVolume.getValue() / 100f));
        menu.add(musicVolume);

        // Add a slider for sound volume
        JSlider fxVolume = new JSlider(0, 100, 50); // Initial volume set to 50
        fxVolume.addChangeListener(e -> musicPlayer.setVolume("fx", fxVolume.getValue() / 100f));
        menu.add(fxVolume);


        // Similarly, add options for FX.

        launchButton.addActionListener(e -> {
            menu.show(launchButton, 0, launchButton.getHeight());
        });

        bottomPanel.add(launchButton);


        addDialogueText("textFiles/intro.txt");

//        mainWindow.add(bottomPanel, BorderLayout.SOUTH);

        this.setVisible(true);

        titlePanel.requestFocusInWindow();

    }

    private JPanel createInventoryPanel(Map<String, Object> inventoryItems) {
        JPanel inventoryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel inventoryLabel = new JLabel("Inventory:");
        inventoryPanel.add(inventoryLabel);

        for (String itemName : inventoryItems.keySet()) {
            JLabel itemLabel = new JLabel(itemName);
            inventoryPanel.add(itemLabel);
        }
        return inventoryPanel;
    }

    public void updateInventoryPanel() {
        bottomPanel.remove(inventoryPanel); // Remove the current inventory panel
        inventoryPanel = createInventoryPanel(inventoryItems); // Create a new inventory panel
        bottomPanel.add(inventoryPanel, createGridBagConstraints(0, 3, GridBagConstraints.CENTER)); // Add the new panel
        bottomPanel.revalidate(); // Refresh the bottom panel
        bottomPanel.repaint();
    }

    private GridBagConstraints createGridBagConstraints(int x, int y, int fill) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.fill = fill;
        return gbc;
    }

    public static void removeTitlePanel(GameWindow gameWindow) {
        Container mainWindow = gameWindow.getMainContainer();
        JPanel titlePanel = gameWindow.getTitlePanel();
        mainWindow.remove(titlePanel);

        mainWindow.add(gameWindow.getTopPanel(), BorderLayout.NORTH);
        mainWindow.add(gameWindow.getBottomPanel(), BorderLayout.SOUTH);

        mainWindow.validate();
        mainWindow.repaint();
    }

    public void addDialogueText(String fileName) {
        textArea.append(DisplayMethods.readFromResourceFile(fileName) + "\n"); // Append the new text with a newline
        textArea.setCaretPosition(textArea.getDocument().getLength()); // Scroll to the bottom
    }

    public static synchronized GameWindow getInstance() {
        if (instance == null) {
            instance = new GameWindow();
        }
        return instance;
    }

    public Container getMainContainer() {
        return this.getContentPane();
    }

    public JPanel getTitlePanel() {
        return this.titlePanel;
    }

    public JPanel getTopPanel() {
        return this.topPanel;
    }

    public JPanel getBottomPanel() {
        return this.bottomPanel;
    }

}