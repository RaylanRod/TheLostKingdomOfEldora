import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public static List<String> extractVerbsAndNouns(String input) {

        List<String> verbsAndNouns = new ArrayList<>();
        List<String> defaultValue = new ArrayList<>(Arrays.asList("I don't understand, please try again.  Type 'Help' for a list of commands."));

        // Define regular expressions for verbs and nouns
        String verbRegex = "\\b(?:go|attack|take|look|inspect|quit|help)\\b";
        String nounRegex = "\\b(?:north|south|east|west|vampire|crystalball|crown|spirit|stairs)\\b";

        Pattern verbPattern = Pattern.compile(verbRegex, Pattern.CASE_INSENSITIVE);
        Pattern nounPattern = Pattern.compile(nounRegex, Pattern.CASE_INSENSITIVE);

        try{
            Matcher verbMatcher = verbPattern.matcher(input);
            Matcher nounMatcher = nounPattern.matcher(input);

            // Commands Extractor to String to List
            String stringOfCommands = verbRegex.replace("\\b(?:", "").replace(")\\b", "");
            String [] commands = stringOfCommands.split("\\|");
            List<String> commandList = Arrays.asList(commands);

            // Extract verb
            while (verbMatcher.find()) {
             String verb = verbMatcher.group();
             verbsAndNouns.add(verb);
            }

            // Extract noun
            while (nounMatcher.find()) {
                String noun = nounMatcher.group();
                verbsAndNouns.add(noun);
            }
            if (verbsAndNouns.get(0).equalsIgnoreCase("help")) {
                return commandList;
            } else {
                //return verbsAndNouns;
                        return verbsAndNouns;
            }
        } catch (Exception e) {
            return defaultValue;
        }
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

            System.out.println(response);

            // Check for game over condition or other exit conditions
            if (response.get(0).equalsIgnoreCase("quit")) {
                System.out.println("Exiting the game. Goodbye!");
                break;
            }
        }
    }
}