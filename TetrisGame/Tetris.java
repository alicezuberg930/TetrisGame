package TetrisGame;

public class Tetris {

    private static LeaderboardForm lf;
    private static StartupForm sf;
    private static GameForm gf;
    private static GameForm2 gf2;
    private static PauseForm pf;
    private static PauseForm2 pf2;

    public static void Start() {
        gf.setVisible(true);
        gf.StartGame();
    }

    public static void VersusMode() {
        gf2.setVisible(true);
        gf2.StartGame();
    }

    public static void ShowLeaderboard() {
        lf.setVisible(true);
        sf.setVisible(false);
    }

    public static void Pause() {
        pf.setVisible(true);
    }

    public static void Play() {
        gf.Continue();
    }

    public static void Restart() {
        gf.Restart();
    }

    public static void ShowMenu() {
        gf.Interrupt();
        sf.setVisible(true);
    }

    public static void Pause2() {
        pf2.setVisible(true);
    }

    public static void Play2() {
        gf2.Continue();
    }

    public static void Restart2() {
        gf2.Restart();
    }

    public static void ShowMenu2() {
        gf2.Interrupt();
        sf.setVisible(true);
    }

    public static void main(String agrs[]) {
        java.awt.EventQueue.invokeLater(() -> {
            sf = new StartupForm();
            gf = new GameForm();
            gf2 = new GameForm2();
            lf = new LeaderboardForm();
            pf = new PauseForm();
            pf2 = new PauseForm2();
        });
    }

}
