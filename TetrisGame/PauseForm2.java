/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisGame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 *
 * @author HP
 */
public class PauseForm2 extends PauseForm {

    public PauseForm2() {
        super();
    }

    @Override
    public void Play() {
        Play.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.Play2();
                invisible();
            }
        });
    }

    @Override
    public void Menu() {
        Menu.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.ShowMenu2();
                invisible();
            }
        });
    }

    @Override
    public void Restart() {
        Restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Tetris.Restart2();
                invisible();
            }
        });
    }
}
