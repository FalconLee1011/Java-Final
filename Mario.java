package com.huaxin.mario;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;
import java.util.ArrayList;
import com.huaxin.enery.*;
import java.awt.Point;
//自己的角色類
public abstract class Mario extends Thread {

    public GameFrame gameFrame;//遊戲地圖
    public int x = 0, y = 360;// 角色的坐標(一開始在左下角)
    public int xspeed = 8, yspeed = 8;// 角色的坐標(一開始在左下角)
    public static final int width = 30, height = 30;// 角色的寬高
    public Image img = new ImageIcon("img/human_downMove_gif_160.gif").getImage();// 角色圖片

    public boolean up = false, down = false,left = false, right = false ;
    private static final String Str_Up = "Up",Str_Down ="Down", Str_Left = "Left", Str_Right = "Right";

    public Mario(GameFrame g) {
        this.gameFrame = g;
        // this.touchFloor();
    }

    public int getX(){ return this.x; }
    public int getY(){ return this.y; }
    
    public void run() {
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

    // public void jump() {// 向上跳的函數
    //     int jumpHeigh = 0;
    //     for (int i = 0; i < 150; i++) {
    //         gameFrame.mario.y -= this.yspeed;//跳
    //         jumpHeigh++;
    //         if (bump(Str_Up)) {//若撞到障礙物
    //             break;
    //         }
    //         try {
    //             Thread.sleep(5);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     for (int i = 0; i < jumpHeigh; i++) {
    //         gameFrame.mario.y += this.yspeed;
    //         if (bump(Str_Down)) {//若撞到障礙物
    //             this.yspeed = 0;
    //         }
    //         try {
    //             Thread.sleep(5);
    //         } catch (InterruptedException e) {
    //             e.printStackTrace();
    //         }
    //     }
    //     this.yspeed = 1;// 還原速度
    // }

    // 檢測碰撞
    public int bump(ArrayList<Enery>somethings,String dir) {

        Rectangle myrect = new Rectangle((this.x - (width / 2)), (this.y - (height / 2)), this.width, this.height);//(左上角,)
        Rectangle rect = null;

        for (int i = 0; i < somethings.size(); i++) {
            Enery enery = somethings.get(i);//障礙物
            if (dir.equals("Left")) {
                rect = new Rectangle(enery.x - (width), enery.y - (height), enery.width, enery.height);
            }
            else if (dir.equals("Right")) {
                rect = new Rectangle(enery.x - (width), enery.y - (height), enery.width, enery.height);
            }
            else if (dir.equals("Up")) {
                rect = new Rectangle(enery.x - (width), enery.y - (height), enery.width, enery.height);
            } else if (dir.equals("Down")) {
                rect = new Rectangle(enery.x - (width), enery.y - (height), enery.width, enery.height);
            }
            if (myrect.intersects(rect)) {// 碰撞檢測
                return i;
            }
        }
        return 0;
    }

    // 檢查是否貼地
    // public boolean istouchFloor = false;
    // public void touchFloor() {
    //     new Thread() {
    //         public void run() {
    //             while (true) {
    //                 try {
    //                     sleep(10);
    //                 } catch (InterruptedException e) {
    //                     e.printStackTrace();
    //                 }
    //                 if (!jumpFlag) {//可刪?
    //                 }
    //                 while (true) {
    //                     if (!jumpFlag) {
    //                         break;
    //                     }
    //                     if (bump(Str_Down)) {//若撞到障礙物
    //                         break;
    //                     }
    //                     if (y >= 360) {//y >=人物高度
    //                         istouchFloor = false;
    //                     } else {
    //                         istouchFloor = true;
    //                         y += yspeed;
    //                     }
    //                     try {
    //                         sleep(10);
    //                     } catch (InterruptedException e) {
    //                         e.printStackTrace();
    //                     }
    //                 }
    //             }
    //         }
    //     }.start();
    // }
}