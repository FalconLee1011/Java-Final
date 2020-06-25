// 遊戲起始

package peekaboo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import peekaboo.props.*;
import peekaboo.role.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Map;
import java.util.HashMap;
import java.util.Random;
//screen width: 1536, height: 864

public class GameFrame extends JFrame {

    public BackgroundImage bg = new BackgroundImage(); // 背景圖片
    public InitMap iMap;
    public int[][] map;
    public PkbHuman human;
    public PkbTimer timer = new PkbTimer();

    public Map<String, Map<String, Enery>> mapEneryByPos = new HashMap<String, Map<String, Enery>>();
    public Map<String, Map<String, Enery>> backEneryByPos = new HashMap<String, Map<String, Enery>>();
    public Map<String, Map<String, Enery>> rockEneryByPos = new HashMap<String, Map<String, Enery>>();
    public ArrayList<PkbFlyingRock> flyingRocks = new ArrayList<PkbFlyingRock>();
    public ArrayList<PkbGhost> ghosts = new ArrayList<PkbGhost>();

    public ArrayList<Door> doors = new ArrayList<Door>();
    public int doorSerial = 0;
    public int hp = 1;//玩家的生命值
    public int window_width = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getWidth();
    public int window_height = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice().getDisplayMode().getHeight();
    public int numOfGhost = 4;

    public boolean hasWon = false;
    public boolean isGameOver = false;
    public boolean save = false;
    public boolean kaboom = false;
    public boolean timeTrial = false;
    private int breakTime = 0;
    private static final String[] cactusArr = { "img/cactus1.png", "img/cactus2.png", "img/cactus3.png" };
    private static final String[] fruitArr = { "img/devilFruit_golden_GIF.gif", "img/devilFruit_grape_GIF.gif", "img/devilFruit_heart_GIF.gif" };

    public ArrayList<Enery> eneryList = new ArrayList<Enery>();// 裝道具+石頭
    public ArrayList<Enery> rockList = new ArrayList<Enery>();// 裝石頭
    public ArrayList<Integer> rockList2 = new ArrayList<Integer>();// 裝石頭的數字
    public ArrayList<Enery> toolList = new ArrayList<Enery>();// 放道具
    public ArrayList<Integer> toolList2 = new ArrayList<Integer>();// 放道具數字
    public ArrayList<Barrier> brickList = new ArrayList<Barrier>();
    int[][] ghostPos = {{26,10}, {22,30}, {19,49}, {11,64}, {29,63}, {33,19}, {33,59}, {9,28}, {18,58}, {26,27}, {18,33}, {26,26}, {15,50}, {30,21}, {26,53}, {16,19}, {11,38}, {22,62}, {24,9}, {38,13}, {30,57}};

    Random r = new Random();

    Music music = new Music("/MUSIC/gameMusic.wav");

    public GameFrame() {// 初始化bgImg和player
        try { this.map = this.iMap.readMap(); } 
        catch (Exception e) {}
    }

    public GameFrame(String fileName, int numOfGhost) {// 初始化bgImg和player
        if(fileName.equals("maze")){ this.map = new CreateMaze().getMaze(); }
        else{
            this.iMap = new InitMap(fileName);
            try { this.map = this.iMap.readMap(); } 
            catch (Exception e) {}
            if(numOfGhost >= 20){
                this.numOfGhost = 20;
            }
        }
        this.numOfGhost = numOfGhost;
    }

    public void Game() {
        initFrame();
        loadGameProp();
        gameStart();
    }

    public void initFrame() {
        setBackground(new Color(197, 168, 111));
        this.setSize(this.window_width, this.window_height);
        this.setTitle("PeeKaBoo~");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        KeyListener kl = new KeyListener(this);
        this.addKeyListener(kl);
        // 設置背景音樂
        music.loop();
    }

    public void loadGameProp() {
        
        for (int i = 0; i < this.numOfGhost; i++) {
            // 直接追隨
            // public PkbGhost ghost = new PkbGhost();
            // 距離追隨
            // public PkbGhost ghost = new PkbGhost(3120, 3120, 1, true, 1200);
            // 距離追隨、會巡邏
            // ghost_add = new PkbGhost(rndx, rndy, 1, true, 600, true, 300);
            PkbGhost ghost_add;
            int rndx = r.nextInt(this.window_width);
            int rndy = r.nextInt(this.window_height);
            ghost_add = new PkbGhost(ghostPos[i][0] * 120, ghostPos[i][1] * 120, 1, true, 1200, true, 300);
            this.ghosts.add(ghost_add);
        }

        for (int i = 0; i < map.length; i++) {// 讀取地圖，並配置地圖
            for (int j = 0; j < map[0].length; j++) {
                String x_key = String.valueOf(i * 120);
                String y_key = String.valueOf(j * 120);
                Map<String, Enery> map_row = new HashMap<String, Enery>();
                Map<String, Enery> rock_row = new HashMap<String, Enery>();
                Map<String, Enery> brick_row = new HashMap<String, Enery>();
                map_row = mapEneryByPos.get(x_key);
                rock_row = backEneryByPos.get(x_key);
                brick_row = rockEneryByPos.get(x_key);
                if (map_row == null) {
                    map_row = new HashMap<String, Enery>();
                }
                if (rock_row == null) {
                    rock_row = new HashMap<String, Enery>();
                }
                if (brick_row == null) {
                    brick_row = new HashMap<String, Enery>();
                }
                switch (map[i][j]) {
                    case 0: // 畫地板
                        Stone back = new Stone(j * 120, i * 120, 120, 120, new ImageIcon("img/Back.png").getImage());// (x軸，y軸，寬，高
                        eneryList.add(back);
                        toolList.add(back);
                        rockList.add(back);
                        rock_row.put(y_key, back);
                        break;
                    case 1: // 畫邊界
                        Barrier brick = new Barrier(j * 120, i * 120, 120, 120,new ImageIcon(cactusArr[r.nextInt(3)]).getImage());// (x軸，y軸，寬，高)
                        eneryList.add(brick);
                        brickList.add(brick);
                        brick_row.put(y_key, brick);
                        break;
                    case 2: // 畫skates
                        Shoe skates = new Shoe(j * 120, i * 120, 120, 120, new ImageIcon("img/camel_GIF.gif").getImage());
                        eneryList.add(skates);
                        toolList.add(skates);
                        map_row.put(y_key, skates);
                        break;
                    case 3: // 畫烏龜
                        Turtle turtle = new Turtle(j * 120, i * 120, 120, 120,new ImageIcon("img/quickSend_GIF_160.gif").getImage());
                        eneryList.add(turtle);
                        toolList.add(turtle);
                        map_row.put(y_key, turtle);
                        break;
                    case 4: // 畫任意門
                        Door door = new Door(j * 120, i * 120, 120, 120,new ImageIcon("img/rightCave_GIF.gif").getImage(), doorSerial);
                        eneryList.add(door);
                        toolList.add(door);
                        map_row.put(y_key, door);
                        doors.add(door);
                        doorSerial += 1;
                        break;
                    case 5: // 畫迷惑
                        Bewitch bewitch = new Bewitch(j * 120, i * 120, 120, 120,new ImageIcon("img/scorpion_GIF.gif").getImage());
                        eneryList.add(bewitch);
                        toolList.add(bewitch);
                        map_row.put(y_key, bewitch);
                        break;
                    case 6: // 畫果實
                        Fruit Fruit = new Fruit(j * 120, i * 120, 120, 120,new ImageIcon(fruitArr[r.nextInt(3)]).getImage());
                        eneryList.add(Fruit);
                        toolList.add(Fruit);
                        map_row.put(y_key, Fruit);
                        break;
                    case 7:  // 畫凹洞
                        Hole dig = new Hole(j * 120, i * 120, 120, 120, new ImageIcon("img/dig.png").getImage());
                        eneryList.add(dig);
                        rockList.add(dig);
                        // map_row.put(y_key, dig);
                        brick_row.put(y_key, dig);
                        break;
                    case 8: // 畫心
                        Heart heart = new Heart(j * 120, i * 120, 120, 120,new ImageIcon("img/heartWithSend.png").getImage());
                        eneryList.add(heart);
                        toolList.add(heart);
                        // map_row.put(y_key, dig);
                        map_row.put(y_key, heart);
                        break;
                    case 9: // 畫地板
                        MazeExit mazeexit = new MazeExit(j * 120, i * 120, 120, 120, new ImageIcon("img/Back.png").getImage());// (x軸，y軸，寬，高
                        eneryList.add(mazeexit);
                        toolList.add(mazeexit);
                        map_row.put(y_key, mazeexit);
                        break;
                }
                // Evary enery on map
                mapEneryByPos.put(x_key, map_row);
                // Every reachable area
                backEneryByPos.put(x_key, rock_row);
                // Every unreachable area
                rockEneryByPos.put(x_key, brick_row);
            }
        }
    }

    public void gameStart(){
        this.human = new PkbHuman(this);// player
        human.start();
        new Thread() {
            public void run() {
                while (true) {
                    repaint();
                    if(isGameOver){ 
                        // if(breakTime > 75) break; 
                        breakTime += 1;
                    }
                    if (timer.midTime <= 0) {//時間<=0，會結束
                        // System.out.println("GAME OVER");
                        hasWon = true;
                        isGameOver = true;
                        // test.timergame.cancel();////////計時賽的時間暫停
                        // repaint();
                        break;
                    }
                    if (kaboom) {
                        for (int i = 0; i < brickList.size(); i++) {
                            r.nextInt(brickList.size() - 1);
                            Enery e = brickList.get(i);
                            e.img = new ImageIcon("img/Back.png").getImage();
                        }
                        kaboom = false;
                        // System.out.println("BanBooZoled!");
                        continue;
                    }
                    for (PkbGhost ghost : ghosts) {

                        if (ghost.pursue(human)) {
                            if(save==false)
                            {
                                hp--;
                                save=true;
                                // System.out.println("save");
                                human.Time(2000, 100);
                            }
                            
                            if (hp <= 0) {
                                // System.out.println("GAME OVER");
                                hasWon = false;
                                isGameOver = true;
                                // timer.timergame.cancel();////////計時賽的時間暫停
                                // repaint();
                                break;
                            }
                        }
                    }
                    try { Thread.sleep(10); }
                    catch (InterruptedException e) { e.printStackTrace(); }
                }
            }
        }.start();
    }

    

    public void paint(Graphics g) {

        BufferedImage bi = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        Graphics big = bi.getGraphics();

        big.drawImage(bg.img, bg.x, bg.y, null);
        for (int i = 0; i < eneryList.size(); i++) {
            Enery e = eneryList.get(i);
            big.drawImage(e.img, e.x, e.y, e.width, e.height, null);// null不備擋住
        }
        for (int i = 0; i < toolList.size(); i++) {
            Enery e = toolList.get(i);
            big.drawImage(e.img, e.x, e.y, e.width, e.height, null);// null不備擋住
        }
        for (PkbFlyingRock fock : this.flyingRocks) {
            big.drawImage(fock.img, fock.x, fock.y, fock.width, fock.height, null);
        }
        big.setColor(new Color(255, 255, 255));

        int fontSize = 100;
        String strTime,strMin,strSec, strHp;
        timer.timerStart();
        if (timeTrial == true) {
            strSec=(timer.MapSec<10)?"0"+timer.MapSec:""+timer.MapSec;
            strMin=(timer.MapMin<10)?"0"+timer.MapMin:""+timer.MapMin;
        }else {
            strSec=(timer.countSec<10)?"0"+timer.countSec:""+timer.countSec;
            strMin=(timer.countMin<10)?"0"+timer.countMin:""+timer.countMin;
        }
        strTime= "Time " +strMin+":"+strSec;
        big.setFont(new Font("SansSerif", Font.BOLD, fontSize));
        big.drawString(strTime, 500, 200);
        strHp = "HEALTH " + hp;
        big.setColor(new Color(238, 50, 86));
        big.setFont(new Font("SansSerif", Font.BOLD, 50));
        big.drawString(strHp, 100, 200);

        big.drawImage(human.img, human.x, human.y, human.width, human.height, null);
        for (PkbGhost ghost : ghosts) {
            big.drawImage(ghost.img, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }
        if(hasWon && isGameOver){
            ImageIcon img = new ImageIcon("img/win_GIF.gif");
            int m = 2;
            big.drawImage(img.getImage(), (this.window_width / m) - (img.getIconWidth() / m), (this.window_height / m) - (img.getIconHeight() / m), img.getIconWidth(), img.getIconHeight(), null);
        }
        else if(!hasWon && isGameOver){
            ImageIcon img = new ImageIcon("img/gameover_GIF.gif");
            int m = 2;
            big.drawImage(img.getImage(), (this.window_width / m) - (img.getIconWidth() / m), (this.window_height / m) - (img.getIconHeight() / m), img.getIconWidth(), img.getIconHeight(), null);
        }

        g.drawImage(bi, 0, 0, null);

    }
}
