package com.eldoria.thelostkingdom.gamelogic;

public class ItemBox {
    private String itemName;
    private String itemImage;

    public ItemBox(String itemName, String itemImage) {
        this.itemName = itemName;
        this.itemImage = itemImage;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemImage() {
        return itemImage;
    }
}

