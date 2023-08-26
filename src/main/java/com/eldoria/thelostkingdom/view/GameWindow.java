package com.eldoria.thelostkingdom.view;

import com.eldoria.thelostkingdom.Main;
import com.eldoria.thelostkingdom.character.Character;
import com.eldoria.thelostkingdom.display.DisplayMethods;
import com.eldoria.thelostkingdom.gamelogic.GameMethods;
import com.eldoria.thelostkingdom.gamelogic.ItemBox;
import com.eldoria.thelostkingdom.gamelogic.TextParser;
import com.eldoria.thelostkingdom.music.MusicPlayer;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameWindow extends JFrame {

    private final Container mainWindow = this;
    private JPanel topPanel;
    private static JPanel bottomLeftPanel;
    private static JPanel bottomCenterPanel;
    private static JPanel bottomRightPanel;
    private static JPanel picPane;
    public static JTextArea textArea;
    private JFrame titleFrame;
    JPanel miniMapPane;
    private JTextArea titleTextArea;
    private static JTextField inputField;
    private MusicPlayer musicPlayer;
    private Character inventory = new Character();
    private static Map<String, Object> inventoryItems;
    private static JPanel inventoryPanel;
    private static GameWindow instance;
    private JButton submitButton;
    private static JFrame inventoryFrame; // Reference to the inventory window
    private static JPanel inventoryContentPanel; // Panel inside the inventory window
    private static int clicker = 0;

    public GameWindow() {

        //MAIN WINDOW:
        setTitle("Lost Kingdom of Eldoria");                  //text on top bar
        this.setSize(800, 600);                  //main window size
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  //X ends game (default is hide -but is still running)
        mainWindow.setLayout(null);             //main layout function

        //SPLASH:
        titleFrame = new JFrame();         //make splash panel
        titleFrame.setSize(800, 800);
        ImageIcon originalImage = new ImageIcon(getClass().getResource("/pictures/EldoriaTitle.png"));
        Image scaledImage = originalImage.getImage().getScaledInstance(800, 800, Image.SCALE_SMOOTH);
        ImageIcon titleImage = new ImageIcon(scaledImage);    // make image scalable
        JLabel imageLabel = new JLabel(titleImage);
        int width = 780;
        int height = 580;
        imageLabel.setPreferredSize(new Dimension(width, height));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);     //center splash L/R
        imageLabel.setVerticalAlignment(JLabel.CENTER);       //center splash up/down
        titleFrame.add(imageLabel);                           //add splash image to splash panel
        titleFrame.setFocusable(true);
        titleFrame.setVisible(true);
        titleFrame.setLocationRelativeTo(null);
        titleFrame.requestFocusInWindow();
        titleFrame.addKeyListener(new KeyAdapter() {          //listener for splash (goes away on 'enter')
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {    //when user hits enter
                    removeTitlePanel(GameWindow.this);  //remove the splash
                    titleFrame.removeKeyListener(this);    //this listener stops listening after 1x function
                    titleFrame.dispose();
                    mainWindow.setVisible(true);
                }
            }
        });

        //_________________________________TOP-PANEL_______________________________

        topPanel = new JPanel();                           //create top panel
        topPanel.setBounds(0, 0, 800, 400);
        topPanel.setBackground(Helper.randomColor());

        picPane = Helper.createImagePanel("/pictures/rooms/courtyard.png");
        picPane.setBounds(100, 0, 600, 400);
        picPane.setOpaque(false);
        mainWindow.add(picPane);

        JPanel directionPane = addDirectionPanel();
        directionPane.setBounds(750, 350, 50, 50);
        directionPane.setOpaque(false);
        mainWindow.add(directionPane);

        //---------------------------BOTTOM-LEFT-PANEL------------------------------
        bottomLeftPanel = new JPanel();
        bottomLeftPanel.setLayout(new OverlayLayout(bottomLeftPanel));
        bottomLeftPanel.setBackground(Helper.randomColor());
        bottomLeftPanel.setBounds(0, 400, 200, 200);

        // MINI MAP:
        bottomLeftPanel.setLayout(new OverlayLayout(bottomLeftPanel));
        miniMapPane = Helper.createImagePanel("/pictures/CastleMap.png");
        miniMapPane.setOpaque(false);

        JLabel locationLabel = new JLabel("X");
        locationLabel.setForeground(Color.yellow);

        bottomLeftPanel.add(locationLabel);
        bottomLeftPanel.add(miniMapPane);

        mainWindow.add(bottomLeftPanel);

        //______________________________BOTTOM-CENTER-PANEL______________________________

        //-------------------BOTTOM-CENTER-NORTH-------------------
        bottomCenterPanel = new JPanel(new BorderLayout());
        bottomCenterPanel.setBounds(200, 400, 400, 200);
        mainWindow.add(bottomCenterPanel);

        JPanel bcNorthPane = new JPanel(new GridLayout(1,4));
        bottomCenterPanel.add(bcNorthPane, BorderLayout.NORTH);

        Font buttonFont = new Font("SansSerif", Font.BOLD, 10);

        //--------------------HELP BUTTON-------------------

        JButton helpButton = new JButton("Help");                       //create button
        helpButton.setFont(buttonFont);
        helpButton.addActionListener(e -> {Help.openHelpDialog();});         //click listener
        bcNorthPane.add(helpButton);                  //add to bottom panel

        //--------------------MAP-BUTTON---------------------

        JButton mapButton = new JButton("Map");    //create button
        mapButton.setFont(buttonFont);
        mapButton.addActionListener(e -> {CastleMap.openMapRef();});         //add listener
        bcNorthPane.add(mapButton);  //add to bottom panel

        //--------------------MUSIC-BUTTON-------------------

        //MUSIC PLAYER MENU:
        try {
            musicPlayer = new MusicPlayer("music", "audioFiles/hauntedCastle.wav");
        } catch (Exception e) {
            e.printStackTrace();
        }
        JButton launchButton = new JButton("Sound");      //create button
        launchButton.setFont(buttonFont);
        bcNorthPane.add(launchButton);               //add to bottom panel

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

        //------------------INVENTORY-BUTTON---------------------

        JButton inventoryButton = new JButton("Inventory");
        inventoryButton.setFont(buttonFont);
        inventoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showInventoryWindow(); // Call method to open inventory window
                clicker++;
            }
        });
        bcNorthPane.add(inventoryButton);


        //----------------------------------BOTTOM-CENTER-SOUTH-------------------------------
        //TEXT AREA: (game text output)
        textArea = new JTextArea();    //create text area
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        textArea.setBackground(Helper.randomColor());
        textArea.setFont(new Font("Arial", Font.ITALIC, 14));

        //SCROLL PANE:
        JScrollPane scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(200, 425, 400, 175);
        mainWindow.add(scrollPane);

        //---------------------------BOTTOM-RIGHT-PANEL-----------------------------
        //__________________________________________________________________________

        bottomRightPanel = new JPanel(new BorderLayout());
        bottomRightPanel.setBackground(Helper.randomColor());
        bottomRightPanel.setBounds(600, 400, 200, 200);
        mainWindow.add(bottomRightPanel);

        //---------------------MINI-INVENTORY-GRIDS-SHOW-----------------------
        inventoryItems = inventory.getInventory();                            //inventory function
        inventoryPanel = createInventoryPanel(inventoryItems);                //inventory panel
        bottomRightPanel.add(inventoryPanel); //add to panel

        // Create inventory window and content panel
        inventoryFrame = new JFrame("Inventory");
        inventoryFrame.setSize(400, 400);
        inventoryContentPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        inventoryFrame.add(inventoryContentPanel);


        JPanel brSPane = new JPanel();
        bottomRightPanel.add(brSPane, BorderLayout.SOUTH);
        //INPUT FIELD:
        inputField = new JTextField();  //create input
        inputField.setFont(new Font("Arial", Font.BOLD, 14));
        inputField.setPreferredSize(new Dimension(120, 30));
        inputField.addActionListener(e -> processUserInput(inputField.getText().trim().toLowerCase())); //functionality
        brSPane.add(inputField);  //add to bottom panel
        //SUBMIT BUTTON:
        submitButton = new JButton("GO");    //create button
        submitButton.addActionListener(e -> processUserInput(inputField.getText().trim().toLowerCase()));//functionality
        brSPane.add(submitButton);               //add to bottom panel



//        bottomRightPanel.add(addDirectionPanel());
        //_________________________________________________________________________________________

        //INITIAL WINDOW TIE IN:
        addDialogueText("textFiles/intro.txt");                     //create initial text
        this.setVisible(false);                                               //can see the GUI plz
        titleFrame.requestFocusInWindow();
    }

    private JPanel createInventoryPanel(Map<String, Object> inventoryItems) {
        JPanel inventoryPanel = new JPanel(new GridLayout(4, 4, 5, 5));
        inventoryPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int totalSlots = 4 * 4;

        for (int slot = 0; slot < totalSlots; slot++) {
            JPanel slotPanel = new JPanel(new BorderLayout());
            slotPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            if (slot < inventoryItems.size()) {
                String itemName = inventoryItems.keySet().toArray(new String[0])[slot];
                JLabel itemLabel = new JLabel(itemName);
                itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
                itemLabel.setVerticalAlignment(SwingConstants.CENTER);
                itemLabel.setFont(new Font("Arial", Font.BOLD, 16));
                slotPanel.add(itemLabel, BorderLayout.CENTER);
            }

            inventoryPanel.add(slotPanel);
        }

        return inventoryPanel;
    }

    public static void updateInventoryPanel(List<ItemBox> inventoryItems) {
        // Clear existing items in the inventory content panel
        inventoryContentPanel.removeAll();

        int totalSlots = 4 * 4; // Total number of slots in the grid

        // Populate inventory slots based on updated inventory items
        for (int slot = 0; slot < totalSlots; slot++) {
            JPanel slotPanel = new JPanel(new BorderLayout());
            slotPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));

            // Check if the slot should display an item from the updated inventory
            if (slot < inventoryItems.size()) {
                ItemBox item = inventoryItems.get(slot);
                String itemName = item.getItemName();
                String itemImage = item.getItemImage();

                // Check if itemImage is not null before creating ImageIcon
                if (itemImage != null) {
                    Image scaledItemImage = new ImageIcon(GameWindow.class.getResource(itemImage))
                            .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
                    ImageIcon itemIcon = new ImageIcon(scaledItemImage);
                    JLabel itemLabel = new JLabel(itemIcon);
                    itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
                    itemLabel.setVerticalAlignment(SwingConstants.CENTER);
                    slotPanel.add(itemLabel, BorderLayout.CENTER);
                }

            }

            inventoryContentPanel.add(slotPanel);
        }

        inventoryContentPanel.revalidate(); // Refresh the inventory content panel
        inventoryContentPanel.repaint();
    }

    private void showInventoryWindow() {
        if (inventoryItems.isEmpty() && clicker < 1) {
            // Inventory is empty, show empty slot grid
            for (int slot = 0; slot < 16; slot++) {
                JPanel slotPanel = new JPanel(new BorderLayout());
                slotPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                inventoryContentPanel.add(slotPanel);
            }
        } else {
            // Inventory has items, populate inventory slots
            for (int slot = 0; slot < inventoryItems.size(); slot++) {
                String itemName = inventoryItems.keySet().toArray(new String[0])[slot];
                JPanel slotPanel = new JPanel(new BorderLayout());
                slotPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
                JLabel itemLabel = new JLabel(itemName);
                itemLabel.setHorizontalAlignment(SwingConstants.CENTER);
                itemLabel.setVerticalAlignment(SwingConstants.CENTER);
                itemLabel.setFont(new Font("Arial", Font.BOLD, 16));
                slotPanel.add(itemLabel, BorderLayout.CENTER);
                inventoryContentPanel.add(slotPanel);
            }
        }

        inventoryFrame.setVisible(true);
    }

    private static GridBagConstraints createGBC(
            int x, int y, double weightx, double weighty, int fill, int anchor) {
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
        JFrame titlePanel = gameWindow.titleFrame;

        mainWindow.add(gameWindow.getTopPanel());

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

    public JPanel getTopPanel() {
        return this.topPanel;
    }

    public static void setTextArea(JTextArea textArea) {
        GameWindow.textArea = textArea;
    }

    public void setInputField(JTextField inputField) {
        this.inputField = inputField;
    }

    public void processUserInput(String input) {
        List<String> verbsAndNouns = TextParser.extractVerbsAndNouns(input);
        if (Main.isExpectingRiddleAnswer) {
            if (input.equals(Main.currentRiddleAnswer)) {
                textArea.append("correct\n" + Main.currentRiddleHint);
                inputField.setText("");
            } else {
                textArea.append("\n" + "wrong answer");
                inputField.setText("");
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
                    textArea.append("\nEnter a file name: ");
                    inputField.setText("");
                    break;
                case "talk":
                    String response = GameMethods.talk();
                    textArea.append("\n" + response);
                    inputField.setText("");
                    break;
                default:
                    textArea.append("\nUnknown command: " + verbsAndNouns.get(0));
                    break;
            }
            return;
        }

        if (verbsAndNouns.size() == 2) {
            switch (verbsAndNouns.get(0)) {
                case "attack":
                    try {
                        GameMethods.handleCombat(verbsAndNouns.get(0));
                    } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
                        e.printStackTrace();
                    }
                    inputField.setText("");
                    break;
                case "go":
                    try {
                        GameMethods.moveRoom(verbsAndNouns.get(1), true, 0.5f);

                        HashMap<String, String> roomPics = GameMethods.loadJSONFile("json/room-pics.json", HashMap.class);
                        String currentRoomPicPath = roomPics.get(Integer.toString(Main.player.getCurrentRoomId()));


                        picPane = Helper.createImagePanel(currentRoomPicPath);
                        picPane.setBounds(100, 0, 600, 400);
                        picPane.setOpaque(false);
                        mainWindow.add(picPane);
                        mainWindow.revalidate();
                        mainWindow.repaint();

                        textArea.setText("");
                        textArea.append("\nMoved to: " + Main.player.getRoomName());
                    } catch (Exception e) {
                        e.printStackTrace();
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

    private JPanel addDirectionPanel() {

        JPanel directionPanel = new JPanel(new GridLayout(3, 3));
        directionPanel.setOpaque(false);

        JButton upButton = new BasicArrowButton(BasicArrowButton.NORTH);
        JButton downButton = new BasicArrowButton(BasicArrowButton.SOUTH);
        JButton leftButton = new BasicArrowButton(BasicArrowButton.WEST);
        JButton rightButton = new BasicArrowButton(BasicArrowButton.EAST);

        JPanel[] transPanes = new JPanel[5];
        for (int i = 0; i < 5; i++) {
            JPanel p = new JPanel();
            p.setOpaque(false);
            transPanes[i] = p;
        }

        directionPanel.add(transPanes[0]);
        directionPanel.add(upButton);
        directionPanel.add(transPanes[1]);
        directionPanel.add(leftButton);
        directionPanel.add(transPanes[2]);
        directionPanel.add(rightButton);
        directionPanel.add(transPanes[3]);
        directionPanel.add(downButton);
        directionPanel.add(transPanes[4]);

        class MoveActionListener implements ActionListener {
            private String direction;

            public MoveActionListener(String direction) {
                this.direction = direction;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(() -> {
                    processUserInput(direction);
                });
            }
        }

        // Add action listeners to the buttons
        upButton.addActionListener(new MoveActionListener("go north"));
        downButton.addActionListener(new MoveActionListener("go south"));
        leftButton.addActionListener(new MoveActionListener("go west"));
        rightButton.addActionListener(new MoveActionListener("go east"));

        return directionPanel;
    }
}