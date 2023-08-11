import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.Map;
import java.util.Scanner;

public class DisplayMethods extends Colors {

    public static void printTextJsonFile(String fileName){
        try{
            Map<String, String> textMap = GameMethods.loadJSONTextFile(fileName, new TypeToken<Map<String, String>>() {});
            System.out.println(green + textMap.get("text") + white);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void printHeader() {
        System.out.println(green + "Character:\n"+ blue + Main.player);
        System.out.println(green + "Description:\n" + blue + Rooms.getRoomById(Main.player.getCurrentRoom()).getDescription() + "\n");
    }

    public static void printRoomItems() {
        if (Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().size() > 0){
            System.out.print(green + "Things in the Room: | " + blue);
            for (int i=0; i < Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().size(); ++i ) {
                System.out.print(blue + Rooms.getRoomById(Main.player.getCurrentRoom()).getItems().get(i).get("name") + green + " | " + white);
            }
            System.out.println("\n");
        } else {
            System.out.println(green + "Things in the Room: |" + blue +" Nothing in the room " + green +"|\n" + white);
        }
    }

    public static void printTextFile(String fileName) {
        // Prints the opening splash screen
        //noinspection ConstantConditions
        try (BufferedReader br = new BufferedReader(new InputStreamReader(DisplayMethods.class.getClassLoader().getResourceAsStream(fileName)))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(red + line + white);
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
                            System.out.println(spaces32 + "##     "+blue+"X"+green+"    ##");
                        break;
                    case 18:
                        if (inRoom == 10)
                            System.out.println("##         "+blue+"X"+green+"              ##                  ##                          ##");
                        if (inRoom == 5)
                            System.out.println("##                        ##         "+blue+"X"+green+"        ##                          ##");
                        if (inRoom == 8)
                            System.out.println("##                        ##                  ##            "+blue+"X"+green+"             ##");
                        break;
                    case 26:
                        if (inRoom == 2)
                            System.out.println("##                    ##             "+blue+"X"+green+"             ##                     ##");
                        if (inRoom == 6)
                            System.out.println("##        "+blue+"X"+green+"           ##                           ##                     ##");
                        if (inRoom == 7)
                            System.out.println("##                    ##                           ##        "+blue+"X"+green+"            ##");
                        break;
                    case 35:
                        if (inRoom == 4)
                            System.out.println("##    #   "+blue+"X"+green+"  #                                             #      #       ##");
                        if (inRoom == 1)
                            System.out.println("##    #      #                       "+blue+"X"+green+"                     #      #       ##");
                        if (inRoom == 3)
                            System.out.println("##    #      #                                             #   "+blue+"X"+green+"  #       ##");
                        break;
                    default:
                        System.out.println(green + line);
                        break;
            }
                i++;
            }
            System.out.println("\n" + blue+"X"+green+" - denotes players current position"+white);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.print(white+"Press any key to continue...");
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