package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class MultiplePanel extends JPanel {
    public PkbButton startGameBtn;
    public PkbButton roomBackToMulBtn;

    public MultiplePanel() {
        setOpaque(false);
        startGameBtn = new PkbButton("   Start !   ");
        roomBackToMulBtn = new PkbButton("   Back  ");

        // if(masterGuest== MASTER){//= 1
        startGameBtn.setEnabled(true);
        // }
        // else{//== GUEST= 0
        // startGameBtn.setEnabled(false);
        // }
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}