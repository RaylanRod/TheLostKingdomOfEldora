import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.nio.file.Files;
import java.util.*;
import java.io.IOException;

public class Main extends Colors {
    public static Character player = new Character();
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
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
            // GameState gameState = new GameState();
            DisplayMethods.clearScreen();

            // Player is starting in room ID, pass it to the playerLocation method
            // GameMethods.playerLocation(1);

            //Room startingRoom = Rooms.getRoomById(1);
            //Character player = new Character("Player name", 100, startingRoom);
            MusicPlayer musicPlayer = new MusicPlayer("audioFiles/hauntedCastle.wav");
            musicPlayer.play();
            musicPlayer.setVolume((float) 7.0/10);
            DisplayMethods.printHeader();
            DisplayMethods.printRoomItems();
            System.out.println(purple+"Music is playing. To turn off the music just type 'stop'; to start just type 'play'; adjust volum type 'adjust' and a level from '1' to '10'....."+white);

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
                                System.out.println(blue + "Resuming game..." + white);
                            }
                            break;
                        case "talk":
                            //Execute the Talk Function
                            GameMethods.talk();
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        case "save":
                            System.out.println("Enter a file name: ");
                            String fileName = scanner.nextLine();
                            String filePath = fileName + ".json";
                            try {
                                GameMethods.saveJSONFile(filePath, player);
                                System.out.println("game saved");
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "map":
                            DisplayMethods.clearScreen();
                            DisplayMethods.printTextMap();
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        case "play":
                            musicPlayer.play();
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        case "stop":
                            musicPlayer.stop();
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        default:
                            System.out.println(verbsAndNouns.get(0));
                            break;

                    }
                } else {
                    switch (verbsAndNouns.get(0)) {
                        case "move":
                            //Execute the Move Function
                            GameMethods.moveRoom(verbsAndNouns.get(1));
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        case "look":
                            //Execute the Look Function
                            GameMethods.look(verbsAndNouns.get(1));
                            break;
                        case "get":
                            //Execute the GetItem Function
                            GameMethods.getItem(verbsAndNouns.get(1));
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        case "drop":
                            //Execute the Drop Function
                            GameMethods.dropItem(verbsAndNouns.get(1));
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        case "adjust":
                            //Execute the Adjust Function
                            double adjustTo = Double.parseDouble(verbsAndNouns.get(1));
                            adjustTo = adjustTo/10;
                            float level = (float)adjustTo;
                            musicPlayer.setVolume(level);
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            break;
                        default:
                            break;
                    }
                }
            }

            // Game loop ends here
            System.out.println(blue + "Thank you for playing The Lost Kingdom of Eldoria!" + white);
        }
    }}