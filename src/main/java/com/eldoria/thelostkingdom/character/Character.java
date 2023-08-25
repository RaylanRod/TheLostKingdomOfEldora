package com.eldoria.thelostkingdom.character;

import com.eldoria.thelostkingdom.display.Colors;

import java.util.*;

public class Character extends Colors {
    private String name;
    private int health;
    private int currentRoomId;
    private int attackDamage;
    private String roomName;
    private Map<String, Object> inventory;

    //CTORs
    public Character() {
//        this.name = "Bob";  //<-- OLD CODE: username always Bob
        this.name = "";  // NEW CODE: Custom name
        this.health = 100;
        this.attackDamage = 10;
        this.currentRoomId = 1;
        this.roomName = "The Forgotten Courtyard";
        this.inventory = new HashMap<>();
    }

    public Character(String playerName) {
        this();
        setName(playerName);
    }
    //Functional methods to allow the player to participate in glorious combat -jp
    public int attack() {
        if (inventory.containsKey("silver sword")) {
            return 15 + new Random().nextInt(36);  // random number between 15 and 50
        } else {
            return attackDamage;
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public boolean hasItem(String itemName){
        return inventory.containsKey(itemName);
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

    public int getCurrentRoomId() {
        return currentRoomId;
    }

    public void setCurrentRoomId(int currentRoomId) {
        this.currentRoomId = currentRoomId;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }

    public Map<String, Object> getInventory() {
        return inventory;
    }

    public void setInventory(Map<String, Object> inventory) {
        this.inventory = inventory;
    }
    public void setAttackDamage(int attackDamage){
        this.attackDamage = attackDamage;
    }

    @Override  //NEW CODE: all cyan previously blue
    public String toString() {
        return  green + "Name: " +  name  +
                ", Current Health: "  + health  +
                ", Current Room: " + roomName  +
                ", Current Inventory: " + inventory.keySet() + '\n';
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
