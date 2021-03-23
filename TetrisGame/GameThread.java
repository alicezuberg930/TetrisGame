package TetrisGame;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.UnsupportedAudioFileException;

public class GameThread extends Thread {

    private GameArea ga;
    private GameForm gf;
    public AudioPlayer audio;
    private int CurrentScore = 0;
    public int score = 0;
    private int level = 1;
    public int speed = 1000;
    public GenerateBlock block;

    public GameThread(GameArea ga, GameForm gf) {
        this.gf = gf;
        this.ga = ga;
        gf.UpdateLevel(level);
        gf.UpdateScore(score);
        ga.InitializeBackground();
    }

    @Override
    public void run() {
        try {
            audio = new AudioPlayer();
        } catch (UnsupportedAudioFileException | IOException ex) {
            Logger.getLogger(GameThread.class.getName()).log(Level.SEVERE, null, ex);
        }

        int currentLevel;
        while (true) {
            block = ga.SpawnBlock();
            while (ga.MoveBlockDown()) {
                try {
                    Thread.sleep(speed);
                } catch (InterruptedException ex) {
                    return;
                }
            }
            if (ga.CheckBounds()) {
                audio.PlayGameOver();
                LeaderboardForm.GameOver(score, level);
                break;
            }
            ga.MoveBlockToBackground();
            CurrentScore = ga.ClearFullLine();
            if (CurrentScore > 0) {
                audio.PlayClearLine();
                score += CurrentScore;
                gf.UpdateScore(score);
                currentLevel = score / 3 + 1;
                if (currentLevel > level) {
                    level = currentLevel;
                    speed -= 100;
                    gf.UpdateLevel(level);
                }
            }
        }
    }
}
