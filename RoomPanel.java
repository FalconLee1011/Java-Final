package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class RoomPanel extends JPanel {
    private JPanel playerPanel;
    private JLabel idLabel;
    private JTextField idText;

    private final JLabel[] icons;
    private final int maxPlayerCount;
    private final FlowLayout layout;

    public RoomPanel(String roomID, int playerCount) {
        setOpaque(false);

        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        int myWidth = (int) (d.width / 45);

        idLabel = new JLabel("Game ID: ");
        idLabel.setFont(new Font("SansSerif", Font.BOLD, myWidth));
        idLabel.setForeground(Color.GRAY);
        idText = new JTextField(roomID, 12);
        idText.setForeground(Color.GRAY);
        idText.setFont(new Font("SansSerif", Font.BOLD, myWidth));
        idText.setEditable(false);

        JPanel littePanel = new JPanel();
        littePanel.setOpaque(false);
        littePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        littePanel.add(idLabel);
        littePanel.add(idText);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(littePanel);
        add(Box.createRigidArea(new Dimension(50, 50)));

        playerPanel = new JPanel();
        playerPanel.setOpaque(false);
        layout = new FlowLayout(FlowLayout.CENTER);
        playerPanel.setLayout(layout);
        maxPlayerCount = playerCount;
        icons = new JLabel[maxPlayerCount];
        for (int i = 0; i < maxPlayerCount; i++) {
            icons[i] = new JLabel();
            icons[i].setIcon(new ImageIcon("img/user"+ (i+ 1)+ "_160.png"));
            playerPanel.add(icons[i]);
        }
        add(playerPanel);
    }

    public void setCamelImgs(int playerCount) {
        for (int i = 0; i < maxPlayerCount; i++) {
            if (i >= playerCount)
                icons[i].setVisible(false);
            else
                icons[i].setVisible(true);
        }
        layout.layoutContainer(playerPanel); // 佈置內容窗格
    }

    public void setTitle(String roomID) {
        idText.setText(roomID);
    }
}