package com.eldoria.thelostkingdom.gamelogic;

import javax.swing.*;
import java.awt.*;

public class InventoryItemComponent extends JPanel {

    private JLabel itemNameLabel;

    public InventoryItemComponent(String itemName) {
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(50, 50)); // Adjust the size as needed

        itemNameLabel = new JLabel(itemName);
        itemNameLabel.setHorizontalAlignment(JLabel.CENTER);
        itemNameLabel.setVerticalAlignment(JLabel.CENTER);
        add(itemNameLabel, BorderLayout.CENTER);
    }
}