import com.google.gson.Gson;
import org.ietf.jgss.GSSManager;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Rooms {
 //   private static Map<Integer, Room> roomMap = new HashMap<>();
    private static Room[] rooms;

    public static void loadRoomsFromJSON() throws IOException {
//        Room[] rooms = GameMethods.loadJSONFile("json/Rooms.json", Room[].class);
//        Arrays.stream(rooms)
//                .collect(Collectors.toMap(Room::getRoomId, Function.identity()));
        rooms = GameMethods.loadJSONFile("json/Rooms.json",Room[].class);
    }

    public static Room getRoomById(int roomId) {
        //return roomMap.get(roomId);
            for (Room room : rooms) {
                if (room.getRoomId() == roomId) {
                    return room;
                }
            }
            return null; // If the room is not found, return null
        }

    public static List<Room> getAllRooms() {
        //return all rooms
        List<Room> allRooms = new ArrayList<>();
        for (Room room : rooms) {
            allRooms.add(room);
        }
        return allRooms; // If the room is not found, return null
    }




//    public static void printRoomInfo(int roomId) {
//        Room room = getRoomById(roomId);
//        Map<String, Object> map = new LinkedHashMap<>();
//
//        if (room != null) {
//            System.out.println("You are in: " + room.getName());
//            System.out.println(room.getDescription());
//
//            Map<String, Object> items = room.getItems();
//            if (!items.isEmpty()) {
//                System.out.println("You see the following items in the room:");
//                for (Map.Entry<String, Object> item : map.entrySet()) {
//                    System.out.println("- " + item.getKey() + ": " + item.getValue());
//                }
//            } else {
//                System.out.println("There are no items in the room.");
//            }
//
//            Map<String, Integer> exits = room.getExits();
//            if (!exits.isEmpty()) {
//                System.out.println("You can go the following directions:");
//                for (String direction : exits.keySet()) {
//                    System.out.println("- " + direction);
//                }
//            } else {
//                System.out.println("There are no exits from this room.");
//            }
//        } else {
//            System.out.println("Invalid room ID.");
//        }
//    }
}
