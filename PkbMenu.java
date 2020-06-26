package peekaboo.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import peekaboo.Music;
import peekaboo.menu.RoomPanel;
import peekaboo.*;


public class PkbMenu extends JFrame{
    JPanel startPanel;
    PkbButton startBtn;
    PkbButton musicBtn;
    PkbButton descriptBtn;

    JPanel instrucPanel;
    JLabel instrucLabel;
    PkbButton instrucToStartBtn;

    JPanel modePanel;
    PkbButton specialModeBtn;
    PkbButton normalModeBtn;
    PkbButton multiplePlayerBtn;
    PkbButton backToStartBtn;

    JPanel specialModePanel;
    PkbButton timeTrialBtn;
    PkbButton mazeBtn;
    PkbButton specialBackToModeBtn;

    JPanel normalModePanel;
    PkbButton levelOneBtn;
    PkbButton levelTwoBtn;
    PkbButton levelThreeBtn;
    PkbButton levelFourBtn;
    PkbButton normalBackToModeBtn;

    JPanel multiplePlayerPanel;
    PkbButton createRoomBtn;
    PkbButton enterRoomBtn;
    PkbButton multipleBackToModeBtn;

    JPanel multiplePanel;
    private final int MASTER= 1;
    private final int GUEST= 0;
    private int masterGuest;
    PkbButton startGameBtn;
    PkbButton roomBackToMulBtn;

    JPanel inputIDPanel;
    JTextField enterIDText;
    PkbButton enterIDBtn;
    PkbButton inputBackToMulBtn;

    JPanel loadingPanel;
    JLabel loadingLabel;
    JLabel logoLabel;

    private JPanel btnJPanel= null;
    GridBagConstraints btnPlace= null;
    ButtonClick btnClick;

    Music music = new Music("/MUSIC/startmusic.wav");

    String room;
    RoomPanel roomPanel;

    GameFrame gf = new GameFrame();

    public PkbMenu(String frameTitle){//constructor
        super(frameTitle);
        JPanel contentPanel= new JPanel();
        contentPanel= (JPanel)(this.getContentPane());
        contentPanel.setOpaque(false);
        // contentPanel.setBackground(Color.PINK);
        setBackGroundPane();

        btnClick= new ButtonClick();
        //讓按鈕Panel在中間
        setLayout(new GridBagLayout());
        GridBagConstraints space= new GridBagConstraints();
        setGridBagAttr(space, 2, 0, 1, 4);
        
        btnPlace= new GridBagConstraints();
        setGridBagAttr(btnPlace, 1, 1, 1, 2);
        btnPlace.fill= GridBagConstraints.BOTH;

        setStartPanel();
        add(startPanel, btnPlace);
        music.play();
    }
    
    private void setBackGroundPane(){
        this.setBackground(Color.PINK);
        JLabel imglabel= new JLabel(new ImageIcon("img/background_PNG.png"));
        // imglabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        JLabel testLabel= new JLabel("hiii");
        this.getLayeredPane().add(testLabel, -128);
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
        startBtn= new PkbButton("     START    ");//3 4
        musicBtn= new PkbButton("     MUSIC    ");//3 4
        descriptBtn= new PkbButton(" Instrutions ");
        
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
        instrucToStartBtn= new PkbButton("   Back   ");//3 3

        instrucToStartBtn.addActionListener(btnClick);
        
        instrucPanel.setLayout(new BorderLayout());//可傳參數(間隙)
        instrucPanel.add(instrucLabel, BorderLayout.CENTER);
        instrucPanel.add(instrucToStartBtn, BorderLayout.SOUTH);
    }
    private void setModePanel(){
        modePanel= new JPanel();
        modePanel.setOpaque(false);
        specialModeBtn= new PkbButton("  special Mode   ");//1 2
        normalModeBtn= new PkbButton("  normal Mode   ");//2 3
        multiplePlayerBtn= new PkbButton(" Multiple Player ");
        backToStartBtn= new PkbButton("         Back         ");//9 9

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
        
        // specialModePanel.add(image);
        timeTrialBtn= new PkbButton("    Time Trial   ");//可存活的時間
        mazeBtn= new PkbButton("        Maze       ");//2 3
        specialBackToModeBtn= new PkbButton("        Back       ");//3 3

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
        levelOneBtn= new PkbButton("    Level 1    ");//4 4
        levelTwoBtn= new PkbButton("    Level 2    ");//4 4
        levelThreeBtn= new PkbButton("    Level 3    ");//4 4
        levelFourBtn= new PkbButton("    Level 4    ");//4 4
        normalBackToModeBtn= new PkbButton("      Back      ");//6 6

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
        createRoomBtn= new PkbButton(" Create Room ");//創建房間1 1
        enterRoomBtn= new PkbButton("  Enter Room  ");//進入房間2 2
        multipleBackToModeBtn= new PkbButton("       Back        ");//8 8

        createRoomBtn.addActionListener(btnClick);
        enterRoomBtn.addActionListener(btnClick);
        multipleBackToModeBtn.addActionListener(btnClick);
        
        multiplePlayerPanel.setLayout(new BoxLayout(multiplePlayerPanel, BoxLayout.Y_AXIS));
        multiplePlayerPanel.add(createRoomBtn);
        multiplePlayerPanel.add(Box.createRigidArea (new Dimension(15, 25)));
        multiplePlayerPanel.add(enterRoomBtn);
        multiplePlayerPanel.add(Box.createRigidArea (new Dimension(15, 25)));
        multiplePlayerPanel.add(multipleBackToModeBtn);

        setMultiplePanel();
        setInputIDPanel();
        add(multiplePanel, btnPlace);
        add(inputIDPanel, btnPlace);
        multiplePanel.setVisible(false);
        inputIDPanel.setVisible(false);
    }

    private void setMultiplePanel(){
        multiplePanel= new JPanel();
        multiplePanel.setOpaque(false);
        roomPanel = new RoomPanel("banana!", 4);
        masterGuest= GUEST;//= 0
        startGameBtn= new PkbButton("   Start !   ");//2 2
        roomBackToMulBtn= new PkbButton("   Back  ");//5 5

        startGameBtn.addActionListener(btnClick);
        roomBackToMulBtn.addActionListener(btnClick);

        // if(masterGuest== MASTER){//= 1
            startGameBtn.setEnabled(true);
        // }
        // else{//== GUEST= 0
            // startGameBtn.setEnabled(false);
        // }

        JPanel littePanel= new JPanel();
        littePanel.setOpaque(false);
        littePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        littePanel.add(roomBackToMulBtn);
        littePanel.add(startGameBtn);
        
        multiplePanel.setLayout(new BoxLayout(multiplePanel, BoxLayout.Y_AXIS));
        multiplePanel.add(roomPanel);
        multiplePanel.add(Box.createRigidArea (new Dimension(15, 25)));
        multiplePanel.add(littePanel);
    }
    private void setInputIDPanel(){
        inputIDPanel= new JPanel();
        inputIDPanel.setOpaque(false);
        enterIDBtn= new PkbButton("   Enter   ");//2 2
        inputBackToMulBtn= new PkbButton("   Back   ");//4 4

        Dimension d= Toolkit.getDefaultToolkit().getScreenSize();
        int myWidth= (int)(d.width/ 45);
        JLabel enterIDLabel= new JLabel("Input Game ID: ");
        enterIDLabel.setFont(new Font("SansSerif", Font.BOLD, myWidth));
        enterIDLabel.setForeground(Color.GRAY);
        enterIDText= new JTextField(12);
        enterIDText.setFont(new Font("SansSerif", Font.BOLD+ 10, myWidth));
        enterIDText.setForeground(Color.GRAY);

        enterIDBtn.addActionListener(btnClick);
        inputBackToMulBtn.addActionListener(btnClick);

        JPanel littePanel= new JPanel();
        littePanel.setOpaque(false);
        littePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        littePanel.add(inputBackToMulBtn);
        littePanel.add(enterIDBtn);
        
        inputIDPanel.setLayout(new BoxLayout(inputIDPanel, BoxLayout.Y_AXIS));
        inputIDPanel.add(enterIDLabel);
        inputIDPanel.add(enterIDText);
        inputIDPanel.add(Box.createRigidArea (new Dimension(15, 25)));
        inputIDPanel.add(littePanel);
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

            else if(e.getSource()== createRoomBtn){//create room
                multiplePlayerPanel.setVisible(false);
                multiplePanel.setVisible(true);
                masterGuest= MASTER;
                startGameBtn.setEnabled(true);
                gf = new GameFrame("MAPS/map.txt", 4, true);
                gf.isMaster = true;
                PkbAPIHandler api = new PkbAPIHandler(gf);
                room = api.create_game();
                System.out.println(room);
                roomPanel.setTitle(room);
                roomPanel.setCamelImgs(1);//目前房間的玩家人數
                int playerID = api.joinGame();
                gf.playerID = playerID;
                gf.api = api;
            }

            else if(e.getSource() == startGameBtn){
                try { gf.Game(); }
                catch(Exception errrrr){}
            }
            
            else if(e.getSource()== enterRoomBtn){//enter room
                multiplePlayerPanel.setVisible(false);
                inputIDPanel.setVisible(true);
                masterGuest= GUEST;
                // startGameBtn.setEnabled(false);
            }


            else if(e.getSource()== roomBackToMulBtn){//back
                multiplePanel.setVisible(false);
                multiplePlayerPanel.setVisible(true);
                masterGuest= GUEST;
                // startGameBtn.setEnabled(false);
            }
            
            else if(e.getSource()== enterIDBtn){//input+ enter
                inputIDPanel.setVisible(false);
                multiplePanel.setVisible(true);
                room = enterIDText.getText();
                roomPanel.setTitle(room);
                System.out.println("input: "+ enterIDText.getText());
                gf = new GameFrame("MAPS/map.txt", 4, true);
                PkbAPIHandler api = new PkbAPIHandler(gf);
                api.gameID = room;
                int playerID = api.joinGame();
                gf.playerID = playerID;
                gf.api = api;
                roomPanel.setCamelImgs(1);// 更新目前房間的玩家人數
            }
            else if(e.getSource()== inputBackToMulBtn){//back
                inputIDPanel.setVisible(false);
                multiplePlayerPanel.setVisible(true);
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