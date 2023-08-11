import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MusicPlayer {
    private Clip clip;

    public MusicPlayer(String filePath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(filePath)));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }

    public void play() {
            clip.start();
        }

    public void stop() {
            clip.stop();
            clip.setFramePosition(0); // Rewind to the beginning
    }

    public void setVolume(float volume) {
        if (clip != null) {
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            float minVolume = gainControl.getMinimum();
            float maxVolume = gainControl.getMaximum();
            float volumeRange = maxVolume - minVolume;
            float gain = (volume * volumeRange) + minVolume;
            gainControl.setValue(gain);
        }
    }
}
