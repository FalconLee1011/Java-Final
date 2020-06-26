package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class InstrucPanel extends JPanel {
    
    public JLabel instrucLabel;
    public PkbButton instrucToStartBtn;

    public InstrucPanel() {
        instrucLabel = new JLabel();
        instrucLabel.setIcon(new ImageIcon("img/gameInstruction.png"));

        setOpaque(false);
        instrucToStartBtn = new PkbButton("   Back   ");// 回到上一頁(首頁)

        setLayout(new BorderLayout());// 可傳參數(間隙)
        add(this.instrucLabel, BorderLayout.CENTER);
        add(this.instrucToStartBtn, BorderLayout.SOUTH);
    }
}