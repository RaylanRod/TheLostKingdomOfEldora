// Items class manages the collection of Item objects in the game
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Items {
    private static Map<Integer, Item> itemMap = new HashMap<>(); // Map to hold all items with their IDs as keys

    // Method to load items from a JSON file into the 'itemMap'
    public static void loadItemsFromJSON() throws IOException {
        Gson gson = new Gson();
        try (FileReader fileReader = new FileReader("json/Items.json")) {
            Item[] items = gson.fromJson(fileReader, Item[].class);
            for (Item item : items) {
                itemMap.put(item.getItemID(), item); // Add items to the 'itemMap' with their IDs as keys
            }
        }
    }

    // Method to get an item by its unique ID
    public static Item getItemById(int itemId) {
        return itemMap.get(itemId); // Returns the item corresponding to the given ID, or null if not found
    }

    // Optionally, you can add a method to get all items if needed
    public static Item[] getAllItems() {
        return itemMap.values().toArray(new Item[0]); // Returns an array of all items in the 'itemMap'
    }
}
