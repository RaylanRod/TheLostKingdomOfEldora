// Items class manages the collection of Item objects in the game
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Items {
    private static Map<Integer, Item> itemMap = new HashMap<>(); // Map to hold all items with their IDs as keys

    // Method to load items from a JSON file into the 'itemMap'
    public static void loadItemsFromJSON() throws IOException {
        Item[] items = GameMethods.loadJSONFile("json/Items.json", Item[].class);
        Arrays.stream(items)
                .collect(Collectors.toMap(Item::getItemID, Function.identity()));
    }

    // Method using Gson to read a json file and return the object


    // Method to get an item by its unique ID
    public static Item getItemById(int itemId) {
        return itemMap.get(itemId); // Returns the item corresponding to the given ID, or null if not found
    }

    // Optionally, you can add a method to get all items if needed
    public static Item[] getAllItems() {
        return itemMap.values().toArray(new Item[0]); // Returns an array of all items in the 'itemMap'
    }
}
