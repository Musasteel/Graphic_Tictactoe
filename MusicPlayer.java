import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class MusicPlayer {

    private Clip clip;
    public String fileName;

    public MusicPlayer() {
        fileName = "audio/defaulttrack.wav";
    }

    public void playAudio() {
        if (clip != null) {
            clip.stop();
        }
        try {
            File file = new File(fileName);
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseAudio() {
        if (clip != null) {
            clip.stop();
        }
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}