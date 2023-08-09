import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
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

    public static void moveRoom(String direction){
       try {
           int newRoom = Rooms.getRoomById(Main.player.getCurrentRoom()).getExits().get(direction);
           Main.player.setCurrentRoom(newRoom);
           Main.player.setRoomName(Rooms.getRoomById(Main.player.getCurrentRoom()).getName());
       } catch (Exception e) {
           System.out.println("You entered an INVALID direction");
           System.out.print("Press any key to continue...");
           Scanner scanner = new Scanner(System.in);
           scanner.nextLine();
       }
    }

    public static void talk(){
        try {
            DisplayMethods.clearScreen();
            System.out.println(Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("dialog") + "\n");
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println("There isn't any NPC to talk to...");
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void getItem(String item){
        try {
            List<String> items = Main.player.getInventory();
            if (items == null){
                items = new ArrayList<>();
            }
            String oItem = Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().get("name").toString();
            System.out.println("This is OITEM: " + oItem);
            items.add(0, oItem);
            Main.player.setInventory(items);
            Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().remove("name");
        } catch (Exception e) {
            System.out.println("There isn't an item to take...");
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void drop(String item){
//        try {
//            int newRoom = Rooms.getRoomById(Main.player.getCurrentRoom()).getExits().get(direction);
//            Main.player.setCurrentRoom(newRoom);
//            Main.player.setRoomName(Rooms.getRoomById(Main.player.getCurrentRoom()).getName());
//        } catch (Exception e) {
//            System.out.println("There isn't any item to drop...");
//            System.out.print("Press any key to continue...");
//            Scanner scanner = new Scanner(System.in);
//            scanner.nextLine();
//        }
    }

//    public static void playerLocation(int roomId) {
//        // Get the Room object from the Rooms class using the provided roomId
//        Room room = Rooms.getRoomById(roomId);
//
//        // Check if the room object is not null, meaning the room with the given ID was found
//        if (room != null) {
//            // Display the room name and description to the player
//            System.out.println("You are in the " + room.getName());
//            System.out.println(room.getDescription());
//        } else {
//            // The room with the provided ID was not found in the game's rooms collection
//            System.out.println("Invalid room ID. Unable to determine your location.");
//        }
//    }

    public static <T> T loadJSONFile (String path, Class<T> clazz) throws IOException {
        //noinspection ConstantConditions
        try (Reader reader = new InputStreamReader(GameMethods.class.getClassLoader().getResourceAsStream(path))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, clazz);
        }
    }

    public static <T> T loadJSONTextFile (String path, TypeToken<T> token) throws IOException {
        //noinspection ConstantConditions
        try (Reader reader = new InputStreamReader(GameMethods.class.getClassLoader().getResourceAsStream(path))) {
            Gson gson = new Gson();
            return gson.fromJson(reader, token);
        }
    }

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