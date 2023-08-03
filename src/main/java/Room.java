// Room class represents a location in the game world
import java.util.List;
import java.util.Map;

public class Room {
    private int roomId;                 // Unique identifier for each room
    private String name;                // Name of the room
    private String description;         // Description of the room
    private Map<String, Integer> exits; // Map of exits from the room to other room IDs
    private List<Integer> items;        // List of item IDs present in the room

    // Constructor to initialize the room properties
    public Room(int roomId, String name, String description, Map<String, Integer> exits, List<Integer> items) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
    }

    // Getters and setters for the room properties
    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }

    public void setExits(Map<String, Integer> exits) {
        this.exits = exits;
    }

    public List<Integer> getItems() {
        return items;
    }

    public void setItems(List<Integer> items) {
        this.items = items;
    }
}
