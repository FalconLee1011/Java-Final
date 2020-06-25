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
    PkbMenuButton specialModeBtn;
    PkbMenuButton normalModeBtn;
    PkbMenuButton multiplePlayerBtn;
    PkbMenuButton backToStartBtn;

    JPanel specialModePanel;
    PkbMenuButton timeTrialBtn;
    PkbMenuButton mazeBtn;
    PkbMenuButton specialBackToModeBtn;

    JPanel normalModePanel;
    PkbMenuButton levelOneBtn;
    PkbMenuButton levelTwoBtn;
    PkbMenuButton levelThreeBtn;
    PkbMenuButton levelFourBtn;
    PkbMenuButton normalBackToModeBtn;

    JPanel multiplePlayerPanel;
    PkbMenuButton createRoomBtn;
    PkbMenuButton enterRoomBtn;
    PkbMenuButton multipleBackToModeBtn;

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
        setLoadingPanel();
    
        add(modePanel, btnPlace);
        add(instrucPanel, btnPlace);
        add(loadingPanel, btnPlace);
        modePanel.setVisible(false);
        instrucPanel.setVisible(false);
        loadingPanel.setVisible(false);
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
        specialModeBtn= new PkbMenuButton("  special Mode   ");//1 2
        normalModeBtn= new PkbMenuButton("  normal Mode   ");//2 3
        multiplePlayerBtn= new PkbMenuButton(" Multiple Player ");
        backToStartBtn= new PkbMenuButton("         Back         ");//9 9

        specialModeBtn.addActionListener(btnClick);
        normalModeBtn.addActionListener(btnClick);
        multiplePlayerBtn.addActionListener(btnClick);
        backToStartBtn.addActionListener(btnClick);
        
        modePanel.setLayout(new BoxLayout(modePanel, BoxLayout.Y_AXIS));
        modePanel.add(normalModeBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25))); 
        modePanel.add(specialModeBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        modePanel.add(multiplePlayerBtn);
        modePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        modePanel.add(backToStartBtn);

        setspecialModePanel();
        setnormalModePanel();
        setMultiplePlayerPanel();
        
        add(specialModePanel, btnPlace);
        add(normalModePanel, btnPlace);
        add(multiplePlayerPanel, btnPlace);
        specialModePanel.setVisible(false);
        normalModePanel.setVisible(false);
        multiplePlayerPanel.setVisible(false);
    }

    private void setspecialModePanel(){
        specialModePanel= new JPanel();
        specialModePanel.setOpaque(false);
        timeTrialBtn= new PkbMenuButton("    Time Trial   ");//可存活的時間
        mazeBtn= new PkbMenuButton("        Maze       ");//2 3
        specialBackToModeBtn= new PkbMenuButton("        Back       ");//3 3

        timeTrialBtn.addActionListener(btnClick);
        mazeBtn.addActionListener(btnClick);
        specialBackToModeBtn.addActionListener(btnClick);
        
        specialModePanel.setLayout(new BoxLayout(specialModePanel, BoxLayout.Y_AXIS));
        specialModePanel.add(timeTrialBtn);
        specialModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        specialModePanel.add(mazeBtn);
        specialModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        specialModePanel.add(specialBackToModeBtn);
    }

    private void setnormalModePanel(){
        normalModePanel= new JPanel();
        normalModePanel.setOpaque(false);
        levelOneBtn= new PkbMenuButton("    Level 1    ");//4 4
        levelTwoBtn= new PkbMenuButton("    Level 2    ");//4 4
        levelThreeBtn= new PkbMenuButton("    Level 3    ");//4 4
        levelFourBtn= new PkbMenuButton("    Level 4    ");//4 4
        normalBackToModeBtn= new PkbMenuButton("      Back      ");//6 6

        levelOneBtn.addActionListener(btnClick);
        levelTwoBtn.addActionListener(btnClick);
        levelThreeBtn.addActionListener(btnClick);
        levelFourBtn.addActionListener(btnClick);
        normalBackToModeBtn.addActionListener(btnClick);
        
        normalModePanel.setLayout(new BoxLayout(normalModePanel, BoxLayout.Y_AXIS));
        normalModePanel.add(levelOneBtn);
        normalModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        normalModePanel.add(levelTwoBtn);
        normalModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        normalModePanel.add(levelThreeBtn);
        normalModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        normalModePanel.add(levelFourBtn);
        normalModePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        
        normalModePanel.add(normalBackToModeBtn);
    }

    private void setMultiplePlayerPanel(){
        multiplePlayerPanel= new JPanel();
        multiplePlayerPanel.setOpaque(false);
        createRoomBtn= new PkbMenuButton(" Create Room ");//創建房間1 1
        enterRoomBtn= new PkbMenuButton("  Enter Room  ");//進入房間2 2
        multipleBackToModeBtn= new PkbMenuButton("       Back        ");//8 8

        createRoomBtn.addActionListener(btnClick);
        enterRoomBtn.addActionListener(btnClick);
        multipleBackToModeBtn.addActionListener(btnClick);
        
        multiplePlayerPanel.setLayout(new BoxLayout(multiplePlayerPanel, BoxLayout.Y_AXIS));
        multiplePlayerPanel.add(createRoomBtn);
        multiplePlayerPanel.add(Box.createRigidArea (new Dimension(15, 25)));
        multiplePlayerPanel.add(enterRoomBtn);
        multiplePlayerPanel.add(Box.createRigidArea (new Dimension(15, 25)));
        multiplePlayerPanel.add(multipleBackToModeBtn);
    }

    private void setLoadingPanel(){
        loadingPanel = new JPanel();
        loadingPanel.setOpaque(false); 
        loadingLabel = new JLabel();
        logoLabel =  new JLabel();
        loadingLabel.setIcon(new ImageIcon("img/loading.gif"));
        logoLabel.setIcon(new ImageIcon("img/PeeKaBoo_300.png"));
        
        loadingPanel.setLayout(new BorderLayout(4, 4));//可傳參數(間隙)
        loadingPanel.add(loadingLabel, BorderLayout.CENTER);
        loadingPanel.add(logoLabel, BorderLayout.SOUTH);
    }
    private class ButtonClick implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            GameFrame gf;
            if(e.getSource()== startBtn){//start
                startPanel.setVisible(false);
                modePanel.setVisible(true);
            }
            else if(e.getSource()== musicBtn){//music
                if(music.isPlaying()==false){
                    music.play();
                }
                else{
                    music.stop();
                }
            }
            else if(e.getSource()== descriptBtn){//Instrution
                startPanel.setVisible(false);
                instrucPanel.setVisible(true);
            }
            else if(e.getSource()== instrucToStartBtn){//Back
                instrucPanel.setVisible(false);
                startPanel.setVisible(true);
            }
            else if(e.getSource()== specialModeBtn){//special mode
                modePanel.setVisible(false);
                specialModePanel.setVisible(true);
            }
            else if(e.getSource()== normalModeBtn){//mormal mode
                modePanel.setVisible(false);
                normalModePanel.setVisible(true);
            }
            else if(e.getSource()== multiplePlayerBtn){//multiple player
                modePanel.setVisible(false);
                multiplePlayerPanel.setVisible(true);
            }
            else if(e.getSource()== multipleBackToModeBtn){//back
                multiplePlayerPanel.setVisible(false);
                modePanel.setVisible(true);
            }
            else if(e.getSource()== backToStartBtn){//back
                modePanel.setVisible(false);
                startPanel.setVisible(true);
            }
            else if(e.getSource()== specialBackToModeBtn){//back
                specialModePanel.setVisible(false);
                modePanel.setVisible(true);
            }
            else if(e.getSource()== normalBackToModeBtn){//back
                normalModePanel.setVisible(false);
                modePanel.setVisible(true);
            }
            else if(e.getSource()== timeTrialBtn){//time trial
                gf = new GameFrame("MAPS/mapTimeTrail.txt", 20);
                gf.timeTrial=true;
                gf.Game();
                music.close();
            }
            else if(e.getSource()== mazeBtn){//maze
                gf = new GameFrame("maze", 4);
                gf.Game();
                music.close();
            }
            else{//(in normal)
                if(e.getSource() == levelOneBtn){//level 1
                    // normalModePanel.setVisible(false);
                    // loadingPanel.setVisible(true);
                    // try{
                    //     Thread.sleep(10000);
                    // }catch(InterruptedException err){
                    //     System.err.println(err);
                    // }
                    gf = new GameFrame("MAPS/map.txt", 4);
                    gf.Game();
                }
                else if(e.getSource() == levelTwoBtn){//level 2
                    gf = new GameFrame("MAPS/map1.txt", 8);
                    gf.Game();
                }
                else if(e.getSource()== levelThreeBtn){//level 3
                    gf = new GameFrame("MAPS/map2.txt", 12);
                    gf.Game();
                }
                else if(e.getSource() == levelFourBtn){//level 4
                    gf = new GameFrame("MAPS/mapPaceman.txt", 16);
                    gf.Game();             
                }
                music.close();
            }
        }
    }
}