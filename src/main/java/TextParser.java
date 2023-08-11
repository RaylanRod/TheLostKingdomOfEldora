import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextParser {

    public static List<String> extractVerbsAndNouns(String input) {

        List<String> verbsAndNouns = new ArrayList<>();

        // Define regular expressions for verbs and nouns
        String verbRegex = "\\b(?:look|quit|get|move|talk|drop|help|save|map|play|stop|attack)\\b";
        String nounRegex = "\\b(?:north|south|east|west|up|down|vampire|crystalball|royal crown piece left|spirit|stairs|rock|royal seal|royal crown piece right|royal crown piece middle|shadow cloak|elixir of restoration|royal crown piece back|ancient amulet of binding|bardleston)\\b";

        Pattern verbPattern = Pattern.compile(verbRegex, Pattern.CASE_INSENSITIVE);
        Pattern nounPattern = Pattern.compile(nounRegex, Pattern.CASE_INSENSITIVE);

        Matcher verbMatcher = verbPattern.matcher(input.toLowerCase());
        Matcher nounMatcher = nounPattern.matcher(input.toLowerCase());

        // Verbs that should not have any nouns associated with it
        List<String> verbsOnly = Arrays.asList("quit", "help", "talk", "map", "play", "stop");

        //Create Return Statements:

        // Commands Extractor to String to List
        String stringOfCommands = verbRegex.replace("\\b(?:", "").replace(")\\b", "");

        // Look for input of an invalid Noun
        String badLook = "You didn't enter an item to look at.  Please try again";

        // Get for input of an invalid Noun
        String badGet = "You didn't enter an item to get.  Please try again";

        // Drop for input of an invalid item
        String badDrop = "You didn't enter an item to drop.  Please try again";

        // Move for input of an invalid Noun
        String badMove = "You didn't enter a location to move to.  Please try again";

        // Attack for input of an invalid Noun
        String badAttack = "You didn't enter a NPC to attack.  Please try again";

        // Invalid Command
        String badCommand = "I don't understand, please try again.  Type 'Help' for a list of commands.";

        // Extract verb
        while (verbMatcher.find()) {
            String verb = verbMatcher.group();
            verbsAndNouns.add(verb);
        }
        // If the verb is found then extract noun
        if (verbsAndNouns.size() == 1) {
            if (!verbsOnly.contains(verbsAndNouns.get(0))){
                // Extract noun
                while (nounMatcher.find()) {
                    String noun = nounMatcher.group();
                    verbsAndNouns.add(noun);
                }
            }
        }
        // No valid commander
        if (verbsAndNouns.size() == 0){
            verbsAndNouns.add(badCommand);
          //Valid command and no nouns
        } else if (verbsAndNouns.size()==1) {
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
                case "get":
                    verbsAndNouns.set(0, badGet);
                    break;
                case "drop":
                    verbsAndNouns.set(0, badDrop);
                    break;
                case "attack":
                    verbsAndNouns.set(0, badAttack);
                    break;
                default:
                    break;
            }
        }
        return verbsAndNouns;
    }

//    public static void main(String[] args) {
//        System.out.println("Welcome to Lost Kingdom of Eldoria!");
//        System.out.println("Type 'help' for a list of commands.");
//        Scanner scanner = new Scanner(System.in);
//        String command = "";
//        List<String> response = new ArrayList<>();
//
//        while (true) {
//            System.out.print("> ");
//            command = scanner.nextLine();
//            response = extractVerbsAndNouns(command);
//
//            System.out.println(response);
//
//            //Check for game over condition or other exit conditions
//            if (response.get(0).equalsIgnoreCase("quit")) {
//                System.out.println("Exiting the game. Goodbye!");
//                break;
//            }
//        }
//    }
}