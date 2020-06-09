package peekaboo.huaxin.mario;

import javax.swing.JFrame;

public class PkbMenu__Test {
    public static void main(String[] args) {
        PkbMenuFrame startFrame= new PkbMenuFrame("Peekaboo");
        startFrame.setLocationRelativeTo(null);//置中
        startFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);//全螢幕
        startFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        startFrame.setVisible(true);
    }
}