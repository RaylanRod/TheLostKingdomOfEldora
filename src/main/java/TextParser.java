import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public static List<String> extractVerbsAndNouns(String input) {

        List<String> verbsAndNouns = new ArrayList<>();

        // Define regular expressions for verbs and nouns
        String verbRegex = "\\b(?:go|attack|take|look|inspect|quit|move|help)\\b";
        String nounRegex = "\\b(?:north|south|east|west|vampire|crystalball|crown|spirit|stairs)\\b";

        Pattern verbPattern = Pattern.compile(verbRegex, Pattern.CASE_INSENSITIVE);
        Pattern nounPattern = Pattern.compile(nounRegex, Pattern.CASE_INSENSITIVE);


        Matcher verbMatcher = verbPattern.matcher(input.toLowerCase());
        Matcher nounMatcher = nounPattern.matcher(input.toLowerCase());

        //Create Return Statements

        // Commands Extractor to String to List
        String stringOfCommands = verbRegex.replace("\\b(?:", "").replace(")\\b", "");
        //String [] commands = stringOfCommands.split("\\|");

        // Look for invalid Noun
        String badLook = "The item you entered is not valid.  Please try again";

        // Move for invalid Noun
        String badMove = "The location you entered to move to is not valid.  Please try again";

        // Invalid Command
        String badCommand = "I don't understand, please try again.  Type 'Help' for a list of commands.";


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

        if (verbsAndNouns.size() == 1 && !verbsAndNouns.isEmpty()) {
            switch (verbsAndNouns.get(0)) {
                case "help":
                    verbsAndNouns.set(0, stringOfCommands);
                    break;
                case "look":
                    verbsAndNouns.set(0, badLook);
                    break;
                case "move":
                    verbsAndNouns.set(0, badMove);
                    break;
                default:
                    break;
            }
        } else if (verbsAndNouns.isEmpty()){
            verbsAndNouns.add(badCommand);
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

            System.out.println(response);

            //Check for game over condition or other exit conditions
            if (response.get(0).equalsIgnoreCase("quit")) {
                System.out.println("Exiting the game. Goodbye!");
                break;
            }
        }
    }
}