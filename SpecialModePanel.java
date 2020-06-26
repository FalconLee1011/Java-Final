package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class SpecialModePanel extends JPanel {
    public PkbButton timeTrialBtn;
    public PkbButton mazeBtn;
    public PkbButton specialBackToModeBtn;

    public SpecialModePanel() {
        setOpaque(false);

        timeTrialBtn = new PkbButton("    Time Trial   ");// 計時賽
        mazeBtn = new PkbButton("        Maze       ");// 迷宮
        specialBackToModeBtn = new PkbButton("        Back       ");// 回到上一頁(選擇遊戲模式)

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(timeTrialBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(mazeBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(specialBackToModeBtn);
    }
}