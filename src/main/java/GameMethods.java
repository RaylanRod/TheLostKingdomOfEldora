import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import java.io.*;
import java.lang.reflect.Type;
import java.util.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class GameMethods extends Colors{

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
            System.out.println(blue + Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("dialog") + "\n");
            Scanner scanner = new Scanner(System.in);
            String npcName = (String) Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("name");
 //            //if the NPC name is enigma
            if("Enigma".equalsIgnoreCase(npcName)) {
//                //get/print a random riddle
                List<Map<String, Object>> riddles = (List<Map<String, Object>>) Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("riddle");
                int randomIndex = (int) (Math.random() * riddles.size());
                Map<String, Object> randomRiddle = riddles.get(randomIndex);
                System.out.println(green + randomRiddle.get("question") + blue);
//                //get the user input
                System.out.println(green + "Type you answer:> " + white);
                String input = scanner.nextLine().trim().toLowerCase();
//                //compare the user input to the riddle answer
//                //if the answer is correct
                if(input.equals(randomRiddle.get("answer")) ) {
                    //print you answered correct
                    System.out.println("correct");
                }//else
                else {
                    System.out.println(blue + "wrong answer" + white);
                }
            } else {
            System.out.print("Press any key to continue...");
            scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println(blue + "There isn't any NPC to talk to..." + white);
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void look(String itemToLookAT){
            DisplayMethods.clearScreen();
        List<Map<String, Object>> curRoomItemsArray = Rooms.getRoomById(Main.player.getCurrentRoom()).getItems();
        Map<String, Object> inventory = Main.player.getInventory();
        if(curRoomItemsArray != null) {
            for(Map<String, Object> item : curRoomItemsArray){
                if(item.get("name").equals(itemToLookAT)){
                    System.out.println(itemToLookAT + " description: " + item.get("description"));
                    System.out.println(itemToLookAT + " usage: " + item.get("usage"));
                }
            }
        } else {

            System.out.println("There isn't any NPC to talk to...");
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void getItem(String itemToGet){
        List<Map<String, Object>> curRoomItemsArray = Rooms.getRoomById(Main.player.getCurrentRoom()).getItems();
        Map<String, Object> inventory = Main.player.getInventory();
        if(curRoomItemsArray != null) {
            Iterator<Map<String, Object>> iterator = curRoomItemsArray.iterator();
            while (iterator.hasNext()) {
                Map<String, Object> item = iterator.next();
                    if (item.get("name").equals(itemToGet)) {
                        String itemName = (String) item.get("name");
                        inventory.put(itemName, item);
                        iterator.remove();
                        break;
                    }
            }
        } else {
            System.out.println("There isn't an item to take...");
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        System.out.println("current room array" + curRoomItemsArray);
    }

    public static void dropItem(String itemToDrop){
        List<Map<String, Object>> curRoomItemsArray = Rooms.getRoomById(Main.player.getCurrentRoom()).getItems();
        Map<String, Object> inventory = Main.player.getInventory();
        if(inventory == null || inventory.isEmpty()) {
            System.out.println("Your inventory is empty. Nothing to drop.");
            return;
        }

        if (!inventory.containsKey(itemToDrop)){
            System.out.println("Item not found in your inventory.");
            return;
        }
        Map<String, Object> droppedItem = (Map<String, Object>) inventory.remove(itemToDrop);
        if (droppedItem != null) {
            curRoomItemsArray.add(droppedItem);
            System.out.println("you dropped: " + itemToDrop);
            System.out.println("Dropped items in the room " + curRoomItemsArray);
        } else {
            System.out.println("Failed to drop the item.");
        }


//        try {
//            List<String> items = Main.player.getInventory();
//            if (items == null || items.isEmpty()) {
//                System.out.println("Your inventory is empty. Nothing to drop.");
//                return;
//            }
//
//            if (!items.contains(item)) {
//                System.out.println("Item not found in your inventory.");
//                return;
//            }
//
//            Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().put("name", item);
//            items.remove(item);
//            Main.player.setInventory(items);
//
//            System.out.println("You have dropped the item: " + item);
//        } catch (Exception e) {
//            System.out.println("An error occurred while dropping the item.");

//        }
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

    public static <T> void saveJSONFile(String path, Character data) throws IOException {
        try {
            Gson gson = new Gson();
            String jsonData = gson.toJson(data);

            FileOutputStream file = new FileOutputStream(path);
            OutputStreamWriter output = new OutputStreamWriter(file);

            output.write(jsonData);
            output.close();

        }
        catch (Exception e) {
            e.printStackTrace();
        }

    }


}

//class Tests {
//    public static void main(String[] args) {
//    getItem()
//    }
//}
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