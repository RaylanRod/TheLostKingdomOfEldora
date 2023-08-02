import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class GameMethods {

    public static void printtextFile(String fileName) {
        // Prints the opening splash screen
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("An I/O Error Occurred");
        }
    }

}