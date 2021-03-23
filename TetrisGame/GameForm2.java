package TetrisGame;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class GameForm2 extends GameForm {

    public GameArea ga2;
    public JPanel separator;
    public JLabel level2, score2;

    public GameForm2() {
        super();
        level2 = new JLabel("Level: 1");
        score2 = new JLabel("Score: 0");
        filter.setBounds(0, 680, 830, 150);
        this.setSize(825, 850);
        pause.setBounds(filter.getWidth() - 80, 5, 50, 50);
        level.setLocation(10, 10);
        score.setLocation(10, 50);
        level2.setBounds(415, 10, 110, 30);
        score2.setBounds(415, 50, 150, 30);
        score2.setFont(new Font("LucidaSans", Font.ITALIC, 30));
        level2.setFont(new Font("LucidaSans", Font.ITALIC, 30));
        filter.add(score2);
        filter.add(level2);
        separator = new JPanel();
        separator.setBounds(400, 0, 5, 850);
        separator.setBackground(Color.black);
        ga2 = new GameArea(10);
        ga2.setBounds(405, 0, 400, 680);
        this.add(ga2);
        this.add(separator);
        Control2();
    }

    @Override
    public void StartGame() {
        super.StartGame();
        gt2 = new GameThread(ga2, this);
        gt2.start();
    }

    @Override
    public void Interrupt() {
        gt.interrupt();
        gt2.interrupt();
    }

    @Override
    public void Restart() {
        Interrupt();
        StartGame();
        this.setVisible(true);
    }

    @Override
    public void Continue() {
        super.Continue();
        gt2.resume();
    }

    @Override
    public void Pause() {
        pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.Pause2();
                gt.suspend();
                gt2.suspend();
                invisible();
            }
        });
    }

    public void Control2() {
        InputMap im2 = ga2.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am2 = ga2.getActionMap();
        im2.put(KeyStroke.getKeyStroke("RIGHT"), "right2");
        im2.put(KeyStroke.getKeyStroke("LEFT"), "left2");
        im2.put(KeyStroke.getKeyStroke("DOWN"), "down2");
        im2.put(KeyStroke.getKeyStroke("UP"), "up2");
        im2.put(KeyStroke.getKeyStroke("ENTER"), "rotate2");
        am2.put("right2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.MoveBlockRight();
            }
        });
        am2.put("left2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.MoveBlockLeft();
            }
        });
        am2.put("down2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.MoveBlockDown();
            }
        });
        am2.put("up2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                ga2.MoveBlockUp();
            }
        });
        am2.put("rotate2", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ga2.RotateBlock();
            }
        });
    }

    @Override
    public void UpdateScore(int s) {
        super.UpdateScore(s);
        score2.setText("Score: " + s);
    }

    @Override
    public void UpdateLevel(int l) {
        super.UpdateLevel(l);
        level2.setText("Level: " + l);
    }
}
