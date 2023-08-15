import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.*;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class GameMethods extends Colors{

    public static boolean startNewGame(Scanner scanner) {
        // Prompt the player for confirmation
        System.out.print("Would you like to start a new game? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        // Check if the player's input is "yes" (case-insensitive)
//        return input.equals("yes");  //OLD CODE: edits for custom name
        if (input.equals("yes")) {  //NEW CODE: Custom name starts here
            System.out.println("Enter your character's name: ");
            String playerName = scanner.nextLine().trim();
            Main.player = new Character(playerName);
            return true;
        }
        return false;  //NEW CODE: Custom name ends here
    }

    public static boolean confirmQuit(Scanner scanner) {
        // Prompt the player for confirmation
        System.out.print("Are you sure you want to quit? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        // Check if the player's input is "yes" (case-insensitive)
        return input.equals("yes");
    }

    public static void moveRoom(String direction, boolean playFX, float fxVolumeLevel) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
       try {
           int newRoom = Rooms.getRoomById(Main.player.getCurrentRoom()).getExits().get(direction);
           Main.player.setCurrentRoom(newRoom);
           Main.player.setRoomName(Rooms.getRoomById(Main.player.getCurrentRoom()).getName());
           if (playFX) {
               MusicPlayer fxPlayer = new MusicPlayer("fx", "audioFiles/moveRoom.wav");
               fxPlayer.setVolume( "fx", fxVolumeLevel);
               fxPlayer.play("fx");
           }
       } catch (Exception e) {
           System.out.println(red + "You entered an INVALID direction" + red);
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
                    System.out.println(red + "wrong answer" + white);
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
                    System.out.println(green + " Description: " + blue + item.get("description") +white);
                    System.out.println(green + " Usage: " + blue + item.get("usage") + white);
                }
            }
        } else {

            System.out.println(red + "There isn't anything to look at..." + white);
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void getItem(String itemToGet, boolean playFX, float fxVolumeLevel) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Map<String, Object>> curRoomItemsArray = Rooms.getRoomById(Main.player.getCurrentRoom()).getItems();
        Map<String, Object> inventory = Main.player.getInventory();
        if(curRoomItemsArray.size() != 0) {
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
            if (playFX) {
                MusicPlayer fxPlayer = new MusicPlayer("fx", "audioFiles/getItem.wav");
                fxPlayer.setVolume( "fx", fxVolumeLevel);
                fxPlayer.play("fx");
            }
        } else {
            System.out.println(red + "There isn't an item to take..." + white);
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
        System.out.println("current room array" + curRoomItemsArray);
    }

    public static void dropItem(String itemToDrop, boolean playFX, float fxVolumeLevel) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        List<Map<String, Object>> curRoomItemsArray = Rooms.getRoomById(Main.player.getCurrentRoom()).getItems();
        Map<String, Object> inventory = Main.player.getInventory();
        if(inventory == null || inventory.isEmpty()) {
            System.out.println(red + "Your inventory is empty. Nothing to drop." + white);
            return;
        }

        if (!inventory.containsKey(itemToDrop)){
            System.out.println("Item not found in your inventory.");
            return;
        }
        Map<String, Object> droppedItem = (Map<String, Object>) inventory.remove(itemToDrop);
        if (droppedItem != null) {
            curRoomItemsArray.add(droppedItem);
            //System.out.println(blue + "You dropped: " + green + itemToDrop + white);
            if (playFX) {
                MusicPlayer fxPlayer = new MusicPlayer("fx", "audioFiles/dropItem.wav");
                fxPlayer.setVolume( "fx", fxVolumeLevel);
                fxPlayer.play("fx");
            }
            //System.out.println(blue + "Dropped items in the room " + green +  curRoomItemsArray + white);
        } else {
            System.out.println(red + "Failed to drop the item." + white);
        }

    }

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

    public static <T> void saveJSONFile(String path, T data) throws IOException {
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

    public static void saveRoomsToJSON(Rooms rooms, String path) throws IOException {
        Gson gson = new Gson();
        String json = gson.toJson(rooms);

        try (FileWriter writer = new FileWriter(path)) {
            writer.write(json);
        }
    }

    public static void attack(boolean playFX, float fxVolumeLevel){
        try {
            String npcName = (String) Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("name");
            System.out.println("the target is: " + npcName);
            System.out.println("the player is attacking the target.");
            if (playFX) {
                MusicPlayer fxPlayer = new MusicPlayer("fx", "audioFiles/attackNPC.wav");
                fxPlayer.setVolume( "fx", fxVolumeLevel);
                fxPlayer.play("fx");
            }
        } catch (Exception e) {
            System.out.println("There is not a valid target to attack..." );
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void winGame(boolean playFX) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
       Room room = Rooms.getRoomById(Main.player.getCurrentRoom());
        Map<String, Object> inventory = Main.player.getInventory();
        boolean hasItem1 = inventory.containsKey("royal crown piece left");
        boolean hasItem2 = inventory.containsKey("royal crown piece right");
        boolean hasItem3 = inventory.containsKey("royal crown piece middle");
        boolean hasItem4 = inventory.containsKey("royal crown piece back");
        if(room.getRoomId() == 5) {
            if (hasItem1 && hasItem2 && hasItem3 && hasItem4) {
                MusicPlayer fxPlayer = new MusicPlayer("fx", "audioFiles/win.wav");
                fxPlayer.setVolume( "fx", (float) 8.0/10);
                fxPlayer.play("fx");
                DisplayMethods.clearScreen();
                DisplayMethods.printTextFile("textFiles/Win_Screen.txt");
            } else {
                System.out.println(purple + "You are in the right room to win, but you need more items to win." + white);
            }
        } else if(hasItem1 && hasItem2 && hasItem3 && hasItem4) {
            System.out.println(purple + "You have all the pieces but you are not in the correct room to win the game." + white);
        }
    }

}
