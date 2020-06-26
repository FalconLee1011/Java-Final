package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class MultiplePanel extends JPanel {
    
    public PkbButton startGameBtn;
    public PkbButton roomBackToMulBtn;

    public MultiplePanel() {
        setOpaque(false);
        startGameBtn = new PkbButton("   Start !   ");// 開始遊戲
        roomBackToMulBtn = new PkbButton("   Back  ");// 回到上一頁(選擇 進入/創建房間)

        startGameBtn.setEnabled(true);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}