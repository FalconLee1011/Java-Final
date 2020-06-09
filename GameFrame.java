// 遊戲起始
package peekaboo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
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


public class GameFrame extends JFrame {

    public BackgroundImage bg= new BackgroundImage(); // 背景圖片
    public int[][] map = (new initMap()).readMap();// 畫地圖，制定規則，是1畫磚頭，是2畫skates，是3畫水管
    public PkbHuman human = new PkbHuman(this);// player
    public PkbTimer test=new PkbTimer(this);
    public Map <String, Map<String, Enery>> mapEneryByPos = new HashMap<String, Map<String, Enery>>();
    public Map <String, Map<String, Enery>> backEneryByPos = new HashMap<String, Map<String, Enery>>();
    public Map <String, Map<String, Enery>> rockEneryByPos = new HashMap<String, Map<String, Enery>>();
    // 直接追隨
    // public PkbGhost ghost = new PkbGhost();
    // 距離追隨
    // public PkbGhost ghost = new PkbGhost(360, 360, 1, true, 300);
    // 距離追隨、會巡邏
    public PkbGhost ghost = new PkbGhost(360, 360, 1, true, 300, true, 100);

    public ArrayList<Enery> eneryList = new ArrayList<Enery>();// 裝道具+石頭
    public ArrayList<Enery> rockList = new ArrayList<Enery>();// 裝石頭
    public ArrayList<Integer> rockList2 = new ArrayList<Integer>();// 裝石頭的數字
    public ArrayList<Enery> toolList = new ArrayList<Enery>();//放道具
    public ArrayList<Integer> toolList2 = new ArrayList<Integer>();//放道具數字
    public ArrayList<Boom> boomList = new ArrayList<Boom>();// 子彈
    public int bspeed = 0;// 子彈速度

    public GameFrame() throws Exception {// 初始化bgImg和player

        human.start();
        // ghost.start();
        // 視窗重繪線程
        new Thread() {//。thread以外的一個子thread則是指程式人員自行在主thread裡再定義一個「程式區塊」，並請cpu同步的去執行那個區塊。
            public void run() {
                while (true) {
                    repaint();// 重繪視窗
                    // checkBoom();// 檢查子彈是否出界
                    if(ghost.pursue(human)){
                        System.out.println("GAME OVER");
                        // break;
                    }
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();//在命令行打印异常信息在程序中出错的位置及原因
                    }
                }
            }
        }.start();
        
        // Map mp = new Map();
        // map = mp.readMap();// 建置地圖

        for (int i = 0; i < map.length; i++) {// 讀取地圖，並配置地圖
            for (int j = 0; j < map[0].length; j++) {
                String x_key = String.valueOf(i * 30);
                String y_key = String.valueOf(j * 30);
                Map <String, Enery> map_row = new HashMap<String, Enery>();
                Map <String, Enery> rock_row = new HashMap<String, Enery>();
                Map <String, Enery> brick_row = new HashMap<String, Enery>();
                map_row = mapEneryByPos.get(x_key);
                rock_row = backEneryByPos.get(x_key);
                brick_row = rockEneryByPos.get(x_key);
                if(map_row == null) { map_row = new HashMap<String, Enery>(); }
                if(rock_row == null) { rock_row = new HashMap<String, Enery>(); }
                if(brick_row == null) { brick_row = new HashMap<String, Enery>(); }
                switch(map[i][j]) { 
                    case 0: // 畫地板                
                        Stone back = new Stone(j * 30, i * 30, 30, 30, new ImageIcon("img/Back.png").getImage());// (x軸，y軸，寬，高
                        eneryList.add(back);
                        toolList.add(back);
                        toolList2.add(0);
                        rockList.add(back);
                        rockList2.add(0);
                        rock_row.put(y_key, back);
                        break;
                    case 1: // 畫邊界
                        Barrier brick = new Barrier(j * 30, i * 30, 30, 30, new ImageIcon("img/cactus3.png").getImage());//(x軸，y軸，寬，高)
                        eneryList.add(brick);
                        brick_row.put(y_key, brick);
                        break;
                    case 2: // 畫skates
                        Shoe skates = new Shoe(j * 30, i * 30, 30, 30, new ImageIcon("img/camel_GIF.gif").getImage());
                        eneryList.add(skates);
                        toolList.add(skates);
                        toolList2.add(2);
                        map_row.put(y_key, skates);
                        break; 
                    case 3: // 畫烏龜
                        Turtle turtle = new Turtle(j * 30, i * 30, 30, 30, new ImageIcon("img/turtle.jpg").getImage());
                        eneryList.add(turtle);
                        toolList.add(turtle);  
                        toolList2.add(3);  
                        map_row.put(y_key, turtle);
                        break; 
                    case 4: 
                        Door door = new Door(j * 30, i * 30, 30, 30, new ImageIcon("img/door.jpg").getImage());
                        eneryList.add(door);
                        toolList.add(door);  
                        toolList2.add(4);   
                        map_row.put(y_key, door);
                        break; 
                    case 5: 
                        Bewitch bewitch = new Bewitch(j * 30, i * 30, 30, 30, new ImageIcon("img/bewitch.jpg").getImage());
                        eneryList.add(bewitch);
                        toolList.add(bewitch);
                        toolList2.add(5);
                        map_row.put(y_key, bewitch);
                        break; 
                    case 7: 
                        Hole dig = new Hole(j * 30, i * 30, 30, 30, new ImageIcon("img/dig.png").getImage());
                        eneryList.add(dig);
                        rockList.add(dig);
                        rockList2.add(7);
                        map_row.put(y_key, dig);
                        break; 
               }
               mapEneryByPos.put(x_key, map_row);
               backEneryByPos.put(x_key, rock_row);
               rockEneryByPos.put(x_key, brick_row);
            }
        }
        // 設置背景音樂
        //peekaboo.huaxin.music.Util.startMusic("/startmusic.wav");
    }

    public void initFrame() {
        // 設置視窗相關屬性
        this.setSize(800,450);
        this.setTitle("超級瑪麗");
        this.setResizable(false);//resizeable值为true时，表示生成的窗体可以自由改变大小；
        //resizeable值为false时，表示生成的窗体大小是由程序员决定的，用户不可以自由改变该窗体的大小。
        this.setLocationRelativeTo(null);//设置窗口相对于指定组件的位置 null/c代表中央
        this.setDefaultCloseOperation(3);//窗口關閉時的呈現(3)->使用 System exit 方法退出应用程序。仅在应用程序中使用。结束了应用程序。
        this.setVisible(true);//设置可看的見
        KeyListener kl = new KeyListener(this);// 視窗添加鍵盤監聽
        this.addKeyListener(kl);
    }

    public void paint(Graphics g) {

        // 利用雙緩衝畫背景圖片和馬里奧
        BufferedImage bi = (BufferedImage) this.createImage(this.getSize().width, this.getSize().height);
        Graphics big = bi.getGraphics();

        big.drawImage(bg.img, bg.x, bg.y, null);
        for (int i = 0; i < eneryList.size(); i++) {
            Enery e = eneryList.get(i);
            big.drawImage(e.img, e.x, e.y, e.width, e.height, null);//null不備擋住
        }
        for (int i = 0; i < toolList.size(); i++) {
            Enery e = toolList.get(i);
            big.drawImage(e.img, e.x, e.y, e.width, e.height, null);//null不備擋住
        }

        // 畫子彈
        for (int i = 0; i < boomList.size(); i++) {
            Boom b = boomList.get(i);
            Color c = big.getColor();
            big.setColor(Color.red);
            big.fillOval(b.x += b.speed, b.y, b.width, b.width);//填充椭圆的
            big.setColor(c);
        }
        // 畫人物
        // big.drawImage(mario.img, mario.x, mario.y, mario.width, mario.height, null);
        // g.drawImage(bi, 0, 0, null);
        test.TimeGame();
        big.setColor(Color.BLUE);
        int fontSize = 50;
        long kkk;
        String s;
        
        s = "Time: " + test.hh + ":" + test.mm + ":" + test.ss;
        Font font = new Font("楷体",Font.BOLD,fontSize);
        big.setFont(font);
        big.drawString(s,150,100);         
        big.drawImage(human.img, human.x, human.y, human.width, human.height, null);
        big.drawImage(ghost.img, ghost.x, ghost.y, ghost.width, ghost.height, null);
        // Ghost
        g.drawImage(bi, 0, 0, null);
    }

    // 檢查子彈是否出界，出界則從容器中移除，不移除的話，內存會泄漏
    public void checkBoom() {
        for (int i = 0; i < boomList.size(); i++) {
            Boom b = boomList.get(i);
            if (b.x < 0 || b.x > 800) { boomList.remove(i); }
        }
    }
}
