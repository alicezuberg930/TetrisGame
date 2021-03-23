/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class PauseForm extends JFrame {
    
    public JLabel Restart, Menu, Play, background, logo;
    
    public PauseForm() {
        this.setSize(650, 800);
        Play = new JLabel();
        Restart = new JLabel();
        Menu = new JLabel();
        background = new JLabel();
        logo = new JLabel();
        background.setBounds(0, 0, this.getWidth(), this.getHeight());
        logo.setBounds((this.getWidth() - 500) / 2, 50, 500, 250);
        Play.setBounds((this.getWidth() - 60) / 2, 350, 60, 60);
        Restart.setBounds((this.getWidth() - 60) / 2, 450, 60, 60);
        Menu.setBounds((this.getWidth() - 60) / 2, 550, 60, 60);
        background.add(Play);
        background.add(Restart);
        background.add(Menu);
        background.add(logo);
        new SetIcon(Restart, "/Image/restart.png");
        new SetIcon(Menu, "/Image/back.png");
        new SetIcon(Play, "/Image/play.png");
        new SetIcon(background, "/Image/tetris_background.jpg");
        new SetIcon(logo, "/Image/tetris_logo.png");
        background.setLayout(null);
        this.add(background);
        this.setLocationRelativeTo(null);
        Play();
        Restart();
        Menu();
    }
    
    public void invisible() {
        this.setVisible(false);
    }
    
    public void Play() {
        Play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.Play();
                invisible();
            }
        });
    }
    
    public void Menu() {
        Menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.ShowMenu();
                invisible();
            }
        });
    }
    
    public void Restart() {
        Restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.Restart();
                invisible();
            }
        });
    }
}
