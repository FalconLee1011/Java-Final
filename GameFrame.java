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
    public int[][] map = (new InitMap()).readMap();// 畫地圖，制定規則，是1畫磚頭，是2畫skates，是3畫水管
    public PkbHuman human;
    public PkbTimer test = new PkbTimer(this);

    public Map<String, Map<String, Enery>> mapEneryByPos = new HashMap<String, Map<String, Enery>>();
    public Map<String, Map<String, Enery>> backEneryByPos = new HashMap<String, Map<String, Enery>>();
    public Map<String, Map<String, Enery>> rockEneryByPos = new HashMap<String, Map<String, Enery>>();
    public ArrayList<PkbFlyingRock> flyingRocks = new ArrayList<PkbFlyingRock>();
    public ArrayList<PkbGhost> ghosts = new ArrayList<PkbGhost>();

    public ArrayList<Door> doors = new ArrayList<Door>();
    public int doorSerial = 0;
    public int hp = 1;
    public int window_width;
    public int window_height;

    public boolean kaboom = false;

    private static final String[] cactusArr = { "img/cactus1.png", "img/cactus2.png", "img/cactus3.png" };
    private static final String[] fruitArr = { "img/devilFruit_golden_GIF.gif", "img/devilFruit_grape_GIF.gif", "img/devilFruit_heart_GIF.gif" };

    public ArrayList<Enery> eneryList = new ArrayList<Enery>();// 裝道具+石頭
    public ArrayList<Enery> rockList = new ArrayList<Enery>();// 裝石頭
    public ArrayList<Integer> rockList2 = new ArrayList<Integer>();// 裝石頭的數字
    public ArrayList<Enery> toolList = new ArrayList<Enery>();// 放道具
    public ArrayList<Integer> toolList2 = new ArrayList<Integer>();// 放道具數字
    public ArrayList<Barrier> brickList = new ArrayList<Barrier>();
    Random r = new Random();

    // Music music = new Music("/MUSIC/gameMusic.wav");

    public GameFrame() throws Exception {// 初始化bgImg和player
        // 直接追隨
        // public PkbGhost ghost = new PkbGhost();
        // 距離追隨
        // public PkbGhost ghost = new PkbGhost(3120, 3120, 1, true, 1200);
        // 距離追隨、會巡邏
        PkbGhost ghost_add;
        ghost_add = new PkbGhost(3120, 3120, 1, true, 600, true, 300);
        this.ghosts.add(ghost_add);

        ghost_add = new PkbGhost(3120, 3120, 1, true, 600, true, 120);
        this.ghosts.add(ghost_add);

        ghost_add = new PkbGhost(3120,3120, 1, true, 600, true, 600);
        this.ghosts.add(ghost_add);

        ghost_add = new PkbGhost(3120,3120, 1, true, 600, true, 900);
        this.ghosts.add(ghost_add);

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
                        toolList2.add(0);
                        rockList.add(back);
                        rockList2.add(0);
                        rock_row.put(y_key, back);
                        break;
                    case 1: // 畫邊界
                        Barrier brick = new Barrier(j * 120, i * 120, 120, 120,
                                new ImageIcon(cactusArr[r.nextInt(3)]).getImage());// (x軸，y軸，寬，高)
                        eneryList.add(brick);
                        brickList.add(brick);
                        brick_row.put(y_key, brick);
                        break;
                    case 2: // 畫skates
                        Shoe skates = new Shoe(j * 120, i * 120, 120, 120,
                                new ImageIcon("img/camel_GIF.gif").getImage());
                        eneryList.add(skates);
                        toolList.add(skates);
                        toolList2.add(2);
                        map_row.put(y_key, skates);
                        break;
                    case 3: // 畫烏龜
                        Turtle turtle = new Turtle(j * 120, i * 120, 120, 120,
                                new ImageIcon("img/quickSend_GIF_160.gif").getImage());
                        eneryList.add(turtle);
                        toolList.add(turtle);
                        toolList2.add(3);
                        map_row.put(y_key, turtle);
                        break;
                    case 4:
                        Door door = new Door(j * 120, i * 120, 120, 120,
                                new ImageIcon("img/rightCave_GIF.gif").getImage(), doorSerial);
                        eneryList.add(door);
                        toolList.add(door);
                        toolList2.add(4);
                        map_row.put(y_key, door);
                        doors.add(door);
                        doorSerial += 1;
                        break;
                    case 5:
                        Bewitch bewitch = new Bewitch(j * 120, i * 120, 120, 120,
                                new ImageIcon("img/scorpion.gif").getImage());
                        eneryList.add(bewitch);
                        toolList.add(bewitch);
                        toolList2.add(5);
                        map_row.put(y_key, bewitch);
                        break;
                    case 6:
                        Fruit Fruit = new Fruit(j * 120, i * 120, 120, 120,
                                new ImageIcon(fruitArr[r.nextInt(3)]).getImage());
                        eneryList.add(Fruit);
                        toolList.add(Fruit);
                        toolList2.add(6);
                        map_row.put(y_key, Fruit);
                        break;
                    case 7:
                        Hole dig = new Hole(j * 120, i * 120, 120, 120, new ImageIcon("img/dig.png").getImage());
                        eneryList.add(dig);
                        rockList.add(dig);
                        rockList2.add(7);
                        // map_row.put(y_key, dig);
                        brick_row.put(y_key, dig);
                        break;
                    case 8:
                        Heart heart = new Heart(j * 120, i * 120, 120, 120, new ImageIcon("img/heartWithSend.png").getImage());
                        eneryList.add(heart);
                        toolList.add(heart);
                        toolList2.add(8);
                        // map_row.put(y_key, dig);
                        map_row.put(y_key, heart);
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
        // 設置背景音樂
        // music.loop();

        this.human = new PkbHuman(this);// player
        human.start();
        new Thread() {
            public void run() {
                while (true) {
                    repaint();
                    if(kaboom){
                        for (int i = 0; i < brickList.size(); i++) {
                            r.nextInt(brickList.size() - 1);
                            Enery e = brickList.get(i);
                            e.img = new ImageIcon("img/Back.png").getImage();
                            // try { Thread.sleep(5); }
                            // catch (Exception e3) {}
                        }
                        // for (int i = 0; i < eneryList.size(); i++) {
                        //     // r.nextInt(eneryList.size() - 1);
                        //     Enery e = eneryList.get(i);
                        //     e.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                        // }
                        kaboom = false;
                        System.out.println("BanBooZoled!");
                        // for (int i = 0; i < eneryList.size(); i++) {
                        //     r.nextInt(eneryList.size() - 1);
                        //     Enery e = eneryList.get(i);
                        //     e.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                        //     try { Thread.sleep(5); }
                        //     catch (Exception e3) {}
                        // }
                        // for (int i = 0; i < toolList.size(); i++) {
                        //     r.nextInt(toolList.size() - 1);
                        //     Enery e = toolList.get(i);
                        //     e.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                        //     try { Thread.sleep(5); }
                        //     catch (Exception e12) {}
                        // }
                        // for (int i = 0; i < flyingRocks.size(); i++) {
                        //     r.nextInt(flyingRocks.size() - 1);
                        //     PkbFlyingRock fock = flyingRocks.get(i);
                        //     fock.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                        //     try { Thread.sleep(5); }
                        //     catch (Exception e) {}
                        // }
                        // for (int i = 0; i < ghosts.size(); i++) {
                        //     r.nextInt(ghosts.size() - 1);
                        //     PkbGhost ghost = ghosts.get(i);
                        //     ghost.img = new ImageIcon("img/teacher_downMove_GIF.gif").getImage();
                        //     try { Thread.sleep(5); }
                        //     catch (Exception e) {}
                        // }
                        continue;
                    }
                    for (PkbGhost ghost : ghosts) {

                        if (ghost.pursue(human)) {
                            hp--;
                            if (hp <= 0){
                                System.out.println("GAME OVER");
                                // break;
                            }
                        }
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();

    }

    public void initFrame() {
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        this.window_width = gd.getDisplayMode().getWidth();
        this.window_height = gd.getDisplayMode().getHeight();
        this.setSize(this.window_width, this.window_height);
        this.setTitle("PeeKaBoo~");
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(3);
        this.setVisible(true);
        KeyListener kl = new KeyListener(this);
        this.addKeyListener(kl);
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
        // 畫人物
        // big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height, null);
        // g.drawImage(bi, 0, 0, null);
        test.TimeGame();
        big.setColor(new Color(255, 255, 255));
        int fontSize = 100;
        String s, h;

        s = "Time " + test.hh + ":" + test.mm + ":" + test.ss;
        Font font = new Font("宋体", Font.BOLD, fontSize);
        big.setFont(font);
        big.drawString(s, 500, 200);
        h = "health " + hp;
        big.setColor(new Color(0, 0, 255));
        Font font2 = new Font("宋体", Font.BOLD, 50);
        big.setFont(font2);
        big.drawString(h, 100, 200);
        big.drawImage(human.img, human.x, human.y, human.width, human.height, null);
        for (PkbGhost ghost : ghosts) {
            big.drawImage(ghost.img, ghost.x, ghost.y, ghost.width, ghost.height, null);
        }
        // Ghost
        g.drawImage(bi, 0, 0, null);
    }
}
