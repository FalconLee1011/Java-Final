package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class NormalModePanel extends JPanel {
    public PkbButton levelOneBtn;
    public PkbButton levelTwoBtn;
    public PkbButton levelThreeBtn;
    public PkbButton levelFourBtn;
    public PkbButton normalBackToModeBtn;

    public NormalModePanel() {
        setOpaque(false);
        levelOneBtn = new PkbButton("    Level 1    ");
        levelTwoBtn = new PkbButton("    Level 2    ");
        levelThreeBtn = new PkbButton("    Level 3    ");
        levelFourBtn = new PkbButton("    Level 4    ");
        normalBackToModeBtn = new PkbButton("      Back      ");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(levelOneBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(levelTwoBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(levelThreeBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(levelFourBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));

        add(normalBackToModeBtn);
    }
}