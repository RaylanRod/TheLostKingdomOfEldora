// Item class represents an in-game item that can be found in rooms or used by the player
public class Item {
    private int itemID;          // Unique identifier for each item
    private String name;         // Name of the item
    private String description;  // Description of the item
    private String usage;        // How the item can be used
    private boolean canBeUsed;   // Whether the item can be used by the player

    // Constructor to initialize the item properties
    public Item(int itemID, String name, String description, String usage, boolean canBeUsed) {
        this.itemID = itemID;
        this.name = name;
        this.description = description;
        this.usage = usage;
        this.canBeUsed = canBeUsed;
    }

    // Getters and setters for the item properties
    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUsage() {
        return usage;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public boolean canBeUsed() {
        return canBeUsed;
    }

    public void setCanBeUsed(boolean canBeUsed) {
        this.canBeUsed = canBeUsed;
    }
}
