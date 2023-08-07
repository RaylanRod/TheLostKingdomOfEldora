// Rooms class manages the collection of Room objects in the game
import com.google.gson.Gson;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Rooms {
    private static Room[] rooms; // Array to hold all the rooms in the game

    // Method to load rooms from a JSON file into the 'rooms' array
    public static void loadRoomsFromJSON() throws IOException {
        rooms = GameMethods.loadJSONFile("json/Rooms.json", Room[].class);
    }


    // Method to get a room by its unique ID
    public static Room getRoomById(int roomId) {
        for (Room room : rooms) {
            if (room.getRoomId() == roomId) {
                return room;
            }
        }
        return null; // If the room is not found, return null
    }

    // Optionally, you can add a method to get all rooms if needed
    public static Room[] getAllRooms() {
        return rooms; // Returns the entire array of rooms
    }
}
