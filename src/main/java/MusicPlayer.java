import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer {
    private Clip clip;
    private boolean isPlaying;

    public MusicPlayer(String filePath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(filePath));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
        isPlaying = false;
    }

    public void play() {
        if (!isPlaying) {
            clip.start();
            isPlaying = true;
            System.out.println("Music is playing...");
        }
    }

    public void stop() {
        if (isPlaying) {
            clip.stop();
            clip.setFramePosition(0); // Rewind to the beginning
            isPlaying = false;
            System.out.println("Music stopped.");
        }
    }

    public static void main(String[] args) {
        try {
            MusicPlayer musicPlayer = new MusicPlayer("src/main/resources/audioFiles/hauntedCastle.wav");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.println("Enter 'play' to play music or 'stop' to stop: ");
                String input = scanner.nextLine();

                if (input.equalsIgnoreCase("play")) {
                    musicPlayer.play();
                } else if (input.equalsIgnoreCase("stop")) {
                    musicPlayer.stop();
                } else {
                    System.out.println("Invalid command.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
