import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Main extends Colors {
    public static Character player = new Character();
    public static boolean aBooleanFX = true;
    public static void main(String[] args) throws UnsupportedAudioFileException, LineUnavailableException, IOException {
        // Load JSON files
        try {
          Rooms.loadRoomsFromJSON();
        } catch (IOException e) {
            System.out.println(red + "Error loading game data: " + e.getMessage() + white);
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

            //Music and FX setup section
            boolean playFX = true;
            float fxVolumelevel = (float) 9.0/10;
            MusicPlayer musicPlayer = new MusicPlayer("music", "audioFiles/hauntedCastle.wav");
            musicPlayer.play("music");
            musicPlayer.setVolume( "music", (float) 7.0/10);


            //Display Info
            DisplayMethods.printHeader();
            DisplayMethods.printRoomItems();
            DisplayMethods.printRoomNPC();
            System.out.println(purple+"Music/FX is currently playing. To turn off the music or fx just type 'stop music(or fx)'; to start just type 'play music(or fx)'; adjust volume type 'volume music(or fx)'...."+white);


            while (!isGameOver) {
                // Display the prompt to the player and read their input
                System.out.print("What would you like to do? Type 'Help' for commands or 'Quit' to exit > ");
                String input = scanner.nextLine().trim().toLowerCase();
                List<String> verbsAndNouns = TextParser.extractVerbsAndNouns(input);

                // Process the player's input and handle game actions
                // This section is for one word actions only or if a noun is required, prints error message
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
                            DisplayMethods.printRoomNPC();
                            break;
                        case "save":
                            System.out.print(blue + "Enter a file name: > " + white);
                            String fileName = scanner.nextLine();
                            String filePath = fileName + ".json";
                            try {
                                GameMethods.saveJSONFile(filePath, player);
                                GameMethods.saveJSONFile(fileName+"-rooms.json", Rooms.getAllRooms());
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
                            DisplayMethods.printRoomNPC();
                            break;
                        default:
                            System.out.println(verbsAndNouns.get(0));
                            break;

                    }
                } else {
                    switch (verbsAndNouns.get(0)) {
                        case "move":
                            //Execute the Move Function
                            GameMethods.moveRoom(verbsAndNouns.get(1), playFX, fxVolumelevel);
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            break;
                        case "look":
                            //Execute the Look Function
                            GameMethods.look(verbsAndNouns.get(1));
                            break;
                        case "get":
                            //Execute the GetItem Function
                            GameMethods.getItem(verbsAndNouns.get(1), playFX, fxVolumelevel);
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            break;
                        case "drop":
                            //Execute the Drop Function
                            DisplayMethods.clearScreen();
                            GameMethods.dropItem(verbsAndNouns.get(1), playFX, fxVolumelevel);
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            break;
                        case "volume":
                            //Execute the Volume Function
                            System.out.print("Enter the volume level from 1.0 to 10.0 > ");
                            String stringLevel = scanner.nextLine().trim().toLowerCase();
                            try {
                                if (Double.parseDouble(stringLevel) < 1.0 || Double.parseDouble(stringLevel) > 10.0) {
                                    System.out.println(red + "Invalid input. Please enter a number between 1 and 10." + white);
                                    System.out.print("Press any key to continue...");
                                    scanner.nextLine();
                                } else {
                                    double adjustTo = Double.parseDouble(stringLevel);
                                    adjustTo = adjustTo / 10;
                                    fxVolumelevel = (float) adjustTo;
                                    musicPlayer.setVolume(verbsAndNouns.get(1), fxVolumelevel);
                                }
                            } catch (Exception e){
                                System.out.println(red + "Invalid input. Please enter a number between 1 and 10." + white);
                                System.out.print("Press any key to continue...");
                                scanner.nextLine();
                            }
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            break;
                        case "attack":
                            DisplayMethods.clearScreen();
                            GameMethods.attack(playFX, fxVolumelevel);
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            DisplayMethods.printRoomNPC();
                            break;
                        case "play":
                            if (verbsAndNouns.get(1).equals("music")) {
                                musicPlayer.play(verbsAndNouns.get(1));
                            } else {
                                playFX = true;
                            }
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            break;
                        case "stop":
                            if (verbsAndNouns.get(1).equals("music")) {
                                musicPlayer.stop();
                            } else {
                                playFX = false;
                            }
                            DisplayMethods.clearScreen();
                            DisplayMethods.printHeader();
                            DisplayMethods.printRoomItems();
                            DisplayMethods.printRoomNPC();
                            break;
                        default:
                            break;
                    }
                }
                GameMethods.winGame();
            }

            // Game loop ends here
            System.out.println(blue + "Thank you for playing The Lost Kingdom of Eldoria!" + white);
        }
    }}