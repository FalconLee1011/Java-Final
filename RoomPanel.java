package peekaboo.menu;

import java.awt.*;
import javax.swing.*;

public class RoomPanel extends JPanel{
    JLabel idLabel;
    JTextField idText;

    JPanel playerPanel;

    public RoomPanel(String roomID, int playerCount){
        idLabel= new JLabel("Game ID: ");
        idText= new JTextField(roomID);
        idText.setEditable(false);
        
        playerPanel= new JPanel();
        playerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        for(int i= 0; i< playerCount; i++){
            JLabel playerIcon= new JLabel();
            playerIcon.setIcon(new ImageIcon("img/camel.png"));
            playerPanel.add(playerIcon);
        }
    }    
}