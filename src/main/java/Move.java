import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Move {

    private final Gson gson = new Gson();
    private Rooms rooms;
    private Room currentRoom; // hold the current room

    public Move() {
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
            String direction = verbsAndNouns.get(1);
            moveDirection(direction);
        }
    }

    public void moveDirection(String direction) {
        if (currentRoom.getExits().containsKey(direction)) {
            Integer nextRoomId = currentRoom.getExits().get(direction);
            if (nextRoomId == null) {
                System.out.println("There is no room in that direction");
            } else {
                Room nextRoom = rooms.getRoomById(nextRoomId);
                if (nextRoom != null) {
                    // move the player to the next room
                    currentRoom = nextRoom;
                    System.out.println(nextRoomId);
                }
            }
        } else {
            System.out.println("There is no room in that direction");
        }
    }

}






