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
    PkbMenuButton descriptBtn;

    JPanel instrucPanel;
    JLabel instrucLabel;
    PkbMenuButton instrucToStartBtn;

    JPanel modePanel;
    PkbMenuButton normalModeBtn;
    PkbMenuButton specialModeBtn;
    PkbMenuButton backToStartBtn;

    JPanel normalModePanel;
    PkbMenuButton timeTrialBtn;
    PkbMenuButton mazeBtn;
    PkbMenuButton normalBackToModeBtn;

    JPanel specialModePanel;
    PkbMenuButton levelOneBtn;
    PkbMenuButton levelTwoBtn;
    PkbMenuButton levelThreeBtn;
    PkbMenuButton levelFourBtn;
    PkbMenuButton specialBackToModeBtn;

    JPanel loadingPanel;
    JLabel loadingLabel;
    JLabel logoLabel;

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
        startBtn= new PkbMenuButton("     START    ");//3 4
        musicBtn= new PkbMenuButton("     MUSIC    ");//3 4
        descriptBtn= new PkbMenuButton(" Instrutions ");
        
        startBtn.addActionListener(btnClick);
        musicBtn.addActionListener(btnClick);
        descriptBtn.addActionListener(btnClick);
        
        startPanel.setLayout(new BoxLayout(startPanel, BoxLayout.Y_AXIS));
        startPanel.add(startBtn);
        startPanel.add(Box.createRigidArea(new Dimension(15, 25))); 
        startPanel.add(musicBtn);
        startPanel.add(Box.createRigidArea(new Dimension(15, 25))); 
        startPanel.add(descriptBtn);

        setModePanel();
        setInstrucPanel();
    
        add(modePanel, btnPlace);
        add(instrucPanel, btnPlace);
        modePanel.setVisible(false);
        instrucPanel.setVisible(false);
    }
    private void setInstrucPanel(){
        instrucPanel= new JPanel();
        instrucLabel= new JLabel();
        instrucLabel.setIcon(new ImageIcon("img/gameInstruction.png"));

        instrucPanel.setOpaque(false);
        instrucToStartBtn= new PkbMenuButton("   Back   ");//3 3

        instrucToStartBtn.addActionListener(btnClick);
        
        instrucPanel.setLayout(new BorderLayout());//可傳參數(間隙)
        instrucPanel.add(instrucLabel, BorderLayout.CENTER);
        instrucPanel.add(instrucToStartBtn, BorderLayout.SOUTH);
    }
    private void setModePanel(){
        modePanel= new JPanel();
        modePanel.setOpaque(false);
        normalModeBtn= new PkbMenuButton("  Normal Mode  ");//1 1
        specialModeBtn= new PkbMenuButton("  Special Mode  ");//2 2
        backToStartBtn= new PkbMenuButton("         Back         ");//8 8

        normalModeBtn.addActionListener(btnClick);
        specialModeBtn.addActionListener(btnClick);
        backToStartBtn.addActionListener(btnClick);
        
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        modePanel.add(normalModeBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25))); 
        modePanel.add(specialModeBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        modePanel.add(backToStartBtn);

        setnormalModePanel();
        setspecialModePanel();
        
        add(normalModePanel, btnPlace);
        add(specialModePanel, btnPlace);
        normalModePanel.setVisible(false);
        specialModePanel.setVisible(false);
    }

    private void setnormalModePanel(){
        normalModePanel= new JPanel();
        normalModePanel.setOpaque(false);
        timeTrialBtn= new PkbMenuButton("    Time Trial   ");//可存活的時間
        mazeBtn= new PkbMenuButton("        Maze       ");//2 3
        normalBackToModeBtn= new PkbMenuButton("        Back       ");//3 3

        timeTrialBtn.addActionListener(btnClick);
        mazeBtn.addActionListener(btnClick);
        normalBackToModeBtn.addActionListener(btnClick);
        
        normalModePanel.setLayout(new BoxLayout(normalModePanel, BoxLayout.Y_AXIS));
        normalModePanel.add(timeTrialBtn);
        normalModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        normalModePanel.add(mazeBtn);
        normalModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        normalModePanel.add(normalBackToModeBtn);
    }

    private void setspecialModePanel(){
        specialModePanel= new JPanel();
        specialModePanel.setOpaque(false);
        levelOneBtn= new PkbMenuButton("  Level 1  ");//2 2
        levelTwoBtn= new PkbMenuButton("  Level 2  ");//1 1
        levelThreeBtn= new PkbMenuButton("  Level 3  ");//1 1
        levelFourBtn= new PkbMenuButton("  Level 4  ");//1 1
        specialBackToModeBtn= new PkbMenuButton("    Back    ");//4 5

        levelOneBtn.addActionListener(btnClick);
        levelTwoBtn.addActionListener(btnClick);
        levelThreeBtn.addActionListener(btnClick);
        levelFourBtn.addActionListener(btnClick);
        specialBackToModeBtn.addActionListener(btnClick);
        
        specialModePanel.setLayout(new BoxLayout(specialModePanel, BoxLayout.Y_AXIS));
        specialModePanel.add(levelOneBtn);
        specialModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        specialModePanel.add(levelTwoBtn);
        specialModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        specialModePanel.add(levelThreeBtn);
        specialModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        specialModePanel.add(levelFourBtn);
        specialModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        
        specialModePanel.add(specialBackToModeBtn);
    }
    private void setLoadingPanel(){
        loadingPanel = new JPanel();
        loadingLabel = new JLabel();
        logoLabel =  new JLabel();
        loadingLabel.setIcon(new ImageIcon("img/loading.gif"));
        logoLabel.setIcon(new ImageIcon("img/logo_GIF.gif"));

        loadingPanel.setOpaque(false);
        
        loadingPanel.setLayout(new BorderLayout());//可傳參數(間隙)
        loadingPanel.add(loadingLabel, BorderLayout.CENTER);
        loadingPanel.add(logoLabel, BorderLayout.SOUTH);
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
            else if(e.getSource()== descriptBtn){
                startPanel.setVisible(false);
                instrucPanel.setVisible(true);
            }
            else if(e.getSource()== instrucToStartBtn){
                instrucPanel.setVisible(false);
                startPanel.setVisible(true);
            }
            else if(e.getSource()== normalModeBtn){
                modePanel.setVisible(false);
                specialModePanel.setVisible(true);
            }
            else if(e.getSource()== specialModeBtn){
                modePanel.setVisible(false);
                normalModePanel.setVisible(true);
            }
            else if(e.getSource()== backToStartBtn){
                modePanel.setVisible(false);
                startPanel.setVisible(true);
            }
            else if(e.getSource()== normalBackToModeBtn){
                normalModePanel.setVisible(false);
                modePanel.setVisible(true);
            }
            else if(e.getSource()== specialBackToModeBtn){
                specialModePanel.setVisible(false);
                modePanel.setVisible(true);
            }
            else if(e.getSource()== timeTrialBtn){
                gf = new GameFrame("MAPS/mapTimeTrail.txt", 20);
                gf.timeTrial=true;
                gf.initGame();
                gf.gameStart();
                music.close();
            }
            else if(e.getSource()== mazeBtn){
                gf = new GameFrame("maze", 4);
                gf.initGame();
                gf.gameStart();
                music.close();
            }
            else{
                if(e.getSource() == levelOneBtn){
                    callLoadingScreen();
                    startPanel.setVisible(false);
                    instrucPanel.setVisible(true);
                    // gf = new GameFrame("MAPS/map.txt", 4);
                    // gf.initGame();
                }
                else if(e.getSource() == levelTwoBtn){
                    gf = new GameFrame("MAPS/map1.txt", 8);
                    gf.initGame();
                    gf.gameStart();
                }
                else if(e.getSource()== levelThreeBtn){
                    gf = new GameFrame("MAPS/map2.txt", 12);
                    gf.initGame();
                    gf.gameStart();
                }
                else if(e.getSource() == levelFourBtn){
                    gf = new GameFrame("MAPS/mapPaceman.txt", 16);
                    gf.initGame();    
                    gf.gameStart();                
                }
                music.close();
            }
        }
    }

    private void callLoadingScreen(){
        startPanel.setVisible(false);
        modePanel.setVisible(false);
        normalModePanel.setVisible(false);
        specialModePanel.setVisible(false);
        normalModePanel.setVisible(false);
        btnJPanel.setVisible(false);
        loadingPanel.setVisible(true);
    }
}