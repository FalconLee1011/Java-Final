package peekaboo.menu;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import peekaboo.*;

public class PkbMenu extends JFrame {
    private final StartPanel startPanel = new StartPanel();
    private final InstrucPanel instrucPanel = new InstrucPanel();
    private final ModePanel modePanel = new ModePanel();

    private final NormalModePanel normalModePanel = new NormalModePanel();
    private final SpecialModePanel specialModePanel = new SpecialModePanel();
    private final MultiplePlayerPanel multiplePlayerPanel = new MultiplePlayerPanel();

    private final MultiplePanel multiplePanel = new MultiplePanel();
    Thread serverThr= new Thread();
    private final int MASTER = 1;
    private final int GUEST = 0;
    private int masterGuest;

    private final InputIDPanel inputIDPanel = new InputIDPanel();

    private final LoadingPanel loadingPanel = new LoadingPanel();

    private GridBagConstraints btnPlace = null;
    private ButtonClick btnClick;

    private Music music = new Music("/MUSIC/startmusic.wav");

    private String room;
    private RoomPanel roomPanel = new RoomPanel("banana!", 4);;

    private GameFrame gf = new GameFrame();

    public PkbMenu(String frameTitle) {// constructor
        super(frameTitle);
        JPanel contentPanel = new JPanel();
        contentPanel = (JPanel) (this.getContentPane());
        contentPanel.setOpaque(false);
        setBackGroundPane();

        btnClick = new ButtonClick();
        // 讓按鈕Panel在中間
        setLayout(new GridBagLayout());
        GridBagConstraints space = new GridBagConstraints();
        setGridBagAttr(space, 2, 0, 1, 4);

        btnPlace = new GridBagConstraints();
        setGridBagAttr(btnPlace, 1, 1, 1, 2);
        btnPlace.fill = GridBagConstraints.BOTH;

        setStartPanel();
        add(startPanel, btnPlace);
        music.play();
    }

    private void setBackGroundPane() {
        this.setBackground(Color.PINK);
        JLabel imglabel = new JLabel(new ImageIcon("img/background_PNG.png"));
        // imglabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        JLabel testLabel = new JLabel("hiii");
        this.getLayeredPane().add(testLabel, -128);
    }

    public void setGridBagAttr(GridBagConstraints gridBag, int x, int y, int w, int h) {
        gridBag.gridx = x;
        gridBag.gridy = y;
        gridBag.gridwidth = w;
        gridBag.gridheight = h;
    }

    private void setStartPanel() {

        startPanel.startBtn.addActionListener(btnClick);
        startPanel.musicBtn.addActionListener(btnClick);
        startPanel.descriptBtn.addActionListener(btnClick);

        setModePanel();
        setInstrucPanel();

        add(modePanel, btnPlace);
        add(instrucPanel, btnPlace);
        add(loadingPanel, btnPlace);
        modePanel.setVisible(false);
        instrucPanel.setVisible(false);
        loadingPanel.setVisible(false);
    }

    private void setInstrucPanel() {
        instrucPanel.instrucToStartBtn.addActionListener(btnClick);
    }

    private void setModePanel() {
        modePanel.specialModeBtn.addActionListener(btnClick);
        modePanel.normalModeBtn.addActionListener(btnClick);
        modePanel.multiplePlayerBtn.addActionListener(btnClick);
        modePanel.backToStartBtn.addActionListener(btnClick);

        setNormalModePanel();
        setSpecialModePanel();
        setMultiplePlayerPanel();

        add(specialModePanel, btnPlace);
        add(normalModePanel, btnPlace);
        add(multiplePlayerPanel, btnPlace);
        specialModePanel.setVisible(false);
        normalModePanel.setVisible(false);
        multiplePlayerPanel.setVisible(false);
    }

    private void setNormalModePanel() {
        normalModePanel.levelOneBtn.addActionListener(btnClick);
        normalModePanel.levelTwoBtn.addActionListener(btnClick);
        normalModePanel.levelThreeBtn.addActionListener(btnClick);
        normalModePanel.levelFourBtn.addActionListener(btnClick);
        normalModePanel.normalBackToModeBtn.addActionListener(btnClick);
    }

    private void setSpecialModePanel() {
        specialModePanel.timeTrialBtn.addActionListener(btnClick);
        specialModePanel.mazeBtn.addActionListener(btnClick);
        specialModePanel.specialBackToModeBtn.addActionListener(btnClick);
    }

    private void setMultiplePlayerPanel() {
        multiplePlayerPanel.createRoomBtn.addActionListener(btnClick);
        multiplePlayerPanel.enterRoomBtn.addActionListener(btnClick);
        multiplePlayerPanel.multipleBackToModeBtn.addActionListener(btnClick);

        setMultiplePanel();
        setInputIDPanel();
        add(multiplePanel, btnPlace);
        add(inputIDPanel, btnPlace);
        multiplePanel.setVisible(false);
        inputIDPanel.setVisible(false);
    }

    private void setMultiplePanel() {
        masterGuest = GUEST;// = 0

        multiplePanel.startGameBtn.addActionListener(btnClick);
        multiplePanel.roomBackToMulBtn.addActionListener(btnClick);

        JPanel littePanel = new JPanel();
        littePanel.setOpaque(false);
        littePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        littePanel.add(multiplePanel.roomBackToMulBtn);
        littePanel.add(multiplePanel.startGameBtn);

        multiplePanel.add(roomPanel);
        multiplePanel.add(Box.createRigidArea(new Dimension(15, 25)));
        multiplePanel.add(littePanel);
    }

    private void setInputIDPanel() {
        inputIDPanel.enterIDBtn.addActionListener(btnClick);
        inputIDPanel.inputBackToMulBtn.addActionListener(btnClick);
    }

    private class ButtonClick implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == startPanel.startBtn) {// start
                startPanel.setVisible(false);
                modePanel.setVisible(true);
            } else if (e.getSource() == startPanel.musicBtn) {// music
                if (music.isPlaying() == false) {
                    music.play();
                } else {
                    music.stop();
                }
            } else if (e.getSource() == startPanel.descriptBtn) {// Instrution
                startPanel.setVisible(false);
                instrucPanel.setVisible(true);
            } else if (e.getSource() == instrucPanel.instrucToStartBtn) {// Back
                instrucPanel.setVisible(false);
                startPanel.setVisible(true);
            } else if (e.getSource() == modePanel.specialModeBtn) {// special mode
                modePanel.setVisible(false);
                specialModePanel.setVisible(true);
            } else if (e.getSource() == modePanel.normalModeBtn) {// mormal mode
                modePanel.setVisible(false);
                normalModePanel.setVisible(true);
            } else if (e.getSource() == modePanel.multiplePlayerBtn) {// multiple player
                modePanel.setVisible(false);
                multiplePlayerPanel.setVisible(true);
            } else if (e.getSource() == multiplePlayerPanel.multipleBackToModeBtn) {// back
                multiplePlayerPanel.setVisible(false);
                modePanel.setVisible(true);
            } else if (e.getSource() == multiplePlayerPanel.createRoomBtn) {// create room
                multiplePlayerPanel.setVisible(false);
                multiplePanel.setVisible(true);
                masterGuest = MASTER;
                multiplePanel.startGameBtn.setEnabled(true);
                gf = new GameFrame("MAPS/map.txt", 4, true);
                gf.isMaster = true;
                PkbAPIHandler api = new PkbAPIHandler(gf);
                room = api.create_game();
                System.out.println(room);
                roomPanel.setTitle(room);
                serverThr = new Thread() {
                    public void run() {
                        while (true) {
                            roomPanel.setCamelImgs(api.getPlayerCount());// 目前房間的玩家人數
                        }
                    }
                };
                serverThr.start();
                int playerID = api.joinGame();
                gf.playerID = playerID;
                gf.api = api;
            } else if (e.getSource() == multiplePanel.startGameBtn) {
                serverThr.interrupt();
                try {
                    gf.Game();
                } catch (Exception errrrr) {
                    errrrr.printStackTrace();
                }
            } else if (e.getSource() == multiplePlayerPanel.enterRoomBtn) {// enter room
                multiplePlayerPanel.setVisible(false);
                inputIDPanel.setVisible(true);
                masterGuest = GUEST;
            } else if (e.getSource() == multiplePanel.roomBackToMulBtn) {// back
                serverThr.interrupt();
                multiplePanel.setVisible(false);
                multiplePlayerPanel.setVisible(true);
                masterGuest = GUEST;
            } else if (e.getSource() == inputIDPanel.enterIDBtn) {// input+ enter
                inputIDPanel.setVisible(false);
                multiplePanel.setVisible(true);
                room = inputIDPanel.enterIDText.getText();
                roomPanel.setTitle(room);
                // System.out.println("input: " + inputIDPanel.enterIDText.getText());
                gf = new GameFrame("MAPS/map.txt", 4, true);
                PkbAPIHandler api = new PkbAPIHandler(gf);
                api.gameID = room;
                int playerID = api.joinGame();
                gf.playerID = playerID;
                gf.api = api;
                serverThr = new Thread() {
                    public void run() {
                        while (true) {
                            roomPanel.setCamelImgs(api.getPlayerCount());// 目前房間的玩家人數
                        }
                    }
                };
                serverThr.start();

            } else if (e.getSource() == inputIDPanel.inputBackToMulBtn) {// back

                inputIDPanel.setVisible(false);
                multiplePlayerPanel.setVisible(true);
            } else if (e.getSource() == modePanel.backToStartBtn) {// back
                modePanel.setVisible(false);
                startPanel.setVisible(true);
            } else if (e.getSource() == specialModePanel.specialBackToModeBtn) {// back
                specialModePanel.setVisible(false);
                modePanel.setVisible(true);
            } else if (e.getSource() == normalModePanel.normalBackToModeBtn) {// back
                normalModePanel.setVisible(false);
                modePanel.setVisible(true);
            } else if (e.getSource() == specialModePanel.timeTrialBtn) {// time trial
                gf = new GameFrame("MAPS/mapTimeTrail.txt", 20);
                gf.timeTrial = true;
                gf.Game();
                music.close();
            } else if (e.getSource() == specialModePanel.mazeBtn) {// maze
                gf = new GameFrame("maze", 4);
                gf.Game();
                music.close();
            } else {// (in normal)
                if (e.getSource() == normalModePanel.levelOneBtn) {// level 1
                    // normalModePanel.setVisible(false);
                    // loadingPanel.setVisible(true);
                    // try{
                    // Thread.sleep(10000);
                    // }catch(InterruptedException err){
                    // System.err.println(err);
                    // }
                    gf = new GameFrame("MAPS/map.txt", 4);
                    gf.Game();
                } else if (e.getSource() == normalModePanel.levelTwoBtn) {// level 2
                    gf = new GameFrame("MAPS/map1.txt", 8);
                    gf.Game();
                } else if (e.getSource() == normalModePanel.levelThreeBtn) {// level 3
                    gf = new GameFrame("MAPS/map2.txt", 12);
                    gf.Game();
                } else if (e.getSource() == normalModePanel.levelFourBtn) {// level 4
                    gf = new GameFrame("MAPS/mapPaceman.txt", 16);
                    gf.Game();
                }
                music.close();
            }
        }
    }
}