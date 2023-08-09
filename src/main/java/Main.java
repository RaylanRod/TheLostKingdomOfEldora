import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import java.util.*;
import java.io.IOException;

public class Main {
    public static Character player = new Character();
    public static void main(String[] args) {
        // Load JSON files
        try {
          Rooms.loadRoomsFromJSON();
        } catch (IOException e) {
            System.out.println("Error loading game data: " + e.getMessage());
            return;
        }

        //Display Functions to act as a preface to the game starting.
        DisplayMethods.printTextFile("textFiles/Welcome_Screen.txt");
        DisplayMethods.printTextJsonFile("json/Description.json");

        // Game loop starts here
        boolean isGameOver = false;
        Scanner scanner = new Scanner(System.in);

        // Ask the player if they want to start a new game
        if (GameMethods.startNewGame(scanner)) {
            DisplayMethods.clearScreen();

            // Player is starting in room ID, pass it to the playerLocation method
            // GameMethods.playerLocation(1);

            //Room startingRoom = Rooms.getRoomById(1);
            //Character player = new Character("Player name", 100, startingRoom);
            System.out.println("Character:\n" + player);
            System.out.println("Description:\n" + Rooms.getRoomById(player.getCurrentRoom()).getDescription());
            System.out.println("Things in the Room: |" + Rooms.getRoomById(player.getCurrentRoom()).getItems().get("name") + "|\n");

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
                        case "talk":
                            //Execute the Talk Function
                            GameMethods.talk();
                            DisplayMethods.clearScreen();
                            System.out.println("Character:\n" + player);
                            System.out.println("Description:\n" + Rooms.getRoomById(player.getCurrentRoom()).getDescription());

                            break;
                        default:
                            System.out.println(verbsAndNouns.get(0));
                            break;
                    }
                } else {
                    switch (verbsAndNouns.get(0)) {
                        case "move":
                            //Execute the Move Function
                            // player.moveDirection(verbsAndNouns.get(1));
                            GameMethods.moveRoom(verbsAndNouns.get(1));
                            DisplayMethods.clearScreen();
                            System.out.println("Character:\n" + player);
                            System.out.println("Description:\n" + Rooms.getRoomById(player.getCurrentRoom()).getDescription());
                            System.out.println("Things in the Room: |" + Rooms.getRoomById(player.getCurrentRoom()).getItems().get("name") + "|\n");
                            //System.out.println("NPCs:\n" + Rooms.getRoomById(player.getCurrentRoom()).getNPC());
                            break;
                        case "look":
                            //Execute the Look Function
                            System.out.println("I'm executing the LOOK function to see: " + verbsAndNouns.get(1));
                            break;
                        case "get":
                            //Execute the GetItem Function
                            GameMethods.getItem(verbsAndNouns.get(1));
                            DisplayMethods.clearScreen();
                            System.out.println("Character:\n" + player);
                            System.out.println("Description:\n" + Rooms.getRoomById(player.getCurrentRoom()).getDescription());
                            System.out.println("Things in the Room: |" + Rooms.getRoomById(player.getCurrentRoom()).getItems().get("name") + "|\n");
                            break;
                        case "drop":
                            //Exectue the DROP Function
                            System.out.println("I'm executing the DROP function");
                            break;
                        default:
                            break;
                    }
                }
            }

            // Game loop ends here
            System.out.println("Thank you for playing The Lost Kingdom of Eldoria!");
        }
    }}