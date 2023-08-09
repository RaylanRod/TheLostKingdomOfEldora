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

        System.out.print("Press Enter to begin your adventure...");
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