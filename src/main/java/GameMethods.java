import java.util.Scanner;


public class GameMethods {

    public static boolean confirmQuit(Scanner scanner) {
        // Prompt the player for confirmation
        System.out.print("Are you sure you want to quit? (yes/no): ");
        String input = scanner.nextLine().trim().toLowerCase();
        // Check if the player's input is "yes" (case-insensitive)
        return input.equals("yes");
    }

}