package com.eldoria.thelostkingdom.gamelogic;

import javax.swing.*;
import java.awt.*;

public class ItemBox extends JPanel {
    private String itemName;
    private JLabel label;

    public ItemBox(String itemName) {
        this.itemName = itemName;
        initialize();
    }

    private void initialize() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        setPreferredSize(new Dimension(100, 100)); // Adjust the size of each item box

        // Create and customize the label to display the item name
        label = new JLabel(itemName);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setVerticalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.CENTER);
    }
}
