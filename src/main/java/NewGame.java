import javax.sound.midi.Soundbank;
import java.util.Scanner;
class NewGame {
    private boolean isPlaying;
    private final Scanner scanner = new Scanner(System.in); // read input from console

    public NewGame() {
        isPlaying = false;
    }

    private void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    private void startNewGame() {
        isPlaying = true;
        clearScreen();
        // function to call the welcome screen
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
