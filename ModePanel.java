package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class ModePanel extends JPanel {

    public PkbButton normalModeBtn;
    public PkbButton specialModeBtn;
    public PkbButton multiplePlayerBtn;
    public PkbButton backToStartBtn;

    public ModePanel() {
        setOpaque(false);
        normalModeBtn = new PkbButton("  normal Mode   ");// 一般模式(4種遊戲難度)
        specialModeBtn = new PkbButton("  special Mode   ");// 特別模式(迷宮場、計時賽)
        multiplePlayerBtn = new PkbButton(" Multiple Player ");// 連線其他玩家
        backToStartBtn = new PkbButton("         Back         ");// 回到上一頁(首頁)

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(normalModeBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(specialModeBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(multiplePlayerBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(backToStartBtn);
    }
}