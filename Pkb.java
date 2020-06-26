package peekaboo;

import javax.swing.JFrame;
import peekaboo.menu.*;

public class Pkb {
    public static void main(String[] args) {
        
        PkbMenu startFrame = new PkbMenu("Peekaboo");
        startFrame.setLocationRelativeTo(null);// 置中
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);// 全螢幕
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);
    }
}