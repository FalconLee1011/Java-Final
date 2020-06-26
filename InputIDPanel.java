package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class InputIDPanel extends JPanel {
    public JTextField enterIDText;
    public PkbButton enterIDBtn;
    public PkbButton inputBackToMulBtn;

    public InputIDPanel() {
        setOpaque(false);
        enterIDBtn = new PkbButton("   Enter   ");// 進入房間
        inputBackToMulBtn = new PkbButton("   Back   ");// 回到上一頁(選擇 進入/創建房間)

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int myWidth = (int) (d.width / 45);
        JLabel enterIDLabel = new JLabel("Input Game ID: ");
        enterIDLabel.setFont(new Font("SansSerif", Font.BOLD, myWidth));
        enterIDLabel.setForeground(Color.GRAY);
        enterIDText = new JTextField(12);
        enterIDText.setFont(new Font("SansSerif", Font.BOLD + 10, myWidth));
        enterIDText.setForeground(Color.GRAY);

        JPanel littePanel = new JPanel();
        littePanel.setOpaque(false);
        littePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        littePanel.add(inputBackToMulBtn);
        littePanel.add(enterIDBtn);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(enterIDLabel);
        add(enterIDText);
        add(Box.createRigidArea(new Dimension(15, 25)));
        add(littePanel);
    }
}