package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class ModePanel extends JPanel {
    public PkbButton specialModeBtn;
    public PkbButton normalModeBtn;
    public PkbButton multiplePlayerBtn;
    public PkbButton backToStartBtn;

    public ModePanel() {
        setOpaque(false);
        specialModeBtn = new PkbButton("  special Mode   ");
        normalModeBtn = new PkbButton("  normal Mode   ");
        multiplePlayerBtn = new PkbButton(" Multiple Player ");
        backToStartBtn = new PkbButton("         Back         ");
        
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