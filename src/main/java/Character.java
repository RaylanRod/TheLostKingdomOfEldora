import java.util.*;

public class Character {
    private String name;
    private int health = 100;
    Rooms rooms = new Rooms();
    Room startingRoom = rooms.getRoomById(1);
    private Move move = new Move(rooms, startingRoom);
//    List<Items> inventory = new LinkedList<>();


    public Character(String name, int health, Room room) {
        this.name = name;
        this.health = health;
        this.startingRoom = room;
    }

    private String getName() {
        return name;
    }

    private void setName(String name) {
        this.name = name;
    }

    private int getHealth() {
        return health;
    }

    private void setHealth(int health) {
        this.health = health;
    }

    public void moveDirection(String direction) {
        move.moveDirection(direction);
    }

    private void setMove(Move move) {
        this.move = move;
    }

    private String exits(){
        Map<String, String> currentExits = new TreeMap<>();
        Set<Map.Entry<String, Integer>> entrySet = startingRoom.getExits().entrySet();

       for(Map.Entry<String, Integer> entry : entrySet) {
           Integer roomNumber = entry.getValue();
           if (entry.getValue() != null) {
               Room roomNameByID = rooms.getRoomById(roomNumber);
               currentExits.put(entry.getKey(), roomNameByID.getName());
           }
       }
       return currentExits.toString();
    }

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
