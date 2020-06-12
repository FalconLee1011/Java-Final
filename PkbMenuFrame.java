package peekaboo.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import peekaboo.Music;
import peekaboo.*;


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

    Music music = new Music("/MUSIC/startmusic.wav");

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
        music.play();
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
        startBtn= new PkbMenuButton("   START    ");//3 4
        musicBtn= new PkbMenuButton("  MUSIC   ");//2 3
        
        startBtn.addActionListener(btnClick);
        musicBtn.addActionListener(btnClick);
        
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add(startBtn);
        startPanel.add(Box.createRigidArea(new Dimension(15, 25))); 
        startPanel.add(musicBtn);

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
                GameFrame gf;
                if(e.getSource()== startBtn){
                    startPanel.setVisible(false);
                    modePanel.setVisible(true);
                }
                else if(e.getSource()== musicBtn){
                    
                    if(music.isPlaying()==false){
                        music.play();
                    }
                    else{
                        music.stop();
                    }
                    
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
                else if(e.getSource()== oneThreeBtn){
                    gf = new GameFrame("MAPS/mapDiffcult.txt", 20);
                    gf.timeTrial=true;
                    JOptionPane.showMessageDialog(PkbMenuFrame.this, "Click ok to start!", "Loading Difficult...", JOptionPane.INFORMATION_MESSAGE);
                    gf.startGame();
                }
                else if(e.getSource()== twoTwoBtn){
                    gf = new GameFrame("MAPS/maze.txt", 4);
                    JOptionPane.showMessageDialog(PkbMenuFrame.this, "Click ok to start!", "Loading Maze...", JOptionPane.INFORMATION_MESSAGE);
                    gf.startGame();
                }
                else{
                    if(e.getSource() == onePBtn){
                        gf = new GameFrame("MAPS/map.txt", 4);
                        JOptionPane.showMessageDialog(PkbMenuFrame.this, "Click ok to start!", "Loading Level 1...", JOptionPane.INFORMATION_MESSAGE);
                        gf.startGame();
                    }
                    else if(e.getSource() == twoPBtn){
                        gf = new GameFrame("MAPS/map1.txt", 8);
                        JOptionPane.showMessageDialog(PkbMenuFrame.this, "Click ok to start!", "Loading Level 2...", JOptionPane.INFORMATION_MESSAGE);
                        gf.startGame();
                    }
                    else if(e.getSource() == threePBtn){
                        gf = new GameFrame("MAPS/map2.txt", 12);
                        JOptionPane.showMessageDialog(PkbMenuFrame.this, "Click ok to start!", "Loading Level 3...", JOptionPane.INFORMATION_MESSAGE);
                        gf.startGame();
                    }
                    else if(e.getSource() == fivePBtn){
                        JOptionPane.showMessageDialog(PkbMenuFrame.this, "Click ok to start!", "Loading secreat map...", JOptionPane.INFORMATION_MESSAGE);
                         gf = new GameFrame("MAPS/mapPaceman.txt", 16);
                         gf.startGame();
                    }

                    music.close();
                }
        }
    }
}