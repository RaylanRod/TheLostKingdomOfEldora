package com.eldoria.thelostkingdom.view;

import com.eldoria.thelostkingdom.Main;
import com.eldoria.thelostkingdom.character.Character;
import com.eldoria.thelostkingdom.display.DisplayMethods;
import com.eldoria.thelostkingdom.gamelogic.GameMethods;
import com.eldoria.thelostkingdom.gamelogic.TextParser;
import com.eldoria.thelostkingdom.music.MusicPlayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Map;

public class GameWindow extends JFrame {

    private JPanel topPanel;
    private JPanel bottomPanel;
    private JButton clickToStartButton;
    public static JTextArea textArea;
    private JPanel titlePanel;
    private JTextArea titleTextArea;
    private JTextField inputField;
    private MusicPlayer musicPlayer;
    private Character inventory = new Character();
    private Map<String, Object> inventoryItems;
    private JPanel inventoryPanel;
    private static GameWindow instance;
    private JButton submitButton;


    public GameWindow() {

        //MAIN WINDOW:
        setTitle("Lost Kingdom of Eldoria");                  //text on top bar
        this.setSize(800, 600);                  //main window size
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //X ends game (default is hide -but is still running)
        Container mainWindow = this.getContentPane();         //above 'this.'stuff goes in window
        mainWindow.setLayout(new BorderLayout());             //main layout function

        //SPLASH:
        titlePanel = new JPanel(new GridBagLayout());         //make splash panel
        ImageIcon originalImage = new ImageIcon(getClass().getResource("/pictures/EldoriaTitle.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        ImageIcon titleImage = new ImageIcon(scaledImage);    // make image scalable
        JLabel imageLabel = new JLabel(titleImage);
        int width = 780;
        int height = 580;
        imageLabel.setPreferredSize(new Dimension(width, height));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);     //center splash L/R
        imageLabel.setVerticalAlignment(JLabel.CENTER);       //center splash up/down
        titlePanel.add(imageLabel);                           //add splash image to splash panel
        mainWindow.add(titlePanel, BorderLayout.CENTER);      //center splash panel on main window
        titlePanel.setFocusable(true);
        titlePanel.requestFocusInWindow();
        titlePanel.addKeyListener(new KeyAdapter() {          //listener for splash (goes away on 'enter')
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {    //when user hits enter
                    removeTitlePanel(GameWindow.this);  //remove the splash
                    titlePanel.removeKeyListener(this);    //this listener stops listening after 1x function
                }
            }
        });

        //TOP PANEL:
        topPanel = new JPanel(new GridBagLayout());                           //create top panel
        topPanel.setPreferredSize(new Dimension(800, 400));      //start with this size
        topPanel.setMaximumSize(new Dimension(800, 400));        //size is fixed (bigger window=margins)
        topPanel.setBackground(Color.BLACK);                                  //default BGC

        //BOTTOM PANEL:
        bottomPanel = new JPanel(new GridBagLayout());                        //create bottom panel
        bottomPanel.setPreferredSize(new Dimension(800, 200));   //start with this size
        bottomPanel.setBackground(Color.DARK_GRAY);                           //default BGC

        //INVENTORY:
        inventoryItems = inventory.getInventory();                            //inventory function
        inventoryPanel = createInventoryPanel(inventoryItems);                //inventory panel
        bottomPanel.add(inventoryPanel, createGBC(0, 3, 0, 0, 0, GridBagConstraints.CENTER)); //add to panel

        //TEXT AREA: (game text output)
        textArea = new JTextArea();                                           //create text area
        textArea.setLineWrap(true);                                           //wrap oversized lines
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));      //font

        //SCROLL PANE:
        JScrollPane scrollPane = new JScrollPane(textArea);                  //create scroll pane
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED); //vert. bar as needed
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER); //no L/R bar
        topPanel.add(scrollPane, createGBC(0, 0, 1, 1, GridBagConstraints.BOTH, 10));                             //add scroll panel to top panel

        //INPUT FIELD:
        inputField = new JTextField();                                        //create input
        inputField.addActionListener(e -> processUserInput(inputField.getText().trim().toLowerCase())); //functionality
        bottomPanel.add(inputField, createGBC(0, 1, 1, 0, GridBagConstraints.HORIZONTAL, 10));                   //add to bottom panel

        //SUBMIT BUTTON:
        submitButton = new JButton("Submit");                            //create button
        submitButton.addActionListener(e -> processUserInput(inputField.getText().trim().toLowerCase()));//functionality
        bottomPanel.add(submitButton, createGBC(1, 0, 1, 0, 0, GridBagConstraints.LINE_END));               //add to bottom panel

        //HELP BUTTON:
        JButton helpButton = new JButton("Help");                       //create button
        helpButton.addActionListener(e -> {Help.openHelpDialog();});         //click listener
        bottomPanel.add(helpButton, createGBC(2, 0, 0, 0, 0, GridBagConstraints.LINE_END));                  //add to bottom panel

        //MAP BUTTON:
        JButton mapButton = new JButton("Map");                         //create button
        mapButton.addActionListener(e -> {CastleMap.openMapRef();});         //add listener
        bottomPanel.add(mapButton, createGBC(3, 0, 0, 0, 0, GridBagConstraints.LINE_END));                    //add to bottom panel

        //MUSIC PLAYER MENU:
        try {
            musicPlayer = new MusicPlayer("music", "audioFiles/hauntedCastle.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton launchButton = new JButton("Sound");                     //create button
        bottomPanel.add(launchButton, createGBC(4, 0, 0, 0, 0, GridBagConstraints.LINE_END));               //add to bottom panel
        JPopupMenu menu = new JPopupMenu();                                   //create menu

        //MUSIC ON:
        JMenuItem musicOn = new JMenuItem("Turn Music On");              //on command
        musicOn.addActionListener(e -> musicPlayer.play("music"));       //function on
        menu.add(musicOn);                                                    //add to menu

        //MUSIC OFF:
        JMenuItem musicOff = new JMenuItem("Turn Music Off");            //off command
        musicOff.addActionListener(e -> musicPlayer.stop());                  //function stop
        menu.add(musicOff);                                                   //add to menu

        //SOUND FX ON:
        JMenuItem fxOn = new JMenuItem("Turn FX On");                    //on command
        fxOn.addActionListener(e -> musicPlayer.play("fx"));             //function on
        menu.add(fxOn);                                                       //add to menu

        //SOUND FX OFF:
        JMenuItem fxOff = new JMenuItem("Turn FX Off");                  //off command
        fxOff.addActionListener(e -> musicPlayer.stop());                     //function off
        menu.add(fxOff);                                                      //add to menu

        //MUSIC VOLUME SLIDER:
        JSlider musicVolume = new JSlider(0, 100, 50);       // Initial volume set to 50
        musicVolume.addChangeListener(e -> musicPlayer.setVolume("music", musicVolume.getValue() / 100f));
        menu.add(musicVolume);                                               //add to menu

        //SOUND FX VOLUME SLIDER:
        JSlider fxVolume = new JSlider(0, 100, 50);          // Initial volume set to 50
        fxVolume.addChangeListener(e -> musicPlayer.setVolume("fx", fxVolume.getValue() / 100f));
        menu.add(fxVolume);                                                  //add to menu

        //SOUND MENU TIE IN:
        launchButton.addActionListener(e -> {
            menu.show(launchButton, 0, launchButton.getHeight());
        });
        bottomPanel.add(launchButton);                                       //add to bottom panel

        //INITIAL WINDOW TIE IN:
        addDialogueText("textFiles/intro.txt");                     //create initial text
//        mainWindow.add(bottomPanel, BorderLayout.SOUTH);
        this.setVisible(true);                                               //can see the GUI plz
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
        bottomPanel.add(inventoryPanel, createGBC(0, 3, 0, 0, 0, GridBagConstraints.CENTER)); // Add the new panel
        bottomPanel.revalidate(); // Refresh the bottom panel
        bottomPanel.repaint();
    }

    private GridBagConstraints createGBC(
            int x, int y, int weightx, int weighty, int fill, int anchor) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = x;
        gbc.gridy = y;
        gbc.weightx = weightx;
        gbc.weighty = weighty;
        gbc.fill = fill;
        gbc.anchor = anchor;
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

    public void processUserInput(String input) {
        List<String> verbsAndNouns = TextParser.extractVerbsAndNouns(input);
        if (Main.isExpectingRiddleAnswer) {
            if (input.equals(Main.currentRiddleAnswer)) {
                textArea.append("\n" + "correct");
            } else {
                textArea.append("\n" + "wrong answer");
            }
            // Reset expectations and the riddle answer
            Main.isExpectingRiddleAnswer = false;
            Main.currentRiddleAnswer = null;
            return;
        }
        if (verbsAndNouns.size() == 1) {
            switch (verbsAndNouns.get(0)) {
                case "quit":
                    textArea.append("\nThank you for playing The Lost Kingdom of Eldoria!");
                    System.exit(0);
                    break;
                case "save":
                    textArea.append("\nEnter a file name: > ");
                    break;
                case "talk":
                    String response = GameMethods.talk();
                    textArea.append("\n" + response);
                    break;
                default:
                    textArea.append("\nUnknown command: " + verbsAndNouns.get(0));
                    break;
            }
            return;
        }

        if (verbsAndNouns.size() == 2) {
            switch (verbsAndNouns.get(0)) {
                case "go":
                    try {
                        GameMethods.moveRoom(verbsAndNouns.get(1), true, 0.5f);
                        textArea.append("\nMoved to: " + Main.player.getRoomName());
                    } catch (Exception e) {
                        textArea.append("\nAn error occurred while moving.");
                    }
                    break;
                case "get":
                    try {
                        GameMethods.getItem(verbsAndNouns.get(1), true, 0.5f);
                        textArea.append("\nYou took the " + verbsAndNouns.get(1) + "!");
                    } catch (Exception e) {
                        textArea.append("\nAn error occurred while trying to take the item.");
                    }
                    break;
                case "drop":
                    try{
                        GameMethods.dropItem(verbsAndNouns.get(1), true, 05.f);
                        textArea.append("\nYou dropped the " + verbsAndNouns.get(1) + "!");
                    } catch (Exception e) {
                        textArea.append("\nAn error occurred while trying to drop the item.");
                    }
                case "look":
                    String lookResult = GameMethods.look(verbsAndNouns.get(1));
                    textArea.append("\n" + lookResult);
                    break;
                default:
                    textArea.append("\nUnknown command: " + verbsAndNouns.get(0));
                    break;
            }
        }
        DisplayMethods.printHeader();
        DisplayMethods.printRoomItems();
        DisplayMethods.printRoomNPC();
        inputField.setText("");
    }
}