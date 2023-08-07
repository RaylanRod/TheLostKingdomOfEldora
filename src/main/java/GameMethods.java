import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.*;


public class GameMethods {

    public static boolean startNewGame(Scanner scanner) {
        // Prompt the player for confirmation
        System.out.print("Would you like to start a new game? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        // Check if the player's input is "yes" (case-insensitive)
        return input.equals("yes");
    }

    public static boolean confirmQuit(Scanner scanner) {
        // Prompt the player for confirmation
        System.out.print("Are you sure you want to quit? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        // Check if the player's input is "yes" (case-insensitive)
        return input.equals("yes");
    }

    public static void playerLocation(int roomId) {
        // Get the Room object from the Rooms class using the provided roomId
        Room room = Rooms.getRoomById(roomId);

        // Check if the room object is not null, meaning the room with the given ID was found
        if (room != null) {
            // Display the room name and description to the player
            System.out.println("You are in the " + room.getName());
            System.out.println(room.getDescription());
        } else {
            // The room with the provided ID was not found in the game's rooms collection
            System.out.println("Invalid room ID. Unable to determine your location.");
        }
    }

    public static <T> T loadJSONFile (String path, Class<T> clazz) throws IOException {
        //noinspection ConstantConditions
        try (Reader reader = new InputStreamReader(GameMethods.class.getClassLoader().getResourceAsStream(path))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, clazz);
        }
    }

//    public static void main(String[] args) throws IOException {
//        Map<Integer, Item> test = new HashMap<>();
//        test = loadJSONFile("src/main/resources/json/Description.json");
//        test.forEach((integer, Item) -> System.out.println(Item));
//
//    }
}

//class Tests {
//    public static void main(String[] args) {
//        // Load rooms and items from JSON files
//        try {
//            Rooms.loadRoomsFromJSON();
//            Items.loadItemsFromJSON();
//        } catch (IOException e) {
//            System.out.println("Error loading rooms/items from JSON files: " + e.getMessage());
//            return;
//        }
//
//        // Assuming you have a starting room ID, pass it to the playerLocation method
//        int startingRoomId = 1;
//
//        // Test the playerLocation method with the starting room ID
//        GameMethods.playerLocation(startingRoomId);
//    }
//}