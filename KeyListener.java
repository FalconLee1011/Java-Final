package peekaboo;

import java.awt.event.KeyAdapter;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import java.awt.*;
//import sun.tools.asm.CatchData;

//鍵盤按下監聽類

public class KeyListener extends KeyAdapter {
    Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    public GameFrame gf;

    public KeyListener(GameFrame gf) {
        this.gf = gf;
    }

    // 鍵盤監聽
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        // System.out.printf("CODE -> %d%n", code);
        switch (code) {
            case 37:// 向左走
                gf.human.left = true;
                break;
            case 38:// 向上跳
                gf.human.up = true;
                break;
            case 39: // 向右走
                gf.human.right = true;
                break;
            case 40:
                gf.human.down = true;
                break;
            case 65:
                gf.human.pick = true;
                break;
            case 87:
                gf.human.use = true;
                break;
            case 81:
                gf.kaboom = true;
                break;
        }
    }

    // 鍵盤釋放監聽
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        switch (code) {
            case 37:// 向左走
                gf.human.left = false;
                break;
            case 38:// 向上跳
                gf.human.up = false;
                break;
            case 39: // 向右走
                gf.human.right = false;
                break;
            case 40:
                gf.human.down = false;
                break;
            case 65:
                gf.human.pick = false;
                break;
            case 87:
                gf.human.use = false;

                break;
        }
    }
}
