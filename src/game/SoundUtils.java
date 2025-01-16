package game;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundUtils {

    public static void playSound(String soundFilePath) {
        try {
            // Load the sound file as a resource
            URL soundURL = SoundUtils.class.getResource(soundFilePath);
            if (soundURL == null) {
                System.err.println("Sound file not found: " + soundFilePath);
                return;
            }

            // Set up the audio input stream
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundURL);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start(); // Play the sound
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
}
