/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TetrisGame;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author HP
 */
public class SetIcon {

    public SetIcon(JLabel jl, String image_path) {
        ImageIcon ic = new ImageIcon(getClass().getResource(image_path));
        Image img = ic.getImage().getScaledInstance(jl.getWidth(), jl.getHeight(), Image.SCALE_SMOOTH);
        jl.setIcon(new ImageIcon(img));
    }
}
