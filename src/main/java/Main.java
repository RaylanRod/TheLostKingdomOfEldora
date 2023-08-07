import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        // Load JSON files
        try {
            Rooms.loadRoomsFromJSON();
            Items.loadItemsFromJSON();
        } catch (IOException e) {
            System.out.println("Error loading game data: " + e.getMessage());
            return;
        }

        //Display Functions to act as a preface to the game starting.
        DisplayMethods.printTextFile("textFiles/Welcome_Screen.txt");
        DisplayMethods.displayIntro();

        // Game loop starts here
        boolean isGameOver = false;
        Scanner scanner = new Scanner(System.in);

        // Ask the player if they want to start a new game
        if (GameMethods.startNewGame(scanner)) {
            DisplayMethods.clearScreen();

            // Player is starting in room ID, pass it to the playerLocation method
            GameMethods.playerLocation(1);

            while (!isGameOver) {
                // Display the prompt to the player and read their input
                System.out.print("What would you like to do? Type 'Help' for commands or 'Quit' to exit > ");
                String input = scanner.nextLine().trim().toLowerCase();
                List<String> verbsAndNouns = TextParser.extractVerbsAndNouns(input);

                // Process the player's input and handle game actions
                if (verbsAndNouns.size() == 1) {
                    switch (verbsAndNouns.get(0)) {
                        case "quit":
                            if (GameMethods.confirmQuit(scanner)) {
                             // Player confirmed to quit, set isGameOver to true to end the game loop
                                isGameOver = true;
                            } else {
                            // Player did not confirm, continue the game loop
                                System.out.println("Resuming game...");}
                            break;
                        default:
                            System.out.println(verbsAndNouns.get(0));
                            break;
                    }
                } else {
                    switch (verbsAndNouns.get(0)) {
                        case "move":
                            //Execute the Move Function
                            Move move = new Move();
                            move.moveDirection(verbsAndNouns.get(1));
                            break;
                        case "look":
                            //Execute the Move Function
                            System.out.println("I'm executing the LOOK function to see: " + verbsAndNouns.get(1));
                            break;
                        default:
                            break;
                    }
                }
            }

//                if (input.equals("quit")) {
//                    // Player wants to quit, ask for confirmation
//                    if (GameMethods.confirmQuit(scanner)) {
//                        // Player confirmed to quit, set isGameOver to true to end the game loop
//                        isGameOver = true;
//                    } else {
//                        // Player did not confirm, continue the game loop
//                        System.out.println("Resuming game...");
//                    }
//                } else {
//                    // Handle other game actions based on the input
//                    // For example, process movement, interactions, etc.
//                    System.out.println("You entered: " + input);
//                }
//            }
//        } else {
//            // Player chose not to start a new game, exit the program
//            System.out.println("Thank you for considering The Lost Kingdom of Eldoria!");
//        }

            // Game loop ends here
            System.out.println("Thank you for playing The Lost Kingdom of Eldoria!");
        }
    }}