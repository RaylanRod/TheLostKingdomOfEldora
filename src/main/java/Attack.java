import java.util.Scanner;

class Attack {
    public static void attack(){
        try {
            DisplayMethods.clearScreen();
            String npcName = (String) Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("name");
            System.out.println("the target is: " + npcName);
            System.out.println("the player is attacking the target.");
        } catch (Exception e) {
            System.out.println("There is not a valid target to attack..." );
            System.out.print("Press any key to continue...");
            Scanner scanner = new Scanner(System.in);
            scanner.nextLine();
        }
    }

    public static void printRoomNPC(){
        Object roomNPC = Rooms.getRoomById(Main.player.getCurrentRoom()).getNPC().get("name");
        try {
            System.out.println("Characters in Room: | "+ roomNPC + "|");
        } catch (Exception e) {
            System.out.println("There are no Characters in this room.");
        }
    }
}