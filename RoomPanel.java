package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class RoomPanel extends JPanel{
    JPanel playerPanel;
    JLabel idLabel;
    JTextField idText;
    
    public RoomPanel(String roomID, int playerCount){
        setOpaque(false);
        
        Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
        int myWidth= (int)(d.width/ 45);

        idLabel= new JLabel("Game ID: ");
        idLabel.setFont(new Font("SansSerif", Font.BOLD, myWidth));
        idLabel.setForeground(Color.GRAY);
        idText= new JTextField(roomID, 12);
        idText.setForeground(Color.GRAY);
        idText.setFont(new Font("SansSerif", Font.BOLD, myWidth));
        idText.setEditable(false);

        JPanel littePanel= new JPanel();
        littePanel.setOpaque(false);
        littePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        littePanel.add(idLabel);
        littePanel.add(idText);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(littePanel);
        add(Box.createRigidArea (new Dimension(50, 50)));
        
        playerPanel= new JPanel();
        playerPanel.setOpaque(false);
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(int i= 0; i< playerCount; i++){
            JLabel playerIcon= new JLabel();
            playerIcon.setIcon(new ImageIcon("img/camel.png"));
            playerPanel.add(playerIcon);
        }
        add(playerPanel);
    }    
    public void setTitle(String roomID){
        idText.setText(roomID);
    }
}