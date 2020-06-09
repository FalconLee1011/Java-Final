package peekaboo.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class PkbMenuFrame extends JFrame{
    JPanel startPanel;
    PkbMenuButton startBtn;
    PkbMenuButton musicBtn;

    JPanel modePanel;
    PkbMenuButton humanModeBtn;
    PkbMenuButton ghostModeBtn;
    PkbMenuButton backToStartBtn;

    JPanel ghostModePanel;
    PkbMenuButton oneThreeBtn;
    PkbMenuButton twoTwoBtn;
    PkbMenuButton ghostBackToModeBtn;

    JPanel humanModePanel;
    PkbMenuButton onePBtn;
    PkbMenuButton twoPBtn;
    PkbMenuButton threePBtn;
    PkbMenuButton fivePBtn;
    PkbMenuButton humanBackToModeBtn;

    private JPanel btnJPanel= null;
    GridBagConstraints btnPlace= null;
    ButtonClick btnClick;

    Container c= null;
    public PkbMenuFrame(String frameTitle){//constructor
        super(frameTitle);
        btnClick= new ButtonClick();
        
        //讓按鈕Panel在中間
        setLayout(new GridBagLayout());
        GridBagConstraints space= new GridBagConstraints();
        setGridBagAttr(space, 2, 0, 1, 4);
        
        btnPlace= new GridBagConstraints();
        setGridBagAttr(btnPlace, 1, 1, 1, 2);
        btnPlace.fill= GridBagConstraints.BOTH;

        c= getContentPane();
        c.setBackground(Color.PINK);
        
        setStartPanel();
        add(startPanel, btnPlace);
    }
    public void setGridBagAttr(GridBagConstraints gridBag, int x, int y, int w, int h){
        gridBag.gridx= x;
        gridBag.gridy= y;
        gridBag.gridwidth= w;
        gridBag.gridheight= h;
    }
    private void setStartPanel(){
        startPanel= new JPanel();
        startPanel.setOpaque(false);
        startBtn= new PkbMenuButton("   Start    ");//3 4
        //musicBtn= new PkbMenuButton("  music   ");//2 3
        
        startBtn.addActionListener(btnClick);
       //musicBtn.addActionListener(btnClick);
        
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add(startBtn);
        startPanel.add(Box.createRigidArea(new Dimension(15, 25))); 
        //startPanel.add(musicBtn);

        setModePanel();
        add(modePanel, btnPlace);
        modePanel.setVisible(false);
    }
    private void setModePanel(){
        modePanel= new JPanel();
        modePanel.setOpaque(false);
        humanModeBtn= new PkbMenuButton("  Normal Mode  ");//1 1
        ghostModeBtn= new PkbMenuButton("  Special Mode  ");//2 2
        backToStartBtn= new PkbMenuButton("         Back         ");//8 8

        humanModeBtn.addActionListener(btnClick);
        ghostModeBtn.addActionListener(btnClick);
        backToStartBtn.addActionListener(btnClick);
        
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        modePanel.add(humanModeBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25))); 
        modePanel.add(ghostModeBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        modePanel.add(backToStartBtn);

        setGhostModePanel();
        setHumanModePanel();
        add(ghostModePanel, btnPlace);
        add(humanModePanel, btnPlace);
        ghostModePanel.setVisible(false);
        humanModePanel.setVisible(false);
    }
    private void setGhostModePanel(){
        ghostModePanel= new JPanel();
        ghostModePanel.setOpaque(false);
        oneThreeBtn= new PkbMenuButton("    Time Trial   ");//可存活的時間
        twoTwoBtn= new PkbMenuButton("        Maze       ");//2 3
        ghostBackToModeBtn= new PkbMenuButton("        Back       ");//3 3

        oneThreeBtn.addActionListener(btnClick);
        twoTwoBtn.addActionListener(btnClick);
        ghostBackToModeBtn.addActionListener(btnClick);
        
        ghostModePanel.setLayout(new BoxLayout(ghostModePanel, BoxLayout.Y_AXIS));
        ghostModePanel.add(oneThreeBtn);
        ghostModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        ghostModePanel.add(twoTwoBtn);
        ghostModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        ghostModePanel.add(ghostBackToModeBtn);
    }
    private void setHumanModePanel(){
        humanModePanel= new JPanel();
        humanModePanel.setOpaque(false);
        onePBtn= new PkbMenuButton("  Level 1  ");//2 2
        twoPBtn= new PkbMenuButton("  Level 2  ");//1 1
        threePBtn= new PkbMenuButton("  Level 3  ");//1 1
        fivePBtn= new PkbMenuButton("  Level 4  ");//1 1
        humanBackToModeBtn= new PkbMenuButton("    Back    ");//4 5

        onePBtn.addActionListener(btnClick);
        twoPBtn.addActionListener(btnClick);
        threePBtn.addActionListener(btnClick);
        fivePBtn.addActionListener(btnClick);
        humanBackToModeBtn.addActionListener(btnClick);
        
        humanModePanel.setLayout(new BoxLayout(humanModePanel, BoxLayout.Y_AXIS));
        humanModePanel.add(onePBtn);
        humanModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        humanModePanel.add(twoPBtn);
        humanModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        humanModePanel.add(threePBtn);
        humanModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        humanModePanel.add(fivePBtn);
        humanModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        
        humanModePanel.add(humanBackToModeBtn);
    }
    private class ButtonClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
                if(e.getSource()== startBtn){
                    startPanel.setVisible(false);
                    modePanel.setVisible(true);
                }
                else if(e.getSource()== musicBtn){
                    /*
                    if(musicBtn.getActionCommand()== "music_on"){}
                    else{}
                    */
                }
                else if(e.getSource()== humanModeBtn){
                    modePanel.setVisible(false);
                    humanModePanel.setVisible(true);
                }
                else if(e.getSource()== ghostModeBtn){
                    modePanel.setVisible(false);
                    ghostModePanel.setVisible(true);
                }
                else if(e.getSource()== backToStartBtn){
                    modePanel.setVisible(false);
                    startPanel.setVisible(true);
                }
                else if(e.getSource()== ghostBackToModeBtn){
                    ghostModePanel.setVisible(false);
                    modePanel.setVisible(true);
                }
                else if(e.getSource()== humanBackToModeBtn){
                    humanModePanel.setVisible(false);
                    modePanel.setVisible(true);
                }
                else{
                    JOptionPane.showMessageDialog(PkbMenuFrame.this, "Loading game...", "Welcome!", JOptionPane.INFORMATION_MESSAGE);
                }
        }
    }
}