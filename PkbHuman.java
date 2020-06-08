package com.huaxin.mario;

import java.util.ArrayList;
import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import com.huaxin.enery.*;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.awt.Toolkit;

public class PkbHuman extends Thread {
    // ArrayList<Integer> arrlist = new ArrayList<Integer<(8);

    public GameFrame gameFrame;//遊戲地圖
    public int x = 0, y = 360;// 角色的坐標(一開始在左下角)
    public int xspeed = 8, yspeed = 8;// 角色的坐標(一開始在左下角)
    public static final int width = 30, height = 30;// 角色的寬高
    public Image img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();// 角色圖片

    public boolean up = false, down = false,left = false, right = false ;
    private static final String Str_Up = "Up",Str_Down ="Down", Str_Left = "Left", Str_Right = "Right";


    Timer timer = new Timer();
    private static boolean run = true;
    ArrayList<Integer> bagList = new ArrayList<Integer>(0);
    ArrayList<Enery> bageneryList = new ArrayList<Enery>(0);
    ArrayList<Integer> bagList2 = new ArrayList<Integer>(0);
    ArrayList<Point> pp = new ArrayList<>();

    public boolean pick = false, use = false, div = false;
    public int num=0;
    public PkbHuman(GameFrame g) {
        this.gameFrame = g;
    }

    public int getX(){ return this.x; }
    public int getY(){ return this.y; }

    public void Time(int t) {    
        TimerTask test = new TimerTask() {
            @Override
            public void run() {
                System.out.println("10秒到了");
                xspeed=8;
                yspeed=8;
                Toolkit.getDefaultToolkit().beep();
                //timer.cancel();
                timer.purge();
            }
        };

        timer.schedule(test, t);
        run = false;
    }
                        
    public void run() {
        this.bagList.add(0);
        //this.bagList2.add(0);
        //pp.add(event.getPoint());//如果是滑鼠event，他的(x,y)
        //pp.x=super.x; //int x座標
        //pp.y; //int y座標
        //System.out.println("point:" +  pp.x);
        while (true) {           
            int j = bump(gameFrame.toolList, "Left");//觸碰到
            if (j != 0) {//道具類
                int temp = gameFrame.toolList2.get(j);
                Enery enery = gameFrame.toolList.get(j);
                this.bagList2.add(temp);// 放入
                if (bagList2.get(bagList2.size() - 1) == 0) {
                    //System.out.println("222222222 ");
                }
                if (bagList2.get(bagList2.size() - 1) == 2) {

                    xspeed = 20;
                    yspeed = 20;
                    enery.img=new ImageIcon("img/back.png").getImage();
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    Time(10000);

                } else if (bagList2.get(bagList2.size() - 1) == 3) {
                    xspeed = 2;
                    yspeed = 2;
                    enery.img=new ImageIcon("img/back.png").getImage();
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    Time(10000);

                    
                }
                else if (bagList2.get(bagList2.size() - 1) == 4) {//門
                    enery.img=new ImageIcon("img/back.png").getImage();

                }
                else if (bagList2.get(bagList2.size() - 1) == 5) {//迷失
                    enery.img=new ImageIcon("img/back.png").getImage();
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    
                }
            }
            
            if (pick) {// 撿起來(石頭類)
                int i = bump(gameFrame.rockList, "Left");
                
                if (i != 0) {// 若有
                    Enery enery = gameFrame.rockList.get(i);
                    int tooltemp = gameFrame.rockList2.get(i);
                    this.bagList.add(tooltemp);// 放入
                    this.bageneryList.add(enery);// 放入bageneryList
                    //enery.x = 360 - this.x;
                    //enery.y = 360 - this.y;
                    enery.img=new ImageIcon("img/dig.png").getImage();
                    num++;
                    for (int A : bagList) {
                        System.out.println("Number = " + A+" "+num);
                    }
                }
            }
            if (use) {// 用起來石頭區
                    if (num>0)
                    {
                        Enery enery=this.bageneryList.get(bageneryList.size() - 1);
                        enery.x=this.x;
                        enery.y=this.y+30;
                        num=num-1;
                        System.out.println("use num= "+num);
                    }
                    else
                    {
                        System.out.println("沒石頭了 ");
                    }
                } 
                
            move();
                 
            try {
                this.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void move() {
        // System.out.printf("x: %d, y: %d%n", this.x, this.y);
        //while (true) {
            if(up){
                System.out.printf("tyring right at %d, %d%n", this.x, this.y);
                // if(bump(gameFrame.eneryList,Str_Up)!=0 && bump(gameFrame.toolList,Str_Up)==0  && bump(gameFrame.rockList,Str_Up)==0){//碰觸到道具，道具不影響速度變0 this.yspeed = 0; }
                if (this.y >= 0 && this.y < 300) {
                    this.y -= this.yspeed;
                    this.img = new ImageIcon("img/humanUp.jpg").getImage();
                }
                else if (this.y > 300) {
                    gameFrame.bg.y += this.yspeed;// 背景向下移動
                    // 障礙物項左移動
                    for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                        Enery enery = gameFrame.eneryList.get(i);
                        enery.y += this.yspeed;
                    }
                    PkbGhost ghost = gameFrame.ghost;
                    ghost.y += this.yspeed;
                    this.img = new ImageIcon("img/humanUp.jpg").getImage();
                }
            }
            if(down){
                // if(bump(gameFrame.eneryList,Str_Down)!=0 && bump(gameFrame.toolList,Str_Down)==0&& bump(gameFrame.rockList,Str_Down)==0){ this.yspeed = 0; }
                if (this.y < 300) {
                    this.y += this.yspeed;
                    this.img = new ImageIcon("img/humanDown.jpg").getImage();
                }
                else if (this.y >= 300) {
                    gameFrame.bg.y -= this.yspeed;// 背景向上移動
                    // 障礙物項左移動
                    for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                        Enery enery = gameFrame.eneryList.get(i);
                        enery.y -= this.yspeed;
                    }
                    PkbGhost ghost = gameFrame.ghost;
                    ghost.y -= this.yspeed;
                    this.img = new ImageIcon("img/humanDown.jpg").getImage();
                }
                //this.yspeed = 5;
            }
            if (left) {// 向左走
                // if (bump(gameFrame.eneryList,Str_Left)!=0 && bump(gameFrame.toolList,Str_Left)==0&& bump(gameFrame.rockList,Str_Left)==0) {//若撞到障礙物 this.xspeed = 0; }
                if (this.x > 30) {
                    this.x -= this.xspeed;
                    this.img = new ImageIcon("img/humanLeft.jpg").getImage();
                }
                else if (this.x < 650) {
                    gameFrame.bg.x += this.xspeed;// 背景向右移動
                    // 障礙物項右移動
                    for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                        Enery enery = gameFrame.eneryList.get(i);
                        enery.x += this.xspeed;
                    }
                    PkbGhost ghost = gameFrame.ghost;
                    ghost.x += this.xspeed;
                    this.img = new ImageIcon("img/humanLeft.jpg").getImage();
                }
                //this.xspeed = 5;
            }
            if (right) {// 向右走
                // if (bump(gameFrame.eneryList,Str_Right)!=0 && bump(gameFrame.toolList,Str_Right)==0&& bump(gameFrame.rockList,Str_Right)==0) {//若撞到障礙物 this.xspeed = 0; }
                if (this.x < 650) { // 任人物向右移動
                    this.x += this.xspeed;
                    this.img = new ImageIcon("img/humanRight.png").getImage();
                }
                else if (this.x > 650) {
                    gameFrame.bg.x -= this.xspeed;// 背景向左移動
                    // 障礙物項左移動
                    for (int i = 0; i < gameFrame.eneryList.size(); i++) {
                        Enery enery = gameFrame.eneryList.get(i);
                        enery.x -= this.xspeed;
                    }
                    PkbGhost ghost = gameFrame.ghost;
                    ghost.x -= this.xspeed;
                    this.img = new ImageIcon("img/humanRight.png").getImage();
                }
                //this.xspeed = 5;
            }
            try {
                this.sleep(20); 
            } catch (InterruptedException e) {
                e.printStackTrace();  
            }
       // }
    }

    public int bump(ArrayList<Enery>somethings,String dir) {

        Rectangle myrect = new Rectangle((this.x - (width / 2)), (this.y - (height / 2)), width, height);//(左上角,)
        Rectangle rect = null;

        for (int i = 0; i < somethings.size(); i++) {
            Enery enery = somethings.get(i);//障礙物
            // if (dir.equals("Left")) {
            rect = new Rectangle(enery.x - (width / 2), enery.y - (height / 2), enery.width, enery.height);
            // }
            // else if (dir.equals("Right")) {
            //     rect = new Rectangle(enery.x - (width / 2), enery.y - (height / 2), enery.width, enery.height);
            // }
            // else if (dir.equals("Up")) {
            //     rect = new Rectangle(enery.x - (width / 2), enery.y - (height / 2), enery.width, enery.height);
            // } 
            // else if (dir.equals("Down")) {
            //     rect = new Rectangle(enery.x - (width / 2), enery.y - (height / 2), enery.width, enery.height);
            // }
            if (myrect.intersects(rect)) {// 碰撞檢測
                return i;
            }
        }
        return 0;
    }

}