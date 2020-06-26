package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class MultiplePlayerPanel extends JPanel {
    
    public PkbButton createRoomBtn;
    public PkbButton enterRoomBtn;
    public PkbButton multipleBackToModeBtn;

    public MultiplePlayerPanel() {
        setOpaque(false);
        createRoomBtn = new PkbButton(" Create Room ");// 創建房間
        enterRoomBtn = new PkbButton("  Enter Room  ");// 進入房間
        multipleBackToModeBtn = new PkbButton("       Back        ");// 回到上一頁(選擇遊戲模式)

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(createRoomBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(enterRoomBtn);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(multipleBackToModeBtn);
    }
}