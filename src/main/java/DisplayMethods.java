import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class DisplayMethods {

    public static void printTextJsonFile(String fileName){
        try{
            Map<String, String> textMap = GameMethods.loadJSONTextFile(fileName, new TypeToken<Map<String, String>>() {});
            System.out.println(textMap.get("text"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHeader() {
        System.out.println("Character:\n" + Main.player);
        System.out.println("Description:\n" + Rooms.getRoomById(Main.player.getCurrentRoom()).getDescription() + "\n");
    }

    public static void printRoomItems() {
        if (Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().size() > 0){
            System.out.print("Things in the Room: | ");
            for (int i=0; i < Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().size(); ++i ) {
                System.out.print(Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().get(i).get("name") + " | ");
            }
            System.out.println("\n");
        } else {
            System.out.println("Things in the Room: | Nothing in the room |\n");
        }
    }

    public static void printTextFile(String fileName) {
        // Prints the opening splash screen
        //noinspection ConstantConditions
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DisplayMethods.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
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
                            System.out.println(spaces32 + "##     X    ##");
                        break;
                    case 18:
                        if (inRoom == 10)
                            System.out.println("##         X              ##                  ##                          ##");
                        if (inRoom == 5)
                            System.out.println("##                        ##         X        ##                          ##");
                        if (inRoom == 8)
                            System.out.println("##                        ##                  ##            X             ##");
                        break;
                    case 26:
                        if (inRoom == 2)
                            System.out.println("##                    ##             X             ##                     ##");
                        if (inRoom == 6)
                            System.out.println("##        X           ##                           ##                     ##");
                        if (inRoom == 7)
                            System.out.println("##                    ##                           ##        X            ##");
                        break;
                    case 35:
                        if (inRoom == 4)
                            System.out.println("##    #   X  #                                             #      #       ##");
                        if (inRoom == 1)
                            System.out.println("##    #      #                       X                     #      #       ##");
                        if (inRoom == 3)
                            System.out.println("##    #      #                                             #   X  #       ##");
                        break;
                    default:
                        System.out.println(line);
                        break;
            }
                i++;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print("Press any key to continue...");
        // Wait for the player to press Enter before continuing
        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();
    }

    public static void clearScreen() {
        // Print 50 empty lines to simulate screen clearing
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}