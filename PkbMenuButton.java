package peekaboo.menu;

import java.awt.*;
import javax.swing.*;
public class PkbMenuButton extends JButton{
    public PkbMenuButton(String btnText){
        super(btnText);
        Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println("screen width: "+d.width+ ", height: "+ d.height);
        int myWidth= (int)(d.width/ 30);
        //System.out.println("myWidth: "+myWidth);
        setMargin(new Insets(10, 10, 10, 10));//上左下右
        setFont(new Font("SansSerif", Font.BOLD, myWidth));
        //setBackground(Color.WHITE);//背景色
        setOpaque(false);
        setForeground(Color.WHITE);//前景色
        setFocusPainted(false);//字的框線消失
        
        setContentAreaFilled(false); //Button的背景消失，剩字和框線
        setBorder(BorderFactory.createLineBorder(Color.WHITE, myWidth/ 4, false));
        
        //setBorder(new RoundedBorder(myWidth));
        //setBorderPainted(false);//Button的框線消失
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//游標變小手手
    }
}