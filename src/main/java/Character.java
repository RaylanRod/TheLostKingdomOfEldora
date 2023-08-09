import java.util.*;

public class Character {
//    private String name;
//    private int health = 100;
//    Rooms rooms = new Rooms();
//    Room startingRoom = rooms.getRoomById(1);
//    private Move move = new Move(rooms, startingRoom);
//    List<Items> inventory = new LinkedList<>();

    private String name;
    private int health;
    private int currentRoom;
    private String roomName;
    private ArrayList<String> inventory;

    //CTORs
    public Character() {
        this.name = "Bob";
        this.health = 100;
        this.currentRoom = 1;
        this.roomName = "The Forgotten Courtyard";
        this.inventory = null;
    }

    public Character(String playerName) {
        this();
        setName(playerName);
    }

    //Getters and Setters
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

    public int getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(int currentRoom) {
        this.currentRoom = currentRoom;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public ArrayList<String> getInventory() {
        return inventory;
    }

    public void setInventory(ArrayList<String> inventory) {
        this.inventory = inventory;
    }

    @Override
    public String toString() {
        return  "Name: " + name +
                ", Current Health: " + health +
                ", Current Room: " + roomName +
                ", Current Inventory: " + inventory +
                '\n';
    }

    //    private String getName() {
//        return name;
//    }
//
//    private void setName(String name) {
//        this.name = name;
//    }
//
//    private int getHealth() {
//        return health;
//    }
//
//    private void setHealth(int health) {
//        this.health = health;
//    }
//
//    public void moveDirection(String direction) {
//        move.moveDirection(direction);
//    }
//
//    private void setMove(Move move) {
//        this.move = move;
//    }

//    private String exits(){
//        Map<String, String> currentExits = new TreeMap<>();
//        Set<Map.Entry<String, Integer>> entrySet = startingRoom.getExits().entrySet();
//
//       for(Map.Entry<String, Integer> entry : entrySet) {
//           Integer roomNumber = entry.getValue();
//           if (entry.getValue() != null) {
//               Room roomNameByID = rooms.getRoomById(roomNumber);
//               currentExits.put(entry.getKey(), roomNameByID.getName());
//           }
//       }
//       return currentExits.toString();
//    }

//    @Override
//    public String toString() {
//        return "******************************************************" +"\n" +
//                name + "\n" +
//                " health: " + health +
//                ", \n your current location: " + startingRoom.getName() +
//                ", \n inventoryL: " + inventory +
//                ", \n you can go: " +  exits() +
//                "\n"+
//                "*****************************************************";
//    }
}
