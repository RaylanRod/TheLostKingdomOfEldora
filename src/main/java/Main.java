import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        //Display Functions to act as a preface to the game starting.
        DisplayMethods.printTextFile("src/main/textFiles/Welcome_Screen.txt");
        DisplayMethods.displayIntro();

        // Game loop starts here
        boolean isGameOver = false;
        Scanner scanner = new Scanner(System.in);

        // Ask the player if they want to start a new game
        if (GameMethods.startNewGame(scanner)) {
            DisplayMethods.clearScreen();
            while (!isGameOver) {
                // Display the prompt to the player and read their input
                System.out.print("> ");
                String input = scanner.nextLine().trim().toLowerCase();

                // Process the player's input and handle game actions
                if (input.equals("quit")) {
                    // Player wants to quit, ask for confirmation
                    if (GameMethods.confirmQuit(scanner)) {
                        // Player confirmed to quit, set isGameOver to true to end the game loop
                        isGameOver = true;
                    } else {
                        // Player did not confirm, continue the game loop
                        System.out.println("Resuming game...");
                    }
                } else {
                    // Handle other game actions based on the input
                    // For example, process movement, interactions, etc.
                    System.out.println("You entered: " + input);
                }
            }
        } else {
            // Player chose not to start a new game, exit the program
            System.out.println("Thank you for considering The Lost Kingdom of Eldoria!");
        }

        // Game loop ends here
        System.out.println("Thank you for playing The Lost Kingdom of Eldoria!");
    }
}