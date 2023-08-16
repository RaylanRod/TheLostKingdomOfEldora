package com.eldoria.thelostkingdom.display;

import com.eldoria.thelostkingdom.Main;
import com.eldoria.thelostkingdom.gamelogic.GameMethods;
import com.eldoria.thelostkingdom.rooms.Rooms;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class DisplayMethods extends Colors {  //NEW CODE: all cyan previously blue

    public static void printTextJsonFile(String fileName) {
        try {
            Map<String, String> textMap = GameMethods.loadJSONTextFile(fileName, new TypeToken<Map<String, String>>() {
            });
            System.out.println(Colors.green + textMap.get("text") + Colors.white);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHeader() {
        System.out.println(Colors.green + "Character:\n" + Colors.cyan + Main.player);
        System.out.println(Colors.green + "Description:\n" + Colors.cyan + Rooms.getRoomById(Main.player.getCurrentRoom()).getDescription() + "\n");
    }

    public static void printRoomItems() {
        if (Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().size() > 0) {
            System.out.print(Colors.green + "Items in the Room: | " + Colors.cyan);
            for (int i = 0; i < Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().size(); ++i) {
                System.out.print(Colors.cyan + Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().get(i).get("name") + Colors.green + " | " + Colors.white);
            }
            System.out.print("\n");
        } else {
            System.out.print(Colors.green + "Items in the Room: |" + Colors.cyan + " No items in the room " + Colors.green + "|" + Colors.white + "\n");
        }
    }

    public static void printRoomNPC() {
        try {
            Object roomNPC = Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("name");
            System.out.println(Colors.green + "Characters in the Room: | " + Colors.cyan + roomNPC + Colors.green + " |" + Colors.white + "\n");
        } catch (Exception e) {
            System.out.print(Colors.green + "Characters in the Room: |" + Colors.cyan + " No characters in the room " + Colors.green + "|" + Colors.white + "\n");
        }
    }

    public static void printSuccessfulAttack() {
        try {
            Object roomNPC = Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("name");
            System.out.println(Colors.purple + "You have successfully attacked: | " + Colors.cyan + roomNPC + Colors.purple + " |" + Colors.white + "\n");
        } catch (Exception e) {
            System.out.println(Colors.purple + "There was no character to attack!!!" + Colors.white + "\n");
        }
    }

    public static void printTextFile(String fileName) {
        // Prints the opening splash screen
        //noinspection ConstantConditions
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DisplayMethods.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(Colors.red + line + Colors.white);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Press any key to continue...");
        // Wait for the player to press Enter before continuing
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void printTextMap() {

        String path = "textFiles/Castle_Map.txt";
        String spaces32 = new String(new char[32]).replace('\0', ' ');

        int inRoom = Main.player.getCurrentRoom();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DisplayMethods.class.getClassLoader().getResourceAsStream(path)))) {
            String line;
            int i = 1;
            while ((line = br.readLine()) != null) {
                switch (i) {
                    case 9:
                        if (inRoom == 9)
                            System.out.println(spaces32 + "##     " + Colors.cyan + "X" + Colors.green + "    ##");
                        break;
                    case 18:
                        if (inRoom == 10)
                            System.out.println("##         " + Colors.cyan + "X" + Colors.green + "              ##                  ##                          ##");
                        if (inRoom == 5)
                            System.out.println("##                        ##         " + Colors.cyan + "X" + Colors.green + "        ##                          ##");
                        if (inRoom == 8)
                            System.out.println("##                        ##                  ##            " + Colors.cyan + "X" + Colors.green + "             ##");
                        break;
                    case 26:
                        if (inRoom == 2)
                            System.out.println("##                    ##             " + Colors.cyan + "X" + Colors.green + "             ##                     ##");
                        if (inRoom == 6)
                            System.out.println("##        " + Colors.cyan + "X" + Colors.green + "           ##                           ##                     ##");
                        if (inRoom == 7)
                            System.out.println("##                    ##                           ##        " + Colors.cyan + "X" + Colors.green + "            ##");
                        break;
                    case 35:
                        if (inRoom == 4)
                            System.out.println("##    #   " + Colors.cyan + "X" + Colors.green + "  #                                             #      #       ##");
                        if (inRoom == 1)
                            System.out.println("##    #      #                       " + Colors.cyan + "X" + Colors.green + "                     #      #       ##");
                        if (inRoom == 3)
                            System.out.println("##    #      #                                             #   " + Colors.cyan + "X" + Colors.green + "  #       ##");
                        break;
                    default:
                        System.out.println(Colors.green + line);
                        break;
                }
                i++;
            }
            System.out.println("\n" + Colors.cyan + "X" + Colors.green + " - denotes players current position; player can go north, south, east or west; up/down for stairs" + Colors.white);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print(Colors.white + "Press any key to continue...");
        // Wait for the player to press Enter before continuing
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }
        ////===================old clearScreen function ============================///
    //    public static void clearScreen() {
//        // Print 50 empty lines to simulate screen clearing
//        for (int i = 0; i < 50; i++) {
//            System.out.println();
//        }
//    }

    //============RMR new ClearScreen engine 8.16.23============================//
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
