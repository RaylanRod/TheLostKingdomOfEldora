import javax.sound.midi.Soundbank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
class NewGame {
    private boolean isPlaying;
    private final Scanner scanner = new Scanner(System.in); // read input from console
    GameMethods textFiles = new GameMethods();
    private final HashMap<String, Location> locations;
    private Location playerLocation;


    public NewGame() {
        isPlaying = false;
        textFiles = new GameMethods(); // Initialize GameMethods to read item data from JSON
        locations = initializeLocations(); // Create and set up all the locations in the game
        playerLocation = locations.get("Starting Location"); // Set the player's starting location
    }

    private void startNewGame() {
        isPlaying = true;
        clearScreen();
        // function to call the welcome screen
        textFiles.printtextFile("src/main/textFiles/Welcome_Screen.txt");
        displayLocation();
    }
    private HashMap<String, Location> initializeLocations() {
        // Create and set up all the locations in the game
        HashMap<String, Location> locations = new HashMap<>();

        Location startingLocation = new Location("Starting Location", "You find yourself at the entrance of a dark cave.");
        startingLocation.addItem("Torch");
        startingLocation.addItem("Map");
        locations.put("Starting Location", startingLocation);

        Location nextLocation = new Location("Next Location", "You are in the forest.");
        nextLocation.addItem("Key");
        nextLocation.addItem("Potion");
        locations.put("Next Location", nextLocation);

        // Add more locations and items as needed

        return locations;
    }

    private void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private void displayLocation() {
        System.out.println("Current Location: " + playerLocation.getName());
        System.out.println(playerLocation.getDescription());

        ArrayList<String> items = playerLocation.getItems();
        if (items.isEmpty()) {
            System.out.println("There are no items here.");
        } else {
            System.out.println("Items at this location:");
            for (String item : items) {
                System.out.println("- " + item);
            }
        }
    }

    public void play() {
        while (true) {
            if (!isPlaying) {
                System.out.println("Would you like to play Y/n");
                String choice = scanner.nextLine().trim();

                if(choice.contains("y")) {
                    clearScreen();
                    startNewGame();
                } else if (choice.contains("n")) {
                    //call quit
                    break;
                } else {
                    System.out.println("Invalid choice. Please try again.");
                }
            }
        }
    }
}
