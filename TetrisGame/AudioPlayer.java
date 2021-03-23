package TetrisGame;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioPlayer {

    private String SoundPath = "src/Audio/" + File.separator;
    private String ClearLinePath = SoundPath + "clear.wav";
    private String GameOverPath = SoundPath + "game_over.wav";
    private Clip ClearLineSound, GameOverSound;

    public AudioPlayer() throws UnsupportedAudioFileException, IOException {
        try {
            ClearLineSound = AudioSystem.getClip();
            GameOverSound = AudioSystem.getClip();
            ClearLineSound.open(AudioSystem.getAudioInputStream(new File(ClearLinePath).getAbsoluteFile()));
            GameOverSound.open(AudioSystem.getAudioInputStream(new File(GameOverPath).getAbsoluteFile()));
        } catch (LineUnavailableException ex) {
            Logger.getLogger(AudioPlayer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void PlayClearLine() {
        ClearLineSound.setFramePosition(0);
        ClearLineSound.start();
    }

    public void PlayGameOver() {
        GameOverSound.setFramePosition(0);
        GameOverSound.start();
    }
}
