package com.eldoria.thelostkingdom.rooms;

import java.util.List;
import java.util.Map;

public class Room {
    private int roomId;                 // Unique identifier for each room
    private String name;                // Name of the room
    private String description;         // Description of the room
    private Map<String, Integer> exits; // Map of exits from the room to other room IDs
    private List<Map<String, Object>> items;  // Map of items associated with the room
    private Map<String, Object> NPC;    // Map of NPC information associated with the room


    // Constructor to initialize the room properties
    public Room(int roomId, String name, String description, Map<String, Integer> exits,
                List<Map<String, Object>> items, Map<String, Object> NPC) {
        this.roomId = roomId;
        this.name = name;
        this.description = description;
        this.exits = exits;
        this.items = items;
        this.NPC = NPC;
    }

    // Getters and setters for the room properties
    public int getRoomId() {
        return roomId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getExits() {
        return exits;
    }

    public List<Map<String, Object>> getItems() {
        return items;
    }

    public void setItems(List<Map<String, Object>> items) {
        this.items = items;
    }

    public Map<String, Object> getNPC() {
        return NPC;
    }


}