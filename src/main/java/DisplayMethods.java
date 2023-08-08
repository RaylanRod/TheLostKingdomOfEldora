import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class DisplayMethods {
    public static void displayIntro() {
        // Display the game's title and introductory message
        System.out.println();
        System.out.println("Welcome to The Lost Kingdom!");
        System.out.println("Where magic flows through the very air,a young hero stands at the crossroads of destiny.");
        System.out.println("The legends of the Kingdom have reached their ears, and an insatiable desire to uncover its secrets burns within them.");
        System.out.println("In this text-based RPG, you will embark on a thrilling adventure through a mysterious realm.");
        System.out.println("Your journey will be full of puzzles, treasures, and ancient secrets waiting to be uncovered.");
        System.out.println("Use natural language commands to interact with the world and discover the fate of the lost kingdom.");
        System.out.println("Type 'help' at any time to see a list of available commands.");
        System.out.println();
        System.out.println();
        System.out.println("Prepare yourself for an epic quest!");
        System.out.println();
        System.out.println("===========================================");
        System.out.println();
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