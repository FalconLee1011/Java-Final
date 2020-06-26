package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class StartPanel extends JPanel {
    public PkbButton startBtn;
    public PkbButton musicBtn;
    public PkbButton descriptBtn;

    public StartPanel() {
        setOpaque(false);
        startBtn = new PkbButton("     START    ");
        musicBtn = new PkbButton("     MUSIC    ");
        descriptBtn = new PkbButton(" Instrutions ");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(startBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(musicBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(descriptBtn);
    }
}