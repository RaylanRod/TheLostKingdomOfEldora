import com.google.gson.Gson;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Locale;


public class Move {

    private final Gson gson = new Gson();
    private Rooms rooms;
    private Room currentRoom; // hold the current room

    public Move(Rooms rooms, Room startingRoom) {
        this.rooms = rooms;
        this.currentRoom = startingRoom;
//        rooms = new Rooms();
//        try {
//            rooms.loadRoomsFromJSON();
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        currentRoom= getStartingRoom();
    }

    private Room getStartingRoom() {
        return rooms.getRoomById(1); // sets the starting room as room_id 1
    }

    public void moveDirection(String direction) {
        if (currentRoom.getExits().containsKey(direction)) {
            Integer nextRoomId = currentRoom.getExits().get(direction);
            if (nextRoomId == null) {
                System.out.println("There is no room in that direction");
            } else {
                Room nextRoom = rooms.getRoomById(nextRoomId);
                String roomName = nextRoom.getName();
                if (nextRoom != null) {
                    // move the player to the next room
                    currentRoom = nextRoom;
                    System.out.println("your in room " + roomName);
                }
            }
        } else {
            System.out.println("There is no room in that direction");
        }
    }

}






