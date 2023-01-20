import javax.sound.sampled.*;
import java.io.*;

/**
 * @author Shaoor Baig
 * @version 1.0
 */

public class SoundPlayer {
  public void playSound(String fileName) {
    try {
      // Opens the audio input stream
      File audioFile = new File(fileName);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);

      // Gets the audio format
      AudioFormat format = audioStream.getFormat();

      // Gets the audio data line
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      Clip audioClip = (Clip) AudioSystem.getLine(info);

      // Opens the audio clip
      audioClip.open(audioStream);

      // Starts playing the audio clip
      audioClip.start();
    } catch (UnsupportedAudioFileException ex) {
      ex.printStackTrace();
    } catch (IOException ex) {
      ex.printStackTrace();
    } catch (LineUnavailableException ex) {
      ex.printStackTrace();
    }
  }
}
