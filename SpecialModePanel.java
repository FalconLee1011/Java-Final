package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class SpecialModePanel extends JPanel {
    public PkbButton timeTrialBtn;
    public PkbButton mazeBtn;
    public PkbButton specialBackToModeBtn;

    public SpecialModePanel() {
        setOpaque(false);

        // specialModePanel.add(image);
        timeTrialBtn = new PkbButton("    Time Trial   ");// 可存活的時間
        mazeBtn = new PkbButton("        Maze       ");
        specialBackToModeBtn = new PkbButton("        Back       ");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(timeTrialBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(mazeBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(specialBackToModeBtn);
    }
}