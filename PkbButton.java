package peekaboo.menu;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.*;

public class PkbButton extends JButton {
    public PkbButton(String btnText) {
        super(btnText);
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int myWidth = (int) (d.width / 30);
        setMargin(new Insets(10, 10, 10, 10));// 上左下右
        setFont(new Font("SansSerif", Font.BOLD, myWidth));
        setBackground(new Color(255, 115, 115));
        setOpaque(false);
        setForeground(Color.WHITE);// 前景色
        setFocusPainted(false);// 字的框線消失
        setContentAreaFilled(false); // Button的背景消失，剩字和框線
        setBorder(BorderFactory.createLineBorder(Color.WHITE, myWidth / 4, false));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));// 游標變小手手
        MouseEnter mouser = new MouseEnter();
        this.addMouseListener(mouser);
    }

    private class MouseEnter extends MouseAdapter {
        @Override
        public void mouseEntered(MouseEvent e) {
            setContentAreaFilled(true); // Button的背景
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setContentAreaFilled(false); // Button的背景消失，剩字和框線
        }
    }
}