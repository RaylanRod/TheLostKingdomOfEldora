import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Move {

    private final Gson gson = new Gson();
    private Character player; // declare the player object
    private Rooms rooms;
    private Room currentRoom; // hold the current room

    public Move() {
        player = createCharacter();
        rooms = new Rooms();
        try {
            rooms.loadRoomsFromJSON();

        } catch (IOException e) {
            e.printStackTrace();
        }
        currentRoom= getStartingRoom();
    }
    private Room getStartingRoom() {
        return rooms.getRoomById(1); // sets the starting room as room_id 1
    }
    public void handleMove(String command) {
        List<String> verbsAndNouns = TextParser.extractVerbsAndNouns(command);
        if (verbsAndNouns.size() >= 2 && verbsAndNouns.get(0).equalsIgnoreCase("go")) {
            String direction = verbsAndNouns.get(1).toUpperCase();
            moveDirection(direction);
        }
    }

    public void moveDirection(String direction) {
        if (currentRoom.getExits().containsKey(direction)) {
            int nextRoomId = currentRoom.getExits().get(direction);
            Room nextRoom = rooms.getRoomById(nextRoomId);
                if (nextRoom != null) {
                    // move the player to the next room
                    currentRoom = nextRoom;
                }
        } else {
            System.out.println("There is no room in that direction");
        }
    }

     public Character createCharacter(){
         try (FileReader reader = new FileReader("src/main/resources/json/Character.json")) {
             Character player1 = gson.fromJson(reader, Character.class);
             System.out.println(player1.getName());
             System.out.println(player1.getHealth());
             return player1; // Return the player object
         } catch (IOException e) {
             e.printStackTrace();
         }
        return null;
     }



    public class Character {
        private String name;
        private int health;

        // Add constructors, getters, setters, etc. as needed

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHealth() {
            return health;
        }

        public void setHealth(int health) {
            this.health = health;
        }
    }
}






