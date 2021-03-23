package TetrisGame;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.MatteBorder;

public class GameForm extends JFrame {

    public JPanel filter;
    public GameArea ga;
    public JLabel level, score, pause;
    public GameThread gt, gt2;

    public GameForm() {
        this.setSize(670, 727);
        filter = new JPanel();
        filter.setBounds(400, 0, 250, 727);
        filter.setBackground(new Color(165, 177, 165));
        filter.setBorder(new MatteBorder(1, 1, 1, 1, Color.black));
        pause = new JLabel("");
        ga = new GameArea(10);
        ga.setBounds(0, 0, 400, 680);
        pause.setBounds((filter.getWidth() - 50) / 2, 10, 50, 50);
        score = new JLabel("Score: 0");
        level = new JLabel("Level: 1");
        score.setFont(new Font("LucidaSans", Font.ITALIC, 30));
        level.setFont(new Font("LucidaSans", Font.ITALIC, 30));
        score.setBounds((filter.getWidth() - 150) / 2, 100, 130, 30);
        level.setBounds((filter.getWidth() - 150) / 2, 150, 130, 30);
        new SetIcon(pause, "/Image/pause.png");
        filter.add(pause);
        filter.add(score);
        filter.add(level);
        filter.setLayout(null);
        this.add(filter);
        this.add(ga);
        this.setLayout(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        pause.setFocusable(false);
        Control();
        Pause();
    }

    public void StartGame() {
        gt = new GameThread(ga, this);
        gt.start();
    }

    public void Interrupt() {
        gt.interrupt();
    }

    public void Restart() {
        Interrupt();
        StartGame();
        this.setVisible(true);
    }

    public void Continue() {
        gt.resume();
        this.setVisible(true);
    }

    public void invisible() {
        this.setVisible(false);
    }

    public void Pause() {
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.Pause();
                gt.suspend();
                invisible();
            }
        });
    }

    public void Control() {
        InputMap im = ga.getInputMap();
        ActionMap am = ga.getActionMap();
        im.put(KeyStroke.getKeyStroke("D"), "right");
        im.put(KeyStroke.getKeyStroke("A"), "left");
        im.put(KeyStroke.getKeyStroke("S"), "down");
        im.put(KeyStroke.getKeyStroke("W"), "up");
        im.put(KeyStroke.getKeyStroke("R"), "rotate");
        am.put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.MoveBlockRight();
            }
        });
        am.put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.MoveBlockLeft();
            }
        });
        am.put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.MoveBlockDown();
            }
        });
        am.put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.MoveBlockUp();
            }
        });
        am.put("rotate", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga.RotateBlock();
            }
        });
    }

    public void UpdateScore(int s) {
        score.setText("Score: " + s);
    }

    public void UpdateLevel(int l) {
        level.setText("Level: " + l);
    }
}
