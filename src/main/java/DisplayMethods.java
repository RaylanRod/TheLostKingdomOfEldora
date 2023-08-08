import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.*;
import java.util.Scanner;

public class DisplayMethods {

    public static void printTextJsonFile(String fileName){
        File theFile = new File(fileName);
        JsonElement fileElement = null;
        try {
            fileElement = JsonParser.parseReader(new FileReader(theFile));
            JsonObject fileObject = fileElement.getAsJsonObject();
            String text = fileObject.get("text").getAsString();
            System.out.println(text);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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