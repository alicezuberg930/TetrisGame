package TetrisGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import javax.swing.*;
import javax.swing.border.*;

public class StartupForm extends JFrame {

    private JLabel background, logo;
    private LeaderboardForm lf;
    private JButton start, versus, quit, ShowLeaderBoard;

    public StartupForm() {
        background = new JLabel();
        logo = new JLabel();
        start = new JButton("Start Game");
        versus = new JButton("2 Players");
        ShowLeaderBoard = new JButton("Leaderboard");
        quit = new JButton("Quit");
        this.setSize(650, 800);
        background.setSize(650, 800);
        logo.setBounds((this.getWidth() - 500) / 2, 50, 500, 250);
        start.setBounds((this.getWidth() - 220) / 2, 400, 220, 35);
        versus.setBounds((this.getWidth() - 220) / 2, 470, 220, 35);
        ShowLeaderBoard.setBounds((this.getWidth() - 220) / 2, 540, 220, 35);
        quit.setBounds((this.getWidth() - 220) / 2, 610, 220, 35);
        background.add(start);
        background.add(versus);
        background.add(ShowLeaderBoard);
        background.add(quit);
        background.add(logo);
        background.setLayout(null);
        this.add(background);
        ButtonUI(start);
        ButtonUI(versus);
        ButtonUI(ShowLeaderBoard);
        ButtonUI(quit);
        new SetIcon(logo, "/Image/tetris_logo.png");
        new SetIcon(background, "/Image/tetris_background.jpg");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
        this.setTitle("Game Xếp Hình v1.0");
        StartGame();
        VersusMode();
        ShowLeaderBoard();
        Quit();
    }

    public void StartGame() {
        start.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            Tetris.Start();
        });
    }

    public void ShowLeaderBoard() {
        ShowLeaderBoard.addActionListener((ActionEvent e) -> {
            lf = new LeaderboardForm();
            lf.setVisible(true);
            this.setVisible(false);
        });
    }

    public void VersusMode() {
        versus.addActionListener((ActionEvent e) -> {
            this.setVisible(false);
            Tetris.VersusMode();
        });
    }

    public void Quit() {
        quit.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
    }

    public void ButtonUI(JButton button) {
        button.setForeground(Color.white);
        button.setBackground(new Color(81, 124, 210));
        button.setBorder(new BevelBorder(BevelBorder.RAISED));
        button.setFont(new Font("Arial", Font.BOLD, 17));
    }

}
