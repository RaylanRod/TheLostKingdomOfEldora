import javax.sound.sampled.*;
import java.io.BufferedInputStream;
import java.io.IOException;

public class MusicPlayer {
    private Clip music;
    private Clip fx;

    public MusicPlayer(String type, String filePath) throws LineUnavailableException, IOException, UnsupportedAudioFileException {
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new BufferedInputStream(getClass().getClassLoader().getResourceAsStream(filePath)));

        switch (type) {
            case "music":
                music = AudioSystem.getClip();
                music.open(audioInputStream);
                break;
            case "fx":
                fx = AudioSystem.getClip();
                fx.open(audioInputStream);
                break;
        }
    }

    public void play(String item) {
        switch (item) {
            case "music":
                music.start();
                break;
            case "fx":
                fx.start();
                break;
            }
        }

    public void stop() {
            music.stop();
            music.setFramePosition(0); // Rewind to the beginning
    }

    public void setVolume(String item, float volume) {

        switch (item) {
            case "music":
                if (music != null) {
                    FloatControl gainControl = (FloatControl) music.getControl(FloatControl.Type.MASTER_GAIN);
                    float minVolume = gainControl.getMinimum();
                    float maxVolume = gainControl.getMaximum();
                    float volumeRange = maxVolume - minVolume;
                    float gain = (volume * volumeRange) + minVolume;
                    gainControl.setValue(gain);
                }
                break;
            case "fx":
                if (fx != null) {
                    FloatControl gainControl = (FloatControl) fx.getControl(FloatControl.Type.MASTER_GAIN);
                    float minVolume = gainControl.getMinimum();
                    float maxVolume = gainControl.getMaximum();
                    float volumeRange = maxVolume - minVolume;
                    float gain = (volume * volumeRange) + minVolume;
                    gainControl.setValue(gain);
                }
                break;
        }
    }
}
