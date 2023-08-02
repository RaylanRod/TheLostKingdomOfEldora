import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public static List<String> extractVerbsAndNouns(String input) {
        List<String> verbsAndNouns = new ArrayList<>();
        // Define regular expressions for verbs and nouns
        String verbRegex = "\\b(?:attack|fight|take|go|head|walk|run|jump|duck|hit|smash|turn|touch|quit|help)\\b";
        String nounRegex = "\\b(?:north|south|east|west|vampire|crystalball|crown|sprit|stairs)\\b";

        Pattern verbPattern = Pattern.compile(verbRegex, Pattern.CASE_INSENSITIVE);
        Pattern nounPattern = Pattern.compile(nounRegex, Pattern.CASE_INSENSITIVE);

        Matcher verbMatcher = verbPattern.matcher(input);
        Matcher nounMatcher = nounPattern.matcher(input);

        // Extract verbs
        while (verbMatcher.find()) {
            String verb = verbMatcher.group();
            verbsAndNouns.add(verb);
        }

        // Extract nouns
        while (nounMatcher.find()) {
            String noun = nounMatcher.group();
            verbsAndNouns.add(noun);
        }

        return verbsAndNouns;
    }



    public static void main(String[] args) {
        System.out.println("Welcome to Lost Kingdom of Eldoria!");
        System.out.println("Type 'help' for a list of commands.");
        Scanner scanner = new Scanner(System.in);
        String command = "";
        List<String> response = new ArrayList<>();


        while (true) {
            System.out.print("> ");
            command = scanner.nextLine();
            response = extractVerbsAndNouns(command);

            System.out.println("Verbs and Nouns:");
            for (String word : response) {
                System.out.println(word);
            }

            // Check for game over condition or other exit conditions
            if (command.equalsIgnoreCase("quit")) {
                System.out.println("Exiting the game. Goodbye!");
                break;
            }
        }
    }
}